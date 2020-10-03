package com.isltranslator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.skydoves.elasticviews.ElasticButton;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CAMERA_PERMISSION = 200;

  private   ElasticButton startTranslatorBtn,settingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check Realtime permissions if run higher API 23
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.CAMERA,
            },REQUEST_CAMERA_PERMISSION);
            return;
        }

        //This just checks whether Wi-Fi is switched on or not and prompts if it isn't
        //Reference: https://stackoverflow.com/a/6593908/5370202
        WifiManager wifi = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifi.isWifiEnabled()){
            Toast.makeText(getApplicationContext(),"Please switch on Wi-Fi before proceeding to use the app",Toast.LENGTH_SHORT).show();
        }

        startTranslatorBtn = (ElasticButton)findViewById(R.id.startTranslator);

        startTranslatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                Intent i=new Intent(getBaseContext(),CameraActivity.class);
                startActivity(i);
            }
        });
        settingBtn = (ElasticButton)findViewById(R.id.settings);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // do something
                Intent i=new Intent(getApplicationContext(),SettingsActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CAMERA_PERMISSION)
        {
            if(grantResults[0] != PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "You need Camera Permission !", Toast.LENGTH_SHORT).show();
                //finish();
            }
        }
    }
}