/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mWeatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);

        String []weatherData = {
                "Today, Feb 26 - Clear - 10°C / 13°C",
                "Tomorrow, Feb 27 - Rainy - 5°C / 8°C",
                "Tuesday, Feb 28 - Snow - -1°C / 0°C",
                "Wednesday, Mar 1 - Sunny - 10°C / 13°C",
                "Thursday, Mar 2 - Sunny/Clear - 15°C / 17°C",
                "Friday, Mar 3 - Clear - 10°C / 13°C",
                "Saturday, Mar 4 - Clear - 10°C / 13°C",
        };

        for( String weatherDay : weatherData){
            mWeatherTextView.append(weatherDay + "\n\n\n");
        }
    }
}