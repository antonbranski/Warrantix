package com.warrantix.main.common;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;

import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.UrlConnectionDownloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Pattern;

import static android.content.Context.ACTIVITY_SERVICE;
import static android.content.pm.ApplicationInfo.FLAG_LARGE_HEAP;
import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.HONEYCOMB_MR1;
import static android.os.Process.THREAD_PRIORITY_BACKGROUND;
import static android.provider.Settings.System.AIRPLANE_MODE_ON;

public final class Utils {
    private static String TAG = Utils.class.getSimpleName();

  static final String THREAD_PREFIX = "Picasso-";
  static final String THREAD_IDLE_NAME = THREAD_PREFIX + "Idle";
  static final int DEFAULT_READ_TIMEOUT = 20 * 1000; // 20s
  static final int DEFAULT_CONNECT_TIMEOUT = 15 * 1000; // 15s
  private static final String PICASSO_CACHE = "picasso-cache";
  private static final int KEY_PADDING = 50; // Determined by exact science.
  private static final int MIN_DISK_CACHE_SIZE = 5 * 1024 * 1024; // 5MB
  private static final int MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
  private static final int MAX_MEM_CACHE_SIZE = 30 * 1024 * 1024; // 30MB

  private Utils() {
    // No instances.
  }

  static int getBitmapBytes(Bitmap bitmap) {
    int result;
    if (SDK_INT >= HONEYCOMB_MR1) {
      result = BitmapHoneycombMR1.getByteCount(bitmap);
    } else {
      result = bitmap.getRowBytes() * bitmap.getHeight();
    }
    if (result < 0) {
      throw new IllegalStateException("Negative size: " + bitmap);
    }
    return result;
  }

  static void checkNotMain() {
    if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
      throw new IllegalStateException("Method call should not happen from the main thread.");
    }
  }

  public static void closeQuietly(InputStream is) {
    if (is == null) return;
    try {
      is.close();
    } catch (IOException ignored) {
    }
  }

  /** Returns {@code true} if header indicates the response body was loaded from the disk cache. */
  static boolean parseResponseSourceHeader(String header) {
    if (header == null) {
      return false;
    }
    String[] parts = header.split(" ", 2);
    if ("CACHE".equals(parts[0])) {
      return true;
    }
    if (parts.length == 1) {
      return false;
    }
    try {
      return "CONDITIONAL_CACHE".equals(parts[0]) && Integer.parseInt(parts[1]) == 304;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  static Downloader createDefaultDownloader(Context context) {
    try {
      Class.forName("com.squareup.okhttp.OkHttpClient");
      return OkHttpLoaderCreator.create(context);
    } catch (ClassNotFoundException e) {
      return new UrlConnectionDownloader(context);
    }
  }

  static File createDefaultCacheDir(Context context) {
    File cache = new File(context.getApplicationContext().getCacheDir(), PICASSO_CACHE);
    if (!cache.exists()) {
      cache.mkdirs();
    } else {

	}
    return cache;
  }

  static long calculateDiskCacheSize(File dir) {
    long size = MIN_DISK_CACHE_SIZE;

    try {
      StatFs statFs = new StatFs(dir.getAbsolutePath());
      long available = ((long) statFs.getBlockCount()) * statFs.getBlockSize();
      // Target 15% of the total space.
      size = available / 7;
    } catch (IllegalArgumentException ignored) {
    }

    // Bound inside min/max size for disk cache.
	  long cacheSize = Math.max(Math.min(size, MAX_DISK_CACHE_SIZE), MIN_DISK_CACHE_SIZE);
	  Log.d("Picasso", "Disk cache size = " + cacheSize + " bytes");
	  return cacheSize;
  }

  public static int calculateMemoryCacheSize(Context context) {
    ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
    boolean largeHeap = (context.getApplicationInfo().flags & FLAG_LARGE_HEAP) != 0;
    int memoryClass = am.getMemoryClass();
    if (largeHeap && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
      memoryClass = ActivityManagerHoneycomb.getLargeMemoryClass(am);
    }
    // Target 15% of the available RAM.
    int size = 1024 * 1024 * memoryClass / 7;
    // Bound to max size for mem cache.
	  int cacheSize = Math.min(size, MAX_MEM_CACHE_SIZE);
	  Log.d("Picasso", "Memory cache size = " + cacheSize + " bytes");
	  return cacheSize;
  }

  static boolean isAirplaneModeOn(Context context) {
    ContentResolver contentResolver = context.getContentResolver();
    return Settings.System.getInt(contentResolver, AIRPLANE_MODE_ON, 0) != 0;
  }

  static boolean hasPermission(Context context, String permission) {
    return context.checkCallingOrSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB)
  private static class ActivityManagerHoneycomb {
    static int getLargeMemoryClass(ActivityManager activityManager) {
      return activityManager.getLargeMemoryClass();
    }
  }

  public static class PicassoThreadFactory implements ThreadFactory {
    @SuppressWarnings("NullableProblems")
    public Thread newThread(Runnable r) {
      return new PicassoThread(r);
    }
  }

  private static class PicassoThread extends Thread {
    public PicassoThread(Runnable r) {
      super(r);
    }

    @Override public void run() {
      Process.setThreadPriority(THREAD_PRIORITY_BACKGROUND);
      super.run();
    }
  }

  public static void clearAnimationDrawable(AnimationDrawable anim) {
    if (anim != null) {
      anim.stop();
      for (int i = 0; i < anim.getNumberOfFrames(); ++i) {
        Drawable frame = anim.getFrame(i);
        if (frame instanceof BitmapDrawable) {
          Bitmap mBitmap = ((BitmapDrawable) frame).getBitmap();
          if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap.recycle();
          }
        }
        frame.setCallback(null);
      }
      anim.setCallback(null);
    }
  }

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
  private static class BitmapHoneycombMR1 {
    static int getByteCount(Bitmap bitmap) {
      return bitmap.getByteCount();
    }
  }

  private static class OkHttpLoaderCreator {
    static Downloader create(Context context) {
      return new OkHttpDownloader(context);
    }
  }

    // DEPRECATED : Not work from 5.0 to NOW
  public static String getRegisteredEmail(Context context) {
    return "";

//    String email = null;
//
//    Pattern gmailPattern = Patterns.EMAIL_ADDRESS;
//    Account[] accounts = AccountManager.get(context).getAccounts();
//    for (Account account : accounts) {
//      if (gmailPattern.matcher(account.name).matches()) {
//        email = account.name;
//      }
//    }
//    return email;
  }

    // DEPRECATED : Not work from 5.0 to NOW
    public static String getPhoneNumber(Context context) {
      return "";

//        TelephonyManager tMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//
//        String MyPhoneNumber = "0000000000";
//
//        try  {
//            MyPhoneNumber = tMgr.getLine1Number();
//            String dId = tMgr.getSimSerialNumber();
//            Log.d(TAG, "DID = " + dId);
//        }
//        catch(NullPointerException ex) {
//        }
//
//        if((MyPhoneNumber == null) || (MyPhoneNumber.equals(""))){
//            MyPhoneNumber = tMgr.getSubscriberId();
//        }
//
//        if (MyPhoneNumber == null)
//            return "";
//
//        return MyPhoneNumber;
    }
}
