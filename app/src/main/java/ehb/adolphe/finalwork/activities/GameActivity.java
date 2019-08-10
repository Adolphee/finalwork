package ehb.adolphe.finalwork.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.model.Answer;
import ehb.adolphe.finalwork.model.Course;
import ehb.adolphe.finalwork.model.Question;
import ehb.adolphe.finalwork.model.Quiz;
import ehb.adolphe.finalwork.retrofit.RetroHelper;
import ehb.adolphe.finalwork.retrofit.services.QuizService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout parent;
    Button b1;
    String[] btn_name={"answer1" , "answer2","answer3" , "answer4"};
    TextView question_tv;
    public static Quiz currentQuiz;
    Course course;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        parent = findViewById(R.id.ll_parent);
        question_tv = findViewById(R.id.question);
        course = new Gson().fromJson(getIntent().getStringExtra("subject"), Course.class);
        getQuiz(course.getId());
    }

    @Override
    public void onClick(View v) {
        String str = v.getTag().toString();
        View activ = findViewById(R.id.game_layout);

        switch (Integer.parseInt(str)) {
            case 1:
                activ.setBackgroundResource(R.drawable.quiz_correct);
                activ.setBackground(getDrawable(R.drawable.quiz_correct));
                Toast.makeText(getApplicationContext(),"CORRECT !!!  + 5",Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(500);
                    if(currentQuiz.nextQuestion()) updateQuizScreen();
                } catch (InterruptedException e) {
                    Log.e("SLEEP", e.getMessage());
                }
                break;
            case 0:
                activ.setBackgroundResource(R.drawable.quiz_wrong);
                activ.setBackground(getDrawable(R.drawable.quiz_wrong));
                Toast.makeText(getApplicationContext(),"WRONG !!!  - 5",Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(500);
                    if(currentQuiz.nextQuestion()) updateQuizScreen();
                } catch (InterruptedException e) {
                    Log.e("SLEEP", e.getMessage());
                }
                break;
            default:
                break;
        }
    }

    void getQuiz(Long course_id){
        Retrofit retrofit = RetroHelper.getInstance();
        QuizService courseService = retrofit.create(QuizService.class);

        Call<Quiz> call = courseService.getQuizByCourseId(course_id);

        call.enqueue(new Callback<Quiz>() {
            @Override
            public void onResponse(Call<Quiz> call, Response<Quiz> response) {
                if(!response.isSuccessful()) {
                    Log.d("QUIZ", "onResponse: " + response.code());
                    return;
                }
                currentQuiz = response.body();
                updateQuizScreen();
            }

            @Override
            public void onFailure(Call<Quiz> call, Throwable t) {
                Log.e("QUIZ", "onFailure: " + t.getMessage());
            }
        });
    }

    void updateQuizScreen(){
        if(currentQuiz.getPosition() > 0) parent.removeAllViews();

        View activ = findViewById(R.id.game_layout);
        Question q = currentQuiz.getQuestions().get(currentQuiz.getPosition());
        question_tv.setText(q.getQuestion());
        for(int i = 0 ; i < q.getAnswers().size()  ; i++) {
            Answer a = q.getAnswers().get(i);
            b1 = new Button(GameActivity.this);
            b1.setId(i + 1);
            b1.setText(a.getWhat());
            b1.setTag(a.getCorrect()? 1: 0);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            parent.addView(b1 ,lp);
            b1.setOnClickListener(GameActivity.this);
        }
    }
}
