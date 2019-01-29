package ehb.adolphe.finalwork.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import ehb.adolphe.finalwork.R;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout parent;
    Button b1;
    String[] btn_name={"answer1" , "answer2","answer3" , "answer4"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        parent = findViewById(R.id.ll_parent);


        for(int i = 0 ; i < btn_name.length ; i++) {

            b1 = new Button(GameActivity.this);
            b1.setId(i + 1);
            b1.setText(btn_name[i]);
            b1.setTag(i);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            // b1.setBackgroundColor(Color.parseColor("#008EFF"));
            //b1.setTextColor(Color.WHITE);
            parent.addView(b1 ,lp);
            b1.setOnClickListener(GameActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        String str = v.getTag().toString();

        switch (str) {
            case "0":
                Toast.makeText(getApplicationContext(),btn_name[0],Toast.LENGTH_SHORT).show();
                break;
            case "1":
                Toast.makeText(getApplicationContext(),btn_name[1],Toast.LENGTH_SHORT).show();
                break;
            case "2": {
                Toast.makeText(getApplicationContext(),btn_name[2],Toast.LENGTH_SHORT).show();
                break;
            }
            case "3": {
                Toast.makeText(getApplicationContext(),btn_name[3],Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
