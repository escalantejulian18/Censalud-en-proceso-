package com.julianescalante.clinic;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.julianescalante.clinic.view.ContainerActivity;
import com.julianescalante.clinic.view.CreateAccountActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText TextEmail;
    private TextInputEditText TextPass;
    private TextView create;
    private Button botonLogin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        mAuthFirebase = (new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    Intent intent = new Intent(MainActivity.this, ContainerActivity.class);
                    startActivity(intent);
                }
            }
        });


        TextEmail = (TextInputEditText) findViewById(R.id.username);
        TextPass = (TextInputEditText) findViewById(R.id.password);
        botonLogin = (Button) findViewById(R.id.login);
        create = (TextView) findViewById(R.id.createHere);


        progressDialog = new ProgressDialog(this);

        botonLogin.setOnClickListener(this);
        create.setOnClickListener(this);
    }

    private void userLogin(){
        String email = TextEmail.getText().toString().trim();
        String password  = TextPass.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Ingresar email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Ingresar contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Cargando");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();

                        } else {

                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(MainActivity.this, "Ese usuario no existe", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthFirebase);
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUi(currentUser);
    }

    private void updateUi(FirebaseUser currentUser) {
    }

    @Override
    public void onClick(View view) {
        if(view == botonLogin){
            userLogin();
        }
        if(view == create){
            startActivity(new Intent(MainActivity.this, CreateAccountActivity.class));
        }
    }
}
