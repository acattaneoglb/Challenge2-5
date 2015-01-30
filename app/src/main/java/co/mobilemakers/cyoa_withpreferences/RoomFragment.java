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
public class RoomFragment extends Fragment implements Levellable {

    public void setDifficulty(MainActivity.DifficultyEnum difficulty) {
        this.difficulty = difficulty;
    }

    MainActivity.DifficultyEnum difficulty;

    int winButton, loseButton;

    Button mButtonDoor1, mButtonDoor2;

    public RoomFragment() {
        // Required empty public constructor
    }

    protected class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int whichButton = 0;
            if (v == mButtonDoor2) {
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
        View view = inflater.inflate(R.layout.fragment_room, container, false);

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

        winButton = new Random().nextInt(2);
        loseButton = new Random().nextInt(2);

        prepareButtons();
    }

    private void prepareButtons() {
        ButtonListener listener = new ButtonListener();

        mButtonDoor1 = (Button)getActivity().findViewById(R.id.button_door_1);
        mButtonDoor2 = (Button)getActivity().findViewById(R.id.button_door_2);
        mButtonDoor1.setOnClickListener(listener);
        mButtonDoor2.setOnClickListener(listener);
    }

}
