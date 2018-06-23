package com.example.vishnupremsanketh.login;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper dbhepler;
    SQLiteDatabase db;
    Button _btnreg,_btnmainlogin;
    EditText _txtfn,_txtln,_txtemail,_txtphone,_txtpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SQLiteOpenHelper openHelper= new DatabaseHelper(this);
        _btnreg = (Button) findViewById(R.id.buttonSubmit);
        _txtfn = (EditText) findViewById(R.id.editTextFN);
        _txtln = (EditText) findViewById(R.id.editTextLN);
        _txtemail = (EditText) findViewById(R.id.editTextEmail);
        _txtpassword = (EditText) findViewById(R.id.editTextPassword);
        _txtphone =(EditText) findViewById(R.id.editTextPhone);
        _btnmainlogin= (Button) findViewById(R.id.buttonMainLogin);
        _btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = openHelper.getWritableDatabase();
                String fname= _txtfn.getText().toString();
                String lname= _txtln.getText().toString();
                String email= _txtemail.getText().toString();
                String password= _txtpassword.getText().toString();
                String phone= _txtphone.getText().toString();
                inserData(fname,lname,email,password,phone);
                Toast.makeText(getApplicationContext(),"Registered successfully",Toast.LENGTH_LONG).show();
            }
        });
        _btnmainlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);

            }
        });
    }

    public void inserData(String fname,String lname,String email,String password,String phone){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COL_2,fname);
        values.put(DatabaseHelper.COL_3,lname);
        values.put(DatabaseHelper.COL_4,password);
        values.put(DatabaseHelper.COL_5,email);
        values.put(DatabaseHelper.COL_6,phone);

        long id= db.insert(DatabaseHelper.TABLE_NAME,null,values);
    }
}
