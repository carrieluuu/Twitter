package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.codepath.apps.restclienttemplate.models.Tweet;
import org.parceler.Parcels;

public class TweetDetailsActivity extends AppCompatActivity {

    ImageView ivProfileImage;
    ImageView ivMedia;
    TextView tvBody;
    TextView tvScreenName;
    TextView tvName;
    TextView tvTimeStamp;
    TextView tvFavoriteCount;
    TextView tvRetweetCount;
    ImageButton btnLike;
    ImageButton btnRetweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet_details);

        ivProfileImage = findViewById(R.id.ivProfileImage);
        ivMedia = findViewById(R.id.ivMedia);
        tvBody = findViewById(R.id.tvBody);
        tvScreenName = findViewById(R.id.tvScreenName);
        tvName = findViewById(R.id.tvName);
        tvTimeStamp = findViewById(R.id.tvTimeStamp);
        tvFavoriteCount = findViewById(R.id.tvFavoriteCount);
        tvRetweetCount = findViewById(R.id.tvRetweetCount);
        btnLike = findViewById(R.id.btnLike);
        btnRetweet = findViewById(R.id.btnRetweet);

        Tweet tweet = Parcels.unwrap(getIntent().getParcelableExtra("tweet"));

        int radius = 100;

        tvBody.setText(tweet.body);
        tvName.setText(tweet.user.name);
        tvScreenName.setText("@" + tweet.user.screenName);

        Glide.with(this).load(tweet.user.profileImageUrl)
                .apply(new RequestOptions()
                        .centerCrop()
                        .transform(new RoundedCorners(radius)))
                .into(ivProfileImage);

        if (tweet.mediaUrl != null) {
            Glide.with(this).load(tweet.mediaUrl)
                    .apply(new RequestOptions()
                            .centerCrop()
                            .transform(new RoundedCorners(radius)))
                    .into(ivMedia);
            ivMedia.setVisibility(View.VISIBLE);
        } else {
            ivMedia.setVisibility(View.GONE);
        }

        tvTimeStamp.setText(tweet.timeStamp);

    }

}
