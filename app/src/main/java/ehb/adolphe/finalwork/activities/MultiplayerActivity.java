package ehb.adolphe.finalwork.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.fragments.MPFriendsFragment;
import ehb.adolphe.finalwork.fragments.MPRandomFragment;
import ehb.adolphe.finalwork.fragments.OnFragmentInteractionListener;
import ehb.adolphe.finalwork.model.Friend;

public class MultiplayerActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ArrayList<Friend> friends;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        initializeFriendlist();
        mViewPager = findViewById(R.id.mp_container);
        TabLayout tabLayout = findViewById(R.id.mp_tabs);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multiplayer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id){
            case R.id.action_friends:
                intent = new Intent(getApplicationContext(), FriendsActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_profile:
                intent = new Intent(getApplicationContext(), ProfileActivity.class);
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
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return position == 0? new MPFriendsFragment(): new MPRandomFragment();
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }


    public ArrayList<Friend> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    private void initializeFriendlist(){
        friends = new ArrayList<>();
        friends.add(new Friend("Dayan ", "Trabanco", "Dayan.Trabanco@ehb.be","Dilbeek", "3BaDig-X"));
        friends.add(new Friend("Adolphe ", "Mk.", "Adolphe.Mk@ehb.be","Aalst", "3BaDig-X"));
        friends.add(new Friend("Katrien ", "Van Melle", "Katrien.Vanmelle@ehb.be","Brussel", "2BaDig-X"));
        friends.add(new Friend("Onur ", "Bugdayci", "Onur.bugdayci@ehb.be","Zele", "1BaDig-X"));
        friends.add(new Friend("Ashot ", "Zijnachternaam", "Ashot.Zijnachternaam@ehb.be","Asse", "2BaDig-X"));
        friends.add(new Friend("Brian ", "Wouters", "Brian.Wouters@ehb.be","Halle", "3BaDig-X"));
    }
}
