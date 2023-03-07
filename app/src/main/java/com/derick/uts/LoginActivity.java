package com.derick.uts;

import static android.app.NotificationManager.IMPORTANCE_HIGH;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel myChannel = new NotificationChannel("my_channel_3", "my_channel", IMPORTANCE_HIGH);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);

            //optional
            myChannel.enableLights(true); //lampu indikator di hpny (Hardware)
            myChannel.setLightColor(Color.RED);
            myChannel.enableVibration(true);
            myChannel.setVibrationPattern( //atur irama getar
                    new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400}
            );
            myChannel.setShowBadge(false);

            notificationManager.createNotificationChannel(myChannel);
        }

        Button btnMasuk = (Button) findViewById(R.id.btnMasuk);

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etUsername = (EditText) findViewById(R.id.etUsername);
                String checkUsername = etUsername.getText().toString();
                if(TextUtils.isEmpty(checkUsername)){
                    Toast.makeText(getApplicationContext(), "Silahkan masukkan username", Toast.LENGTH_LONG).show();
                }
                else{
                    tampilNotifikasi(checkUsername);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("username", checkUsername);
                    startActivity(intent);
                }
            }
        });

        Button btnDaftar = (Button) findViewById(R.id.btnDaftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,DaftarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void tampilNotifikasi(String nama) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(LoginActivity.this, "my_channel_3");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Berhasil Login");
        builder.setContentText("Selamat Datang " + nama);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent notificationIntent = new Intent(this, NotificationActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // supaya saat notifikasi keluar, notifikasi yang lama dihapus
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        //NOTIFIKASI MANAGER
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(3,builder.build());
    }
}