package br.com.escolamegabots.aioh.Settings.fragments;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import br.com.escolamegabots.aioh.R;

/**
 * Created by Gustavo Freitas on 03/11/2017.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DeveloperSettingsFragment extends SettingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_developer);
        setHasOptionsMenu(true);

        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_aioh_address)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_aioh_service_name)));
    }
}
