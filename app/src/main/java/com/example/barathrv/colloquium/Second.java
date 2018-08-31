package com.example.barathrv.colloquium;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;

public class Second extends AppCompatActivity {
EditText ET_NAME,ET_USER_NAME,ET_USER_PASS;
String user_name,name,user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ET_NAME=(EditText) findViewById(R.id.name);
        ET_USER_NAME=(EditText) findViewById(R.id.user_name);
        ET_USER_PASS=(EditText) findViewById(R.id.pwd);
    }
    public void userReg(View v)
    {
name=ET_NAME.getText().toString();
user_name=ET_USER_NAME.getText().toString();
user_pass=ET_USER_PASS.getText().toString();
String method="register";
BackgroundTask bg=new BackgroundTask(this);
bg.execute(method,name,user_name,user_pass);


    }

}
