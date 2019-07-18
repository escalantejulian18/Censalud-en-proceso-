package com.julianescalante.clinic.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.julianescalante.clinic.MainActivity;
import com.julianescalante.clinic.R;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText TextEmail;
    private TextInputEditText TextPass;
    private Button botonUnir;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            // finish();
            startActivity(new Intent(CreateAccountActivity.this, ContainerActivity.class));
        }

        TextEmail = (TextInputEditText) findViewById(R.id.email);
        TextPass = (TextInputEditText) findViewById(R.id.password_createaccount);
        botonUnir = (Button) findViewById(R.id.joinUs);


        progressDialog = new ProgressDialog(this);

        botonUnir.setOnClickListener(this);
    }
    private void registrar(){

        //getting email and password from edit texts
        String email = TextEmail.getText().toString().trim();
        String password  = TextPass.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Ingresar email", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Ingresar contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Registrando usuario");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            // finish();
                            startActivity(new Intent(CreateAccountActivity.this, MainActivity.class));
                        }else {
                            Toast.makeText(CreateAccountActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                        }

                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view == botonUnir){
            registrar();
        }


    }
}

