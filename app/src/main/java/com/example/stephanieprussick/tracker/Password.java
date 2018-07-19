
package com.example.stephanieprussick.tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Password extends AppCompatActivity {

    String email;
    EditText e4_password;
    EditText confirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        e4_password = (EditText)findViewById(R.id.editText4);
        confirmPassword = (EditText)findViewById(R.id.editText6);
        Intent myIntent = getIntent();
        if (myIntent != null)
        {
            email = myIntent.getStringExtra("email");
        }
    }

    public void nextClicked(View v) {
        if (!(e4_password.getText().toString().equals(confirmPassword.getText().toString()))) {
            System.out.println("passwords are not equal!");
            Toast.makeText(getApplicationContext(), "Passwords do not match. Try again", Toast.LENGTH_SHORT).show();
        } else {
            if (e4_password.getText().toString().length() > 6) {
                Intent myIntent = new Intent(Password.this, NameActivity.class);
                myIntent.putExtra("email", email);
                myIntent.putExtra("password", e4_password.getText().toString());
                startActivity(myIntent);
            } else {
                Toast.makeText(getApplicationContext(), "Password length should be more than 6 characters", Toast.LENGTH_SHORT).show();
            }
            //  }
        }
    }
}
