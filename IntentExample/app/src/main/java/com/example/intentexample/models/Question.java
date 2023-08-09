package com.example.intentexample.models;

import java.util.ArrayList;
import java.util.List;

public class Question {
    String text;
    String answer1;
    String answer2;
    String answer3;
    int trueAnswer;

    public Question(String text, String answer1,
                    String answer2, String answer3, int trueAnswer) {
        this.text = text;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.trueAnswer = trueAnswer;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public int getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(int trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public static List<Question> initQuestions(){
        List questions = new ArrayList<Question>();

        questions.add(new Question(
                "What is the opposite of to buy?",
                "to lend",
                "to sell",
                "to take",
                2));

        questions.add(new Question(
                "Which sentence is correct?",
                "I have been to the cinema yesterday.",
                "I was at the cinema yesterday.",
                "I was been at the cinema yesterday.",
                2));

        questions.add(new Question(
                "What is the opposite of dangerous?",
                "easy.",
                "comfortable.",
                "safe.",
                3));

        return  questions;
    }
}
