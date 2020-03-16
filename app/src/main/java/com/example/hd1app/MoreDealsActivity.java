package com.example.hd1app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hd1app.Models.Deal;

import java.util.ArrayList;

public class MoreDealsActivity extends AppCompatActivity {
    TextView topText;
    TextView dealQuantity;
    ArrayList<Deal> fashionDeals;
    ArrayList<Deal> electronicsDeals;
    ArrayList<Deal> foodDeals;
    RecyclerView recyclerView;
    ImageView coverImg;
    ImageView cancelButton;
    EditText searchBar;
    public static Deal thisItem = null;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_deals);

        coverImg = findViewById(R.id.cover_image);
        topText = findViewById(R.id.top_text);
        recyclerView = findViewById(R.id.more_deals_recycler);
        cancelButton = findViewById(R.id.cancel_Button);
        searchBar = findViewById(R.id.editText);
        dealQuantity = findViewById(R.id.deal_quantity);

        intent = getIntent();
        topText.setText(intent.getStringExtra("category_title"));

        fashionDeals = HomePage.fashionDeals;
        foodDeals = HomePage.foodDeals;
        electronicsDeals = HomePage.electronicsDeals;

        initRecyclerView(intent);

    }
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();

            if (intent.getStringExtra("category_title").equals("Fashion")) {
                thisItem = fashionDeals.get(position);
            } else if (intent.getStringExtra("category_title").equals("Electronics")) {
                thisItem = electronicsDeals.get(position);
            } else if (intent.getStringExtra("category_title").equals("Food & Drink")) {
                thisItem = foodDeals.get(position);
            } else if (intent.getStringExtra("category_title").equals("Travel")) {
                thisItem = fashionDeals.get(position);
            } else if (intent.getStringExtra("category_title").equals("Entertainment")) {
                thisItem = fashionDeals.get(position);
            } else if (intent.getStringExtra("category_title").equals("Education")) {
                thisItem = fashionDeals.get(position);
            }
            Intent intent = new Intent(MoreDealsActivity.this, DetailsPageActivity.class);
            startActivity(intent);
            //Toast.makeText(MoreDealsActivity.this, "You Clicked: " + thisItem.getTitle(), Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

    public void fillRecyclerDealList(ArrayList<Deal> dealList, RecyclerView recyclerView){
        CustomAdapter adapter = new CustomAdapter(this, dealList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.setOnItemClickListener(onItemClickListener);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 20, false));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    @SuppressLint("SetTextI18n")
    public void initRecyclerView(Intent intent) {
        if (intent.getStringExtra("category_title").equals("Fashion")) {
            fillRecyclerDealList(fashionDeals, recyclerView);
            dealQuantity.setText("We've found "+ fashionDeals.size()+" deals for you.");
            coverImg.setImageResource(R.drawable.topshopcover);
        } else if (intent.getStringExtra("category_title").equals("Electronics")) {
            fillRecyclerDealList(electronicsDeals, recyclerView);
            coverImg.setImageResource(R.drawable.applecover);
            dealQuantity.setText("We've found "+ electronicsDeals.size()+" deals for you.");
        } else if (intent.getStringExtra("category_title").equals("Food & Drink")) {
            fillRecyclerDealList(foodDeals, recyclerView);
            coverImg.setImageResource(R.drawable.coverimg);
            dealQuantity.setText("We've found "+ foodDeals.size()+" deals for you.");
        } else if (intent.getStringExtra("category_title").equals("Travel")) {
//            fillRecyclerDealList(foodDeals, recyclerView);
              coverImg.setImageResource(R.drawable.nodeals);
        } else if (intent.getStringExtra("category_title").equals("Entertainment")) {
            //fillRecyclerDealList(foodDeals, recyclerView);
            coverImg.setImageResource(R.drawable.nodeals);
        } else if (intent.getStringExtra("category_title").equals("Education")) {
            //fillRecyclerDealList(foodDeals, recyclerView);
            coverImg.setImageResource(R.drawable.nodeals);
        }
    }
    public static void hideSoftKeyboard (Activity activity, View view) {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }
    public void cancelPressed (View v) {
        searchBar.clearFocus();
        hideSoftKeyboard(MoreDealsActivity.this, v);
    }
}
