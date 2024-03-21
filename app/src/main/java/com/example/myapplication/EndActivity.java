package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EndActivity extends AppCompatActivity {

    private TextView timer, textView, textView2, aciertos;
    private ImageView image;
    private View overlay;
    private Button retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        timer = findViewById(R.id.timer2);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        image = findViewById(R.id.imageView3);
        overlay = findViewById(R.id.overlay);
        aciertos = findViewById(R.id.aciertos);
        retry = findViewById(R.id.retry);

        Intent intent = getIntent();
        boolean result = intent.getBooleanExtra("result", false);
        int time = intent.getIntExtra("time", 120);
        int score = intent.getIntExtra("score", 0);

        if (result) {
            overlay.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        }
        else {
            overlay.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        }

        Animation fadeIn = new AlphaAnimation(1, 0);
        fadeIn.setInterpolator(new LinearInterpolator());
        fadeIn.setDuration(1000); // 1 second

        // Set listener to remove view once animation finishes
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation started
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Animation ended, you can remove the view or do other actions here
                overlay.setVisibility(View.GONE); // Hides the view
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animation repeated
            }
        });

        // Start the animation
        overlay.startAnimation(fadeIn);

        int minutes = time / 60;
        int seconds = time % 60;
        timer.setText(String.format("%02d:%02d", minutes, seconds));

        aciertos.setText("Aciertos: " + score);

        if (result) {
            Picasso.get()
                    .load("https://cdn.iconscout.com/icon/premium/png-256-thumb/happy-koala-4242988-3525762.png?f=webp")
                    .error(R.drawable.error)
                    .into(image);
            textView.setText("¡Enhorabuena!");
            textView2.setText("¡Te has koalificado!");
        }
        else {
            Picasso.get()
                    .load("https://cdn.iconscout.com/icon/premium/png-256-thumb/sad-koala-4242991-3525765.png?f=webp")
                    .error(R.drawable.error)
                    .into(image);
            textView.setText("Oh no...");
            textView2.setText("No te has koalificado...");
        }

        Intent intent2 = new Intent(EndActivity.this, MainActivity.class);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent2);
                finish();
            }
        });
    }
}