package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.parceler.Parcels;

public class PostDetailActivity extends AppCompatActivity {

    Post post;

    private TextView tvUsernameTop, tvUsernameBottom;
    private ImageView ivImage;
    private TextView tvDescription, tvRelativeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        tvUsernameTop = findViewById(R.id.tvUsernameTop);
        tvUsernameBottom = findViewById(R.id.tvUsernameBottom);
        ivImage = findViewById(R.id.ivImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvRelativeTime = findViewById(R.id.tvRelativeTime);

        // Unwrap post data sent by FeedActivity
        post = getIntent().getParcelableExtra("POST DETAILS");
        Log.i("PostDetailActivity", "showing details for post "+post.getDescription());

        tvDescription.setText(post.getDescription());
        tvUsernameTop.setText(post.getUser().getUsername());
        tvUsernameBottom.setText(post.getUser().getUsername());

        Glide.with(this).load(post.getImage().getUrl()).into(ivImage);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}