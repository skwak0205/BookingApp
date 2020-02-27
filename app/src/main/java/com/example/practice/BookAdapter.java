package com.example.practice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.DealViewHolder>{
    ArrayList<Booking> booking;
    //connect firebase
    private FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;
    FirebaseAuth firebaseAuth;

    public BookAdapter()
    {
        FirebaseUtil.openFbReference("booking");
        mfirebaseDatabase = FirebaseUtil.mfirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
        booking = FirebaseUtil.booking;
        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                Booking book = dataSnapshot.getValue(Booking.class);
                Log.d("booking", book.getDate());
                book.setId(dataSnapshot.getKey());
                booking.add(book);
                notifyItemInserted(booking.size()-1);
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
    }

    @Override
    public DealViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.rv_row, parent, false);
        return new DealViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DealViewHolder dealViewHolder, int i) {
        Booking b = booking.get(i);
        dealViewHolder.bind(b);
    }

    @Override
    public int getItemCount() {
        return booking.size();
    }

    public class DealViewHolder extends RecyclerView.ViewHolder {
        TextView row1;
        TextView row2;
        TextView row3;
        TextView row4;
        TextView row5;
        TextView row6;
        public DealViewHolder(@NonNull View itemView) {
            super(itemView);
            row1 = (TextView) itemView.findViewById(R.id.book1);
            row2 = (TextView) itemView.findViewById(R.id.book2);
            row3 = (TextView) itemView.findViewById(R.id.book3);
            row4 = (TextView) itemView.findViewById(R.id.book4);
            row5 = (TextView) itemView.findViewById(R.id.book5);
            row6 = (TextView) itemView.findViewById(R.id.book6);

        }
        public void bind (Booking book)
        {
            row1.setText(book.getDate());
            row2.setText(book.getStartTime());
            row3.setText(book.getEndTime());
            row4.setText(book.getNumOfPeople());
            row5.setText(book.getComments());

        }

    }
}
