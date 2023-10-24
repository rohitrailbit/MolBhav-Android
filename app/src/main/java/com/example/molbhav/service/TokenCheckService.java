package com.example.molbhav.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.auth0.android.jwt.JWT;
import com.example.molbhav.LoginActivity;

import java.util.Date;

public class TokenCheckService extends Service {

    private final Handler handler = new Handler();

    // Define the interval for token checks (e.g., every 30 minutes).
    private static final long TOKEN_CHECK_INTERVAL = 60 * 1000; // 60 secs

    private final Runnable tokenCheckRunnable = new Runnable() {
        @Override
        public void run() {
            // Implement token expiration check logic here.
            System.out.println("Token Check Starts");
            if (!isTokenValid()) {
                logOut();
            } else {
                // Schedule the next check after the defined interval.
                handler.postDelayed(this, TOKEN_CHECK_INTERVAL);
            }

        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start the token check process when the service is started.
        handler.post(tokenCheckRunnable);

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        // Stop the token check process when the service is destroyed.
        handler.removeCallbacks(tokenCheckRunnable);
    }

    private boolean isTokenValid() {
        final String TOKEN_PREF_KEY = "authToken";
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN_PREF_KEY, null);
        if (token == null || token.isEmpty()) {
            return false; // Token is not present or empty, consider it invalid
        }

        JWT jwt = new JWT(token);
        Date expiration = jwt.getExpiresAt();
        if (expiration == null) {
            return false; // Token doesn't have an expiration, consider it invalid
        }

        return expiration.after(new Date()); // Compare the expiration date with the current time
    }

    private void logOut() {
        // Clear token and expiration from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Toast.makeText(this, "Session timed out Log in Again", Toast.LENGTH_SHORT).show();

        // Redirect to the login activity
        Intent loginIntent = new Intent(this, LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        stopSelf(); // Stop the service
    }

}

