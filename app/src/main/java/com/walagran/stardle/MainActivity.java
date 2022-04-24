package com.walagran.stardle;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createButtons();

        findViewById(R.id.infoButton).setOnClickListener(view -> {
            MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(view.getContext())
                    .setTitle("More Games?")
                    .setMessage("To suggest another game to add to this, send an e-mail to ranveer@walagran.com");
            alertDialogBuilder.show();
        });
    }

    private void createButtons() {
        Resources r = getResources();
        String name = getPackageName();

        LinearLayout buttonArray = findViewById(R.id.linearLayout);

        for (String game: Util.GAMES.keySet()) {
            TextView button = new TextView(this);
            button.setText(game);
            button.setId(r.getIdentifier("start_" + game, "id", name));

            applyStyleToButton(button);

            button.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                intent.putExtra("GAME_ID", game);
                startActivity(intent);
            });

            buttonArray.addView(button);
        }

    }

    private void applyStyleToButton(TextView button) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 4, 0, 4);
        button.setLayoutParams(params);
        button.setBackgroundColor(getResources().getColor(R.color.md_theme_light_onPrimary));
        button.setTextColor(getResources().getColor(R.color.md_theme_light_primary));
        button.setTextSize(32);
        button.setAllCaps(true);
        button.setGravity(Gravity.CENTER);
        button.setPadding(0, 64, 0, 64);
    }


}