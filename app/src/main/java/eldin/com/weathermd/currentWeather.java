package eldin.com.weathermd;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.DailyForecast;
import net.aksingh.owmjapis.OpenWeatherMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eldin on 5-7-2015.
 */
public class currentWeather extends AsyncTask<Void, Void, Void> {

    static String location;
    float celsius;
    static String description;
    View a;
    String url = "http://www.worldweatheronline.com/api/docs/local-city-town-weather-api.aspx";

    public currentWeather(View v){
        a = v;
    }

    @Override
    protected Void doInBackground(Void... params) {

        return null;
    }

    @Override
    protected void onPostExecute(Void unused){
        super.onPostExecute(unused);
        TextView tv = (TextView) a.findViewById(R.id.descriptionCurr);
        tv.setText(description);
        TextView currentTemp = (TextView) a.findViewById(R.id.currentTemp);
        int currentTempInput = Math.round(celsius);
        String tempOutput = currentTempInput + "\u00b0";
        currentTemp.setText(tempOutput);

    }
}
