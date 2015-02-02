package co.mobilemakers.cyoa_withpreferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Settings fragment class
 *
 * Created by ariel.cattaneo on 02/02/2015.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
    }
}
