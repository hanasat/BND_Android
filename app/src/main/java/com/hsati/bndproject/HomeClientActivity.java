package com.hsati.bndproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class HomeClientActivity extends AppCompatActivity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_client);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 25, 0, "compte");
        menu.add(0, 26, 0, "messagerie");
        menu.add(0, 27, 0, "mot de passe");

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 25:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("en cours de création");
                alertDialogBuilder.show();
                break;

            case 26:
                AlertDialog.Builder alertDialogBuilder2 = new AlertDialog.Builder(this);
                alertDialogBuilder2.setMessage("en cours de création");
                alertDialogBuilder2.show();
                break;

            case 27:
                AlertDialog.Builder alertDialogBuilder3 = new AlertDialog.Builder(this);
                alertDialogBuilder3.setMessage("en cours de création");
                alertDialogBuilder3.show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    public void SingOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, ConnectionActivity.class));
    }
}
