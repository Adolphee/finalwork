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
import ehb.adolphe.finalwork.retrofit.services.CourseService;
import ehb.adolphe.finalwork.retrofit.services.StudentService;
import ehb.adolphe.finalwork.model.Student;
import ehb.adolphe.finalwork.retrofit.RetrofitSingleton;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity{
    private FeatureCoverFlow coverFlow;
    private CourseAdapter courseAdapter;
    public static ArrayList<Course> courses = new ArrayList<>();
    public static Student AUTH_USER = null;
    private TextSwitcher mTitle;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(AUTH_USER == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            setContentView(R.layout.activity_main);
            Toolbar toolbar = findViewById(R.id.main_toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            onLoadCourses();
        }
    }

    public static ArrayList<Course> initData() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("C#","https://camo.githubusercontent.com/0617f4657fef12e8d16db45b8d73def73144b09f/68747470733a2f2f646576656c6f7065722e6665646f726170726f6a6563742e6f72672f7374617469632f6c6f676f2f6373686172702e706e67"));
        courses.add(new Course("Html","https://cdn0.iconfinder.com/data/icons/HTML5/512/HTML_Logo.png"));
        courses.add(new Course("C++","https://raw.githubusercontent.com/isocpp/logos/master/cpp_logo.png"));
        courses.add(new Course("Java","https://qph.fs.quoracdn.net/main-qimg-48b7a3d8958565e7aa3ad4dbf2312770.webp"));
        courses.add(new Course("SapUI5","https://www.simplifier.io/wp-content/uploads/2018/01/sapui5-logo_simplifier.png"));
        courses.add(new Course("JS","https://i1.wp.com/theicom.org/wp-content/uploads/2016/03/js-logo.png?fit=500%2C500&ssl=1"));
        courses.add(new Course("XML","https://pngimage.net/wp-content/uploads/2018/06/xml-logo-png-3.png"));
        courses.add(new Course("NodeJS","https://www.sitevela.com/img2/nodejs_i1.png"));
        courses.add(new Course("Android","http://pngimg.com/uploads/android_logo/android_logo_PNG34.png"));
        courses.add(new Course("Swift","https://www.symphony-solutions.eu/wp-content/uploads/2018/04/programming-language-swift.png"));
        courses.add(new Course("React","https://neoteric.eu/wp-content/uploads/2015/08/react-logo-300x300.png"));
        return courses;
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
                intent = new Intent(getApplicationContext(), FriendMgmtActivity.class);
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

    void onLoadCourses() {

        Retrofit retrofit = RetrofitSingleton.getInstance();

        CourseService courseService = retrofit.create(CourseService.class);

        Call<List<Course>> call = courseService.getAll();

        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + response.code());
                    return;
                }
                courses = new ArrayList<Course>(response.body());
                updateCourses(courses);
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void updateCourses(ArrayList<Course> courses) {
        mTitle = findViewById(R.id.title);
        mTitle.setFactory(() -> {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            TextView txt = (TextView)inflater.inflate(R.layout.layout_title,null);
            txt.setTextSize(30);;
            txt.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            return txt;
        });
        Animation in = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);
        courseAdapter = new CourseAdapter(courses,MainActivity.this);
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
}
