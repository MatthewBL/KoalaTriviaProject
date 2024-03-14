package com.example.myapplication;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.HashSet;
import java.util.Set;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.Question;
import com.example.myapplication.Constants;

public class MainActivity extends AppCompatActivity {

    private TextView question;
    private ImageView image;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private String correctAnswer;
    private Question questionObject;

    private Set<Integer> usedIndexes = new HashSet<>();

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

    }

    void regenerateQuestion() {
        questionObject = getRandomQuestion(Constants.questions);
        setQuestionText(question.getQuestion());
        setImage(question.getImageId());
        setButtonText(question.getAnswers());
        correctAnswer = question.getCorrectAnswer();
    }

    Question getRandomQuestion(Question[] questions) {
        Random random = new Random();
        int randomIndex;
        do {
            randomIndex = random.nextInt(questions.length);
        } while (usedIndexes.contains(randomIndex));

        usedIndexes.add(randomIndex);

        if (usedIndexes.size() == questions.length) {
            // If all questions have been used, clear the set to start over
            usedIndexes.clear();
        }

        return questions[randomIndex];
    }

    // Method to change the text of the question
    void setQuestionText(String text) {
        question.setText(text);
    }

    // Method to change the image displayed
    void setImage(int resourceId) {
        image.setImageResource(resourceId);
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
        // Store original colors of the buttons
        final Drawable originalDrawableButton1 = button1.getBackground();
        final Drawable originalDrawableButton2 = button2.getBackground();
        final Drawable originalDrawableButton3 = button3.getBackground();
        final Drawable originalDrawableButton4 = button4.getBackground();

        // Extracting the color from the Drawables
        final int originalColorButton1 = ((ColorDrawable) originalDrawableButton1).getColor();
        final int originalColorButton2 = ((ColorDrawable) originalDrawableButton2).getColor();
        final int originalColorButton3 = ((ColorDrawable) originalDrawableButton3).getColor();
        final int originalColorButton4 = ((ColorDrawable) originalDrawableButton4).getColor();

        // Check if selected answer is correct
        boolean isCorrect = (selectedAnswer == correctAnswer);
        // Change color of the right answer to green
        switch (questionObject.findAnswerIndex(correctAnswer)) {
            case 1:
                button1.setBackgroundColor(Color.GREEN);
                break;
            case 2:
                button2.setBackgroundColor(Color.GREEN);
                break;
            case 3:
                button3.setBackgroundColor(Color.GREEN);
                break;
            case 4:
                button4.setBackgroundColor(Color.GREEN);
                break;
        }
        // Change chosen button to red if it's wrong
        if (!isCorrect) {
            switch (questionObject.findAnswerIndex(selectedAnswer)) {
                case 1:
                    button1.setBackgroundColor(Color.RED);
                    break;
                case 2:
                    button2.setBackgroundColor(Color.RED);
                    break;
                case 3:
                    button3.setBackgroundColor(Color.RED);
                    break;
                case 4:
                    button4.setBackgroundColor(Color.RED);
                    break;
            }
        }

        // Set a delay to reset button colors after 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Reset button colors to their original colors
                button1.setBackgroundColor(originalColorButton1);
                button2.setBackgroundColor(originalColorButton2);
                button3.setBackgroundColor(originalColorButton3);
                button4.setBackgroundColor(originalColorButton4);

                // Regenerate question after resetting button colors
                regenerateQuestion();
            }
        }, 3000);

        // Show toast message based on correctness
        if (isCorrect) {
            Toast.makeText(getApplicationContext(), "Correct answer!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Incorrect answer! Correct answer is: " + correctAnswer, Toast.LENGTH_SHORT).show();
        }
    }
}
