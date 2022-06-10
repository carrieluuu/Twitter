package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    Context context;
    List<Tweet> tweets;

    // Pass in the context and list of tweets
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.context = context;
        this.tweets = tweets;
    }

    // For each row, inflate the layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tweet, parent, false);
        return new ViewHolder(view);
    }

    // Bind values based on the position of the element
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data at position
        Tweet tweet = tweets.get(position);

        // Bind the tweet with viewholder
        holder.bind(tweet);

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        tweets.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Tweet> tweetList) {
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    // Define a viewholder
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProfileImage;
        ImageView ivMedia;
        TextView tvBody;
        TextView tvScreenName;
        TextView tvName;
        TextView tvRelativeTimeAgo;
        TextView tvLikeCount;
        TextView tvRetweetCount;
        ImageButton btnLike;
        ImageButton btnRetweet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            ivMedia = itemView.findViewById(R.id.ivMedia);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            tvName = itemView.findViewById(R.id.tvName);
            tvRelativeTimeAgo = itemView.findViewById(R.id.tvRelativeTimeAgo);
            tvLikeCount = itemView.findViewById(R.id.tvLikeCount);
            tvRetweetCount = itemView.findViewById(R.id.tvRetweetCount);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnRetweet = itemView.findViewById(R.id.btnRetweet);

        }

        public void bind(Tweet tweet) {
            int radius = 100;

            tvBody.setText(tweet.body);
            tvName.setText(tweet.user.name);
            tvScreenName.setText("@" + tweet.user.screenName);

            Glide.with(context).load(tweet.user.profileImageUrl)
                    .apply(new RequestOptions()
                            .centerCrop()
                            .transform(new RoundedCorners(radius)))
                    .into(ivProfileImage);

            if (tweet.mediaUrl != null) {
                Glide.with(context).load(tweet.mediaUrl)
                                .apply(new RequestOptions()
                                        .centerCrop()
                                        .transform(new RoundedCorners(radius)))
                                        .into(ivMedia);
                ivMedia.setVisibility(View.VISIBLE);
            } else {
                ivMedia.setVisibility(View.GONE);
            }

            tvRelativeTimeAgo.setText(tweet.relativeTimeAgo);
            tvLikeCount.setText(tweet.likeCount+"");
            tvRetweetCount.setText(tweet.retweetCount+"");
        }



    }

}
