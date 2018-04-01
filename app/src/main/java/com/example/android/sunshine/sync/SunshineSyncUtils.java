package com.example.android.sunshine.sync;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.android.sunshine.data.WeatherContract;

/**
 * Created by Dhruv on 3/30/2018.
 */

public class SunshineSyncUtils {


    private static boolean sInitialized;

    /**
     * Creates periodic sync tasks and checks to see if an immediate sync is required. If an
     * immediate sync is required, this method will take care of making sure that sync occurs.
     *
     * @param context
     */
    synchronized public static void initialize(@NonNull final Context context){

        /*
         * occurs only once in the lifetime of the app.
         */
        if(sInitialized)
           return;;

        //set sInitialized variable
        sInitialized = true;

        /*
         * check if any data is available in the ContentResolver
         *
         */
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                // URI for weather data
                Uri forecastQueryUri = WeatherContract.WeatherEntry.CONTENT_URI;

                String[] projectionColumns = {WeatherContract.WeatherEntry._ID};
                String selectionStatement = WeatherContract.WeatherEntry.getSqlSelectForTodayOnwards();

                Cursor cursor = context.getContentResolver().query(
                        forecastQueryUri,
                        projectionColumns,
                        selectionStatement,
                        null,
                        null
                );

                if(null == cursor || cursor.getCount() == 0){
                    startImmediateSync(context);
                }

                cursor.close();
                return null;
            }
        }.execute();
    }
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
