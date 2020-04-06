package com.restaurant.advisor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.restaurant.advisor.R;
import androidx.appcompat.app.AppCompatActivity;

public class LandingScreen extends AppCompatActivity {
    private EditText mZipCode;
    private EditText mKeyword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);
        Button searchButton = findViewById(R.id.search_button);
        mZipCode = findViewById(R.id.etpincode);
        mKeyword = findViewById(R.id.etkeyword);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent = new Intent(LandingScreen.this, SearchResultScreen.class);
                listIntent.putExtra("zipcode", mZipCode.getText().toString());
                listIntent.putExtra("keyword",mKeyword.getText().toString());
                startActivity(listIntent);
            }
        });

    }
}
