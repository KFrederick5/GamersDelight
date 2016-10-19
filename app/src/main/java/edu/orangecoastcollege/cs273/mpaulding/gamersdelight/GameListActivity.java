package edu.orangecoastcollege.cs273.mpaulding.gamersdelight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.List;

public class GameListActivity extends AppCompatActivity {

    private DBHelper db;
    private List<Game> gamesList;
    private GameListAdapter gamesListAdapter;
    private ListView gamesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        this.deleteDatabase(DBHelper.DATABASE_NAME);
        db = new DBHelper(this);

        db.addGame(new Game("League of Legends", "Multiplayer online battle arena", 4.5f, "lol.png"));
        db.addGame(new Game("Dark Souls III", "Action role-playing", 4.0f, "ds3.png"));
        db.addGame(new Game("The Division", "Single player experience", 3.5f, "division.png"));
        db.addGame(new Game("Doom FLH", "First person shooter", 2.5f, "doomflh.png"));
        db.addGame(new Game("Battlefield 1", "Single player campaign", 5.0f, "battlefield1.png"));

        // TODO:  Populate all games from the database into the list
        gamesList = db.getAllGames();

        // TODO:  Create a new ListAdapter connected to the correct layout file and list
        gamesListAdapter = new GameListAdapter(this, R.layout.game_list_item, gamesList);

        // TODO:  Connect the ListView with the ListAdapter
        gamesListView = (ListView) findViewById(R.id.gameListView);

        gamesListView.setAdapter(gamesListAdapter);


    }

    public void viewGameDetails(View view) {
        // TODO: Use an Intent to start the GameDetailsActivity with the data it needs to correctly inflate its views.
        if(view instanceof LinearLayout) {
            LinearLayout selectedLayout = (LinearLayout) view;
            Game selectedGame = (Game) selectedLayout.getTag();

            Log.i("Gamers Delight", selectedGame.toString());

            Intent detailsIntent = new Intent(this, GameDetailsActivity.class);
            detailsIntent.putExtra("Name", selectedGame.getName());
            detailsIntent.putExtra("Description", selectedGame.getDescription());
            detailsIntent.putExtra("Rating", selectedGame.getRating());
            detailsIntent.putExtra("Image", selectedGame.getImageName());
            startActivity(detailsIntent);
        }

    }

    public void addGame(View view)
    {
        EditText nameEditText = (EditText) findViewById(R.id.nameEditText);
        EditText descriptionEditText = (EditText) findViewById(R.id.descriptionEditText);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.gameRatingBar);

        // TODO:  Add a game to the database, list, list adapter
        String name = nameEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        float rating = ratingBar.getRating();

        /*if(name.isEmpty() || description.isEmpty() || rating == 0)
        {
            Toast.makeText(this, "Game name, description, and rating cannot be empty, doofus.",
                    Toast.LENGTH_SHORT).show();
        }
        else
        {*/
            Game game = new Game(name, description, rating);

            db.addGame(game);

            gamesListAdapter.add(game);

            nameEditText.setText("");
            descriptionEditText.setText("");
            ratingBar.setRating(0.0f);
        //}
        // TODO:  Make sure the list adapter is updated


        // TODO:  Clear all entries the user made (edit text and rating bar)

    }

    public void clearAllGames(View view)
    {
        // TODO:  Delete all games from the database and lists
        /*for(int i = 0; i < gamesList.size(); i++)
        {
            gamesList.remove(i);
        }*/
        gamesList.clear();

        db.deleteAllGames();

        gamesListAdapter.notifyDataSetChanged();
    }

}
