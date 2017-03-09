package com.warrantix.main.customview.framevideoview;

import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;

public class InfoListener implements MediaPlayer.OnInfoListener {
    private static final String TAG = InfoListener.class.getSimpleName();

    private View placeholderView;
//    private static final Logger LOG = LoggerFactory.getLogger(InfoListener.class.getSimpleName());

    public InfoListener(View placeholderView) {
        this.placeholderView = placeholderView;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
//        LOG.trace("onInfo what={}, extra={}", what, extra);
        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
//            LOG.trace("[MEDIA_INFO_VIDEO_RENDERING_START] placeholder GONE");
            placeholderView.setVisibility(View.GONE);
            return true;
        }
        return false;
    }
}
