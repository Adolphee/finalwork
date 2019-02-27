package ehb.adolphe.finalwork.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.adapter.FriendsAdapter;
import ehb.adolphe.finalwork.fragments.OnFragmentInteractionListener;
import ehb.adolphe.finalwork.model.Friend;

public class FriendsActivity extends AppCompatActivity implements OnFragmentInteractionListener {


    ArrayList<Friend> friends;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        Toolbar toolbar = findViewById(R.id.friends_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        friends = new ArrayList<>();
        initializeFriendlist();
        RecyclerView rv = findViewById(R.id.friendlist);
        FriendsAdapter adapter = new FriendsAdapter(getApplicationContext(), friends);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> startActivity(new Intent(FriendsActivity.this, AddFriendActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friends, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id){
            case R.id.action_profile:
                intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_play:
                intent = new Intent(getApplicationContext(), MultiplayerActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_home:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_leaderboards:
                intent = new Intent(getApplicationContext(), LeaderboardsActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    public ArrayList<Friend> initializeFriendlist(){
        friends = new ArrayList<>();
        friends.add(new Friend("Dayan ", "Trabanco", "Dayan.Trabanco@ehb.be","Dilbeek", "3BaDig-X"));
        friends.add(new Friend("Adolphe ", "Mk.", "Adolphe.Mk@ehb.be","Aalst", "3BaDig-X"));
        friends.add(new Friend("Katrien ", "Van Melle", "Katrien.Vanmelle@ehb.be","Brussel", "2BaDig-X"));
        return friends;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public ArrayList<Friend> getFriends() {
        return friends;
    }
}
