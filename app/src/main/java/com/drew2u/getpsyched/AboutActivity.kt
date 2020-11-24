package com.drew2u.getpsyched

/**
 * TODO Add information about LSD-25 to R.id.tv_lsd_about
 */

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
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
}