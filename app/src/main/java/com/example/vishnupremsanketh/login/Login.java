package com.example.vishnupremsanketh.login;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    EditText _textemail, _txtpassowrd;
    Button _btnLogin;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();
        _btnLogin = (Button) findViewById(R.id.buttonLogin);
        _textemail = (EditText) findViewById(R.id.editTextLoginEmail);
        _txtpassowrd = (EditText) findViewById(R.id.editTextLginpassword);
        _btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = _textemail.getText().toString();
                String password = _txtpassowrd.getText().toString();
                cursor = db.rawQuery(" SELECT * FROM "+ DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL_5+"=? AND "+ DatabaseHelper.COL_4+"=?",new String[]{email,password});
                if(cursor != null){
                    if(cursor.getCount()>0){
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(),"Login successuflly",Toast.LENGTH_LONG).show();

                    }
                    else {
                        Toast.makeText(getApplicationContext(),"doesnt match",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
