package com.example.practice;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mfirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil firebaseutil;
    public static ArrayList<Booking> booking;

    private FirebaseUtil() {};

    public static void openFbReference (String ref)
    {
        if(firebaseutil == null)
        {
            firebaseutil = new FirebaseUtil();
            mfirebaseDatabase = FirebaseDatabase.getInstance();
            booking = new ArrayList<Booking>();
        }
        mDatabaseReference = mfirebaseDatabase.getReference().child(ref);

    }

}
