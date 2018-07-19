package com.example.stephanieprussick.tracker;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class NameActivity extends AppCompatActivity {

    String email, password;
    EditText e5_name;
    CircleImageView circleImageView;
    Uri resultUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        e5_name = (EditText)findViewById(R.id.editText5);
        circleImageView = (CircleImageView)findViewById(R.id.circleImageView);
        Intent myIntent = getIntent();
        if (myIntent != null)
        {
            email = myIntent.getStringExtra("email");
            password = myIntent.getStringExtra("password");
        }
    }

    public void generateCode(View v)
    {
        Date myDate = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
        String date = format1.format(myDate);
        Random r = new Random();
        int n = 10000 + r.nextInt(900000);
        String code = String.valueOf(n);
        if (resultUri != null)
        {
            Intent myIntent = new Intent(NameActivity.this, InviteCode.class);
            myIntent.putExtra("name", e5_name.getText().toString());
            myIntent.putExtra("email", email);
            myIntent.putExtra("password", password);
            myIntent.putExtra("date", myDate);
            myIntent.putExtra("isSharing", false);
            myIntent.putExtra("code", code);
            myIntent.putExtra("imageUri", resultUri);
            startActivity(myIntent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please choose an image", Toast.LENGTH_SHORT).show();
        }

    }

    public void selectImage(View v)
    {
        Intent i = new Intent();
        i.setAction(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        startActivityForResult(i, 12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 12 && resultCode == RESULT_OK && data!=null)
        {
// start picker to get image for cropping and then use the image in cropping activity
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                circleImageView.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }


    public void createClicked(View v)
    {
        Intent myIntent = new Intent(NameActivity.this, CreatePin.class);
        startActivity(myIntent);
    }
}
