package me.sanura_njaka.parstagram;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.File;

import me.sanura_njaka.parstagram.model.Post;

public class CreateFragment extends Fragment {

    ImageView ivPhoto;
    Button btnPost;
    String filePath;

    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivPhoto = view.findViewById(R.id.ivPhoto);
        btnPost = view.findViewById(R.id.btnPost);

        btnPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                EditText etDescription = view.findViewById(R.id.etDescription);
                String description = etDescription.getText().toString();
                etDescription.setText("");

                ParseFile file = new ParseFile(new File(filePath));

                Post newPost = new Post();
                newPost.setDescription(description);
                newPost.setUser(ParseUser.getCurrentUser());
                newPost.setImage(file);

                final Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        ivPhoto = view.findViewById(R.id.ivPhoto);

        return view;
    }

    public void sendFilePath(String filePath) {
        this.filePath = filePath;
    }
}
