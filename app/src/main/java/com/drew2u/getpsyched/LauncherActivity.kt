package com.drew2u.getpsyched

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LauncherActivity : AppCompatActivity() {

    private lateinit var btnEnter: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        findViewById<Button>(R.id.btn_enter).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }
}