package com.example.bussbuddy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class NittetopaduActivity extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;
    private List<TableRow> allRows;
    private ImageView img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nittetopadu);
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        img=findViewById(R.id.imgntp);
        allRows = new ArrayList<>();
        for (int i = 1; i <= 84; i++) {
            int id = getResources().getIdentifier("row" + i, "id", getPackageName());
            TableRow row = findViewById(id);
            allRows.add(row);
        }
img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent= new Intent(NittetopaduActivity.this, SelectActivity.class);
        startActivity(intent);
    }
});
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performSearch();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void performSearch() {
        String query = searchEditText.getText().toString().toLowerCase().trim();

        for (int i = 1; i <= 84; i++) {
            TableRow row = findViewById(getResources().getIdentifier("row" + i, "id", getPackageName()));
            if (row != null) {
                boolean found = false;
                for (int j = 0; j < row.getChildCount(); j++) {
                    View view = row.getChildAt(j);
                    if (view instanceof TextView) {
                        TextView textView = (TextView) view;
                        String text = textView.getText().toString().toLowerCase();
                        if (text.contains(query)) {
                            found = true;
                            break;
                        }
                    }
                }
                if (found) {
                    row.setVisibility(View.VISIBLE);
                } else {
                    row.setVisibility(View.GONE);
                }
            }
        }
    }
}