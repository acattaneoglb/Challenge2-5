package co.mobilemakers.cyoa_withpreferences;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class LosingFragment extends Fragment {

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_losing, container, false);

        prepareButton(view);

        return view;
    }


}
