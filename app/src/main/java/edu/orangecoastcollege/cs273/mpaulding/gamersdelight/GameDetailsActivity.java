package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class GameDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);


        // TODO:  Use the Intent object sent from GameListActivity to populate the Views on
        ImageView gameDetailsImageView = (ImageView) findViewById(R.id.gameDetailsImageView);
        TextView gameDetailsNameTextView = (TextView) findViewById(R.id.gameDetailsNameTextView);
        TextView gameDetailsDescTextView = (TextView)
                findViewById(R.id.gameDetailsDescriptionTextView);
        RatingBar gameDetailsRatingBar = (RatingBar) findViewById(R.id.gameDetailsRatingBar);


        // TODO:  this layout, including the TextViews, RatingBar and ImageView with the Game details.
        Intent detailsIntent = getIntent();
        String name = detailsIntent.getStringExtra("Name");
        String description = detailsIntent.getStringExtra("Description");
        float rating = detailsIntent.getFloatExtra("Rating", 0.0f);
        String imageName = detailsIntent.getStringExtra("Image");

        AssetManager am = this.getAssets();
        try{
            InputStream stream = am.open(imageName);
            Drawable event = Drawable.createFromStream(stream, name);
            gameDetailsImageView.setImageDrawable(event);
        }
        catch (IOException e)
        {
            Log.e("Gamers Delight", "Error loading " + imageName, e);
        }

        gameDetailsNameTextView.setText(name);
        gameDetailsDescTextView.setText(description);
        gameDetailsRatingBar.setRating(rating);
    }
}
