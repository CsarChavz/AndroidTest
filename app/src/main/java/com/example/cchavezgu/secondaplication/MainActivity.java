package com.example.cchavezgu.secondaplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] taskList = new String[10];
    private ArrayAdapter<String> itemsAdapter;
    //ListView sentenceList = new ListView(this);
    static final int ADD_TASK_REQUEST = 1;
    private int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeAdapter(taskList);
        setContentView(R.layout.activity_main);
        ListView sentenceList;
        sentenceList = (ListView) findViewById(R.id.taskListView);
        sentenceList.setAdapter(itemsAdapter);
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
                taskList[index] = returnString ;
                //TextView textView = (TextView) findViewById(R.id.taskList);
                //textView.setText(returnString);
                itemsAdapter.notifyDataSetChanged();
                index++;
            }
        }
    }

    public void addTaskClicked(View view) {
        Intent intent = new Intent(this, TaskDescriptionActivity.class);
        startActivityForResult(intent,ADD_TASK_REQUEST);
    }

    public void makeAdapter(String[] list) {
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
    }
}
