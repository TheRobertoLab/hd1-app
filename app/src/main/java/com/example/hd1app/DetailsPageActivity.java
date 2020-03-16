package com.example.hd1app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hd1app.Models.Deal;

public class DetailsPageActivity extends AppCompatActivity {
    Deal clickedDeal = null;
    TextView dealTitle;
    ImageView dealImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        clickedDeal = MoreDealsActivity.thisItem;
        dealImage = findViewById(R.id.deal_image);
        dealTitle = findViewById(R.id.deal_title);
        initDetailsPage(dealImage,dealTitle);
    }
    public void initDetailsPage(ImageView dealImage, TextView dealTitle){
        dealImage.setImageResource(clickedDeal.getImage());
        dealTitle.setText(clickedDeal.getTitle());
    }
    public void handleMapPress (View v) {
        Intent intent = new Intent(DetailsPageActivity.this, MapActivity.class);
        startActivity(intent);
    }
}
