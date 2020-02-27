package com.example.practice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class SandwichActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.foodmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item_burger:
                Intent intent = new Intent(this, FoodMenuActivity.class);
                startActivity(intent);
                return true;
            case R.id.item_Sandwitch:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(SandwichActivity.this, MainActivity.class);
                    startActivity(intentHome);
                    //mTextMessage.setText("Welcome");
                    return true;
                case R.id.navigation_reservation:
                    //mTextMessage.setText(R.string.title_Reservation);
                    Intent intent = new Intent(SandwichActivity.this, BookingActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    return true;
                case R.id.navigation_reserveHistory:
                    Intent intentHome2 = new Intent(SandwichActivity.this, ListActivity.class);
                    startActivity(intentHome2);
                    return true;
            }
            return false;
        }
    };
}
