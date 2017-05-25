package com.example.a15017363.p06_taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017363 on 25/5/2017.
 */

public class TaskArrayAdaptor extends ArrayAdapter<Task> {
    Context context;
    transient
    ArrayList<Task> tasks;
    TextView tvName, tvContent;
    int resource;

    public TaskArrayAdaptor(Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);
        //Match the UI components with Java variables
        tvName = (TextView)rowView.findViewById(R.id.tvTaskName);
        tvContent = (TextView)rowView.findViewById(R.id.tvTaskContent);

        Task task = tasks.get(position);
            tvName.setText((position+1)+" "+task.getTaskName());
        tvContent.setText(task.getTaskContent());

        return rowView;
    }



}
