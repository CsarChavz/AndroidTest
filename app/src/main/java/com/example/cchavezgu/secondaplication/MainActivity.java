package com.example.cchavezgu.secondaplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private String[] taskList = {
            "Parmesan",
            "Ricotta",
            "Fontina",
            "Mozzarella",
            "Cheddar"
    };
    private ArrayList<String> taskList1 = new ArrayList<String>();
    private ArrayAdapter<String> itemsAdapter;
    private BroadcastReceiver tickReceiver;
    static final int ADD_TASK_REQUEST = 1;
    static String LOG_TAG = "MainActivityLog";
    static String PREFS_TASKS = "prefs_tasks";
    static String KEY_TASKS_LIST = "tasks_list";

    private int index = 0;

//OVERRIDE SECTION
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeAdapter(taskList1);
        makeBroadcastReceiver();
        setContentView(R.layout.activity_main);
        ListView sentenceList;
        sentenceList = (ListView) findViewById(R.id.taskListView);
        sentenceList.setAdapter(itemsAdapter);

        String savedList = getSharedPreferences(PREFS_TASKS, Context.MODE_PRIVATE).getString(KEY_TASKS_LIST, null);
        if (savedList != null) {
            ArrayList<String> items = savedList.split(",").toString(). dropLastWhile { it.isEmpty() }.toTypedArray();
            int index = 0;
            for (String task : items) {
                if (task.equals("")){
                    items.get(index). ;
                }
                index++;
            }
            taskList1.addAll(items);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == ADD_TASK_REQUEST) {
            if (resultCode == Activity.RESULT_OK) { // Activity.RESULT_OK
                // get String data from Intent
                String returnString = data.getStringExtra(TaskDescriptionActivity.EXTRA_TASK_DESCRIPTION);
                // set text view with string
                taskList1.add(returnString) ;
                itemsAdapter.notifyDataSetChanged();
                index++;
            }
        }
    }
    @Override
    protected  void onResume() {
        // 1
        super.onResume();
        // 2
        TextView textView = (TextView) findViewById(R.id.dateTimeTextView);
        textView.setText(getCurrentTimeStamp());
        // 3
        registerReceiver(tickReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
    }
    @Override
    protected  void onPause() {
        // 4
        super.onPause();
        // 5
        try {
            unregisterReceiver(tickReceiver);
        } catch ( IllegalArgumentException e) {
            Log.e(MainActivity.LOG_TAG, "Time tick Receiver not registered", e);
        }
    }
    @Override
    protected  void onStop() {
        super.onStop();
        StringBuilder savedList = new StringBuilder();
        for (String task : taskList1) {
            savedList.append(task);
            savedList.append(",");
        }

        getSharedPreferences(PREFS_TASKS, Context.MODE_PRIVATE).edit()
                .putString(KEY_TASKS_LIST, savedList.toString()).apply();
    }

    //ACTION SECTION
    public void addTaskClicked(View view) {
        Intent intent = new Intent(this, TaskDescriptionActivity.class);
        startActivityForResult(intent,ADD_TASK_REQUEST);
    }
    //FUNCTIONS SECTION
    public void makeAdapter(ArrayList<String> list) {
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
    }
    public BroadcastReceiver makeBroadcastReceiver() {
        return new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() == Intent.ACTION_TIME_TICK){
                    TextView textView = (TextView) findViewById(R.id.dateTimeTextView);
                    textView.setText(getCurrentTimeStamp());
                }
            }
        };
    }
    private String getCurrentTimeStamp(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US);
        Date now = new Date();
        return simpleDateFormat.format(now);
    }
}
