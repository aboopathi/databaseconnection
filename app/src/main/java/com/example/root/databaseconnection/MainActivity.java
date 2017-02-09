package com.example.root.databaseconnection;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CustomSqliteHelper mCustomSqliteHelper;
    private EditText mEdtText1;
    private EditText mEdtText2;
    private EditText mEdtText3;
    private Button msubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEdtText1 = (EditText) findViewById(R.id.EdtText1);
        mEdtText2 = (EditText) findViewById(R.id.EdtText2);
        mEdtText3 = (EditText) findViewById(R.id.EdtText3);
        msubmit = (Button) findViewById(R.id.sumbit);
        msubmit.setOnClickListener(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //create table
        createTable();

        //to get the student details
        getStudents();

    }

    private void createTable() {
        mCustomSqliteHelper = new CustomSqliteHelper(MainActivity.this, null);
        // mCustomSqliteHelper.insertStudent("Boopathi",25);
        // mCustomSqliteHelper.insertStudent("Nantha",25);

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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getStudents() {
        Cursor cursor = mCustomSqliteHelper.getStudents();

        if (null != cursor && cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String age = cursor.getString(cursor.getColumnIndex("age"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                Log.v("boopathi", "" + name + ":" + age + ":" + email + ":");
            } while (cursor.moveToNext());
        }
    }

    @Override
    public void onClick(View v) {
        String name = mEdtText1.getText().toString();
        String age = mEdtText2.getText().toString();
        String email = mEdtText3.getText().toString();

        mCustomSqliteHelper.insertStudent(name, age, email);

        clearFields();

        getStudents();
    }

    private void clearFields() {
        if(null != mEdtText3){
            mEdtText3.setText("");
        }
        if(null != mEdtText2){
            mEdtText2.setText("");
        }
        if(null != mEdtText1){
            mEdtText1.setText("");
        }
    }
}