package com.example.practice;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookingActivity extends AppCompatActivity {

    //private Spinner startTime, endTime;
    TimePickerDialog timePickerDialog;
    DatePickerDialog datePickerDialog;
    EditText datePick;
    EditText startTime1, endTime1, people, comments;
    Button submit;
    FirebaseAuth firebaseAuth;

    //connect firebase
    private FirebaseDatabase mfirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        firebaseAuth = FirebaseAuth.getInstance();

        people = (EditText) findViewById(R.id.txtpeople);
        comments = (EditText) findViewById(R.id.txtComments);
        submit = (Button) findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           saveBooking();
                Intent intent1 = new Intent(BookingActivity.this, ListActivity.class);
                startActivity(intent1);
                Toast.makeText(BookingActivity.this, "Booking Saved", Toast.LENGTH_SHORT).show();
                clean();

            }
        });

        //set database connection
        mfirebaseDatabase = FirebaseUtil.mfirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;

        //mfirebaseDatabase = FirebaseDatabase.getInstance();
        //mDatabaseReference = mfirebaseDatabase.getReference().child("booking");

        //startTime = (Spinner) findViewById(R.id.startTime);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //startTime.setAdapter(adapter);



        //endTime = (Spinner) findViewById(R.id.endTime);
        //endTime.setAdapter(adapter);

        //date function
        datePick = (EditText) findViewById(R.id.dateChoose);
        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                       datePick.setText(year+" - " + (month+1)+ " - " + dayOfMonth);
                    }
                },2019,3,1);
                datePickerDialog.show();
            }
        });

        endTime1 = (EditText) findViewById(R.id.endTimeChoose);
        endTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                timePickerDialog = new TimePickerDialog(BookingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        endTime1.setText(hourOfDay + ":" + minute);
                    }
                }, 0,0,false);
                timePickerDialog.show();
            }
        });

        startTime1 = (EditText) findViewById(R.id.startTimeChoose);
        startTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                timePickerDialog = new TimePickerDialog(BookingActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        startTime1.setText(hourOfDay + ":" + minute);
                    }
                }, 0,0,false);
                timePickerDialog.show();
            }
        });

    }

    private void saveBooking() {
        String date = datePick.getText().toString();
        String start = startTime1.getText().toString();
        String end = endTime1.getText().toString();
        String noPeople = people.getText().toString();
        String comm = comments.getText().toString();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String id = user.getDisplayName();
        Booking book = new Booking(id, date, start, end, noPeople, comm);
        mDatabaseReference.push().setValue(book);

    }
    private void clean() {
        datePick.setText("");
        startTime1.setText("");
        endTime1.setText("");
        people.setText("");
        comments.setText("");
        datePick.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item_signOut:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
