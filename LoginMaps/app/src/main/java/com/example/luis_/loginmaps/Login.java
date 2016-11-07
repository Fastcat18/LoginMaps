package com.example.luis_.loginmaps;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Login extends AppCompatActivity {

    EditText editDNI, editPassword;
    Button btnLogin;
    TextView txtCadastro;

    String url = "";
    String parametros = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editDNI = (EditText)findViewById(R.id.editDNI);
        editPassword =(EditText)findViewById(R.id.editPassword);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        txtCadastro = (TextView)findViewById(R.id.txtCadastro);

        txtCadastro.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent abreCadastro = new Intent(Login.this, Cadastro.class);
                startActivity(abreCadastro);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {

                    String DNI = editDNI.getText().toString();
                    String password = editPassword.getText().toString();

                    if (DNI.isEmpty() || password.isEmpty()){
                        Toast.makeText(getApplication(), "Campo puede estar vacio", Toast.LENGTH_LONG).show();
                    }else{

                        url = "http://192.168.1.38/login/get_login_2.php";

                        parametros ="dni" + DNI + "&password" + password  ;

                        new SolicitarDatos().execute(url);
                    }

                } else {
                    Toast.makeText(getApplication(), "Nueva conexion detecteda", Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    private class SolicitarDatos extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

                String r = Conexion.postDatos(urls[0], parametros);
            System.out.println("<--"+r);
                return r;

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String resultado) {

            if (resultado.equals("login_ok")){
                Intent abreIncio = new Intent(Login.this,Inicial.class);
                startActivity(abreIncio);
            }else {
                Toast.makeText(getApplication(), "Usuario o contraseña esta incorrecto", Toast.LENGTH_LONG).show();
            }
        }
    }
}
