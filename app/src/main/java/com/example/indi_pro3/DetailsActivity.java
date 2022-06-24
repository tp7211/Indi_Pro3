package com.example.indi_pro3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity {
    public Toolbar toolbar;
    CircleImageView profile_image;
    private TextView name, power, age, priceText, qtyText, totalPriceText,price;
    private LinearLayout priceLayout, spinnerLayout;
    private Spinner spinner;
    Button btn_next;
    private DataBaseHelper dataBaseHelper;
    private String qty = "100";

    String[] qtyArray = new String[]{"100", "150", "200", "250", "300", "400", "500", "1000"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBaseHelper = new DataBaseHelper(this);
        setContentView(R.layout.activity_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        profile_image = findViewById(R.id.profile_image);
        name = findViewById(R.id.name);
        power = findViewById(R.id.power);
        age = findViewById(R.id.age);
//        priceText = findViewById(R.id.priceText);
        price = findViewById(R.id.price);
        qtyText = findViewById(R.id.qty);
        totalPriceText = findViewById(R.id.totalPrice);
        priceLayout = findViewById(R.id.priceLayout);
        spinnerLayout = findViewById(R.id.spinnerLayout);
        btn_next = findViewById(R.id.btn_next);
        spinner = findViewById(R.id.spinner);
        toolbar.setTitle("Details");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        profile_image.setImageResource(TAGS.MEDS.getImageId());
        name.setText("Medicine Name : " + TAGS.MEDS.getName());
        power.setText("Power : " + TAGS.MEDS.getPower());
        age.setText("Age Restriction : " + TAGS.MEDS.getAgeGroup());
        price.setText("Price : " + TAGS.MEDS.getPrice() );


        if (TAGS.isFromOrders) {
            spinnerLayout.setVisibility(View.GONE);
            priceLayout.setVisibility(View.VISIBLE);
            btn_next.setText("Cancel Order");
//            btn_next.getBackground().setTint(getResources().getColor(R.color.red));

            StringBuilder stringBuilder = new StringBuilder();

//            priceText.setText("Price: " + TAGS.MEDS.getPrice());
            qtyText.setText("Qty: " + TAGS.MEDS.getQty());
            totalPriceText.setText("Total Pay: " + TAGS.getMultiplyPrice(TAGS.MEDS.getPrice(), TAGS.MEDS.getQty()));

        } else {

            String hexString = "#14B19E";
            btn_next.setText("Check Out");
            btn_next.getBackground().setTint(Color.parseColor(hexString));

            spinnerLayout.setVisibility(View.VISIBLE);
            priceLayout.setVisibility(View.GONE);

            spinner.setAdapter(new ArrayAdapter<String>(DetailsActivity.this, android.R.layout.simple_spinner_dropdown_item, qtyArray));
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    qty = qtyArray[position];
                    //price.setText(TAGS.getMultiplyPrice(TAGS.product.getPrice(), arrayOrQty[position]));

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TAGS.isFromOrders) {
                    dataBaseHelper.deleteData(TAGS.MEDS.getId());
                    onBackPressed();
                } else {
                    Intent intent = new Intent(DetailsActivity.this, CheckoutActivity.class).putExtra("qty", qty);
                    startActivity(intent);
                }
            }
        });

    }
}