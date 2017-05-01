package com.hfad.mindbreaker;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MathFragment extends Fragment implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    private View view;
    private int randomNum;
    private int replacedNumber;
    private int guessedNumber;

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;

    private EditText editText;

    private Button button3;
    private Button button4;


    Toast toast;
    Context context;
    CharSequence text;
    int duration = Toast.LENGTH_SHORT;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_math, container, false);
        setHasOptionsMenu(true);
        randomNum = setRandomNum();
        initializate();
        createMatrix(MainActivity.difficulty, randomNum);
        return view;
    }

    private void initializate() {
        textView1 = (TextView) view.findViewById(R.id.text1);
        textView2 = (TextView) view.findViewById(R.id.text2);
        textView3 = (TextView) view.findViewById(R.id.text3);
        textView4 = (TextView) view.findViewById(R.id.text4);
        textView5 = (TextView) view.findViewById(R.id.text5);
        textView6 = (TextView) view.findViewById(R.id.text6);
        textView7 = (TextView) view.findViewById(R.id.text7);
        textView8 = (TextView) view.findViewById(R.id.text8);
        textView9 = (TextView) view.findViewById(R.id.text9);

        editText = (EditText) view.findViewById(R.id.missingNumberMath);

        button3 = (Button) view.findViewById(R.id.checkAnswerMath);
        button3.setOnClickListener(this);
        button4 = (Button) view.findViewById(R.id.takeHintMath);
        button4.setOnClickListener(this);
    }

    private void createMatrix(int difficulty, int random) {
        int[][] array = new int[3][3];
        if (difficulty == 1) {
            array[0][0] = random;
            array[0][1] = (random * 2 + 1);
            array[0][2] = ((array[0][1]) * 2 + 1);
            array[1][0] = ((random + 3));
            array[1][1] = ((random + 3) * 2 + 1);
            array[1][2] = ((array[1][1]) * 2 + 1);
            array[2][0] = ((random + 5));
            array[2][1] = ((random + 5) * 2 + 1);
            array[2][2] = ((array[2][1] + 5) * 2 + 1);
        }

        if (difficulty == 2) {
            array[0][0] = random;
            array[0][1] = (random + 2);
            array[0][2] = ((array[0][1]) + 2);
            array[1][0] = ((random + 4));
            array[1][1] = ((random) * 3);
            array[1][2] = ((array[0][1]) * 3);
            array[2][0] = ((array[1][0]) + 4);
            array[2][1] = ((array[1][0]) * 3);
            array[2][2] = ((array[1][1]) * 3);
        }

        if (difficulty == 3) {
            array[0][0] = random;
            array[0][1] = (random * 2 - 3);
            array[0][2] = ((array[0][1]) * 2 - 5);
            array[1][0] = ((random + 2));
            array[1][1] = ((array[1][0]) * 2 - 3);
            array[1][2] = ((array[1][1]) * 2 - 5);
            array[2][0] = ((random + 5));
            array[2][1] = ((array[2][0]) * 2 + 3);
            array[2][2] = ((array[2][1] + 5) * 2 + 5);
        }

        replaceMatrix(array, random);
    }

    private void replaceMatrix(int randomArrray[][], int random) {

        String k1 = String.valueOf(randomArrray[0][0]);
        String k2 = String.valueOf(randomArrray[0][1]);
        String k3 = String.valueOf(randomArrray[0][2]);
        String k4 = String.valueOf(randomArrray[1][0]);
        String k5 = String.valueOf(randomArrray[1][1]);
        String k6 = String.valueOf(randomArrray[1][2]);
        String k7 = String.valueOf(randomArrray[2][0]);
        String k8 = String.valueOf(randomArrray[2][1]);
        String k9 = String.valueOf(randomArrray[2][2]);

        textView1.setText(k1);
        textView2.setText(k2);
        textView3.setText(k3);
        textView4.setText(k4);
        textView5.setText(k5);
        textView6.setText(k6);
        textView7.setText(k7);
        textView8.setText(k8);
        textView9.setText(k9);

        switch (random) {
            case (1):
                replacedNumber = randomArrray[0][0];
                textView1.setText("*");
                break;
            case (2):
                replacedNumber = (randomArrray[0][1]);
                textView2.setText("*");
                break;
            case (3):
                replacedNumber = (randomArrray[0][2]);
                textView3.setText("*");
                break;
            case (4):
                replacedNumber = (randomArrray[1][0]);
                textView4.setText("*");
                break;
            case (5):
                replacedNumber = (randomArrray[1][1]);
                textView5.setText("*");
                break;
            case (6):
                replacedNumber = (randomArrray[1][2]);
                textView6.setText("*");
                break;
            case (7):
                replacedNumber = (randomArrray[2][0]);
                textView7.setText("*");
                break;
            case (8):
                replacedNumber = (randomArrray[2][1]);
                textView8.setText("*");
                break;
            case (9):
                replacedNumber = (randomArrray[2][2]);
                textView9.setText("*");
                break;
            default:
                replacedNumber = (randomArrray[2][2]);
                textView9.setText("*");
                break;
        }

    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_complexity:
                //view, GRAVITTY.Right when AS update for VD
                PopupMenu popup = new PopupMenu(view.getContext(), view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.actions, popup.getMenu());
                popup.setOnMenuItemClickListener(this);
                popup.show();
                return true;


            case R.id.action_close:
                System.exit(0);
                return true;
            case R.id.action_switch:
                replace();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
            switch (item.getItemId()) {
                case R.id.menu_begginer:
                    MainActivity.difficulty = 1;
                    return true;
                case R.id.menu_knowing:
                    MainActivity.difficulty = 2;
                    return true;
                case R.id.menu_unstoppable:
                    MainActivity.difficulty = 3;
                    return true;
                default:
                    return false;
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkAnswerMath:
                String inputNumber = editText.getText().toString();
                if (TextUtils.isEmpty(inputNumber) || inputNumber.length() == 0) {
                    editText.setError("Cannot be empty !");
                    return;
                } else {
                    checkAnswer();
                }
                break;
            case R.id.takeHintMath:
                takeHint();
                break;
        }
    }

    private void takeHint() {
        context = view.getContext();

        text = "Try " + "'" + replacedNumber + "'";

        toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void checkAnswer() {
        context = view.getContext();

        String inputNumber = editText.getText().toString();

        guessedNumber = Integer.parseInt(inputNumber);
        if (guessedNumber <= (-10)) {
            editText.setError("Input wrong number. Repeat !");
        }


        if (guessedNumber == replacedNumber) {
            text = "You're right!";

            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            randomNum = setRandomNum();
            createMatrix(MainActivity.difficulty, randomNum);

            editText.setText("");
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();

            editText.setFocusable(true);

        } else {
            editText.setText("");
            text = "You're wrong! Try another number or take hint.";
            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }


    /**
     * other methods
     */
    public int setRandomNum() {
        randomNum = (int) (Math.random() * 7);
        if (randomNum == 0) {
            randomNum = setRandomNum();
        }
        return randomNum;
    }

    private void replace() {
        Fragment fragment = new PolyglotFragment();
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}



