package edu.zju.SQLiteDemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText;
    private EditText ageEditText;
    private EditText heightEditText;
    private Button addButton;
    private Button showButton;
    private Button clearButton;
    private Button deleteButton;

    private Button IDdeleteButton;
    private Button IDsearchButton;
    private Button IDupdateButton;

    private EditText idEditText;
    private TextView largeTexView;

    private static final String DB_TABLE = "people_info";

    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        db = SQLiteDatabase.openOrCreateDatabase(
                this.getFilesDir().toString() + "/my.db3", null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null && db.isOpen()) {
            db.close();
        }
    }

    private void findViewById() {
        nameEditText = (EditText) findViewById(R.id.name_edtTxt);
        ageEditText = (EditText) findViewById(R.id.age_edtTxt);
        heightEditText = (EditText) findViewById(R.id.height_edtTxt);
        addButton = (Button) findViewById(R.id.add_btn);
        showButton = (Button) findViewById(R.id.show_btn);
        clearButton = (Button) findViewById(R.id.clear_btn);
        deleteButton = (Button) findViewById(R.id.delete_btn);

        IDdeleteButton = (Button) findViewById(R.id.IDdelete_btn);
        IDsearchButton = (Button) findViewById(R.id.IDsearch_btn);
        IDupdateButton = (Button) findViewById(R.id.IDupdate_btn);

        addButton.setOnClickListener(this);
        showButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);
        IDdeleteButton.setOnClickListener(this);
        IDsearchButton.setOnClickListener(this);
        IDupdateButton.setOnClickListener(this);

        idEditText = (EditText) findViewById(R.id.id_edtTxt);
        largeTexView = (TextView) findViewById(R.id.large_tv);

    }

    @Override
    public void onClick(View view) {
        int id;
        long tag;
        switch (view.getId()) {
            case R.id.add_btn:

                People people = new People(nameEditText.getText().toString(),
                        Integer.parseInt(ageEditText.getText().toString()),
                        Double.parseDouble(heightEditText.getText().toString()));
                tag = insert(people);
                Toast.makeText(MainActivity.this, "添加成功,添加的ID为：" + tag, Toast.LENGTH_SHORT).show();
                break;

            case R.id.show_btn:
                Cursor cursor = db.rawQuery("select * from " + DB_TABLE, null);
                People[] peoples = ConvertToPeople(cursor);
                if (peoples != null) {
                    for (int i = 0; i < peoples.length; i++) {
                        largeTexView.append(peoples[i].toString() + "\n");
                    }
                }

                break;
            case R.id.clear_btn:
                largeTexView.setText("");

                break;
            case R.id.delete_btn:


                tag = deleteAllData();
                Toast.makeText(MainActivity.this, "删除" + tag + "条记录", Toast.LENGTH_SHORT).show();

                break;
            case R.id.IDdelete_btn:
                id = Integer.parseInt(idEditText.getText().toString());
                tag = deleteOneData(id);
                Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();

                break;
            case R.id.IDsearch_btn:
                if (!idEditText.getText().toString().equals("")) {
                    id = Integer.parseInt(idEditText.getText().toString());

                    People[] idSearch = getOneData(id);
                    if (idSearch != null) {
                        for (int i = 0; i < idSearch.length; i++) {
                            largeTexView.append(idSearch[i].toString() + "\n");
                        }

                    }
                }
                break;
            case R.id.IDupdate_btn:
                People updatePeople = new People(nameEditText.getText().toString(),
                        Integer.parseInt(ageEditText.getText().toString()),
                        Double.parseDouble(heightEditText.getText().toString()));
                id = Integer.parseInt(idEditText.getText().toString());
                tag = updateOneData(id, updatePeople);
                Toast.makeText(MainActivity.this, "更新成功", Toast.LENGTH_SHORT).show();

                break;
        }
    }

    public long insert(People people) {
        ContentValues newValues = new ContentValues();

        newValues.put("name", people.Name);
        newValues.put("age", people.Age);
        newValues.put("height", people.Height);
        db.execSQL("create table if not exists " + DB_TABLE + " (_id integer " +
                " primary key autoincrement, " +
                " name varchar(20)," +
                " age integer," +
                " height double)");
        return db.insert(DB_TABLE, null, newValues);
    }

    public long deleteAllData() {
        return db.delete(DB_TABLE, null, null);
    }

    private People[] ConvertToPeople(Cursor cursor) {
        int resultCounts = cursor.getCount();
        if (resultCounts == 0 || !cursor.moveToFirst()) {
            return null;
        }
        People[] peoples = new People[resultCounts];
        for (int i = 0; i < resultCounts; i++) {
            peoples[i] = new People();
            peoples[i].ID = cursor.getInt(0);
            peoples[i].Name = cursor.getString(cursor.getColumnIndex("name"));
            peoples[i].Age = cursor.getInt(cursor.getColumnIndex("age"));
            peoples[i].Height = cursor.getDouble(cursor.getColumnIndex("height"));
            cursor.moveToNext();
        }
        return peoples;

    }

    public People[] getOneData(long id) {
        Cursor results = db.query(DB_TABLE, new String[]{"_id",
                "name", "age", "height"}, " _id" + "=" + id, null, null, null, null);
        return ConvertToPeople(results);

    }

    public long updateOneData(long id, People people) {
        ContentValues updateValues = new ContentValues();
        updateValues.put("name", people.Name);
        updateValues.put("age", people.Age);
        updateValues.put("height", people.Height);
        return db.update(DB_TABLE, updateValues, "_id = " + id, null);

    }

    public long deleteOneData(long id) {
        return db.delete(DB_TABLE, " _id" + "=" + id, null);
    }

    class People {
        public int ID = -1;
        public String Name;
        public int Age;
        public double Height;

        People() {

        }

        People(String name, int age, double height) {
            this.Name = name;
            this.Age = age;
            this.Height = height;
        }

        public String toString() {
            String result = "";
            result += "ID: " + this.ID + ",";
            result += "姓名: " + this.Name + ",";
            result += "年龄: " + this.Age + ",";
            result += "身高: " + this.Height + ",";
            return result;
        }
    }
}
