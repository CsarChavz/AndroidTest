package com.example.cchavezgu.secondaplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TaskDescriptionActivity extends AppCompatActivity {

    static final String EXTRA_TASK_DESCRIPTION  = "task";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_description);
    }
    public void doneClicked(View view) {
        // 1
        EditText descriptionText = (EditText) findViewById(R.id.descriptionText);
        String taskDescription =   descriptionText.getText().toString();

        if (!taskDescription.isEmpty()) {
        // 2
            Intent result = new Intent();
            result.putExtra(EXTRA_TASK_DESCRIPTION, taskDescription);
            setResult(Activity.RESULT_OK, result);
        } else {
        // 3
            setResult(Activity.RESULT_CANCELED);
        }
        // 4
        finish();
    }

}
