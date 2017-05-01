package com.hfad.mindbreaker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment implements View.OnClickListener {
    private Button button1;
    private Button button2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        button1 = (Button) view.findViewById(R.id.polyglotButton);
        button1.setOnClickListener(this);
        button2 = (Button) view.findViewById(R.id.mathButton);
        button2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.polyglotButton:
                PolyglotFragment fragment1 = new PolyglotFragment();
                moveToFragment(fragment1);
                break;
            case R.id.mathButton:
                MathFragment fragment2 = new MathFragment();
                moveToFragment(fragment2);
                break;
        }
    }

    protected void moveToFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_frame, fragment, fragment.getClass().getSimpleName());
        ft.addToBackStack(null);
        ft.commit();

    }
}
