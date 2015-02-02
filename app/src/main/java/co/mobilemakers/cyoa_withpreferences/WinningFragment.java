package co.mobilemakers.cyoa_withpreferences;


import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class WinningFragment extends Fragment {

    TextView mTextWinning;
    Button mButtonRestart;

    public WinningFragment() {
        // Required empty public constructor
    }

    private void prepareButton(View view) {
        mButtonRestart = (Button)view.findViewById(R.id.button_restart);
        mButtonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).restartGame();
            }
        });
    }

    private void updateText(View view) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String userName = sharedPreferences.getString(getResources().getString(R.string.key_pref_username),
                getResources().getString(R.string.default_name));

        mTextWinning = (TextView)view.findViewById(R.id.text_winning_message);
        mTextWinning.setText(String.format(getResources().getString(R.string.winning_text),
                userName));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_winning, container, false);

        prepareButton(view);

        updateText(view);

        return view;
    }


}
