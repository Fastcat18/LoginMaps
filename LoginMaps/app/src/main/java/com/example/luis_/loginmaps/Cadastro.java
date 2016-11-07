package com.example.luis_.loginmaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Cadastro extends AppCompatActivity {

    EditText editNombre,editEmail2,editPassword2;
    Button btnCancelar,btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editNombre =(EditText)findViewById(R.id.editNombre);
        editEmail2 =(EditText)findViewById(R.id.editEmail2);
        editPassword2=(EditText)findViewById(R.id.editPassword2);
        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);

        btnCancelar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                finish();
            }
        });



    }
}
