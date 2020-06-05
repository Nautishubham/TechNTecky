package com.example.techntecky;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    private String weburl = "https://linkedgyan.blogspot.com/";
    ProgressBar progressBarweb;
    ProgressDialog progressDialog;
    RelativeLayout relativeLayout;
    Button nointernetconnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.mywebview);
        progressBarweb = findViewById(R.id.progressbar);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading please wait...");
        nointernetconnection = findViewById(R.id.btnnoconnection);

        relativeLayout = findViewById(R.id.relativelaot);
        webView.getSettings().setJavaScriptEnabled(true);

        if (savedInstanceState != null)
        {
            webView.restoreState(savedInstanceState);
        }
        else{
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.getSettings().setDomStorageEnabled(true);
            webView.getSettings().setLoadsImagesAutomatically(true);


            checkconnection();

        }

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                progressBarweb.setVisibility(View.VISIBLE);
                progressBarweb.setProgress(newProgress);

                progressDialog.show();
                if (newProgress == 100) {
                    progressBarweb.setVisibility(View.GONE);

                    progressDialog.dismiss();
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        nointernetconnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkconnection();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to Exit ? ")
                    .setNegativeButton("No", null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                        }
                    }).show();
        }
    }

    public void checkconnection() {


        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobienetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


        if (wifi.isConnected()) {

            webView.loadUrl(weburl);
            webView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);
        } else if (mobienetwork.isConnected()) {

            webView.loadUrl(weburl);
            webView.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);

        } else {
            webView.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navprevious:
                onBackPressed();
                break;

            case R.id.navnext:
                Intent myintent=new Intent(Intent.ACTION_SEND);
                myintent.setType("text/plain");
                String shareBody="Your Body here";
                String ShareSub="Your subject here";
                myintent.putExtra(Intent.EXTRA_SUBJECT,ShareSub);
                myintent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myintent,"Share using"));
                break;
            case  R.id.navRate:
            //    Intent intent = new Intent(MainActivity.this, rating.class);
              //  startActivity(intent);    break;
            case  R.id.navdevloper:Toast.makeText(this, "You can mail me on shubhamnautiyal64@gmail.com", Toast.LENGTH_LONG).show();
                Toast.makeText(this, "You can contact me on +919897512806", Toast.LENGTH_LONG).show();
                break;
            case R.id.navreload:
                checkconnection();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }


    }
