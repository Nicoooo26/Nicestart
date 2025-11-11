package com.example.nicestart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
    }

    public void openMain(View v){
        Intent intent = new Intent(Login.this, Main.class);
    //    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void openSignup(View v){
        Intent intent = new Intent(Login.this, Signup.class);
        startActivity(intent);
    }
}