package com.example.hibsa;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DescriptionActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        titleTextView = findViewById(R.id.textViewTitle);
        descriptionTextView = findViewById(R.id.textViewDescription);
        imageView = findViewById(R.id.imageViewCar);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            String brand = bundle.getString("brand");
            String model = bundle.getString("model");
            int year = bundle.getInt("year");
            String description = bundle.getString("description");

            String image = "drawable/" + brand.toLowerCase() + model.toLowerCase() + year;
            int imageId = getResources().getIdentifier(image, "drawable", getPackageName());

            loadImage(imageId);

            titleTextView.setText(brand + " " + model + " " + year);
            descriptionTextView.setText(description);
        }

    }

    private void loadImage(int imageId) {

        if (imageId != 0) {
            imageView.setImageResource(imageId);
        } else {
            imageView.setImageResource(R.drawable.generic);
        }

    }
}