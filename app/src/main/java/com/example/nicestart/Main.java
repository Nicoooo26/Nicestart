package com.example.nicestart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

public class Main extends AppCompatActivity {

    private SwipeRefreshLayout swipeLayout;
    private WebView miVisorWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

       TextView textSwipe = findViewById(R.id.mycontext);
       registerForContextMenu(textSwipe);

        WebView mycontext = findViewById(R.id.vistaweb);
        registerForContextMenu(mycontext);

        swipeLayout = findViewById(R.id.myswipe);
        swipeLayout.setOnRefreshListener(mOnRefreshListener);

        miVisorWeb = (WebView) findViewById(R.id.vistaweb);

        String html = "<html>" +
                "<head><style>" +
                "html, body { margin:0; padding:0; height:100%; overflow:hidden; }" +
                "img { width:100%; height:100%; object-fit:cover; }" +   // ❤️ el equivalente a centerCrop
                "</style></head>" +
                "<body>" +
                "<img src='https://thispersondoesnotexist.com' />" +
                "</body></html>";

        miVisorWeb.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);

    }

// IMPLEMENTING APP BAR MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent intent = new Intent(Main.this, Profile.class);
            startActivity(intent);
        }
        if (id == R.id.seguridad) {
            Toast toast = Toast.makeText(this, "Go to Seguridad", Toast.LENGTH_LONG);
            toast.show();
        }
        if (id == R.id.privacidad) {
            Toast toast = Toast.makeText(this, "Go to Privacidad", Toast.LENGTH_LONG);
            toast.show();
        }
        if (id == R.id.github) {
            Intent intent = new Intent(Main.this, Github.class);
            startActivity(intent);
        }
        if (id == R.id.item5) {
            showAlertDialogButtonClicked(Main.this);
        }
        return super.onOptionsItemSelected(item);
    }

    // IMPLEMENTING CONTEXT MENU
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
//                item.getMenuInfo();
        if (item.getItemId() == R.id.item1) {
            Toast toast = Toast.makeText(this, "Item copied",
                    Toast.LENGTH_LONG);
            toast.show();
        } else if (item.getItemId() == R.id.item2) {
            Toast toast2 = Toast.makeText(this, "Downloading item...",
                    Toast.LENGTH_LONG);
            toast2.show();
        }
        return false;
    }

    // IMPLEMENTING SWIPE REFRESH
    protected SwipeRefreshLayout.OnRefreshListener
        mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener(){
        @Override
        public void onRefresh() {
//            Toast toast0 = Toast.makeText(Main.this, "Hi there! I don't exist :)", Toast.LENGTH_LONG);
//            toast0.show();

            final ConstraintLayout mLayout = findViewById(R.id.myMainConstraint);
//
            Snackbar snackbar = Snackbar
                    .make(mLayout, "fancy a Snack while you refresh?", Snackbar.LENGTH_SHORT)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(mLayout, "Action is restored!", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });

            snackbar.show();

            miVisorWeb.reload();
            swipeLayout.setRefreshing(false);
        }
    };

    //IMPLEMENTING ALERT DIALOG
    public void showAlertDialogButtonClicked(Main view){
        // setup de alert builder
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        // el dialogo estandar tiene titulo/icono pero podemos sustituirlo por un XML a medida
        //builder.setTitle("NiceStart!");
        //builder.setMessage("¿Que deseas hacer?");
        //builder.setIcon(R.drawable.mailchimp);

        // un XML a medida para el diálogo
        builder.setView(getLayoutInflater().inflate(R.layout.alertdialog_view,null));
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.exit(0);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast toast0 = Toast.makeText(Main.this, "Gracias por continuar en NiceStart :)", Toast.LENGTH_LONG);
                toast0.show();
            }
        });
        builder.setNeutralButton("Go to Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Main.this, Login.class);
                startActivity(intent);
//                dialog.dismiss();
            }
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.purple_700));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.red));
        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(ContextCompat.getColor(this, R.color.purple_700));

    }

}


