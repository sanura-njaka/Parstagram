package me.sanura_njaka.parstagram;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.parse.ParseException;
import com.parse.ParseFile;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import me.sanura_njaka.parstagram.model.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<Post> posts;
    Context context;

    public PostAdapter(ArrayList<Post> posts) {

        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_post, viewGroup, false);
        return new ViewHolder(postView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Post post = posts.get(i);

        viewHolder.tvUsername.setText(post.getUser().getUsername().toString());
        viewHolder.tvDescription.setText(post.getDescription().toString());

        String formattedTime = post.getFormattedTime();

        if (formattedTime != "Just now") {
            formattedTime += " ago";
        }
        viewHolder.tvTime.setText(formattedTime);

        File profilePic = null;
        File postedPic = null;

        try {
            profilePic = post.getUser().getParseFile("profile_pic").getFile();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            postedPic = post.getImage().getFile();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Glide.with(context)
                .load(profilePic)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(viewHolder.ivProfileImage);
        Glide.with(context)
                .load(postedPic)
                .into(viewHolder.ivPicture);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> objects) {
        posts.addAll(objects);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivProfileImage;
        public ImageView ivPicture;
        public TextView tvUsername;
        public TextView tvDescription;
        public TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);

            ivProfileImage = itemView.findViewById(R.id.ivSmallProfilePic);
            ivPicture = itemView.findViewById(R.id.ivPhoto);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTime = itemView.findViewById(R.id.tvTime);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
