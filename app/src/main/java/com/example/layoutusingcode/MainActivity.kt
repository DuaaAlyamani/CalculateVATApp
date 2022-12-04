package com.example.layoutusingcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.layoutusingcode.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    // Binding
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view) // link layout
        // TODO [1] add listener when the user click on "Calculate VAT" button
        binding.buCalculateVAT.setOnClickListener {
            // call claculateVat to calculate the VAT
            claculateVat()
        }
    }

    private fun claculateVat() {
        // TODO [2] get the input from the user
        // get the input from EditeText
        val stringCost = binding.etCostOrTotalPrice.text.toString()
        // convert the value from string to double
        val cost = stringCost.toDouble()
        // get the input from the Radio group
        var percentage = binding.radioGroup.checkedRadioButtonId
        // assign the value for the percentage to vatPercentage
        val vatPercentage = when (percentage) {
            binding.tenRadioButton.id -> 0.10
            binding.fiftRadioButton.id -> 0.15
            else -> 0.20
        }
        //TODO [3] calculate the vat
        val vat = vatPercentage * cost
        // TODO [4] calculate the total
        var total = vat + cost
        // TODO [5] check for the switch value
        // if the value is checked then round up for the total
        if (binding.swRoundUpTotal.isChecked)
            total = kotlin.math.ceil(total)
        // TODO [6] format the result
        // Get the currency from the user device location
        val formattedTotal = NumberFormat.getCurrencyInstance().format(total)
        // format the output depending on the format in the strings.xml file
        // show the result in the textView
        binding.tvResul.text = getString(R.string.total_amount, formattedTotal)
    }


}
