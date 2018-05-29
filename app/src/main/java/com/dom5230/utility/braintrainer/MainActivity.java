package com.dom5230.utility.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView Go;

    LinearLayout InfoBox;
    TextView TimerBox;
    TextView QuestionBox;
    TextView StatusBox;

    GridLayout SelectionBox;
    TextView PositionOne;
    TextView PositionTwo;
    TextView PositionThree;
    TextView PositionFour;

    TextView AnswerResponse;

    Button PlayAgain;

    Random random;

    int countDown = 2;

    int num1, num2, ans;

    int questionCounter = 0;

    int answerCounter = 0;

    public void onGo(View view){
        updateUiElements();
        timerStart();
        setQuestion();
    }

    public void timerStart(){

        new CountDownTimer(30000, 100) {
            @Override
            public void onTick(long l) {
               TimerBox.setText(l/1000 +"s");
               countDown = (int)l/1000;
            }

            @Override
            public void onFinish() {
                TimerBox.setText("0s");
                AnswerResponse.setText("Your Score: "+answerCounter+"/"+questionCounter);
                PlayAgain.setVisibility(View.VISIBLE);
                SelectionBox.setVisibility(View.INVISIBLE);
            }
        }.start();

    }

    public void updateUiElements(){
        Go.setVisibility(View.INVISIBLE);
        PlayAgain.setVisibility(View.INVISIBLE);
        InfoBox.setVisibility(View.VISIBLE);
        SelectionBox.setVisibility(View.VISIBLE);
        AnswerResponse.setVisibility(View.VISIBLE);
        AnswerResponse.setText("");
        StatusBox.setText("0/0");
    }

    public void setQuestion(){
            if(countDown > 1) {
                num1 = random.nextInt(20);
                num2 = random.nextInt(20);
                ans = num1+num2;
                QuestionBox.setText(num1+" + "+num2);
                int temp = random.nextInt(4);
                int[] places = new int[4];
                places[temp] = ans;
                for(int i =0;i<4;i++){
                    if(i!=temp){
                        places[i] = random.nextInt(40);
                    }
                }
                updateSelectionBox(places);
                questionCounter++;
            }
    }

    public void updateSelectionBox(int[] places){
        PositionOne.setText(String.valueOf(places[0]));
        PositionTwo.setText(String.valueOf(places[1]));
        PositionThree.setText(String.valueOf(places[2]));
        PositionFour.setText(String.valueOf(places[3]));
    }

    public void onAnswerSelect(View view){
        int id = view.getId();
        TextView ansView = findViewById(id);
        if(Integer.parseInt(ansView.getText().toString())==ans){
            AnswerResponse.setText("Correct !");
            answerCounter++;
        }else{
            AnswerResponse.setText("Wrong !");
        }
        StatusBox.setText(answerCounter+"/"+questionCounter);
        setQuestion();
    }

    public void reset(){
        num1 =0;
        num2 =0;
        ans = 0;
        countDown =2;
        questionCounter = 0;
        answerCounter = 0;
    }

    public void playAgain(View view){
        reset();
        onGo(view);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Go = findViewById(R.id.Go);

        InfoBox = findViewById(R.id.InfoBox);
        TimerBox = findViewById(R.id.TimerBox);
        QuestionBox = findViewById(R.id.QuestionBox);
        StatusBox = findViewById(R.id.StatusBox);

        SelectionBox = findViewById(R.id.SelectionBox);
        PositionOne = findViewById(R.id.PostionOne);
        PositionTwo = findViewById(R.id.PostionTwo);
        PositionThree = findViewById(R.id.PostionThree);
        PositionFour = findViewById(R.id.PostionFour);

        AnswerResponse = findViewById(R.id.AnswerResponse);
        PlayAgain = findViewById(R.id.PLayAgain);

        random = new Random();
    }
}
