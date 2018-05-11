package com.openlab.dinamicthemes

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.openlab.dinamicthemes.PreferenceHelper.getPreferences
import com.openlab.dinamicthemes.PreferenceHelper.get
import com.openlab.dinamicthemes.PreferenceHelper.set
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var theme: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getSavedTheme())
        setContentView(R.layout.activity_main)
        btnNight.setOnClickListener { saveTheme(getString(R.string.night)) }
        btnDay.setOnClickListener { saveTheme(getString(R.string.day)) }
    }

    private fun saveTheme(theme: String) = if (this.theme != theme) {
        getPreferences(this)[Constants.SP_THEME] = theme
        recreate()
    } else Snackbar.make(findViewById(R.id.container), "the $theme is already active", Snackbar.LENGTH_SHORT).show()

    private fun getSavedTheme(): Int {
        this.theme = getPreferences(this)[Constants.SP_THEME, getString(R.string.night)] as String
        return when (this.theme) {
            getString(R.string.day) -> R.style.AppTheme_LightTheme
            getString(R.string.night) -> R.style.AppTheme_DarkTheme
            else -> R.style.AppTheme_LightTheme
        }
    }
}