package com.example.aven.kalkulator

import android.app.Activity
import android.content.SharedPreferences
import android.preference.PreferenceActivity
import android.os.Bundle
import android.preference.Preference


/**
 * Created by Aven on 2018-04-05.
 */
class SettingsActivity: PreferenceActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        val pref = findPreference(p1)
        pref.setSummary(p0?.getString(p1, ""))
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences
                .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences
                .unregisterOnSharedPreferenceChangeListener(this)
    }
}