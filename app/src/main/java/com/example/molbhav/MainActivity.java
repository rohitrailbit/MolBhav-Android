package com.example.molbhav;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.auth0.android.jwt.JWT;
import com.example.molbhav.databinding.ContentMainBinding;
import com.example.molbhav.service.TokenCheckService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.molbhav.databinding.ActivityMainBinding;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private static final long TOKEN_CHECK_INTERVAL = 60000 ; // 1 minute (adjust as needed)
    private Handler tokenCheckHandler;
    SharedPreferences sharedPreferences;
    String userDetails="", userType = "",jwtToken="";
    private static final String TOKEN_PREF_KEY = "authToken";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        View contentMain = findViewById(R.id.contentMain);

        userType = sharedPreferences.getString("userType","");
        jwtToken = sharedPreferences.getString("authToken","");



        BottomNavigationView bottomNavigationView = contentMain.findViewById(R.id.bottomNavigationView);

        if (userType.equalsIgnoreCase("ROLE_CUSTOMER")){

            // Inflate the new menu resource to replace the existing menu
            bottomNavigationView.getMenu().clear();  // Clear the existing menu
            bottomNavigationView.inflateMenu(R.menu.bottom_nav_drawer);  // Inflate the new menu

            // Assuming you have a reference to the NavHostFragment
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);

            assert navHostFragment != null;
            NavController navController = navHostFragment.getNavController();
            navController.setGraph(R.navigation.customer_navigation);
            NavigationUI.setupWithNavController(bottomNavigationView,navController);

        } else if (userType.equalsIgnoreCase("ROLE_VENDOR")){

            // Inflate the new menu resource to replace the existing menu
            bottomNavigationView.getMenu().clear();  // Clear the existing menu
            bottomNavigationView.inflateMenu(R.menu.vendor_bottom_nav_drawer);  // Inflate the new menu

            // Assuming you have a reference to the NavHostFragment
            NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);

            assert navHostFragment != null;
            NavController navController = navHostFragment.getNavController();
            navController.setGraph(R.navigation.vendor_navigation);
            NavigationUI.setupWithNavController(bottomNavigationView,navController);

        }

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        NavigationUI.setupWithNavController(bottomNavigationView,navController);



        // Check if the user is logged in and the token is valid
        if (isLoggedIn() && isTokenValid()) {
            startTokenExpirationCheck();
            // User is logged in, proceed with the main activity
        } else {
            // User is not logged in or token is invalid, redirect to login activity
            redirectToLogin();
        }

    }

    private boolean isLoggedIn() {
        String token = sharedPreferences.getString(TOKEN_PREF_KEY, null);
        return token != null && !token.isEmpty();
    }
    private void startTokenExpirationCheck() {
//        tokenCheckHandler = new Handler();
//        tokenCheckHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (!isLoggedIn() || !isTokenValid()) {
//                    // Token has expired or user logged out, log out the user
//                    redirectToLogin();
//                } else {
//                    // Token is still valid, schedule the next check
//                    tokenCheckHandler.postDelayed(this, TOKEN_CHECK_INTERVAL);
//                }
//            }
//        }, TOKEN_CHECK_INTERVAL);
        Intent serviceIntent = new Intent(this, TokenCheckService.class);
        this.startService(serviceIntent);


    }
    private void redirectToLogin() {
        // Clear token and expiration from shared preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Toast.makeText(this, "Session timed out Log in Again", Toast.LENGTH_SHORT).show();

        // Redirect to the login activity
        Intent loginIntent = new Intent(this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private boolean isTokenValid() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent serviceIntent = new Intent(this, TokenCheckService.class);
        this.stopService(serviceIntent);
    }
}