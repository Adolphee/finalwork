package ehb.adolphe.finalwork.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.fragments.FSearchFragment;
import ehb.adolphe.finalwork.fragments.FriendsFragment;
import ehb.adolphe.finalwork.fragments.OnFragmentInteractionListener;
import ehb.adolphe.finalwork.model.Friend;

public class FriendMgmtActivity extends AppCompatActivity implements OnFragmentInteractionListener {

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
    private ArrayList<Friend> globals;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fm);
        friends = initializeFriendlist();
        globals = initializeGloballist();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_friend_mgmt, menu);
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
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 0)
                return FriendsFragment.newInstance(friends);
            return FSearchFragment.newInstance(globals);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }

    public static ArrayList<Friend> initializeGloballist(){
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Beernaert", "Van Hyrnaest", "Beernaert.Van.Hyrnaest@ehb.be","Daarnaast", "3BaDig-X"));
        friends.add(new Friend("Benedictus IV", "Van Damascus", "BenedictusIV.Van.Damascus@ehb.be","Damascus", "3BaDig-X"));
        friends.add(new Friend("Marjolein", "Kevermeyn", "Marjolein.Kevermeyn@ehb.be","De Mijnen", "2BaDig-X"));
        friends.add(new Friend("Onur", "Bugdayci", "Onur.bugdayci@ehb.be","Zele", "1BaDig-X"));
        friends.add(new Friend("Ashot", "Zijnachternaam", "Ashot.Zijnachternaam@ehb.be","Asse", "2BaDig-X"));
        friends.add(new Friend("Brian", "Wouters", "Brian.Wouters@ehb.be","Halle", "3BaDig-X"));
        return friends;
    }
    public static ArrayList<Friend> initializeFriendlist(){
        ArrayList<Friend> friends = new ArrayList<>();
        friends.add(new Friend("Dayan ", "Trabanco", "Dayan.Trabanco@ehb.be","Dilbeek", "3BaDig-X"));
        friends.add(new Friend("Adolphe ", "Mk.", "Adolphe.Mk@ehb.be","Aalst", "3BaDig-X"));
        friends.add(new Friend("Katrien ", "Van Melle", "Katrien.Vanmelle@ehb.be","Brussel", "2BaDig-X"));
        return friends;
    }


}
