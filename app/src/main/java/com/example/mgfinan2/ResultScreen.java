package com.example.mgfinan2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ResultScreen extends AppCompatActivity {

    Button btn_voltar_result;
    EditText editTextNumber4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        btn_voltar_result = (Button) findViewById(R.id.btn_voltar_result);
        btn_voltar_result.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(ResultScreen.this, ActMain.class);
                startActivity(intent5);
            }
        });
    }
}