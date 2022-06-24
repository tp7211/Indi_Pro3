package com.example.indi_pro3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.indi_pro3.Dialog.Confirm_dialog;

import org.jetbrains.annotations.NotNull;

public class About extends AppCompatActivity implements View.OnClickListener{
    Button skip, next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        skip = findViewById(R.id.skip);
        next = findViewById(R.id.next);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        skip.setOnClickListener(this);
        next.setOnClickListener(this);
    }
    public void onClick(@NotNull View v) {

        switch (v.getId()) {
            case R.id.skip:

                new Confirm_dialog(this).show("Exit App!", "Are you sure you wan't to exit?", "Yes", "No", new DoInBackGroundCaller() {
                    @Override
                    public void doTask() {
                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory(Intent.CATEGORY_HOME);
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homeIntent);
                    }

                    @Override
                    public void doTaskComplete() {

                    }
                }, new DoInBackGroundCaller() {
                    @Override
                    public void doTask() {

                    }

                    @Override
                    public void doTaskComplete() {

                    }
                });
                break;
            case R.id.next:
                startActivity(new Intent(About.this, MainActivity.class));
                break;


        }

    }
}