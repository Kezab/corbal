package school.androidgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import java.util.ArrayList;

import school.androidgame.databaseContext.HighscoreDatabaseContext;
import school.androidgame.manager.GameManager;
import school.androidgame.utils.Config;

public class MainMenu extends Activity {
    private Config config;
    private GameManager game;
    private HighscoreDatabaseContext highscoreDatabaseContext;

    public Config getConfig()
    {
        return config;
    }

    public HighscoreDatabaseContext getHighscoreDatabaseContext() {
        return this.highscoreDatabaseContext;
    }

    public void openMainMenu()
    {
        setContentView(R.layout.activity_main_menu);

        final Button startButton = (Button)this.findViewById(R.id.startButton);
        final Button scoresButton = (Button)this.findViewById(R.id.scoresButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainMenu.this.game = new GameManager(MainMenu.this);
            }
        });

        scoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openScoresMenu();
            }

        });
    }

    public void openScoresMenu()
    {
        setContentView(R.layout.activity_main_menu_scores);

        final Button backButton = (Button)this.findViewById(R.id.backButtonScores);
        final ListView scoreListView = (ListView)this.findViewById(R.id.scoreList);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });
        ArrayAdapter adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, this.config.getScores());
        scoreListView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.config = new Config(this);
        this.config.loadValues();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        openMainMenu();

        this.highscoreDatabaseContext = new HighscoreDatabaseContext(this);
    }

    @Override
    protected void onStop() {
        this.config.saveValues();
        super.onStop();
    }
}
