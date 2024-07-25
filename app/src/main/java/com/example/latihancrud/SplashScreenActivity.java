package com.example.latihancrud;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.window.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Hapus status login pengguna
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("loggedIn");
        editor.apply();

        // Simulasi delay untuk splash screen
        new Handler().postDelayed(() -> {
            // Arahkan ke LoginActivity
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
            finish();
        }, 3000); // 3 detik
    }
}
