package com.drew2u.getpsyched

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnu_about -> startActivity(Intent(this, AboutActivity::class.java))
            R.id.mnu_lsd_calculator -> startActivity(Intent(this, LsdToleranceCalculatorActivity::class.java))
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}