package me.sanura_njaka.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.Parse.Configuration;
import com.parse.ParseObject;

import me.sanura_njaka.parstagram.model.Post;

public class ParseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("sanura")
                .clientKey("s4000834")
                .server("http://sanura-njaka-parstagram.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }
}
