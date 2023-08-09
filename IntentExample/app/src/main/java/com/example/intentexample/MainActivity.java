package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intentexample.models.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    RadioGroup rd_group;
    RadioButton checkButton;
    RadioButton rb_first;
    RadioButton rb_second;
    RadioButton rb_third;

    TextView tv_question;
    TextView tv_result;
    Button btn_back;
    Button btn_next;
    Button btn_finish;
    Button btn_restart;

    List<Question> question;
    Map<Integer, Integer> answers;
    int currentQuestion = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = Question.initQuestions();
        rd_group = findViewById(R.id.rd_group);

        answers = new HashMap<>();
        initUI();
        showQuestions();
        initClicks();
    }

    @SuppressLint("WrongViewCast")
    private void initUI(){
        rd_group = findViewById(R.id.rd_group);
        tv_question  = findViewById(R.id.tv_question);
        rb_first = findViewById(R.id.rb_first);
        rb_second = findViewById(R.id.rb_second);
        rb_third = findViewById(R.id.rb_third);
        btn_back = findViewById(R.id.btn_back);
        btn_next = findViewById(R.id.btn_next);
        btn_finish = findViewById(R.id.btn_finish);
        tv_result = findViewById(R.id.tv_result);
        btn_restart = findViewById(R.id.btn_restart);
    }

    private void initClicks(){
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQuestion < question.size()-1){
                    int selectedId = rd_group.getCheckedRadioButtonId();
                    RadioButton selectedButton = findViewById(selectedId);
                    //answers.put(currentQuestion, getAnswerId(selectedButton.getId()));
                    currentQuestion++;
                    showQuestions();
                }
                else {
                    Toast.makeText(MainActivity.this, "Savollar tugadi!",
                            Toast.LENGTH_SHORT).show();
                    Log.d("Answers====>>", answers.toString());
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQuestion > 0){
                    //answers.put(currentQuestion, rd_group.getCheckedRadioButtonId());
                    currentQuestion--;
                    showQuestions();
                }
            }
        });

        rd_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton selectedButton = findViewById(i);
                try {
                    answers.put(currentQuestion, getAnswerId(selectedButton.getId()));
                }
                catch (Exception e){

                }
            }
        });

        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_next.setVisibility(View.GONE);
                btn_back.setVisibility(View.GONE);
                rd_group.setVisibility(View.GONE);
                tv_result.setVisibility(View.GONE);
                tv_question.setVisibility(View.GONE);

                AtomicInteger correctAnswers = new AtomicInteger();
                answers.forEach((key, value) -> {
                    if(question.get(key).getTrueAnswer() == value){
                        correctAnswers.getAndIncrement();
                    }
                });

                answers.clear();

                tv_result.setVisibility(View.VISIBLE);
                tv_result.setText(correctAnswers + "/" + question.size());

                btn_restart.setVisibility(View.VISIBLE);

                btn_finish.setVisibility(View.INVISIBLE);
            }
        });

        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_restart.setVisibility(View.INVISIBLE);

                restart();
            }
        });
    }


    private void showQuestions(){
        question = Question.initQuestions();
        if(currentQuestion <= question.size()-1 && currentQuestion >= 0){
            tv_question.setText(question.get(currentQuestion).getText());
            rb_first.setText(question.get(currentQuestion).getAnswer1());
            rb_second.setText(question.get(currentQuestion).getAnswer2());
            rb_third.setText(question.get(currentQuestion).getAnswer3());
            Toast.makeText(MainActivity.this, currentQuestion+1+"", Toast.LENGTH_SHORT).show();
            rd_group.check(0);
        }

        if(currentQuestion == question.size()-1){
            btn_finish.setVisibility(View.VISIBLE);
        }
        else {
            btn_finish.setVisibility(View.INVISIBLE);
        }

    }

    private int getAnswerId(int viewId){
        int ans = 0;
        if(viewId == R.id.rb_first) ans = 1;
        else if (viewId == R.id.rb_second) ans = 2;
        else if (viewId == R.id.rb_third) ans = 3;
        return ans;
    }

    private void restart(){
        currentQuestion = 0;
        showQuestions();

        btn_next.setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.VISIBLE);
        rd_group.setVisibility(View.VISIBLE);
        tv_question.setVisibility(View.VISIBLE);

        tv_result.setVisibility(View.INVISIBLE);
        btn_finish.setVisibility(View.INVISIBLE);
    }
}