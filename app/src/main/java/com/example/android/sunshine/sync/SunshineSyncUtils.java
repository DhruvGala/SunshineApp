package com.example.android.sunshine.sync;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Created by Dhruv on 3/30/2018.
 */

public class SunshineSyncUtils {
    /**
     * Helper method to perform a sync immediately using an IntentService for asynchronous
     * execution
     *
     * @param context
     */
    public static void startImmediateSync(@NonNull final Context context){

        Intent intentToSyncImmediately = new Intent(context, SunshineSyncIntentServices.class);
        context.startService(intentToSyncImmediately);
    }
}
