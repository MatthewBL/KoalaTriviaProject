package com.example.myapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private TextView question;
    private ImageView image;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private String correctAnswer;
    private Question questionObject;
    private boolean stopControl = false;
    private TextView score;
    private TextView timer;
    private TextView wrong;

    private Set<Integer> usedIndexes = new HashSet<>();

    private int scoreValue = 0;
    private int wrongCount = 0;
    private int secondsPassed = 0;
    private Handler timerHandler = new Handler();
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            secondsPassed++;
            int minutes = secondsPassed / 60;
            int seconds = secondsPassed % 60;
            timer.setText(String.format("%02d:%02d", minutes, seconds));
            timerHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables
        question = findViewById(R.id.question);
        image = findViewById(R.id.image);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        score = findViewById(R.id.score);
        timer = findViewById(R.id.timer);
        wrong = findViewById(R.id.wrong);

        score.setText("0");
        timer.setText("00:00");
        wrong.setText("0");

        regenerateQuestion();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(button1.getText().toString(), correctAnswer);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(button2.getText().toString(), correctAnswer);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(button3.getText().toString(), correctAnswer);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleButtonClick(button4.getText().toString(), correctAnswer);
            }
        });

        timerHandler.postDelayed(timerRunnable, 1000);
    }

    void regenerateQuestion() {
        questionObject = getRandomQuestion(Constants.questions);
        setQuestionText(questionObject.getQuestion());
        setImage(questionObject.getImageUrl());
        questionObject.shuffleAnswers();
        setButtonText(questionObject.getAnswers());
        correctAnswer = questionObject.getCorrectAnswer();
    }

    Question getRandomQuestion(Question[] questions) {
        Random random = new Random();
        int randomIndex;
        do {
            randomIndex = random.nextInt(questions.length);
        } while (usedIndexes.contains(randomIndex));

        usedIndexes.add(randomIndex);

        return questions[randomIndex];
    }

    // Method to change the text of the question
    void setQuestionText(String text) {
        question.setText(text);
    }

    // Method to change the image displayed
    void setImage(String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.error)
                .into(image);
    }

    // Method to change the button text displayed
    void setButtonText(String[] buttonTexts) {
        if (buttonTexts.length >= 4) {
            button1.setText(buttonTexts[0]);
            button2.setText(buttonTexts[1]);
            button3.setText(buttonTexts[2]);
            button4.setText(buttonTexts[3]);
        }
    }

    void handleButtonClick(final String selectedAnswer, final String correctAnswer) {
        if (stopControl) {
            return;
        }
        // Store the colors
        ColorStateList backgroundTint1 = button1.getBackgroundTintList();
        ColorStateList backgroundTint2 = button2.getBackgroundTintList();
        ColorStateList backgroundTint3 = button3.getBackgroundTintList();
        ColorStateList backgroundTint4 = button4.getBackgroundTintList();
        // Check if selected answer is correct
        boolean isCorrect = (selectedAnswer == correctAnswer);
        // Change color of the right answer to green
        switch (questionObject.findAnswerIndex(correctAnswer) + 1) {
            case 1:
                button1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;
            case 2:
                button2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;
            case 3:
                button3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;
            case 4:
                button4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                break;
        }
        // Change chosen button to red if it's wrong
        if (!isCorrect) {
            switch (questionObject.findAnswerIndex(selectedAnswer) + 1) {
                case 1:
                    button1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    break;
                case 2:
                    button2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    break;
                case 3:
                    button3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    break;
                case 4:
                    button4.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    break;
            }
        }

        stopControl = true;
        // Set a delay to reset button colors after 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Reset button colors to their original colors
                button1.setBackgroundTintList(backgroundTint1);
                button2.setBackgroundTintList(backgroundTint2);
                button3.setBackgroundTintList(backgroundTint3);
                button4.setBackgroundTintList(backgroundTint4);

                // Regenerate question after resetting button colors
                regenerateQuestion();
                stopControl = false;
            }
        }, 3000);

        if (isCorrect) {
            scoreValue++;
            score.setText(String.valueOf(scoreValue));
        } else {
            wrongCount++;
            wrong.setText(String.valueOf(wrongCount));
        }

        if (usedIndexes.size() == Constants.questions.length) {
            Intent intent = new Intent(MainActivity.this, EndActivity.class);
            intent.putExtra("result", scoreValue >= Math.ceil((float) Constants.questions.length/2));
            intent.putExtra("time", secondsPassed);
            intent.putExtra("score", scoreValue);
            startActivity(intent);
            finish();
        }
    }
}
