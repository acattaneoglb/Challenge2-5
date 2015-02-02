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
public class LosingFragment extends Fragment {

    TextView mTextLosing;
    Button mButtonRestart;

    public LosingFragment() {
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

        mTextLosing = (TextView)view.findViewById(R.id.text_losing_message);
        mTextLosing.setText(String.format(getResources().getString(R.string.losing_text),
                userName));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_losing, container, false);

        prepareButton(view);

        updateText(view);

        return view;
    }


}
