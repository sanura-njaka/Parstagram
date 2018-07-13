package me.sanura_njaka.parstagram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

import me.sanura_njaka.parstagram.model.Post;

public class CreateFragment extends Fragment {

    ImageView ivPhoto;
    Button btnPost;
    String filePath;
    EditText etDescription;
    HomeActivityListener listener;

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
        etDescription = view.findViewById(R.id.etDescription);

        btnPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String description = etDescription.getText().toString();
                etDescription.setText("");

                ParseFile file = new ParseFile(new File(filePath));

                Post newPost = new Post();
                newPost.setDescription(description);
                newPost.setUser(ParseUser.getCurrentUser());
                newPost.setImage(file);

                newPost.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.d("CreateFragment", "Create post success!");
                            listener.switchToTimeline();
                        } else {
                            e.printStackTrace();
                        }
                    }
                });
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

    public interface HomeActivityListener {
        void switchToTimeline();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof HomeActivityListener) {
            listener = (HomeActivityListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + "must implement CreateFragment.HomeActivityListener");
        }
    }
}
