package com.example.instagram.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.instagram.Post;
import com.example.instagram.R;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

public class EditFragment extends ComposeFragment{
    // Uploading profile picture fragment should have same functionality as a post
    // except it saves to a different place

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // Remove the caption field when taking a profile photo
        etDescription = view.findViewById(R.id.etDescription);
        etDescription.setVisibility(View.GONE);
        ivPostImage = view.findViewById(R.id.ivPostImage);
        btnTakePicture = view.findViewById(R.id.btnTakePicture);
        btnSubmit = view.findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = etDescription.getText().toString();
                if(photoFile == null || ivPostImage.getDrawable() == null){
                    Toast.makeText(getContext(), "There is no image.", Toast.LENGTH_SHORT);
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(description, currentUser, photoFile);
            }
        });
        btnTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
            }
        });

    }

    @Override
    protected void savePost(String description, ParseUser currentUser, File photoFile){
        Log.i(TAG, "saving profile picture....");
        ParseUser parseUser = ParseUser.getCurrentUser();
        parseUser.put("profile_image", new ParseFile(photoFile));
        parseUser.saveInBackground(new SaveCallback() {

            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.e(TAG, "error saving profile picture: "+e);
                    Toast.makeText(getContext(), e+"", Toast.LENGTH_SHORT);
                    return;

                }else{
                    Log.i(TAG, "Profile picture upload successful");
                    etDescription.setText("");
                    ivPostImage.setImageResource(0);
                }
            }
        });
        // Return to the profile fragment once the submit button is pressed
        getActivity().onBackPressed();

    }

}
