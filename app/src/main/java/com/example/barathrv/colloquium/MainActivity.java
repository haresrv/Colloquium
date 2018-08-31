package com.example.barathrv.colloquium;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
Button bt;
    EditText pwd;
    CheckBox pw;
    public void onClicking(View view) throws InterruptedException {

        EditText user=(EditText)findViewById(R.id.editText);
        EditText pass=(EditText)findViewById(R.id.editText3);
        String username=user.getText().toString();
        String password=pass.getText().toString();


        if(username.equals("root")&&password.equals("toor")) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            TimeUnit.SECONDS.sleep(2);
            init();
        }
        else
            Toast.makeText(this, "Wrong Credentials.", Toast.LENGTH_SHORT).show();
        }


public void init()
{
    bt=(Button) findViewById(R.id.button);
    bt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent x=new Intent(MainActivity.this,MainPage.class);
            startActivity(x);
        }
    });
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pwd=findViewById(R.id.editText3);
        pw=findViewById(R.id.checkBox2);
        pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
    public void userReg(View v)
    {
        startActivity(new Intent(this,Second.class));
    }
    public void Signin(View v)
    {
        startActivity(new Intent(this,MainPage.class));
    }
}
