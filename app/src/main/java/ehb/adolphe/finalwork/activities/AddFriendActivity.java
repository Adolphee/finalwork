package ehb.adolphe.finalwork.activities;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import java.util.ArrayList;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.adapter.ListAdapter;
import ehb.adolphe.finalwork.databinding.ActivityAddFriendBinding;
import ehb.adolphe.finalwork.model.Friend;

public class AddFriendActivity extends AppCompatActivity {

    ActivityAddFriendBinding binding;
    ListAdapter adapter;
    ArrayList<Friend> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        //Toolbar toolbar = findViewById(R.id.add_friends_toolbar);
        SearchView search = findViewById(R.id.add_friends_search);
        CharSequence query = search.getQuery();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_friend);
        setSupportActionBar(binding.addFriendsToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initializeFriendlist();

        adapter= new ListAdapter(friends);
        binding.listView.setAdapter(adapter);

        binding.addFriendsSearch.setActivated(true);
        binding.addFriendsSearch.setQueryHint("Enter a name...");
        binding.addFriendsSearch.onActionViewExpanded();
        binding.addFriendsSearch.setIconified(false);
        binding.addFriendsSearch.clearFocus();

        binding.addFriendsSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_friend, menu);
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
        friends.add(new Friend("Beernaert", "Van Hyrnaest", "Beernaert.Van.Hyrnaest@ehb.be","Daarnaast", "3BaDig-X"));
        friends.add(new Friend("Benedictus IV", "Van Damascus", "BenedictusIV.Van.Damascus@ehb.be","Damascus", "3BaDig-X"));
        friends.add(new Friend("Marjolein", "Kevermeyn", "Marjolein.Kevermeyn@ehb.be","De Mijnen", "2BaDig-X"));
        friends.add(new Friend("Onur", "Bugdayci", "Onur.bugdayci@ehb.be","Zele", "1BaDig-X"));
        friends.add(new Friend("Ashot", "Zijnachternaam", "Ashot.Zijnachternaam@ehb.be","Asse", "2BaDig-X"));
        friends.add(new Friend("Brian", "Wouters", "Brian.Wouters@ehb.be","Halle", "3BaDig-X"));
        return friends;
    }
}
