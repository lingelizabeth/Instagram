package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

public class PostDetailActivity extends AppCompatActivity {

    Post post;

    private TextView tvUsernameTop, tvUsernameBottom;
    private ImageView ivImage, ivProfileImage;
    private TextView tvDescription, tvRelativeTime, tvNumLikes;
    private Button btnLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        tvUsernameTop = findViewById(R.id.tvUsernameTop);
        tvUsernameBottom = findViewById(R.id.tvUsernameBottom);
        ivImage = findViewById(R.id.ivImage);
        ivProfileImage = findViewById(R.id.ivProfileImage);
        tvDescription = findViewById(R.id.tvDescription);
        tvRelativeTime = findViewById(R.id.tvRelativeTime);
        tvNumLikes = findViewById(R.id.tvNumLikes);
        btnLike = findViewById(R.id.btnLike);

        // Unwrap post data sent by FeedActivity
        post = getIntent().getParcelableExtra("POST DETAILS");
        Log.i("PostDetailActivity", "showing details for post "+post.getDescription());

        tvDescription.setText(post.getDescription());
        tvUsernameTop.setText(post.getUser().getUsername());
        tvUsernameBottom.setText(post.getUser().getUsername());
        tvRelativeTime.setText(post.getRelativeTimeAgo());
        tvNumLikes.setText(post.getNumLikes()+" likes");

        Glide.with(this).load(post.getImage().getUrl()).into(ivImage);
        Glide.with(this).load(post.getProfileImageUrl()).circleCrop().into(ivProfileImage);

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num_likes = post.getNumLikes(); // calling once ensures a consistent value for both of the following calls
                post.setNumLikes(num_likes+1);
                tvNumLikes.setText((num_likes+1)+" likes");
            }
        });

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