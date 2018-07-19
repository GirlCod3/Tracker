package com.example.stephanieprussick.tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePin extends AppCompatActivity {

    EditText newPin, confirmPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pin);
        newPin = (EditText)findViewById(R.id.editText7);
        confirmPin = (EditText)findViewById(R.id.editText9);
    }

    public void nextClicked(View v)
    {
//        if (newPin.getText().toString() != confirmPin.getText().toString())
//        {
//            Toast.makeText(getApplicationContext(), "PINs do not match. Try again", Toast.LENGTH_SHORT).show();
//        }
//        else {
            // if pin is not 4 numbers in a row

            int counter = 0;
            for(int i=0; i < newPin.length(); i++)
            {
                if (Character.isDigit(i))
                {
                    counter++;
                }

            }
            if (counter == 4)
            {
                // TODO
            }
            else {
                Toast.makeText(getApplicationContext(), "PIN must be 4 numbers", Toast.LENGTH_SHORT).show();
            }
       // }
    }
}
