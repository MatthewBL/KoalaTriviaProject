package com.example.myapplication;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Question {
    private String question;
    private String[] answers;
    private String correctAnswer;
    private String imageId;

    // Constructor
    public Question(String question, String[] answers, String correctAnswer, String imageId) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
        this.imageId = imageId;
    }

    // Getters
    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getImageId() {
        return imageId;
    }

    // Setters
    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    // Method to shuffle the answers
    public void shuffleAnswers() {
        List<String> answersList = Arrays.asList(answers);
        Collections.shuffle(answersList);
        answers = answersList.toArray(new String[0]);
    }

    // Method to find the index of an answer
    public int findAnswerIndex(String answer) {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].equals(answer)) {
                return i;
            }
        }
        return -1; // Answer not found
    }
}