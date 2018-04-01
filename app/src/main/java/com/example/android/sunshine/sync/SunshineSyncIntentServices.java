package com.example.android.sunshine.sync;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by Dhruv on 3/30/2018.
 */

public class SunshineSyncIntentServices extends IntentService {
    public SunshineSyncIntentServices() {
        super("SunshineSyncIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SunshineSyncTask.syncWeather(this);
    }
}
