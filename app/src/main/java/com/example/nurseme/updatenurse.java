package com.example.nurseme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.internal.InternalTokenResult;

public class updatenurse extends AppCompatActivity {
EditText e;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatenurse);
        e=findViewById(R.id.editText3);
    }

public void search(View v)
{
    Intent i= new Intent(this,updatenurse2.class);
    i.putExtra("email",e.getText().toString().trim());
    startActivity(i);
}}