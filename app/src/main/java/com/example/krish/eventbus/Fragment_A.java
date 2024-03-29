package com.example.krish.eventbus;

/**
 * Created by krish on 12/13/2016.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.krish.gsonwinuall.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class Fragment_A extends Fragment {

    private EventBus bus = EventBus.getDefault();
    private TextView userStatus;

    public Fragment_A() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a, container, false);
        userStatus = (TextView) rootView.findViewById(R.id.user_status);
        return rootView;
    }

    /**
     * Receiving Login event when it happens
     * */
    @Subscribe
    public void onLoginEvent(LoginEvent event){
        userStatus.setText("User Status : Logged in, userName: " + event.userName);
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this); // registering the bus
    }

    @Override
    public void onStop() {
        bus.unregister(this); // un-registering the bus
        super.onStop();
    }
}