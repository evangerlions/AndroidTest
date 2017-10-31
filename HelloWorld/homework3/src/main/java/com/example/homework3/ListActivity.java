package com.example.homework3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        ListView list = (ListView) findViewById(R.id.list1);
        String[] arry = {"项目1","项目2","项目3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.array_item,arry);
        list.setAdapter(adapter);
    }
}
