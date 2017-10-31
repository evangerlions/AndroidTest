package com.example.homework5_sqlitedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button insert, show, clear, query, delete, update;
    private TextView textShow;
    private EditText textName, textAge, id;
    UsersDBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //为按钮绑定监听器
        initView();

        //初始化数据库
        db = new UsersDBManager(this.getFilesDir().toString());
    }

    public void initView() {
        textName = (EditText) findViewById(R.id.edit_name);
        textAge = (EditText) findViewById(R.id.edit_age);
        id = (EditText) findViewById(R.id.edit_id);

        insert = (Button) findViewById(R.id.insert);
        show = (Button) findViewById(R.id.show);
        clear = (Button) findViewById(R.id.clear);
        query = (Button) findViewById(R.id.query);
        delete = (Button) findViewById(R.id.delete);
        update = (Button) findViewById(R.id.update);

        textShow = (TextView) findViewById(R.id.text_show);

        insert.setOnClickListener(this);
        show.setOnClickListener(this);
        clear.setOnClickListener(this);
        query.setOnClickListener(this);
        delete.setOnClickListener(this);
        update.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        boolean tag = false;
        switch (view.getId()) {
            case R.id.insert:
                Log.d("zk", "姓名：" + textName.getText().toString());
                if (textName.getText().toString() == null || textName.getText().toString().length() <= 0) {
                    Toast.makeText(this, "名字不能为空！", Toast.LENGTH_SHORT).show();
                } else if (textName.getText().toString().length() > 30) {
                    Toast.makeText(this, "名字长度过长", Toast.LENGTH_SHORT).show();
                } else {
                    Users user = new Users(textName.getText().toString(), Integer.parseInt(textAge.getText().toString()));

                    if (!db.insert(user)) {
                        Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.show:
                Users[] resultsAll = db.queryAll();
                if (resultsAll == null || resultsAll.length <= 0) {
                    textShow.setText("");
                    Toast.makeText(this, "数据库为空！", Toast.LENGTH_SHORT).show();
                } else {
                    textShow.setText("");
                    for (Users i : resultsAll) {
                        textShow.append("用户ID:"+i.getId() +" 姓名："+ i.getName() +" 年龄："+ i.getAge() + " 注册日期"+i.getRegDate() + "\n");
                    }
                }
                break;
            case R.id.clear:
                tag = db.deleteAll();
                if (tag) {
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                    textShow.setText("");
                } else {
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.query:

                Users[] results = db.query(Integer.parseInt(id.getText().toString()));
                for (Users i : results) {
                    textShow.setText("");
                    textShow.append("用户ID:"+i.getId() +" 姓名："+ i.getName() +" 年龄："+ i.getAge() + " 注册日期"+i.getRegDate() + "\n");
                }
                break;
            case R.id.delete:
                tag = db.delete(Integer.parseInt(id.getText().toString()));
                if (tag) {
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.update:
                tag = db.update(Integer.parseInt(id.getText().toString()), textName.getText().toString(), Integer.parseInt(textAge.getText().toString()));
                if (tag) {
                    Toast.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "更新失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

}
