package co.mobilemakers.cyoa_withpreferences;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class AlleyFragment extends Fragment implements Levellable {
    int winButton, loseButton;

    Button mButtonLeft, mButtonRight, mButtonContinue;

    @Override
    public void setDifficulty(MainActivity.DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }

    MainActivity.DifficultyEnum difficulty;

    public AlleyFragment() {
        // Required empty public constructor
    }

    protected class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int whichButton = 2;
            if (v == mButtonLeft) {
                whichButton = 0;
            }
            else if (v == mButtonRight) {
                whichButton = 1;
            }

            boolean canWin = (whichButton == winButton);
            boolean canLose = (whichButton == loseButton);

            float random = new Random().nextFloat();

            float winChance, loseChance;

            if (difficulty == MainActivity.DifficultyEnum.EASY) {
                winChance = HIGH_CHANCE;
                loseChance = 1f - LOW_CHANCE;
            }
            else if (difficulty == MainActivity.DifficultyEnum.HARD) {
                winChance = LOW_CHANCE;
                loseChance = 1f - HIGH_CHANCE;
            }
            else {
                winChance = NORMAL_CHANCE;
                loseChance = 1f - NORMAL_CHANCE;
            }

            if (canWin && random < winChance) {
                ((MainActivity)getActivity()).winGame();
            }
            else if (canLose && random > loseChance) {
                ((MainActivity)getActivity()).loseGame();
            }
            else if (random < 0.5) {
                ((MainActivity)getActivity()).goToAlley(difficulty);
            }
            else {
                ((MainActivity)getActivity()).goToRoom(difficulty);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alley, container, false);

        int randomRed = new Random().nextInt(64) + 192;
        int randomGreen = new Random().nextInt(64) + 192;
        int randomBlue = new Random().nextInt(64) + 192;

        view.findViewById(R.id.background).setBackgroundColor((randomRed * 256 + randomGreen) * 256 + randomBlue
            + 0xFF000000);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        winButton = new Random().nextInt(3);
        loseButton = new Random().nextInt(3);

        prepareButtons();
    }

    private void prepareButtons() {
        ButtonListener listener = new ButtonListener();

        mButtonLeft = (Button)getActivity().findViewById(R.id.button_go_left);
        mButtonRight = (Button)getActivity().findViewById(R.id.button_go_right);
        mButtonContinue = (Button)getActivity().findViewById(R.id.button_continue);
        mButtonLeft.setOnClickListener(listener);
        mButtonRight.setOnClickListener(listener);
        mButtonContinue.setOnClickListener(listener);
    }
}
