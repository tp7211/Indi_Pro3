package com.example.indi_pro3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indi_pro3.Dialog.Alert_dialog;

public class Loginactivity extends AppCompatActivity implements View.OnClickListener {
    ImageView ivgoogle, ivfacebook, ivlinkdin, ivtwitter;
    Button btnsignin;
    EditText etmobileno, etpassword;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        ivgoogle = findViewById(R.id.ivgoogle);
        ivfacebook = findViewById(R.id.ivfacebook);
        ivlinkdin = findViewById(R.id.ivlinkdin);
        ivtwitter = findViewById(R.id.ivtwitter);
//        cbsignin = findViewById(R.id.cbsignin);
        btnsignin = findViewById(R.id.btnsignin);
//        tvcreateaccount = findViewById(R.id.tvcreateaccount);
        etmobileno = findViewById(R.id.etmobileno);
        etpassword = findViewById(R.id.etpassword);


        ivgoogle.setOnClickListener(this);
        ivfacebook.setOnClickListener(this);
        ivlinkdin.setOnClickListener(this);
        ivtwitter.setOnClickListener(this);
//        cbsignin.setOnClickListener(this);
        btnsignin.setOnClickListener(this);
//        tvcreateaccount.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ivgoogle:
                new Alert_dialog(Loginactivity.this).show("Alert!", "Sucessfully signin with gmail.", "OK", new DoInBackGroundCaller() {
                    @Override
                    public void doTask() {
                        startActivity(new Intent(Loginactivity.this, About.class));
                    }

                    @Override
                    public void doTaskComplete() {

                    }
                });

                break;
            case R.id.ivfacebook:
                new Alert_dialog(Loginactivity.this).show("Alert!", "Sucessfully signin with facebook.", "OK", new DoInBackGroundCaller() {
                    @Override
                    public void doTask() {
                        startActivity(new Intent(Loginactivity.this, About.class));
                    }

                    @Override
                    public void doTaskComplete() {

                    }
                });
                break;
            case R.id.ivtwitter:
                new Alert_dialog(Loginactivity.this).show("Alert!", "Sucessfully signin with twitter.", "OK", new DoInBackGroundCaller() {
                    @Override
                    public void doTask() {
                        startActivity(new Intent(Loginactivity.this, About.class));
                    }

                    @Override
                    public void doTaskComplete() {

                    }
                });
                break;
            case R.id.ivlinkdin:
                new Alert_dialog(Loginactivity.this).show("Alert!", "Sucessfully signin with linkdin.", "OK", new DoInBackGroundCaller() {
                    @Override
                    public void doTask() {
                        startActivity(new Intent(Loginactivity.this, About.class));
                    }

                    @Override
                    public void doTaskComplete() {

                    }
                });
                break;

            case R.id.btnsignin:
                username = etmobileno.getText().toString().trim();
                password = etpassword.getText().toString().trim();
                if (TextUtils.isEmpty(username)) {
                    new Alert_dialog(Loginactivity.this).show("Alert!", "Invalid Username or Password,\n Please enter valid detail!", "OK", null);
                } else if (TextUtils.isEmpty(password)) {
                    new Alert_dialog(Loginactivity.this).show("Alert!", "Invalid Username or Password,\n Please enter valid detail!", "OK", null);
                } else {
                    startActivity(new Intent(Loginactivity.this, About.class));
                }
                break;


        }

    }
}