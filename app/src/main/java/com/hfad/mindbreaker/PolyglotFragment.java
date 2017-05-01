package com.hfad.mindbreaker;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;

import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.SparseArray;
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
public class PolyglotFragment extends Fragment implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {

    private int randomNum;
    private static int prevValue = 0;

    private Button button1;
    private Button button2;

    private View view;

    private EditText missingLetter;

    private String replaced;

    SparseArray<String> arrayForBegginers = new SparseArray<>();
    SparseArray<String> arrayForKnowing = new SparseArray<>();
    SparseArray<String> arrayForGods = new SparseArray<>();

    Toast toast;
    Context context;
    CharSequence text;
    int duration = Toast.LENGTH_SHORT;
    Editable missingChar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_polyglot, container, false);
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        randomNum = setRandomNum();
        fillArray();
        String editedWord = giveNewWord(randomNum);
        prevValue = randomNum;
        TextView newWord = (TextView) view.findViewById(R.id.new_word);
        newWord.setText(editedWord);
        missingLetter = (EditText) view.findViewById(R.id.missingLetter);
        missingLetter.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        button1 = (Button) view.findViewById(R.id.checkAnswer);
        button1.setOnClickListener(this);
        button2 = (Button) view.findViewById(R.id.takeHint);
        button2.setOnClickListener(this);
        return view;
    }

    public String giveNewWord(int number) {
        String word = "";
        if (number == prevValue) {
            number = setRandomNum();
        }
        if (MainActivity.difficulty == 1) {
            word = arrayForBegginers.get(number, "Error in arrayForBegginners");
        }
        if (MainActivity.difficulty == 2) {
            word = arrayForKnowing.get(number, "Error in arrayForKnowing");
        }
        if (MainActivity.difficulty == 3) {
            word = arrayForGods.get(number, "Error in arrayForGods");
        }
        final int length = word.length();
        final int i = (int) (Math.random() * length);
        word = editWord(word, i, '*');
        return word;
    }

    public int setRandomNum() {
        return randomNum = (int) (Math.random() * 11);
    }

    public void fillArray() {
        if (MainActivity.difficulty == 1) {
            arrayForBegginers.append(0, "apple");
            arrayForBegginers.append(1, "banana");
            arrayForBegginers.append(2, "tomato");
            arrayForBegginers.append(3, "orange");
            arrayForBegginers.append(4, "kiwi");
            arrayForBegginers.append(5, "coconut");
            arrayForBegginers.append(6, "cabbage");
            arrayForBegginers.append(7, "carrot");
            arrayForBegginers.append(8, "cucumber");
            arrayForBegginers.append(9, "pepper");
            arrayForBegginers.append(10, "pumpkin");
        }
        if (MainActivity.difficulty == 2) {
            arrayForKnowing.append(0, "empathy");
            arrayForKnowing.append(1, "fabricate");
            arrayForKnowing.append(2, "flabbergasted");
            arrayForKnowing.append(3, "gratuitous");
            arrayForKnowing.append(4, "impudent");
            arrayForKnowing.append(5, "insatiable");
            arrayForKnowing.append(6, "inveterate");
            arrayForKnowing.append(7, "myriad");
            arrayForKnowing.append(8, "panacea");
            arrayForKnowing.append(9, "refurbish");
            arrayForKnowing.append(10, "truculent");
        }
        if (MainActivity.difficulty == 3) {
            arrayForGods.append(0, "ameliorate");
            arrayForGods.append(1, "advantageous");
            arrayForGods.append(2, "erroneous");
            arrayForGods.append(3, "deleterious");
            arrayForGods.append(4, "endeavo");
            arrayForGods.append(5, "leverage");
            arrayForGods.append(6, "ameliorate");
            arrayForGods.append(7, "promulgate");
            arrayForGods.append(8, "regarding");
            arrayForGods.append(9, "remuneration");
            arrayForGods.append(10, "subsequently");
        }
    }

    private String editWord(String str, int index, char replace) {
        if (str == null) {
            return str;
        } else if (index < 0 || index >= str.length()) {
            return str;
        }
        char[] chars = str.toCharArray();
        replaced = String.valueOf(chars[index]);
        chars[index] = replace;
        return String.valueOf(chars);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkAnswer:
                String inputNumber = missingLetter.getText().toString();
                if (TextUtils.isEmpty(inputNumber) || inputNumber.length() == 0) {
                    missingLetter.setError("Cannot be empty !");
                    return;
                } else {
                    checkAnswer();
                }
                break;
            case R.id.takeHint:
                takeHint();
                break;
        }
    }

    private void checkAnswer() {
        missingChar = missingLetter.getText();
        context = view.getContext();
        if ((missingChar.toString()).equals(replaced)) {

            text = "You're right!";

            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            missingLetter.setText("");
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(this).attach(this).commit();
            missingLetter.setFocusable(true);

        } else {
            missingLetter.setText("");
            text = "You're wrong! Try another letter or take hint.";
            toast = Toast.makeText(context, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    private void takeHint() {
        missingChar = missingLetter.getText();
        context = view.getContext();

        text = "Try " + "'" + replaced + "'";

        toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_complexity:
                //view, GRAVITTY.Right when AS update for VD
                PopupMenu popup = null;
                popup = new PopupMenu(view.getContext(), view);
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

    private void replace() {
        Fragment fragment = new MathFragment();
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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
}
