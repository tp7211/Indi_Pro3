package com.example.indi_pro3;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.indi_pro3.Dialog.Alert_dialog;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class CheckoutActivity extends AppCompatActivity {
    public Toolbar toolbar;
    Button btn_next;
    private String qty = "100";
    private RadioGroup radioGroup;
    private DataBaseHelper dataBaseHelper;
    private LinearLayout ll_online;
    private LinearLayout rl_cod;
    private boolean isFromCard = false;
    TextInputEditText edtName, edtMobile, editCardNumber, edtmobile;
    EditText editNameOnCard, editExpiryDate, editCVVCode;
    CircleImageView imageView;
    String name, mobile, cardNumber, nameOnCard, expiryDate, cvvCode, mobileno;

    @Override
    public boolean navigateUpTo(Intent upIntent) {
        return super.navigateUpTo(upIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("qty")) {
            qty = getIntent().getStringExtra("qty");
        }
        dataBaseHelper = new DataBaseHelper(this);
        setContentView(R.layout.activity_checkout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Check Out");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        btn_next = findViewById(R.id.btn_next);
        edtmobile = findViewById(R.id.edtmobile);
        ll_online = findViewById(R.id.ll_online);
        imageView = findViewById(R.id.profile_image);
        rl_cod = findViewById(R.id.llcod);
        radioGroup = findViewById(R.id.radioGroup);
        edtName = findViewById(R.id.edtName);
        edtMobile = findViewById(R.id.edtaddress);
        editCardNumber = findViewById(R.id.edtcardNo);
        editNameOnCard = findViewById(R.id.edtNameCard);
        editExpiryDate = findViewById(R.id.expiryDate);
        editCVVCode = findViewById(R.id.cvvCode);

        imageView.setImageResource(TAGS.MEDS.getImageId());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.online:
                        isFromCard = true;
                        ll_online.setVisibility(View.VISIBLE);
                        rl_cod.setVisibility(View.GONE);
                        break;
                    case R.id.cod:
                        isFromCard = false;
                        ll_online.setVisibility(View.GONE);
                        rl_cod.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edtName.getText().toString();
                mobile = edtMobile.getText().toString();
                mobileno = edtmobile.getText().toString();
                cardNumber = editCardNumber.getText().toString();
                nameOnCard = editNameOnCard.getText().toString();
                expiryDate = editExpiryDate.getText().toString();
                cvvCode = editCVVCode.getText().toString();


                if (TextUtils.isEmpty(name)) {
                    new Alert_dialog(CheckoutActivity.this).show("Alert", "Name mandatory.", "OK", null);
                } else if (TextUtils.isEmpty(mobile)) {
                    new Alert_dialog(CheckoutActivity.this).show("Alert", "Address mandatory.", "OK", null);
                } else if (TextUtils.isEmpty(mobile)) {
                    new Alert_dialog(CheckoutActivity.this).show("Alert", "Address mandatory.", "OK", null);
                } else if (TextUtils.isEmpty(mobileno)) {
                    new Alert_dialog(CheckoutActivity.this).show("Alert", "Mobile no mandatory.", "OK", null);
                } else if (isFromCard && TextUtils.isEmpty(cardNumber)) {
                    new Alert_dialog(CheckoutActivity.this).show("Alert", "Card no mandatory.", "OK", null);
                } else if (isFromCard && (cardNumber.length() > 16 || cardNumber.length() < 16)) {
                    new Alert_dialog(CheckoutActivity.this).show("Alert", "Card no must be 16 digit.", "OK", null);
                } else if (isFromCard && TextUtils.isEmpty(expiryDate)) {
                    new Alert_dialog(CheckoutActivity.this).show("Alert", "Date mandatory.", "OK", null);
                } else if (isFromCard && TextUtils.isEmpty(cvvCode)) {
                    new Alert_dialog(CheckoutActivity.this).show("Alert", "CVV no mandatory.", "OK", null);
                } else {
                    new Alert_dialog(CheckoutActivity.this).show("Alert!", "Order placed successfully", "OK", new DoInBackGroundCaller() {
                        @Override
                        public void doTask() {
                            adSuccess();
                        }

                        @Override
                        public void doTaskComplete() {
                        }
                    });
                }
            }
        });
    }

    private void adSuccess() {
        dataBaseHelper.insertData(new ItemMed("",
                TAGS.MEDS.getName(),
                TAGS.MEDS.getPrice(),
                qty,
                TAGS.MEDS.getSize(),
                TAGS.MEDS.getPower(),
                TAGS.MEDS.getAgeGroup(),
                TAGS.MEDS.getDescription1(),
                TAGS.MEDS.getDescription2(),
                TAGS.MEDS.getImageId()));


        Intent intent = new Intent(CheckoutActivity.this, MainActivity.class);
        startActivity(intent);
    }
}