package com.drew2u.getpsyched

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import kotlin.system.exitProcess

private const val FIRST_RUN = "firstRun"

class LauncherActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        sharedPreferences = getSharedPreferences("com.drew2u.getpsyched", Context.MODE_PRIVATE)
        firstRunCheck(sharedPreferences)

        findViewById<Button>(R.id.btn_enter).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
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
                    sp.edit { putBoolean(FIRST_RUN, false) }
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