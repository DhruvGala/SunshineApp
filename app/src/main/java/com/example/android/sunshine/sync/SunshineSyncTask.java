package com.example.android.sunshine.sync;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.text.format.DateUtils;

import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.data.WeatherContract;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Dhruv on 3/30/2018.
 */

public class SunshineSyncTask {

    synchronized public static void syncWeather(Context context){
        try{
            URL weatherRequestUrl = NetworkUtils.getUrl(context);

            //Using the URL to get JSON output
            String jsonWeatherResponse = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);

            //Parsing JSON into list of weather values
            ContentValues[] weatherValues = OpenWeatherJsonUtils.getWeatherContentValuesFromJson(context, jsonWeatherResponse);

            /* In case where JSON contained an error code, getWeatherContentValuesFromJson
             * would return null. Setting up mechanism to check these cases here to prevent any
             * Null Pointer Exception being thrown.
             */
            if (weatherValues != null && weatherValues.length != 0){
                //Get content resolver to delete any data if necessary
                ContentResolver sunshineContextResolver = context.getContentResolver();

                //Deleting old weather data as we don't need multiple days' data
                sunshineContextResolver.delete(
                        WeatherContract.WeatherEntry.CONTENT_URI,
                        null,
                        null
                );

                //insert the new weather data from the JSOn parsed
                sunshineContextResolver.bulkInsert(
                        WeatherContract.WeatherEntry.CONTENT_URI,
                        weatherValues
                );

                //get if notifications is enabled by user
                boolean notificationsEnabled = SunshinePreferences.areNotificationsEnabled(context);

                long timeSinceLastNotification = SunshinePreferences
                        .getEllapsedTimeSinceLastNotification(context);

                boolean oneDayPassedSinceLastNotification = false;

                if(timeSinceLastNotification >= DateUtils.DAY_IN_MILLIS) {
                    oneDayPassedSinceLastNotification = true;
                }

                if (notificationsEnabled && oneDayPassedSinceLastNotification) {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
