package eldin.com.weathermd;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.aksingh.owmjapis.CurrentWeather;

import org.w3c.dom.Text;

/**
 * Created by Eldin on 6-7-2015.
 */
public class Tab1 extends android.support.v4.app.Fragment{
    Activity a;
    static Context x;
    static String currentDesc;
    View v;
    Animation animSlide;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(x);
        String restoredText = prefs.getString("location", null);
        animSlide = AnimationUtils.loadAnimation(x, R.anim.slide);
        new TestAsync().execute();
        if (restoredText != null){
            v =inflater.inflate(R.layout.tab_1,container,false);
            TextView tv = (TextView) v.findViewById(R.id.test);
            String output = restoredText.substring(0, 1).toUpperCase() + restoredText.substring(1);
            tv.setText(output);
            LinearLayout l = (LinearLayout) v.findViewById(R.id.testSlide);
            l.startAnimation(animSlide);

            currentWeather c = new currentWeather(v);
            c.location = restoredText;
            //c.execute();
        } else {
            v =inflater.inflate(R.layout.no_restored,container,false);
            Log.d("else", "oops");
        }
        Log.d("made textview", "done");

        return v;
    }

    private void getweather(){

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

