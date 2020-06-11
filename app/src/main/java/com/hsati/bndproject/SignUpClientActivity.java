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

public class SignUpClientActivity extends AppCompatActivity {
    EditText etMail, etMdp, etFisrtName, etLastName;
    Button btnInscription;

    FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_client);

        mFirebaseAuth = FirebaseAuth.getInstance();
        etMail = findViewById(R.id.etMail);
        etMdp = findViewById(R.id.etMdp);
        btnInscription = findViewById(R.id.btnInscription);
        etFisrtName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);

        btnInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpClientActivity.this, connexionClientActivity.class));
            }
        });

        btnInscription.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(SignUpClientActivity.this, "les champs sont vide ! ", Toast.LENGTH_SHORT).show();
                else if (!(email.isEmpty() && mdp.isEmpty())) {
                    System.out.println("je passe");
                    mFirebaseAuth.createUserWithEmailAndPassword(email, mdp).addOnCompleteListener(SignUpClientActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            System.out.println("j'affiche le task " + task.isSuccessful());
                            if (!task.isSuccessful())
                                Toast.makeText(SignUpClientActivity.this, "encore un essaie  ! ", Toast.LENGTH_SHORT).show();
                            else
                                startActivity(new Intent(SignUpClientActivity.this, connexionClientActivity.class));
                        }
                    });
                } else
                    Toast.makeText(SignUpClientActivity.this, "Error Occurred! ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



