package com.example.assignment1_zhiwei_qiu;

import android.graphics.Color;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Questions_bank {

    public ArrayList<Questions> questions = new ArrayList<Questions>();

    public int currentIndex=0 ;
    public int result=0 ;
    public int count=0;

   /* public  static Integer[] color= new Integer[]{
            Color.CYAN, Color.GREEN, Color.YELLOW,Color.RED,Color.MAGENTA
    };*/

    public Questions_bank(){



        Questions q1 = new Questions(R.string.question1,false,Color.CYAN);
        Questions q2 = new Questions(R.string.question2,true,Color.GREEN);
        Questions q3 = new Questions(R.string.question3,true,Color.YELLOW);
        Questions q4 = new Questions(R.string.question4,false,Color.RED);
        Questions q5 = new Questions(R.string.question5,true,Color.MAGENTA);

        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
      //  questions.add(q101);

        Collections.shuffle(questions);
    }

    public boolean checkAnswer(){
        return questions.get(currentIndex).answer;
    }

    public int getSize(){
        return questions.size();
    }

    public int getNextQuestion(int index){
        return questions.get(index).question;
    }

    public int getColor(int index) {return questions.get(index).color;};

    public void refresh(){

        result=0;
        count=1;
    }


}
