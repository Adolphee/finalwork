package ehb.adolphe.finalwork.activities;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.model.Quiz;
import ehb.adolphe.finalwork.model.Student;

public class QuizResultsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView username_tv,
             currentLevel_tv,
             score_tv,
             course_tv,
             bonus_tv,
             experience_tv,
             gameMode_tv,
             nextLevel_tv,
             another_tv;
    ProgressBar progressBar;
    RatingBar ratingBar;
    ImageView avatar;
    Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        another_tv= findViewById(R.id.btn_another_one);
        quiz = GameActivity.currentQuiz;
        initContent();
        another_tv.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    void initContent(){
        toolbar.setSubtitle(MainActivity.AUTH_USER.getFirstname());
        Student student = MainActivity.AUTH_USER;
        currentLevel_tv = findViewById(R.id.currentLevel);
        progressBar = findViewById(R.id.progressBar);
        nextLevel_tv = findViewById(R.id.nextLevel);
        username_tv = findViewById(R.id.username);
        ratingBar = findViewById(R.id.ratingBar);
        avatar = findViewById(R.id.imageButton);
        score_tv = findViewById(R.id.score);
        course_tv = findViewById(R.id.course);
        experience_tv = findViewById(R.id.experience);
        gameMode_tv = findViewById(R.id.gameMode);
        bonus_tv = findViewById(R.id.bonusPoints);

        if(quiz.getSimpleScore() >= 10){
            String green = "#8BC34A";
            avatar.setImageResource(R.drawable.ic_mood_green_133dp);
            progressBar.getProgressDrawable().setColorFilter(
                    Color.parseColor(green), android.graphics.PorterDuff.Mode.SRC_IN);
            score_tv.setTextColor(Color.parseColor(green));
            course_tv.setTextColor(Color.parseColor(green));
            gameMode_tv.setTextColor(Color.parseColor(green));
            bonus_tv.setTextColor(Color.parseColor(green));
            experience_tv.setTextColor(Color.parseColor(green));
        }else {
            String red = "#881818";
            avatar.setImageResource(R.drawable.ic_mood_bad_red_133dp);
            progressBar.getProgressDrawable().setColorFilter(
                    Color.parseColor(red), android.graphics.PorterDuff.Mode.SRC_IN);
            score_tv.setTextColor(Color.parseColor(red));
            course_tv.setTextColor(Color.parseColor(red));
            gameMode_tv.setTextColor(Color.parseColor(red));
            bonus_tv.setTextColor(Color.parseColor(red));
            experience_tv.setTextColor(Color.parseColor(red));
        }

        Integer currentLevel = Math.round(student.getExperience() / 5000);
        Integer exp_to_level_up = Math.round(student.getExperience() % 5000);
        currentLevel_tv.setText(""+currentLevel); // 1000 xp = 1 level
        nextLevel_tv.setText( ""+ (currentLevel + 1));
        progressBar.setProgress(Math.round(new Float(5000 - exp_to_level_up)));
        course_tv.setText(GameActivity.course.getName());
        score_tv.setText(quiz.getSimpleScore() + " / 20");
        experience_tv.setText(""+quiz.getExperienceGained());
        gameMode_tv.setText(quiz.getOpponent() == null ? "SOLO":"MULTIPLAYER");
        bonus_tv.setText(""+ quiz.getBonusPoints());
        username_tv.setText(MainActivity.AUTH_USER.getUsername());
        ratingBar.setIsIndicator(true);
        setAnimations();
    }

    void setAnimations(){

        ObjectAnimator anim;
        Animation anim2;
        LinearLayout details = findViewById(R.id.details_container);

        // avatar
        anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        anim2.setDuration(500);
        avatar.startAnimation(anim2);

        // rating stars
        anim = ObjectAnimator.ofFloat(ratingBar, "rating", 0, quiz.getRating());
        anim.setDuration(3);
        anim.start();

        // prograss bar
        Integer x = Integer.parseInt(quiz.getInitialExperience() + "");
        anim = ObjectAnimator.ofInt(progressBar, "progress", Integer.parseInt(quiz.getInitialExperience() + ""), progressBar.getProgress());
        anim.setDuration(2000);
        anim.start();

        // general info animation
        anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_bottom);
        anim2.setDuration(500);
        details.startAnimation(anim2);
    }
}
