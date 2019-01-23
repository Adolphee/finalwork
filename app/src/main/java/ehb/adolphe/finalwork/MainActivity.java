package ehb.adolphe.finalwork;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

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

        initData();
        mTitle = findViewById(R.id.title);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                TextView txt = (TextView)inflater.inflate(R.layout.layout_title,null);

                return txt;
            }
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
                mTitle.setText(subjectList.get(position).getName());
            }

            @Override
            public void onScrolling() {

            }
        });
    }

    private void initData() {

        subjectList.add(new Subject("C#","https://banner2.kisspng.com/20180831/iua/kisspng-c-programming-language-logo-microsoft-visual-stud-atlas-portfolio-5b89919299aab1.1956912415357423546294.jpg"));
        subjectList.add(new Subject("Html","https://cdn0.iconfinder.com/data/icons/HTML5/512/HTML_Logo.png"));
        subjectList.add(new Subject("C++","https://raw.githubusercontent.com/isocpp/logos/master/cpp_logo.png"));
    }
}
