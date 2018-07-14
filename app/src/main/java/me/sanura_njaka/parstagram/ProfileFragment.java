package me.sanura_njaka.parstagram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.parse.ParseException;
import com.parse.ParseUser;

import org.w3c.dom.Text;


public class ProfileFragment extends Fragment {

    private TextView tvUsername;
    private Button btnLogout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvUsername = view.findViewById(R.id.tvUsername);
        btnLogout = view.findViewById(R.id.btnLogout);
        ImageView ivProfilePic = view.findViewById(R.id.ivProfile);

        tvUsername.setText(ParseUser.getCurrentUser().getUsername());
        try {
            Glide.with(view)
                    .load(ParseUser.getCurrentUser().getParseFile("profile_pic").getFile())
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                    .into(ivProfilePic);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                Toast.makeText(getContext(), "You have been logged out.", Toast.LENGTH_LONG).show();

                final Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
