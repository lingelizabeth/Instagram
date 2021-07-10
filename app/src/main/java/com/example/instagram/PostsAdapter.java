package com.example.instagram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private Context context;
    private List<Post> allPosts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.allPosts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = allPosts.get(position);
        holder.bind(post);
    }

    // Methods for pull to refresh
    // Clean all elements of the recycler
    public void clear() {
        allPosts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        allPosts.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return allPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvUsernameTop, tvUsernameBottom;
        private ImageView ivImage, ivProfileImage;
        private TextView tvDescription, tvRelativeTime, tvNumLikes;
        private Button btnLike, btnComment, btnShare, btnSave, btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsernameTop = itemView.findViewById(R.id.tvUsernameTop);
            tvUsernameBottom = itemView.findViewById(R.id.tvUsernameBottom);
            ivImage = itemView.findViewById(R.id.ivImage);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvRelativeTime = itemView.findViewById(R.id.tvRelativeTime);
            tvNumLikes = itemView.findViewById(R.id.tvNumLikes);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnComment = itemView.findViewById(R.id.btnComment);
            btnSave = itemView.findViewById(R.id.btnSave);
            btnShare = itemView.findViewById(R.id.btnShare);
            btnMore = itemView.findViewById(R.id.btnMore);

            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUsernameTop.setText(post.getUser().getUsername());
            tvUsernameBottom.setText(post.getUser().getUsername());
            tvRelativeTime.setText(post.getRelativeTimeAgo());
            tvNumLikes.setText(post.getNumLikes()+" likes");

            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num_likes = post.getNumLikes(); // calling once ensures a consistent value for both of the following calls
                    post.setNumLikes(num_likes+1);
                    Log.i("PostsAdapter", "like button pressed, post has "+post.getNumLikes()+" likes");
                    tvNumLikes.setText((num_likes+1)+" likes");
                }
            });

            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }

            Glide.with(context).load(post.getProfileImageUrl()).circleCrop().into(ivProfileImage);
        }

        @Override
        public void onClick(View view){
            int position = getAdapterPosition();
            // If clicked position is valid
            if (position != RecyclerView.NO_POSITION) {
                // Get post at the current position
                Post post = allPosts.get(position);

                // Create intent and add extras
                Intent i = new Intent(context, PostDetailActivity.class);
                i.putExtra("POST DETAILS", post); // ParseObjects already implement parcelable
                // Creating transitions for each element of post
                Pair<View, String> pair1 = new Pair<View, String>(ivImage, "image");
                Pair<View, String> pair2 = new Pair<View, String>(tvUsernameTop, "usernameTop");
                Pair<View, String> pair3 = new Pair<View, String>(tvUsernameBottom, "usernameBottom");
                Pair<View, String> pair4 = new Pair<View, String>(tvDescription, "description");
                Pair<View, String> pair5 = new Pair<View, String>(btnLike, "likeButton");
                Pair<View, String> pair6 = new Pair<View, String>(btnComment, "commentButton");
                Pair<View, String> pair7 = new Pair<View, String>(btnSave, "saveButton");
                Pair<View, String> pair8 = new Pair<View, String>(btnShare, "shareButton");
                Pair<View, String> pair9 = new Pair<View, String>(tvRelativeTime, "relativeTime");
                Pair<View, String> pair10 = new Pair<View, String>(ivProfileImage, "profileImage");
                Pair<View, String> pair11 = new Pair<View, String>(tvNumLikes, "likes");
                Pair<View, String> pair12 = new Pair<View, String>(btnMore, "moreButton");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context, pair1, pair2, pair3,pair4, pair5, pair6, pair7,pair8, pair9, pair10, pair11, pair12);
                context.startActivity(i, options.toBundle());
            }
        }
    }
}
