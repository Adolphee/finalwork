package ehb.adolphe.finalwork.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.model.Student;

public class ProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView username_tv, currentLevel_tv, nextLevel_tv, fullname_tv, studies_tv, slogan_tv, email_tv;
    ProgressBar progressBar;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button lbButton = findViewById(R.id.btn_PLeaderboards);
        initContent();
        lbButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LeaderboardsActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id){
            case R.id.action_friends:
                intent = new Intent(getApplicationContext(), FriendMgmtActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_play:
                intent = new Intent(getApplicationContext(), MultiplayerActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_leaderboards:
                intent = new Intent(getApplicationContext(), LeaderboardsActivity.class);
                startActivity(intent);
                return true;
            case R.id.homeAsUp:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }

    void initContent(){
        toolbar.setSubtitle(MainActivity.AUTH_USER.getFirstname());
        Student student = MainActivity.AUTH_USER;
        username_tv = findViewById(R.id.username);
        currentLevel_tv = findViewById(R.id.currentLevel);
        progressBar = findViewById(R.id.progressBar);
        nextLevel_tv = findViewById(R.id.nextLevel);
        fullname_tv = findViewById(R.id.fullname);
        ratingBar = findViewById(R.id.ratingBar);
        studies_tv = findViewById(R.id.studies);
        slogan_tv = findViewById(R.id.slogan);
        email_tv = findViewById(R.id.email);

         username_tv.setText(student.getUsername());
         int currentLevel = Math.round(student.getExperience() / 1000);
         int exp_to_level_up = Math.round(student.getExperience() % 1000);
         currentLevel_tv.setText(""+currentLevel); // 1000 xp = 1 level
         nextLevel_tv.setText( ""+ (currentLevel + 1));
         progressBar.setProgress(Math.round(new Float(exp_to_level_up/10)));
         fullname_tv.setText(student.getFirstname() + " " + student.getLastname());
         studies_tv.setText(student.getFieldOfStudy());
         slogan_tv.setText(student.getSlogan());
         email_tv.setText(student.getEmail());
         ratingBar.setRating((float) 4.5);
    }
}































