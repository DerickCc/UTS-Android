package com.derick.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class DaftarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Button btnDaftar2 = (Button) findViewById(R.id.btnDaftar2);

        btnDaftar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etDaftarUsername = (EditText) findViewById(R.id.etDaftarUsername);
                String checkDaftarUsername = etDaftarUsername.getText().toString();
                if(TextUtils.isEmpty(checkDaftarUsername)){
                    Toast.makeText(getApplicationContext(), "Silahkan masukkan username", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(DaftarActivity.this,MainActivity.class);
                    intent.putExtra("username", checkDaftarUsername);
                    startActivity(intent);
                }
            }
        });

    }
}