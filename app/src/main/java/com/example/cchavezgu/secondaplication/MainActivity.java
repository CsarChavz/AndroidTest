package com.example.cchavezgu.secondaplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> taskList;
    private ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addTaskClicked(View view) {

    }

    public void makeAdapter(List<String> list) {
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
    }
}
