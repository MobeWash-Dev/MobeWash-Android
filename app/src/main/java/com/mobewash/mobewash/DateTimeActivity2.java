package com.mobewash.mobewash;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DateTimeActivity2 extends AppCompatActivity implements DateTimeFragment.OnDateTimeFragmentInteractionListener {

    private DateTimeFragment mDateTimeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_date_time2);


        final CaldroidFragment caldroidFragment = new CaldroidFragment();
        final Bundle args = new Bundle();
        args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
        caldroidFragment.setArguments(args);

        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        DataSingletonClass singleton = DataSingletonClass.getInstance();
        int[] enabledDays = singleton.getCompanyData().getSetting().getWashDays();
        //int[] enabledDays = {2,3};
        ArrayList<String> disabledDates = addDisabledDates(enabledDays);
        caldroidFragment.setDisableDatesFromString(disabledDates);
        caldroidFragment.setMinDate(Calendar.getInstance().getTime());

        ArrayList<Date> disabledDates2 = addDisabledDates2(enabledDays);
        ColorDrawable white = new ColorDrawable(Color.WHITE);
        for (Date date : disabledDates2)
          caldroidFragment.setBackgroundDrawableForDate(white,date);

        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(getApplicationContext(), date.toString().substring(0,10),
                        Toast.LENGTH_SHORT).show();
                caldroidFragment.clearSelectedDates();
                caldroidFragment.moveToDate(date);
                caldroidFragment.setCalendarDate(date);
                caldroidFragment.setSelectedDate(date);
                caldroidFragment.refreshView();

                //Intent detailsIntent = new Intent(DateTimeActivity2.this, DetailsActivity.class);
                //startActivity(detailsIntent);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                Log.d("HERE", calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
                mDateTimeFragment.populateTimes("2017-5-25");
                getSupportFragmentManager().beginTransaction().show(mDateTimeFragment).commit();
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.calendar1);
                linearLayout.setVisibility(View.GONE);
            }

            @Override
            public void onChangeMonth(int month, int year) {
            }

            @Override
            public void onLongClickDate(Date date, View view) {
            }

            @Override
            public void onCaldroidViewCreated() {
            }

        };

        caldroidFragment.setCaldroidListener(listener);

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();


        mDateTimeFragment = new DateTimeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout_time_container, mDateTimeFragment).commit();

        getSupportFragmentManager().beginTransaction().hide(mDateTimeFragment).commit();
    }


    private ArrayList<String> addDisabledDates(int[] enabledDays) {

        //our format of JSON file has 0-6 for Monday to Sunday which isn't desirable
        ArrayList<String> disabledDates = new ArrayList<String>();
        boolean foundFirstDate = false;
        Calendar firstDate = Calendar.getInstance();
        boolean[] formattedEnabledDays = new boolean[7];
        for(int i = 0; i < enabledDays.length ; i++) {
            if(enabledDays[i] == 6)
                formattedEnabledDays[0] = true;
            else
                formattedEnabledDays[enabledDays[i]+1] = true;
        }//set formattedEnabledDays to be true
        for(int i = 0; i < 7; i++) {
            Log.d("myTag","" + i + " " + formattedEnabledDays[i]);
        }
        for(int i = 0; i < 7; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE,i);
            if(!formattedEnabledDays[cal.get(Calendar.DAY_OF_WEEK)-1]) {
                if(!foundFirstDate) {
                    foundFirstDate = true;
                    firstDate = cal;
                }
                Calendar calToAdd = Calendar.getInstance();
                calToAdd.add(Calendar.DATE,i-7);
                for (int j = 0; j < 100; j++) {
                    calToAdd.add(Calendar.DATE,7);
                    String stringToAdd = "" + calToAdd.get(Calendar.YEAR) + "-" +
                            (calToAdd.get(Calendar.MONTH) + 1) + "-" +
                            calToAdd.get(Calendar.DATE);
                    disabledDates.add(stringToAdd);
                }
            }
        }
        return disabledDates;
    }

    private ArrayList<Date> addDisabledDates2(int[] enabledDays) {
        //our format of JSON file has 0-6 for Monday to Sunday which isn't desirable
        ArrayList<Date> disabledDates = new ArrayList<Date>();
        boolean foundFirstDate = false;
        Calendar firstDate = Calendar.getInstance();
        boolean[] formattedEnabledDays = new boolean[7];
        for(int i = 0; i < enabledDays.length ; i++) {
            if(enabledDays[i] == 6)
                formattedEnabledDays[0] = true;
            else
                formattedEnabledDays[enabledDays[i]+1] = true;
        }//set formattedEnabledDays to be true
        for(int i = 0; i < 7; i++) {
            Log.d("myTag","" + i + " " + formattedEnabledDays[i]);
        }
        for(int i = 0; i < 7; i++) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE,i);
            if(!formattedEnabledDays[cal.get(Calendar.DAY_OF_WEEK)-1]) {
                if(!foundFirstDate) {
                    foundFirstDate = true;
                    firstDate = cal;
                }
                Calendar calToAdd = Calendar.getInstance();
                calToAdd.add(Calendar.DATE,i-7);
                for (int j = 0; j < 100; j++) {
                    calToAdd.add(Calendar.DATE,7);
                    Date dateToAdd = calToAdd.getTime();
                    disabledDates.add(dateToAdd);
                }
            }
        }
        return disabledDates;
    }


    @Override
    public void onDateTimeSelected() {
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onEditCalendar() {
        getSupportFragmentManager().beginTransaction().hide(mDateTimeFragment).commit();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.calendar1);
        linearLayout.setVisibility(View.VISIBLE);
    }
}
