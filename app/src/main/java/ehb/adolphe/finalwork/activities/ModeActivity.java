package ehb.adolphe.finalwork.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import ehb.adolphe.finalwork.R;

public class ModeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int)(height*.5));

        Button solo = findViewById(R.id.solo);
        solo.setOnClickListener(v -> {
            Intent intent = new Intent(ModeActivity.this, GameActivity.class);
            if(getIntent().getExtras().getString("subject") != null){
                intent.putExtra("subject", getIntent().getExtras().getString("subject"));
            }
            startActivity(intent);
        });

        Button multiplayer = findViewById(R.id.multiplayer);
        multiplayer.setOnClickListener(v -> startActivity(new Intent(ModeActivity.this, MultiplayerActivity.class)));
    }
}