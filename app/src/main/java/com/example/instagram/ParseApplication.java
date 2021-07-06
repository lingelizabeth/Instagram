package com.example.instagram;

import com.parse.Parse;
import android.app.Application;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("C3sL6FrFRqAeXBamkidLPSNQUHVEt3Y2xhVmKJod")
                .clientKey("Nwt1IHIRyTnVwKSfFQObRN6gwmO0osj3WxOvlPC6")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}