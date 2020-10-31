package com.example.assignment1_zhiwei_qiu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Locale;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.List;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ProgressBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    static int language = 1;
    Questions_bank quiz_questions = new Questions_bank();
    First_Fragment quiz_fragment;
    private Menu menu;
    AlertDialog.Builder builder;



    void newFragment(){
        quiz_fragment= new First_Fragment();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.first_fragment,quiz_fragment).commit();
    }
    void removeFragment(){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.remove(quiz_fragment).commit();
        quiz_fragment=null;
    }

    private void updateMenuTitles()
    {
        if (menu != null) {
            MenuItem langMenuItem = menu.findItem(R.id.change_id);
            if (language == 1) {// if app language is 1 then menu to be Chinese
                langMenuItem.setTitle(R.string.Chinese);
            } else {// if app languege is 2 then menu to be english
                langMenuItem.setTitle(R.string.English);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        this.menu = menu;
        updateMenuTitles();
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.change_id:{
                if (item.getTitle().equals("中文")) {
                    Locale locale = new Locale("zh");
                    Locale.setDefault(locale);

                    Configuration config = new Configuration();
                    config.locale = locale;

                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    Toast.makeText(getApplicationContext(), "Chinese", Toast.LENGTH_SHORT).show();
                    language = 2;

                    finish();
                    startActivity(getIntent());

                }else {
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;

                    language = 1;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }

        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("lang",language);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newFragment();

        if (savedInstanceState != null){
            language = savedInstanceState.getInt("lang");
            if (language == 2){
                Locale locale = new Locale("zh");
                Locale.setDefault(locale);

                Configuration config = new Configuration();
                config.locale = locale;

                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(getApplicationContext(), "Chinese", Toast.LENGTH_SHORT).show();
                language = 2;

                finish();
                startActivity(getIntent());
        }
            else{
                Locale locale = new Locale("en");
                Locale.setDefault(locale);

                Configuration config = new Configuration();
                config.locale = locale;

                getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                Toast.makeText(getApplicationContext(), "English", Toast.LENGTH_SHORT).show();
                language = 1;

                finish();
                startActivity(getIntent());
            }
        }
        setContentView(R.layout.activity_main);

        updateMenuTitles();

        builder = new AlertDialog.Builder(this);
        Button trueButton=(Button)findViewById(R.id.answer_t);
        Button falseButton=(Button)findViewById(R.id.answer_f);
        final ProgressBar progressBar=(ProgressBar)findViewById(R.id.progressBar);


        // true button

        trueButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                             removeFragment();
                                              newFragment();

                                              if(quiz_questions.checkAnswer()) {
                                                  quiz_questions.result++;

                                                  Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                                              }else
                                              {
                                                  Toast.makeText(getApplicationContext(),"Wrong", Toast.LENGTH_SHORT).show();
                                              }
                                              quiz_questions.currentIndex++;
                                                  // check if reach the last question
                                                  if(quiz_questions.currentIndex<=4) {

                                                      progressBar.setProgress(quiz_questions.count);
                                                  }
                                                  else if(quiz_questions.currentIndex==5)
                                                  {
                                                      quiz_questions.currentIndex=4;
                                                      builder.setMessage("Your final score is " + quiz_questions.result +" out of "+ (quiz_questions.getSize())+" .")
                                                              .setCancelable(true)
                                                              .setPositiveButton("Repeat", new DialogInterface.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(DialogInterface dialog, int which) {

                                                                      Intent intent= getIntent();
                                                                      finish();
                                                                      startActivity(intent);
                                                                      quiz_questions.refresh();
                                                                      progressBar.setProgress(quiz_questions.count);
                                                                  }
                                                              })
                                                              .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                                  @Override
                                                                  public void onClick(DialogInterface dialog, int which) {
                                                                      finish();
                                                                  }
                                                              });
                                                      AlertDialog alert = builder.create();
                                                      alert.setTitle("Quiz Finished");
                                                      alert.show();
                                                  }
                                              }

                                          }

        );

        // false button
        falseButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               removeFragment();
                                               newFragment();

                                               if(!quiz_questions.checkAnswer())
                                               {

                                                   quiz_questions.result++;
                                                   Toast.makeText(getApplicationContext(),"Correct", Toast.LENGTH_SHORT).show();}

                                           else{Toast.makeText(getApplicationContext(),"Wrong", Toast.LENGTH_SHORT).show();}
                                               quiz_questions.currentIndex++;
                                                   // check if reach the last question
                                                   if(quiz_questions.currentIndex<=4) {

                                                       progressBar.setProgress(quiz_questions.count);
                                                   }
                                                   else if(quiz_questions.currentIndex==5)
                                                   {
                                                       quiz_questions.currentIndex=4;
                                                       builder.setMessage("Your final score is " + quiz_questions.result +" out of "+ (quiz_questions.getSize())+" .")
                                                               .setCancelable(true)
                                                               .setPositiveButton("Repeat", new DialogInterface.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialog, int which) {
                                                                       Intent intent= getIntent();
                                                                       finish();
                                                                       startActivity(intent);
                                                                       quiz_questions.refresh();
                                                                       progressBar.setProgress(quiz_questions.count);
                                                                   }
                                                               })
                                                               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                                   @Override
                                                                   public void onClick(DialogInterface dialog, int which) {
                                                                       finish();
                                                                   }
                                                               });
                                                       AlertDialog alert = builder.create();
                                                       alert.setTitle("Quiz Finished");
                                                       alert.show();
                                                   }
                                               }


                                       }
        );

    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }




}