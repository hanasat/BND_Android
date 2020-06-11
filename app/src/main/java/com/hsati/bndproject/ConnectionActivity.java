package com.hsati.bndproject;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ConnectionActivity extends AppCompatActivity {

    LocalActivityManager mLocalActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        mLocalActivityManager = new LocalActivityManager(this, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState);
        tabHost.setup(mLocalActivityManager);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab Client");//creer tabSpec utilisant tabHost
        TabHost.TabSpec tab2 = tabHost.newTabSpec("tab Coiffeur");

        tab1.setIndicator("CLIENT");
        tab1.setContent(new Intent(this, connexionClientActivity.class));

        tab2.setIndicator("COIFFEUSE");
        tab2.setContent(new Intent(this, connexionCoiffeuseActivity.class));

        tabHost.addTab(tab1);//ajouter tab client dans tab widget
        tabHost.addTab(tab2);

        //definir loblet selectionné
        tabHost.setCurrentTab(0);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(ConnectionActivity.this, tabId, Toast.LENGTH_SHORT).show();

            }
        });


    }


}
