package com.academiccalendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.academiccalendar.trisem.TriSemAct;
import com.academiccalendar.utils.C;
import com.academiccalendar.weekview.BaseActivity;
import com.academiccalendar.weekview.BasicActivity;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;




public class MonthView extends AppCompatActivity {
    private boolean undo = true;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

    private void setCustomResourceForDates() {



        //        Adding colors to dates ! ! !

        Calendar cal = Calendar.getInstance(Locale.getDefault());

        HashMap<Date, Integer> g = new HashMap<Date, Integer>();

//        February

        for(int i = 1; i< 6; i++) {
            cal.set(2016, Calendar.FEBRUARY, i);
            g.put(cal.getTime(), R.color.lecture_period);
        }
            for(int i = 6; i< 8; i++) {
                cal.set(2016, Calendar.FEBRUARY, i);
                g.put(cal.getTime(), R.color.weekends);
            }
        for(int i = 8; i< 13; i++) {
            cal.set(2016, Calendar.FEBRUARY, i);
            g.put(cal.getTime(), R.color.study_break);
        }
        for(int i = 15; i< 27; i++) {
            cal.set(2016, Calendar.FEBRUARY, i);
            g.put(cal.getTime(), R.color.exam_period);
        }
        cal.set(2016, Calendar.FEBRUARY, 13);
        g.put(cal.getTime(), R.color.weekends);
        cal.set(2016, Calendar.FEBRUARY, 14);
        g.put(cal.getTime(), R.color.weekends);
        cal.set(2016, Calendar.FEBRUARY, 20);
        g.put(cal.getTime(), R.color.weekends);
        cal.set(2016, Calendar.FEBRUARY, 21);
        g.put(cal.getTime(), R.color.weekends);
        cal.set(2016, Calendar.FEBRUARY, 27);
        g.put(cal.getTime(), R.color.weekends);
        cal.set(2016, Calendar.FEBRUARY, 28);
        g.put(cal.getTime(), R.color.weekends);
        cal.set(2016, Calendar.FEBRUARY, 29);
        g.put(cal.getTime(), R.color.lecture_recess);


        // MARCH
        for(int i = 1; i< 5; i++) {
            cal.set(2016, Calendar.MARCH, i);
            g.put(cal.getTime(),R.color.lecture_recess);
        }
        for(int i = 7; i< 10; i++) {
            cal.set(2016, Calendar.MARCH, i);
            g.put(cal.getTime(),R.color.Orientation);
        }

        cal.set(2016, Calendar.MARCH, 10);
        g.put(cal.getTime(),R.color.public_holiday);

        cal.set(2016, Calendar.MARCH, 11);
        g.put(cal.getTime(),R.color.lecture_period);

        for(int i = 14; i< 19; i++) {
            cal.set(2016, Calendar.MARCH, i);
            g.put(cal.getTime(),R.color.lecture_period);
        }
        for(int i = 21; i< 25; i++) {
            cal.set(2016, Calendar.MARCH, i);
            g.put(cal.getTime(),R.color.lecture_period);
        }
        cal.set(2016, Calendar.MARCH, 25);
        g.put(cal.getTime(), R.color.public_holiday);
        cal.set(2016, Calendar.MARCH, 28);
        g.put(cal.getTime(), R.color.public_holiday);
        cal.set(2016, Calendar.MARCH, 29);
        g.put(cal.getTime(), R.color.fee_duedate);
        cal.set(2016, Calendar.MARCH, 30);
        g.put(cal.getTime(), R.color.lecture_period);
        cal.set(2016, Calendar.MARCH, 31);
        g.put(cal.getTime(), R.color.weekends);
        cal.set(2016, Calendar.MARCH, 05);
        g.put(cal.getTime(), R.color.weekends);
        cal.set(2016, Calendar.MARCH, 06);
        g.put(cal.getTime(), R.color.weekends);
        cal.set(2016, Calendar.MARCH, 12);
        g.put(cal.getTime(),R.color.weekends);
        cal.set(2016, Calendar.MARCH, 13);
        g.put(cal.getTime(),R.color.weekends);
        cal.set(2016, Calendar.MARCH, 19);
        g.put(cal.getTime(),R.color.weekends);
        cal.set(2016, Calendar.MARCH, 20);
        g.put(cal.getTime(),R.color.weekends);
        cal.set(2016, Calendar.MARCH, 26);
        g.put(cal.getTime(),R.color.weekends);
        cal.set(2016, Calendar.MARCH, 27);
        g.put(cal.getTime(),R.color.weekends);



        caldroidFragment.setBackgroundResourceForDates(g);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");


        // Floating button

        ImageView icon = new ImageView(this); // Create an icon
        icon.setImageResource(R.mipmap.ic_launcher);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);


        ImageView trimestericon = new ImageView(this);
        trimestericon.setImageResource(R.mipmap.calendar_year_icon);

        ImageView weekicon = new ImageView(this);
        weekicon.setImageResource(R.mipmap.week_icon);

        ImageView monthicon = new ImageView(this);
        monthicon.setImageResource(R.mipmap.calendar_month);
        TextView tv = new TextView(this);
        tv.setText("TriSem");

        SubActionButton button_trimester = itemBuilder.setContentView(trimestericon).build();
//        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.mipmap.calendar_year_icon));
        itemBuilder.setContentView(tv);
        SubActionButton button_week = itemBuilder.setContentView(weekicon).build();
//        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.mipmap.week_icon));

        SubActionButton button_month = itemBuilder.setContentView(monthicon).build();
//        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.mipmap.calendar_month));


        button_trimester.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MonthView.this, TriSemAct.class));
                    };
                });
        button_week.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MonthView.this, BasicActivity.class));
                        C.l("button_week.setOnClic");
                    }

                    ;
                });
        button_month.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        startActivity(new Intent(MonthView.this, MonthView.class));
                        C.l("button_month.setOnCl");
                    }

                    ;
                });

        button_trimester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MonthView.this, TriSemAct.class));
            }
        });
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(button_trimester)
                .addSubActionView(button_week)
                .addSubActionView(button_month)
                .attachTo(actionButton)
                .build();


        // Setup caldroid fragment


        // **** This is to show customized fragment. If you want customized

        caldroidFragment = new MonthFragment();

        // Setup arguments

        // If Activity is created after rotation
        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

            // customize startDayOfWeek
            args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
                    CaldroidFragment.MONDAY);

            // Uncomment this line to use Caldroid in compact mode
//             args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

            // Uncomment this line to use dark theme
//            args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);

            caldroidFragment.setArguments(args);
        }

        setCustomResourceForDates();

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        caldroidFragment.setCaldroidListener(listener);
    }

    // Setup listener
    final CaldroidListener listener = new CaldroidListener() {


        @Override
        public void onSelectDate(Date date, View view) {
            Toast.makeText(getApplicationContext(), formatter.format(date),
                    Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MonthView.this, BasicActivity.class));
//            caldroidFragment.setBackgroundResourceForDate(0xffff0000, date);
//            caldroidFragment.refreshView();

        }

        @Override
        public void onChangeMonth(int month, int year) {
            String text = "month: " + month + " year: " + year;
            Toast.makeText(getApplicationContext(), text,
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onLongClickDate(Date date, View view) {
            Toast.makeText(getApplicationContext(),
                    "Long click " + formatter.format(date),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCaldroidViewCreated() {
            if (caldroidFragment.getLeftArrowButton() != null) {
                Toast.makeText(getApplicationContext(),
                        "Caldroid view is created", Toast.LENGTH_SHORT)
                        .show();
            }
        }


    };


    public String[] getOtherDays(Date selDate) {
        String[] dateOfDays = new String[8];
        switch ((selDate.toString()).split(" ")[0]) {
            case "Mon":
                dateOfDays[0] = selDate.toString();
                dateOfDays[1] = getRequiredDate(selDate, 1);
                dateOfDays[2] = getRequiredDate(selDate, 2);
                dateOfDays[3] = getRequiredDate(selDate, 3);
                dateOfDays[4] = getRequiredDate(selDate, 4);
                dateOfDays[5] = getRequiredDate(selDate, 5);
                dateOfDays[6] = getRequiredDate(selDate, 6);
                dateOfDays[7] = "0";
                break;
            case "Tue":
                dateOfDays[0] = getRequiredDate(selDate, -1);
                dateOfDays[1] = selDate.toString();
                dateOfDays[2] = getRequiredDate(selDate, 1);
                dateOfDays[3] = getRequiredDate(selDate, 2);
                dateOfDays[4] = getRequiredDate(selDate, 3);
                dateOfDays[5] = getRequiredDate(selDate, 4);
                dateOfDays[6] = getRequiredDate(selDate, 5);
                dateOfDays[7] = "1";
                break;
            case "Wed":
                dateOfDays[0] = getRequiredDate(selDate, -2);
                dateOfDays[1] = getRequiredDate(selDate, -1);
                dateOfDays[2] = selDate.toString();
                dateOfDays[3] = getRequiredDate(selDate, 1);
                dateOfDays[4] = getRequiredDate(selDate, 2);
                dateOfDays[5] = getRequiredDate(selDate, 3);
                dateOfDays[6] = getRequiredDate(selDate, 4);
                dateOfDays[7] = "2";
                break;
            case "Thu":
                dateOfDays[0] = getRequiredDate(selDate, -3);
                dateOfDays[1] = getRequiredDate(selDate, -2);
                dateOfDays[2] = getRequiredDate(selDate, -1);
                dateOfDays[3] = selDate.toString();
                dateOfDays[4] = getRequiredDate(selDate, 1);
                dateOfDays[5] = getRequiredDate(selDate, 2);
                dateOfDays[6] = getRequiredDate(selDate, 3);
                dateOfDays[7] = "3";
                break;
            case "Fri":
                dateOfDays[0] = getRequiredDate(selDate, -4);
                dateOfDays[1] = getRequiredDate(selDate, -3);
                dateOfDays[2] = getRequiredDate(selDate, -2);
                dateOfDays[3] = getRequiredDate(selDate, -1);
                dateOfDays[4] = selDate.toString();
                dateOfDays[5] = getRequiredDate(selDate, 1);
                dateOfDays[6] = getRequiredDate(selDate, 2);
                dateOfDays[7] = "4";
                break;
            case "Sat":
                dateOfDays[0] = getRequiredDate(selDate, -5);
                dateOfDays[1] = getRequiredDate(selDate, -4);
                dateOfDays[2] = getRequiredDate(selDate, -3);
                dateOfDays[3] = getRequiredDate(selDate, -2);
                dateOfDays[4] = getRequiredDate(selDate, -1);
                dateOfDays[5] = selDate.toString();
                dateOfDays[6] = getRequiredDate(selDate, 1);
                dateOfDays[7] = "5";
                break;
            case "Sun":
                dateOfDays[0] = getRequiredDate(selDate, -6);
                dateOfDays[1] = getRequiredDate(selDate, -5);
                dateOfDays[2] = getRequiredDate(selDate, -4);
                dateOfDays[3] = getRequiredDate(selDate, -3);
                dateOfDays[4] = getRequiredDate(selDate, -2);
                dateOfDays[5] = getRequiredDate(selDate, -1);
                dateOfDays[6] = selDate.toString();
                dateOfDays[7] = "6";
                break;
        }
        return dateOfDays;
    }

    private String getRequiredDate(Date selDate, int num) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(selDate);
        cal.add(Calendar.DAY_OF_YEAR, num);
        C.l("required date " + cal.getTime());
        return cal.getTime().toString();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.color_keys, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.colorkeys:
                Intent intent = new Intent(this, color_keys.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;

    }

    }
