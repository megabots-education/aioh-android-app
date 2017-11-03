package br.com.escolamegabots.aioh.Settings.fragments;

/**
 * Created by Gustavo Freitas on 03/11/2017.
 */

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import br.com.escolamegabots.aioh.R;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DataSyncPreferenceFragment extends SettingsFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_data_sync);
        setHasOptionsMenu(true);

        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_sync_frequency)));
    }
}
