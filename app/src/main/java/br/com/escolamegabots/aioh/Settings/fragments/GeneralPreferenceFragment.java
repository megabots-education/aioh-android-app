package br.com.escolamegabots.aioh.Settings.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import br.com.escolamegabots.aioh.R;

/**
 * Created by Gustavo Freitas on 03/11/2017.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class GeneralPreferenceFragment extends SettingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        setHasOptionsMenu(true);

        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_user_name)));
    }
}
