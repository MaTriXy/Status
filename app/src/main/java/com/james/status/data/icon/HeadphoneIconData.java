package com.james.status.data.icon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Build;

import com.james.status.R;
import com.james.status.data.IconStyleData;

import java.util.Arrays;
import java.util.List;

public class HeadphoneIconData extends IconData<HeadphoneIconData.HeadphoneReceiver> {

    public HeadphoneIconData(Context context) {
        super(context);
    }

    @Override
    public HeadphoneReceiver getReceiver() {
        return new HeadphoneReceiver();
    }

    @Override
    public IntentFilter getIntentFilter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return new IntentFilter(AudioManager.ACTION_HEADSET_PLUG);
        else return new IntentFilter(Intent.ACTION_HEADSET_PLUG);
    }

    @Override
    public String getTitle() {
        return getContext().getString(R.string.icon_headphone);
    }

    @Override
    public int getIconStyleSize() {
        return 2;
    }

    @Override
    public List<IconStyleData> getIconStyles() {
        List<IconStyleData> styles = super.getIconStyles();

        styles.addAll(
                Arrays.asList(
                        new IconStyleData(
                                getContext().getString(R.string.icon_style_default),
                                IconStyleData.TYPE_VECTOR,
                                R.drawable.ic_headset,
                                R.drawable.ic_headset_mic
                        )
                )
        );

        return styles;
    }

    public class HeadphoneReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra("state", 0) == 1)
                onDrawableUpdate(intent.getIntExtra("microphone", 0));
            else onDrawableUpdate(-1);
        }
    }
}
