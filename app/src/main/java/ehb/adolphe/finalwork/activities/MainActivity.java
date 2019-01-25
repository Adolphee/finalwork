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
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

import ehb.adolphe.finalwork.R;
import ehb.adolphe.finalwork.adapter.SubjectAdapter;
import ehb.adolphe.finalwork.model.Subject;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class MainActivity extends AppCompatActivity {
    private FeatureCoverFlow coverFlow;
    private SubjectAdapter subjectAdapter;
    private List<Subject> subjectList = new ArrayList<>();
    private TextSwitcher mTitle;



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

        subjectAdapter = new SubjectAdapter(subjectList,this);
        coverFlow = findViewById(R.id.coverFlow);
        coverFlow.setAdapter(subjectAdapter);

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText("Take a quick " + subjectList.get(position).getName() + " quiz!");
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
                Log.d("myTag", "test click");
                //Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
               // startActivity(intentLogin);

                //popup single or multi kiezen
                startActivity(new Intent(MainActivity.this,ModeActivity.class));
            }
        });
    }

    private void initData() {

        subjectList.add(new Subject("C#","https://banner2.kisspng.com/20180831/iua/kisspng-c-programming-language-logo-microsoft-visual-stud-atlas-portfolio-5b89919299aab1.1956912415357423546294.jpg"));
        subjectList.add(new Subject("Html","https://cdn0.iconfinder.com/data/icons/HTML5/512/HTML_Logo.png"));
        subjectList.add(new Subject("C++","https://raw.githubusercontent.com/isocpp/logos/master/cpp_logo.png"));
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
}
