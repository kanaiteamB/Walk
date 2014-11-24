package com.example.walk;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Menu_Fragment extends Fragment {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container,
                false);
        RelativeLayout layout = (RelativeLayout) rootView;
        final Button mainbtn = (Button) layout.findViewById(R.id.main_btn);
        final Button mapbtn = (Button) layout.findViewById(R.id.map_btn);
        final Button battlebtn = (Button) layout.findViewById(R.id.battle_btn);
        mainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        mapbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });
        battlebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BattleActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

}
