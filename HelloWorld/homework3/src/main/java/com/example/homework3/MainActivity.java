package com.example.homework3;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Button toList = (Button) findViewById(R.id.to_list);
        toList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExpandableListActivity.class);
                startActivity(intent);
            }
        });
        Button toList2 = (Button) findViewById(R.id.to_list2);
        toList2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] name = getResources().getStringArray(R.array.name);
                Toast.makeText(MainActivity.this, name[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button toAlertDialog = (Button) findViewById(R.id.alert_dialog);
        toAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(MainActivity.this);
                myAlertDialog.show();
                myAlertDialog.setTitle("简单对话框");
                myAlertDialog.setCancelable(false);
                myAlertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "单击了确定按钮", Toast.LENGTH_SHORT).show();
                    }
                });
                myAlertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "单击了取消按钮", Toast.LENGTH_SHORT).show();
                    }
                });
                myAlertDialog.show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        TextView textView = (TextView) findViewById(R.id.setting_text_view);
        switch (item.getItemId()) {
            case R.id.font_min:
                textView.setTextSize(10);
                break;
            case R.id.font_mid:
                textView.setTextSize(15);
                break;
            case R.id.font_max:
                textView.setTextSize(20);
                break;
            case R.id.color_blue:
                textView.setBackgroundColor(Color.parseColor("#0000ff"));
                break;
            case R.id.color_red:
                textView.setBackgroundColor(Color.parseColor("#ff0000"));
                break;
            case R.id.color_yellow:
                textView.setBackgroundColor(Color.parseColor("#ffff00"));
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
