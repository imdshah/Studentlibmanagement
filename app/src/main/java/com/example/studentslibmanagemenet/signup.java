package com.example.studentslibmanagemenet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.lang.reflect.Array;

public class signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button register;
    TextInputLayout user,password,phone;
    String text;
    FirebaseDatabase fb;
    DatabaseReference reference;
    private View add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.des,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        register = findViewById(R.id.registerbutton);
        user = findViewById(R.id.username1text);
        password = findViewById(R.id.passwordtext);
        phone = findViewById(R.id.phoneno);


        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String user1 = user.getEditText().getText().toString();
                String p1 = password.getEditText().getText().toString();
                String a1 = phone.getEditText().getText().toString();
                String sp1 = spinner.getSelectedItem().toString();//value stored in sp1


                Log.d("CREATION", sp1);

                View selectedView = spinner.getSelectedView();
                TextView selectedTextView = null;
                if (selectedView != null && selectedView instanceof TextView) {
                    selectedTextView = (TextView) selectedView;
//                    selectedTextView.setError("Please select a value");

                    if (!user1.isEmpty()) {
                        user.setError(null);
                        user.setErrorEnabled(false);
                        if (!p1.isEmpty()) {
                            password.setError(null);
                            password.setErrorEnabled(false);
                            if (!a1.isEmpty()) {
                                phone.setError(null);
                                phone.setErrorEnabled(false);

                                fb = FirebaseDatabase.getInstance();
                                reference = fb.getReference("users");
                                String sp1_s = spinner.getSelectedItem().toString();
                                String user1_s = user.getEditText().getText().toString();
                                String p1_s = password.getEditText().getText().toString();
                                String a1_s = phone.getEditText().getText().toString();
                               // Storingdata storingdatass = new Storingdata(sp1_s, user1_s, p1_s, a1_s);
                               // reference.child(user1_s).setValue(storingdatass);
                                Toast.makeText(getApplicationContext(), "Insterted Successfully", Toast.LENGTH_SHORT).show();
                                Intent numbersIntent = new Intent(signup.this, dashboard.class);
                                startActivity(numbersIntent);
                            } else {
                                phone.setError("Please enter the phone no.");
                            }
                        } else {
                            password.setError("Please enter the password");
                        }
                    } else {
                        user.setError("Please enter the username");
                    }
                } else {
                    selectedTextView.setError("Please select a value");
                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}