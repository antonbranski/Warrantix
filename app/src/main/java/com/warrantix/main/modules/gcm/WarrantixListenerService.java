package com.warrantix.main.modules.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;
import com.warrantix.main.R;
import com.warrantix.main.activities.MainActivity;
import com.warrantix.main.common.rest.model.B2CGCMMessageContent;
import com.warrantix.main.common.rest.response.PullMessageResponse;
import com.warrantix.main.modules.b2c.B2CManager;

import static com.warrantix.main.modules.b2c.b2cUtil.parseFromJson;

/**********************************************************
 *
 * Reference URL : https://github.com/codepath/android_guides/wiki/Google-Cloud-Messaging
 *
 **********************************************************/

public class WarrantixListenerService extends GcmListenerService {

    private static final String TAG = "WarrantixListenerService";

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    @Override
    public void onMessageReceived(String from, Bundle data) {

//        String message = data.getString("data");

       String message = "{\"command\" : \"b2c\", " +
               "\"type\" : \"video\",\"appServerUrl\" :\"hero.warrantix.com\"," +
               "\"importance\" : \"high\", \"imageURL\" : \"http://hero.com/images/splender_image.png\", " +
               "\"mediaURL\" : \"http://hero.com/video/splender_video.mp4\" } ";

        B2CGCMMessageContent b2CGCMMessageContent = parseFromJson(message);

        if ((b2CGCMMessageContent.getData().getCommand()).equals("b2c")){

        }
        else {

            sendNotification(message);
        }

//        B2CManager.getInstance().getMessages();

    }

    /**
     * Create and show a simple notification containing the received GCM message.
     *
     * @param message GCM message received.
     */
    private void sendNotification(String message) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.app_icon)
                .setContentTitle("GCM Message")
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}