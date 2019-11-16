package com.example.minieducapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity{

    private EditText txtCorreo,txtClave;
    private Button btnEntrar;
    private AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtClave = (EditText) findViewById(R.id.txtClave);
        btnEntrar= (Button) findViewById(R.id.btnEntrar);
        cliente = new AsyncHttpClient();
        botonLogin();

        }
    private void botonLogin(){
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtCorreo.getText().toString().isEmpty() || txtClave.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Debe Llenar los dos campos", Toast.LENGTH_SHORT).show();
                    txtCorreo.setText("");
                    txtClave.setText("");
                }else{
                    String usu = txtCorreo.getText().toString().replace(" ","%20");
                    String pas = txtClave.getText().toString().replace(" ","%20");
                    String url = "https://app1movilmalj.000webhostapp.com/comprobarUsuario.php?Usuario="+usu+"&Password="+pas;
                    cliente.post(url, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            if(statusCode == 200) {
                                String respuesta = new String(responseBody);
                                if (respuesta.equalsIgnoreCase("null")) {
                                    Toast.makeText(MainActivity.this, "Error con correo y/o contraseña", Toast.LENGTH_SHORT).show();
                                    txtCorreo.setText("");
                                    txtClave.setText("");
                                } else {
                                    try {

                                        JSONObject jsonObj = new JSONObject(respuesta);
                                        Usuario u = new Usuario();
                                        u.setRut(jsonObj.getString("rut_usu"));
                                        u.setNombre(jsonObj.getString("nom_usu"));
                                        u.setCorreo(jsonObj.getString("cor_usu"));
                                        u.setContrasena(jsonObj.getString("cla_usu"));
                                        u.setNombre(jsonObj.getString("dir_usu"));
                                        u.setTelefono(jsonObj.getInt("tel_usu"));
                                        u.setId_tip(jsonObj.getInt("id_tip"));
                                        Intent i = null;
                                        switch(u.getId_tip()){
                                            case 1:
                                                u.setNom_tip("Administrador");
                                                i = new Intent(MainActivity.this,Menu.class);
                                                break;
                                            case 2:
                                                u.setNom_tip("Profesor");
                                                i = new Intent(MainActivity.this,Menu.class);
                                                break;
                                            case 3:
                                                u.setNom_tip("Apoderado");
                                                i = new Intent(MainActivity.this,Menu.class);
                                                break;
                                        }
                                        i.putExtra("u",u);
                                        startActivity(i);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(MainActivity.this, "Error Desconocido. Intentelo De Nuevo!!", Toast.LENGTH_SHORT).show();
                            txtCorreo.setText("");
                            txtClave.setText("");
                        }
                    });
                }
            }
        });
    } // Cierra el metodo botonLogin
    }

