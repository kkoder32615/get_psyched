package com.drew2u.getpsyched

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.drew2u.getpsyched.databinding.ActivityMainBinding
import kotlin.system.exitProcess

private const val FIRST_RUN = "firstRun"

class MainActivity : AppCompatActivity() {
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.tvLsdAbout.movementMethod = ScrollingMovementMethod()
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("com.drew2u.getpsyched", Context.MODE_PRIVATE)
        firstRunCheck(sharedPrefs)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnu_lsd_calculator -> startActivity(Intent(this, LsdToleranceCalculatorActivity::class.java))
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return true
    }

    /**
     * Checks if the terms have been accepted or not. If the have not, the user is prompted to
     * accept terms or exit the app.
     */
    private fun firstRunCheck(sp: SharedPreferences) {
        if (sp.getBoolean(FIRST_RUN, true)) {
            with(AlertDialog.Builder(this)) {
                setTitle(getString(R.string.welcome))
                setMessage(getString(R.string.first_run_message))
                setPositiveButton(getString(R.string.agree), fun(_, _) {
                    sharedPrefs.edit { putBoolean(FIRST_RUN, false) }
                })
                setNegativeButton(getString(R.string.exit), fun(_, _) {
                    exitProcess(0)
                })
                setCancelable(true)
                show()
            }
        }
    }
}