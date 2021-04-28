package com.example.mgfinan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResultScreen extends AppCompatActivity {

    Button btn_voltar_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        btn_voltar_result = (Button) findViewById(R.id.btn_voltar_result);
        btn_voltar_result.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(ResultScreen.this, MenuPrincipal.class);
                startActivity(intent4);
            }
        });
    }
}