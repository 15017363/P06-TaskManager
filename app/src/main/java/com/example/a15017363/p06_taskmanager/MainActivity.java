package com.example.a15017363.p06_taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<Task> taskArrayList;
    ArrayAdapter aa;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lvTask);
        btnAdd = (Button)findViewById(R.id.btnAdd);

//        Intent intentReceived = getIntent();
        DBHelper db = new DBHelper(MainActivity.this);
        taskArrayList = db.getAllTasks();

        aa = new TaskArrayAdaptor(MainActivity.this, R.layout.row, taskArrayList) ;
        lv.setAdapter(aa);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(),AddActivity.class);
                startActivityForResult(i,9);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.deleteTask(taskArrayList.get(position).getId());
                taskArrayList.remove(position);
                dbh.close();
                aa.notifyDataSetChanged();
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            if(data != null){
                DBHelper dbh = new DBHelper(MainActivity.this);
                taskArrayList.clear();
                taskArrayList.addAll(dbh.getAllTasks());
                dbh.close();
                aa.notifyDataSetChanged();
            }
        }
    }
}
