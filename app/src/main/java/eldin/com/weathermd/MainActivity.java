package eldin.com.weathermd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import net.aksingh.owmjapis.OpenWeatherMap;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    String location;
    String country;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Overview","Daily forecast"};
    int Numboftabs =2;

    Animation animSlide;

    public void getLoc(View w){
        EditText etLoc = (EditText) findViewById(R.id.loc);
        location = etLoc.getText().toString();
        saveLoc(location);
    }

    private void saveLoc(String loc){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("location", loc);
        editor.apply();
        Tab1 tab1 = new Tab1();
        tab1.x = this;
        //getCurrentWeather();
        changeLayout();
    }

    public void changeLayout(){
        setContentView(R.layout.current_weather);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }

    private void getCurrentWeather(){
        //currentWeather c = new currentWeather();
        //c.location = location;
        //new currentWeather().execute();
    }

    public void wisPref(View w){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);
    }

    @Override
    public void onResume(){
        changeLayout();
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        /**SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.clear();
        editor.apply();*/
        Tab1 tab1 = new Tab1();
        tab1.x = this;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String restoredText = prefs.getString("location", null);
        if (restoredText != null){
            Tab1 tab = new Tab1();
            tab.x = this;
            setContentView(R.layout.current_weather);
            toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
            setSupportActionBar(toolbar);

            adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);

            // Assigning ViewPager View and setting the adapter
            pager = (ViewPager) findViewById(R.id.pager);
            pager.setAdapter(adapter);

            // Assiging the Sliding Tab Layout View
            tabs = (SlidingTabLayout) findViewById(R.id.tabs);
            tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

            // Setting Custom Color for the Scroll bar indicator of the Tab View
            tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
                @Override
                public int getIndicatorColor(int position) {
                    return getResources().getColor(R.color.tabsScrollColor);
                }
            });

            // Setting the ViewPager For the SlidingTabsLayout
            tabs.setViewPager(pager);
            Log.d("check", restoredText);
        } else {
            setContentView(R.layout.activity_main);
            toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
            setSupportActionBar(toolbar);
            Log.d("check", "no message restored");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

