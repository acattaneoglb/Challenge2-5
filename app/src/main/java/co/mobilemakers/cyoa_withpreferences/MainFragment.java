package co.mobilemakers.cyoa_withpreferences;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Random;

/**
 * Welcome fragment.
 *
 * Created by ariel.cattaneo on 23/01/2015.
 */
public class MainFragment extends Fragment {

    Button mButtonStart;

    RadioGroup mDifficultyGroup;

    HashMap<RadioButton, MainActivity.DifficultyEnum> difficultyByRadio;

    protected class ButtonStartListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int nStartPlace = new Random().nextInt(2);

            RadioButton chosenRadio = (RadioButton) getActivity().findViewById(mDifficultyGroup.getCheckedRadioButtonId());

            switch (nStartPlace) {
                case 0:
                    ((MainActivity)getActivity()).goToAlley(
                            difficultyByRadio.get(chosenRadio)
                    );
                    break;
                case 1:
                    ((MainActivity)getActivity()).goToRoom(
                            difficultyByRadio.get(chosenRadio)
                    );
                    break;
                default:
            }
        }
    }

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        controlsToVars();

        mButtonStart.setOnClickListener(new ButtonStartListener());
    }

    private void controlsToVars() {
        mButtonStart = (Button)getActivity().findViewById(R.id.button_start);

        mDifficultyGroup = (RadioGroup)getActivity().findViewById(R.id.difficulty_group);

        difficultyByRadio = new HashMap<>();
        difficultyByRadio.put((RadioButton)getActivity().findViewById(R.id.radio_button_easy),
                MainActivity.DifficultyEnum.EASY);
        difficultyByRadio.put((RadioButton)getActivity().findViewById(R.id.radio_button_normal),
                MainActivity.DifficultyEnum.NORMAL);
        difficultyByRadio.put((RadioButton)getActivity().findViewById(R.id.radio_button_hard),
                MainActivity.DifficultyEnum.HARD);
    }
}
