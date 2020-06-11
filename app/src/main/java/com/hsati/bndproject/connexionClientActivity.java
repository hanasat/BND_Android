package com.hsati.bndproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class connexionClientActivity extends AppCompatActivity {

    EditText etMail, etMdp;
    Button btnConnexion;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_client);

        mFirebaseAuth = FirebaseAuth.getInstance();
        etMail = findViewById(R.id.etMail);
        etMdp = findViewById(R.id.etMdp);
        btnConnexion = findViewById(R.id.btnConnexion);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(connexionClientActivity.this, "vous êtes connecté", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(connexionClientActivity.this, HomeClientActivity.class);
                    startActivity(i);
                } else
                    Toast.makeText(connexionClientActivity.this, "veuillez vous connecter", Toast.LENGTH_SHORT).show();
            }
        };

        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etMail.getText().toString();
                String mdp = etMdp.getText().toString();
                if (email.isEmpty()) {
                    etMail.setError("Merci de saisir votre email! ");
                    etMail.requestFocus();
                } else if (mdp.isEmpty()) {
                    etMdp.setError("Merci de saisir votre mot de passe ! ");
                    etMdp.requestFocus();
                } else if (email.isEmpty() && mdp.isEmpty())
                    Toast.makeText(connexionClientActivity.this, "les champs sont vide ! ", Toast.LENGTH_SHORT).show();
                else if (!(email.isEmpty() && mdp.isEmpty()))
                    mFirebaseAuth.signInWithEmailAndPassword(email, mdp).addOnCompleteListener(connexionClientActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful())
                                Toast.makeText(connexionClientActivity.this, "informations irronées , Merci de réessayer  ! ", Toast.LENGTH_SHORT).show();
                            else {
                                Intent intToHome = new Intent(connexionClientActivity.this, HomeClientActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                else
                    Toast.makeText(connexionClientActivity.this, "encore erreur ! ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }


    public void OnClickInscription(View view) {
        startActivity(new Intent(connexionClientActivity.this, SignUpClientActivity.class));
    }
}


        /* ********* CREATION DU COMPTE *****************
        btnConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etMail.getText().toString();
                String mdp = etMdp.getText().toString();
                if(email.isEmpty()){
                    etMail.setError("Merci de saisir votre email! ");
                    etMail.requestFocus();
                }else if (mdp.isEmpty()){
                    etMdp.setError("Merci de saisir votre mot de passe ! ");
                    etMdp.requestFocus();
                }else if(email.isEmpty() && mdp.isEmpty()){
                    Toast.makeText(connexionClientActivity.this, "les champs sont vide ! ", Toast.LENGTH_SHORT).show();
                }else if(!(email.isEmpty() && mdp.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,mdp).addOnCompleteListener(connexionClientActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(connexionClientActivity.this, "encore un essaie  ! ", Toast.LENGTH_SHORT).show();
                            }else{
                                startActivity(new Intent(connexionClientActivity.this, HomeActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(connexionClientActivity.this, "Error Occurred! ", Toast.LENGTH_SHORT).show();
                }
            }
        });

         */









