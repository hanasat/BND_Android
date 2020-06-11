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

public class connexionCoiffeuseActivity extends AppCompatActivity {
    EditText etMail, etMdp;
    Button btnConnexion;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_coiffeuse);

        mFirebaseAuth = FirebaseAuth.getInstance();
        etMail = findViewById(R.id.etIemailCoiffeuse);
        etMdp = findViewById(R.id.etmdpCoiffeuse);
        btnConnexion = findViewById(R.id.btnConnexionCoiffeuse);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(connexionCoiffeuseActivity.this, "vous êtes connecté", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(connexionCoiffeuseActivity.this, HomeClientActivity.class);
                    startActivity(i);
                } else
                    Toast.makeText(connexionCoiffeuseActivity.this, "veuillez vous connecter", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(connexionCoiffeuseActivity.this, "les champs sont vide ! ", Toast.LENGTH_SHORT).show();
                else if (!(email.isEmpty() && mdp.isEmpty()))
                    mFirebaseAuth.signInWithEmailAndPassword(email, mdp).addOnCompleteListener(connexionCoiffeuseActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful())
                                Toast.makeText(connexionCoiffeuseActivity.this, "informations irronées , Merci de réessayer  ! ", Toast.LENGTH_SHORT).show();
                            else {
                                Intent intToHome = new Intent(connexionCoiffeuseActivity.this, HomeCoiffeuseActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                else
                    Toast.makeText(connexionCoiffeuseActivity.this, "encore erreur ! ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }


}
