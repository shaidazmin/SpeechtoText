package com.learningbaby.speechtotext;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    Button button;
    TextView textView;
    private  final  int REQ_CODE_SPEACH_INPUT = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.TextView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say someting");
                try {
                    startActivityForResult(intent,REQ_CODE_SPEACH_INPUT);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Sorry your device is not suppert Speech to text ",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_CODE_SPEACH_INPUT:
            {
                if(requestCode == RESULT_OK && null != data){
                    ArrayList <String> resultData = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String speechText = textView.getText().toString()+"\n"+ resultData.get(0);
                    textView.setText(speechText);
                  //  textView.setText(resultData[0]);

                }
                break;
            }
        }
    }
}
