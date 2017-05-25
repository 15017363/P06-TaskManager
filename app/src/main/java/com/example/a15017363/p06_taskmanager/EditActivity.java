package com.example.a15017363.p06_taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {

    EditText etName, etContent, etTime;
    Button btnUpdate, btnCancel;
    Task data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etName = (EditText)findViewById(R.id.etUpdatedName);
        etContent = (EditText)findViewById(R.id.etUpdatedContent);
        etTime = (EditText)findViewById(R.id.etUpdatedReminder);

        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnCancel = (Button)findViewById(R.id.btnCancelUpdate);

        Intent i = getIntent();
        data = (Task) i.getSerializableExtra("data");

        etName.setText(data.getTaskName());
        etContent.setText(data.getTaskContent());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTaskName(etName.getText().toString());
                data.setTaskContent(etContent.getText().toString());
                dbh.updateTask(data);
                dbh.close();

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, Integer.parseInt(etTime.getText().toString()));

                Intent intent = new Intent(EditActivity.this,
                        TaskBroadcastReceiver.class);
                intent.putExtra("name",data.getTaskName());
                intent.putExtra("content",data.getTaskContent());

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        EditActivity.this, 123,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);

                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

                Intent returnIntent = new Intent();
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });


    }
}
