package com.example.assignment1_zhiwei_qiu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;

import android.os.Bundle;
import java.util.List;

import android.view.Menu;
import android.view.MenuInflater;

import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;
import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.shuffle;


public class First_Fragment extends Fragment {


    MainActivity activity;
    TextView textView;
   int Index=0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      activity=(MainActivity) getActivity();

        Index=activity.quiz_questions.currentIndex;

      ViewGroup v=(ViewGroup) inflater.inflate(R.layout.fragment_first_,container,false);
      textView=v.findViewById(R.id.question);
      textView.setText(activity.quiz_questions.getNextQuestion(Index));
      textView.setBackgroundColor(activity.quiz_questions.getColor(Index));

      return v;
    }
}