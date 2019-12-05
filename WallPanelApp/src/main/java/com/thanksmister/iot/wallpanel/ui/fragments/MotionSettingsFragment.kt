/*
 * Copyright (c) 2019 ThanksMister LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thanksmister.iot.wallpanel.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.preference.SwitchPreference
import androidx.preference.EditTextPreference
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.Navigation
import com.thanksmister.iot.wallpanel.R
import com.thanksmister.iot.wallpanel.ui.activities.SettingsActivity
import dagger.android.support.AndroidSupportInjection

class MotionSettingsFragment : BaseSettingsFragment() {

    private var motionDetectionPreference: SwitchPreference? = null
    private var motionWakePreference: SwitchPreference? = null
    private var motionClearPreference: EditTextPreference? = null
    private var motionLeniencyPreference: EditTextPreference? = null
    private var motionLumaPreference: EditTextPreference? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if((activity as SettingsActivity).supportActionBar != null) {
            (activity as SettingsActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            (activity as SettingsActivity).supportActionBar!!.setDisplayShowHomeEnabled(true)
            (activity as SettingsActivity).supportActionBar!!.title = (getString(R.string.title_motion_settings))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_help, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            view?.let { Navigation.findNavController(it).navigate(R.id.camera_action) }
            return true
        } else if (id == R.id.action_help) {
            showSupport()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_motion)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        motionDetectionPreference = findPreference<SwitchPreference>(getString(R.string.key_setting_camera_motionenabled)) as SwitchPreference
        motionWakePreference = findPreference<SwitchPreference>(getString(R.string.key_setting_camera_motionwake)) as SwitchPreference
        motionLeniencyPreference = findPreference<EditTextPreference>(getString(R.string.key_setting_camera_motionleniency)) as EditTextPreference
        motionLumaPreference = findPreference<EditTextPreference>(getString(R.string.key_setting_camera_motionminluma)) as EditTextPreference
        motionClearPreference = findPreference<EditTextPreference>(getString(R.string.key_setting_motion_clear)) as EditTextPreference

        bindPreferenceSummaryToValue(motionDetectionPreference!!)
        bindPreferenceSummaryToValue(motionWakePreference!!)
        bindPreferenceSummaryToValue(motionLeniencyPreference!!)
        bindPreferenceSummaryToValue(motionLumaPreference!!)
        bindPreferenceSummaryToValue(motionClearPreference!!)
    }
}