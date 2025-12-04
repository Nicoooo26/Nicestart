package com.example.nicestart;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.snackbar.Snackbar;

public class Github extends AppCompatActivity {

    private SwipeRefreshLayout swipeLayout;
    private WebView miVisorWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_github);

        WebView mycontext = findViewById(R.id.vistaweb);
        registerForContextMenu(mycontext); // Activa menú contextual en WebView

        swipeLayout = findViewById(R.id.myswipe);
        swipeLayout.setOnRefreshListener(mOnRefreshListener);

        miVisorWeb = (WebView) findViewById(R.id.vistaweb);

        WebSettings s = miVisorWeb.getSettings();
        s.setJavaScriptEnabled(true); // Habilita JS
        s.setDomStorageEnabled(true); // Permite almacenamiento web
        miVisorWeb.setWebViewClient(new WebViewClient()); // Abre dentro de la app
        miVisorWeb.loadUrl("https://github.com/Nicoooo26"); // Carga perfil GitHub
    }

    protected SwipeRefreshLayout.OnRefreshListener
            mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener(){
        @Override
        public void onRefresh() {
            miVisorWeb.reload(); // Recarga página
            swipeLayout.setRefreshing(false);
        }
    };

}