package com.drew2u.getpsyched

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class LsdToleranceCalculatorActivity : AppCompatActivity() {

    private lateinit var etLsdDaysSinceLast: EditText
    private lateinit var etLsdDesiredDose: EditText
    private lateinit var etLsdLastDose: EditText
    private lateinit var tvLsdCalculatorResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lsd_tolerance_calculator)

        etLsdDaysSinceLast = findViewById(R.id.et_lsd_days_since_last)
        etLsdDesiredDose = findViewById(R.id.et_lsd_desired_dose)
        etLsdLastDose = findViewById(R.id.et_lsd_last_dose)
        tvLsdCalculatorResult = findViewById(R.id.tv_lsd_calculator_result)

        // Posts calculations on user data to a TextView
        findViewById<Button>(R.id.btn_lsd_calculate).setOnClickListener { postResults() }

        // Clears all entered data and results
        findViewById<Button>(R.id.btn_lsd_reset).setOnClickListener { clearEnteredData() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnu_about -> startActivity(Intent(this, AboutActivity::class.java))
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    /**
     * Clears entered data and sets results view back to default message
     */
    private fun clearEnteredData() {
        etLsdLastDose.setText("")
        etLsdDesiredDose.setText("")
        etLsdDaysSinceLast.setText("")
        tvLsdCalculatorResult.setText(R.string.please_enter_a_number_between_1_and_12_full_tolerance_reset_occurs_after_12_days)
    }

    /**
     * Captures and converts entered data, then posts the results to a TextView
     */
    private fun postResults() = try {
        val lastDose = Integer.parseInt(etLsdLastDose.text.toString())
        val desiredDose = Integer.parseInt(etLsdDesiredDose.text.toString())
        val days = Integer.parseInt(etLsdDaysSinceLast.text.toString())
        val result = calculateTolerance(lastDose, desiredDose, days)
        tvLsdCalculatorResult.setText(result.toString())
    } catch (e: NumberFormatException) {
        alertDialog(getString(R.string.incorrect_number), getString(R.string.please_enter_number), getString(R.string.okay), this)
    }

    /**
     * Takes three params to calculate the approximate dosage to take. Creates alert dialog if
     * invalid number is entered
     */
    private fun calculateTolerance(lastDose: Int, desiredDose: Int, days: Int): Long {
        return when (days) {
            in 1..12 -> (desiredDose + (((lastDose / 100) * 280.059565 * days.toDouble().pow(0.412565956)) - lastDose)).toLong()
            else -> {
                alertDialog(getString(R.string.incorrect_number), getString(R.string.please_enter_a_number_between_1_and_12_full_tolerance_reset_occurs_after_12_days), getString(R.string.okay), this)
                clearEnteredData()
                0
            }
        }
    }
}