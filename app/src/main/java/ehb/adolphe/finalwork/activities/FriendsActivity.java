package ehb.adolphe.finalwork.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.adapter.FriendsAdapter;
import ehb.adolphe.finalwork.model.Friend;

public class FriendsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        Toolbar toolbar = findViewById(R.id.friends_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Dayan", "Trabanco P.", "Dayan.TP@ehb.be","Dilbeek", "3BaDig-X"));

        for(int i=1;i<4;i++){
            friends.add(new Friend("Friend "+i, "lastname "+i, "friend"+i+".lastname"+i +"@ehb.be","Stad "+i, "3BaDig-X"));
        }

        RecyclerView rv = findViewById(R.id.friendlist);
        FriendsAdapter adapter = new FriendsAdapter(getApplicationContext(), friends);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(adapter);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }
}
