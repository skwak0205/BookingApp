package com.example.practice;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    ArrayList<Booking> booking;
    //connect firebase
    private FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //set database connection

        FirebaseUtil.openFbReference("booking");
        mfirebaseDatabase = FirebaseUtil.mfirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        //mfirebaseDatabase = FirebaseDatabase.getInstance();
        //mDatabaseReference = mfirebaseDatabase.getReference().child("booking");
        firebaseAuth = FirebaseAuth.getInstance();
        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                TextView listBook = (TextView) findViewById(R.id.listBooking);
                TextView userName = (TextView) findViewById(R.id.txtuserName);
                Booking book = dataSnapshot.getValue(Booking.class);
                booking = FirebaseUtil.booking;
                booking.add(book);
                userName.setText(user.getDisplayName());
                if(user.getDisplayName().equals("admin"))
                {
                    listBook.setText( listBook.getText() + "\n Customer Name: " + book.getId() +"\n Booking  Date: "+book.getDate()+ "\n  Start   Time: " +
                            book.getStartTime()+ "\n  End     Time: " + book.getEndTime()+ "\n No. of  People: " + book.getNumOfPeople()
                            + "\n Cus  Comments: " + book.getComments() + "\n");
                }
                else
                {
                    for(int i = 0; i < booking.size(); i++)
                    {
                        if(booking.get(i).getId().contains(user.getDisplayName()))
                        {
                            listBook.setText( "\n Customer Name: " + booking.get(i).getId() +"\n Booking  Date: " + booking.get(i).getDate()+
                                    "\n  Start   Time: " + booking.get(i).getStartTime()
                                    + "\n  End     Time: " + booking.get(i).getEndTime()
                                    + "\n No. of  People: " + booking.get(i).getNumOfPeople()
                                    + "\n Cus  Comments: " + booking.get(i).getComments() + "\n");
                        }
                    }
                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListener);

        /*
        // create ref for recycle view
        RecyclerView listBook = (RecyclerView) findViewById(R.id.listBook);
        final BookAdapter adapter = new BookAdapter();
        listBook.setAdapter(adapter);
        LinearLayoutManager bookLayoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listBook.setLayoutManager(bookLayoutManager);
        */
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(ListActivity.this, MainActivity.class);
                    startActivity(intentHome);
                    //mTextMessage.setText("Welcome");
                    return true;
                case R.id.navigation_reservation:
                    //mTextMessage.setText(R.string.title_Reservation);
                    Intent intent = new Intent(ListActivity.this, BookingActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_notifications:
                    Intent intentHome2 = new Intent(ListActivity.this, MainActivity.class);
                    startActivity(intentHome2);
                    return true;
                case R.id.navigation_reserveHistory:
                    return true;
            }
            return false;
        }
    };
}
