package com.android.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.assignment.R;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageView imageView=findViewById(R.id.getimage);
        Picasso.get().load(getIntent().getStringExtra("images")).into(imageView);
        TextView textView=findViewById(R.id.details);
        textView.setText(getIntent().getStringExtra("details"));

    }
}
