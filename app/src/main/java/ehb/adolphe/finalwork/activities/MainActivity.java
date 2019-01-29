package ehb.adolphe.finalwork.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.adapter.CourseAdapter;
import ehb.adolphe.finalwork.model.Course;
import ehb.adolphe.finalwork.services.StudentService;
import ehb.adolphe.finalwork.adapter.SubjectAdapter;
import ehb.adolphe.finalwork.model.Student;
import ehb.adolphe.finalwork.model.Subject;
import ehb.adolphe.finalwork.retrofit.RetrofitSingleton;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private FeatureCoverFlow coverFlow;
    private CourseAdapter courseAdapter;
    private List<Course> courses = new ArrayList<>();
    private TextSwitcher mTitle;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initData();
        mTitle = findViewById(R.id.title);
        mTitle.setFactory(() -> {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            TextView txt = (TextView)inflater.inflate(R.layout.layout_title,null);
            txt.setTextSize(30);;
            txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            return txt;
        });
        Animation in = AnimationUtils.loadAnimation(this,R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this,R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);
        courseAdapter = new CourseAdapter(courses,this);
        coverFlow = findViewById(R.id.coverFlow);
        coverFlow.setAdapter(courseAdapter);

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText("Take a quick " + courses.get(position).getName() + " quiz!");
            }

            @Override
            public void onScrolling() {

            }
        });

        //als je op foto drukt zal er iets gebeuren.
        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //naar login gaan

                //voor de service
                onLoadStudenten();

                //Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                // startActivity(intentLogin);

                //popup single or multi kiezen
                startActivity(new Intent(MainActivity.this,ModeActivity.class));
                overridePendingTransition(R.anim.slide_popup_down, 0);

            }
        });
    }

    private void initData() {

        courses.add(new Course("C#","https://banner2.kisspng.com/20180831/iua/kisspng-c-programming-language-logo-microsoft-visual-stud-atlas-portfolio-5b89919299aab1.1956912415357423546294.jpg"));
        courses.add(new Course("Html","https://cdn0.iconfinder.com/data/icons/HTML5/512/HTML_Logo.png"));
        courses.add(new Course("C++","https://raw.githubusercontent.com/isocpp/logos/master/cpp_logo.png"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            case R.id.action_play:
                intent = new Intent(getApplicationContext(), MultiplayerActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_leaderboards:
                intent = new Intent(getApplicationContext(), LeaderboardsActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void onLoadStudenten() {

        Retrofit retrofit = RetrofitSingleton.getInstance();

        StudentService studentService = retrofit.create(StudentService.class);

        Call<List<Student>> call = studentService.getAll();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }
                List<Student> students = response.body();

                for(Student student : students) {
                    String content = "";
                    content += "firstName" + student.getFirstname() + "\n";
                    content += "email" + student.getEmail() + "\n";
                    content += "firstName" + student.getFieldOfStudy()+ "\n";

                    Log.d(TAG, "onResponse: " + content);
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
