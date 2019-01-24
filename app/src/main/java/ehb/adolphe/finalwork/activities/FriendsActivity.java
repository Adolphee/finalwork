package ehb.adolphe.finalwork.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.adapter.FriendsAdapter;
import ehb.adolphe.finalwork.model.Friend;

public class FriendsActivity extends AppCompatActivity {

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        Toolbar toolbar = (Toolbar) findViewById(R.id.friends_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Dayan", "Trabanco P.", "Dayan.TP@ehb.be","Dilbeek", "3BaDig-X"));

        for(int i=1;i<4;i++){
            friends.add(new Friend("Friend "+i, "lastname "+i, "friend"+i+".lastname"+i +"@ehb.be","Stad "+i, "3BaDig-X"));
        }

        RecyclerView rv = (RecyclerView) findViewById(R.id.friendlist);
        FriendsAdapter adapter = new FriendsAdapter(getApplicationContext(), friends);
        adapter = new FriendsAdapter(getApplicationContext(), friends);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        // if (id == R.id.action_friends) {
        //     Intent intent = new Intent(getApplicationContext(), FriendsActivity.class);
        //     startActivity(intent);
        //     return true;
        // }

        return super.onOptionsItemSelected(item);
    }
}
