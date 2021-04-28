package com.example.mgfinan2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {

    Button btn_cadDespesa,btn_excluir_principal, button_ult_lanc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        button_ult_lanc = (Button) findViewById(R.id.button_ult_lanc);
        button_ult_lanc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent1 = new Intent(MenuPrincipal.this, ResultScreen.class);
                startActivity(intent1);
            }
        });

        btn_cadDespesa = (Button) findViewById(R.id.btn_cadDespesa);
        btn_cadDespesa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MenuPrincipal.this, CadDespesas.class);
                startActivity(intent2);
            }
        });

        btn_excluir_principal = (Button) findViewById(R.id.btn_excluir_principal);
        btn_excluir_principal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MenuPrincipal.this, MainActivity.class);
                startActivity(intent3);
            }
        });
    }
}