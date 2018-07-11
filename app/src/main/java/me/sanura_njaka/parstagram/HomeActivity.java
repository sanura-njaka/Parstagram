package me.sanura_njaka.parstagram;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;
import java.util.List;

import me.sanura_njaka.parstagram.model.Post;

public class HomeActivity extends AppCompatActivity {

    private Button homeButton;
    private Button createButton;
    private Button profileButton;
    private FrameLayout flContainer;

    Fragment timelineFragment = new TimelineFragment();
    Fragment createFragment = new CreateFragment();
    Fragment profileFragment = new ProfileFragment();

    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeButton = findViewById(R.id.btnHome);
        createButton = findViewById(R.id.btnCreate);
        profileButton = findViewById(R.id.btnProfile);
        flContainer = findViewById(R.id.flPlaceholder);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.replace(R.id.flPlaceholder, timelineFragment);
                ft.commit();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.replace(R.id.flPlaceholder, createFragment);
                ft.commit();
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft.replace(R.id.flPlaceholder, profileFragment);
                ft.commit();
            }
        });
    }
}
