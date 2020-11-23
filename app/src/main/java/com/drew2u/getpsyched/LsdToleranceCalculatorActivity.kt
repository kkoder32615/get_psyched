package com.drew2u.getpsyched

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.drew2u.getpsyched.databinding.ActivityLsdToleranceCalculatorBinding
import kotlin.math.pow

class LsdToleranceCalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLsdToleranceCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Posts calculations on user data to a TextView
        binding.btnLsdCalculate.setOnClickListener { postResults(binding) }

        // Clears all entered data and results
        binding.btnLsdReset.setOnClickListener { clearEnteredData(binding) }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnu_about -> startActivity(Intent(this, MainActivity::class.java))
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    /**
     * Clears entered data and sets results view back to default message
     */
    private fun clearEnteredData(binding: ActivityLsdToleranceCalculatorBinding) {
        binding.etLsdLastDose.setText("")
        binding.etLsdDesiredDose.setText("")
        binding.etLsdDaysSinceLast.setText("")
        binding.tvLsdCalculatorResult.setText(R.string.please_enter_a_number_between_1_and_12_full_tolerance_reset_occurs_after_12_days)
    }

    /**
     * Captures and converts entered data, then posts the results to a TextView
     */
    private fun postResults(binding: ActivityLsdToleranceCalculatorBinding) = try {
        val lastDose = Integer.parseInt(binding.etLsdLastDose.text.toString())
        val desiredDose = Integer.parseInt(binding.etLsdDesiredDose.text.toString())
        val days = Integer.parseInt(binding.etLsdDaysSinceLast.text.toString())
        val result = calculateTolerance(lastDose, desiredDose, days, binding)
        binding.tvLsdCalculatorResult.text = result.toString()
    } catch (e: NumberFormatException) {
        alertDialog(getString(R.string.incorrect_number), getString(R.string.please_enter_number), getString(R.string.okay), this)
    }

    /**
     * Takes three params to calculate the approximate dosage to take. Creates alert dialog if
     * incorrect number is entered
     */
    private fun calculateTolerance(lastDose: Int, desiredDose: Int, days: Int, binding: ActivityLsdToleranceCalculatorBinding): Long {
        return when (days) {
            in 1..12 -> (desiredDose + (((lastDose / 100) * 280.059565 * days.toDouble().pow(0.412565956)) - lastDose)).toLong()
            else -> {
                alertDialog(getString(R.string.incorrect_number), getString(R.string.please_enter_a_number_between_1_and_12_full_tolerance_reset_occurs_after_12_days), getString(R.string.okay), this)
                clearEnteredData(binding)
                0
            }
        }
    }
}