package com.mishaismenska.android_2020_task_6.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.mishaismenska.android_2020_task_6.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)
    }
}
