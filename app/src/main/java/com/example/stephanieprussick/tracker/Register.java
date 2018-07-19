package com.example.stephanieprussick.tracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.auth.SignInMethodQueryResult;

public class Register extends AppCompatActivity {

    EditText e3_email;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e3_email = (EditText) findViewById(R.id.editText3);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
    }

    public void nextClicked(View v)
    {
        Intent myIntent = new Intent(Register.this, Password.class);
        myIntent.putExtra("email", e3_email.getText().toString());
        startActivity(myIntent);

        CharSequence mssg = "Checking your e-mail address...";
        dialog.setMessage(mssg);
        dialog.show();

        // check if the email is already registered
        auth.fetchSignInMethodsForEmail(e3_email.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                if (task.isSuccessful())
                {
                    dialog.dismiss();
                    boolean check = !task.getResult().getSignInMethods().isEmpty();

                    if (!check)
                    {
                        // email does not exist so we can create new user
                        Intent myIntent = new Intent(Register.this, Password.class);
                        myIntent.putExtra("email", e3_email.getText().toString());
                        startActivity(myIntent);
                    }
                    else
                    {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Email already in use", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


    public void backClicked(View v)
    {
        Intent myIntent = new Intent(Register.this, MainActivity.class);
        startActivity(myIntent);
    }

}
