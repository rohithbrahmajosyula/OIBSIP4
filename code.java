package com.example.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView, resultTextView;
    private RadioGroup answersRadioGroup;
    private RadioButton answer1RadioButton, answer2RadioButton, answer3RadioButton, answer4RadioButton;
    private Button submitAnswerButton;

    private String[] questions = {
            "What is the capital of France?",
            "What is the largest planet in our solar system?",
            "Who wrote 'To Kill a Mockingbird'?",
            "What is the chemical symbol for water?"
    };

    private String[][] options = {
            {"Paris", "London", "Berlin", "Madrid"},
            {"Earth", "Mars", "Jupiter", "Saturn"},
            {"Harper Lee", "Mark Twain", "Ernest Hemingway", "Jane Austen"},
            {"H2O", "CO2", "O2", "H2SO4"}
    };

    private String[] correctAnswers = {"Paris", "Jupiter", "Harper Lee", "H2O"};

    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answersRadioGroup = findViewById(R.id.answersRadioGroup);
        answer1RadioButton = findViewById(R.id.answer1RadioButton);
        answer2RadioButton = findViewById(R.id.answer2RadioButton);
        answer3RadioButton = findViewById(R.id.answer3RadioButton);
        answer4RadioButton = findViewById(R.id.answer4RadioButton);
        submitAnswerButton = findViewById(R.id.submitAnswerButton);
        resultTextView = findViewById(R.id.resultTextView);

        loadQuestion();

        submitAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = answersRadioGroup.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String selectedAnswer = selectedRadioButton.getText().toString();
                    checkAnswer(selectedAnswer);
                }
            }
        });
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            questionTextView.setText(questions[currentQuestionIndex]);
            answer1RadioButton.setText(options[currentQuestionIndex][0]);
            answer2RadioButton.setText(options[currentQuestionIndex][1]);
            answer3RadioButton.setText(options[currentQuestionIndex][2]);
            answer4RadioButton.setText(options[currentQuestionIndex][3]);
            answersRadioGroup.clearCheck();
            resultTextView.setText("");
        } else {
            questionTextView.setText("Quiz Completed!");
            answersRadioGroup.setVisibility(View.GONE);
            submitAnswerButton.setVisibility(View.GONE);
        }
    }

    private void checkAnswer(String selectedAnswer) {
        if (selectedAnswer.equals(correctAnswers[currentQuestionIndex])) {
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Wrong! Correct answer is: " + correctAnswers[currentQuestionIndex]);
        }
        currentQuestionIndex++;
        loadQuestion();
    }
}
