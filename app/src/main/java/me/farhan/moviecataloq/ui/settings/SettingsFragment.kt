package me.farhan.moviecataloq.ui.settings

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import me.farhan.moviecataloq.App
import me.farhan.moviecataloq.R

/**
 * @author farhan
 * created at at 21:42 on 10/01/21.
 */
class SettingsFragment : PreferenceFragmentCompat() {
  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    setPreferencesFromResource(R.xml.preferences, rootKey)
    val preference = findPreference<Preference>(getString(R.string.pref_key_night))
    preference?.onPreferenceChangeListener = modeChangeListener
  }

  private val modeChangeListener =
    Preference.OnPreferenceChangeListener { preference, newValue ->
      Log.i("newValue", newValue.toString())
      newValue as? String
      when (newValue) {
        getString(R.string.pref_night_on) -> {
          updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
          App.getInstance().setIsNightModeEnabled(AppCompatDelegate.MODE_NIGHT_YES)
        }
        getString(R.string.pref_night_off) -> {
          updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
          App.getInstance().setIsNightModeEnabled(AppCompatDelegate.MODE_NIGHT_NO)
        }
        else -> {
          updateTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
          App.getInstance().setIsNightModeEnabled(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
      }
      true
    }

  private fun updateTheme(nightMode: Int): Boolean {
    AppCompatDelegate.setDefaultNightMode(nightMode)
    requireActivity().recreate()
    return true
  }
}