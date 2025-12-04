package com.example.nicestart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        ImageView mBoy = findViewById(R.id.boy);

        Glide.with(this)
                .load(R.drawable.hipman) // Carga imagen local
//              .load(R.drawable.girl)
//                .transition(DrawableTransitionOptions.withCrossFade(1000))
                .centerCrop()
//                .circleCrop()
//                .placeholder(new ColorDrawable(this.getResources().getColor(R.color.teal_200)))
                .into(mBoy); // La muestra en el ImageView
    }

    // Metodo para ir al Main
    public void openMain(View v){
        Intent intent = new Intent(Signup.this,Main.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    // Metodo para ir al Login
    public void openLogin(View v){
        Intent intent = new Intent(Signup.this,Login.class);
        startActivity(intent);
    }
}