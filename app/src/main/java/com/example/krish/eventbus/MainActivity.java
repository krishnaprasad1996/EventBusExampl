package com.example.krish.eventbus;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.krish.gsonwinuall.R;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by krish on 12/13/2016.
 */

public class MainActivity extends AppCompatActivity {
    private Button loginBtn;
    private Button secondActivityBtn;
    private EditText userName;

    private EventBus bus = EventBus.getDefault();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = (Button) findViewById(R.id.login_btn);
        secondActivityBtn = (Button) findViewById(R.id.second_activity_btn);
        userName = (EditText) findViewById(R.id.user_name);

        // that would show the FragmentA into the frameLayout
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, new Fragment_A())
                .commit();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if userName was empty, then show an error,
                // else, login user and send logged userName to all subscribers
                if (userName.getText().toString().isEmpty()) {
                    userName.setError("Please enter username");
                } else {
                    /**
                     * Send this event to all subscribers
                     * We use here post sticky because the secondActivity is not registered yet in the bus
                     * (registration happens when activity created) and it will not have any updates
                     * if we posted event normally not sticky
                     * */
                    bus.postSticky(new LoginEvent(userName.getText().toString()));
                }
            }
        });

        secondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }
}
