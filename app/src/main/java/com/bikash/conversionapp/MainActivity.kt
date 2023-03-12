package com.bikash.conversionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bikash.conversionapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var inputValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Set up Spinner adapters
//        so that both spinner have same values
        val unitAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.conversion_types,
            android.R.layout.simple_spinner_item
        )
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fromConversion.adapter = unitAdapter
        binding.toConversion.adapter = unitAdapter


// set up
        binding.inputValue.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
// do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                computeAndDisplay()
            }

            override fun afterTextChanged(s: Editable?) {
// do nothong
            }

        })

// set up onItemSelectedListener for [fromConversion] spinner
        binding.fromConversion.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                computeAndDisplay()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
// do nothing
                }

            }
        // set up onItemSelectedListener for [toConversion] spinner
        binding.toConversion.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                computeAndDisplay()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
// do nothing
                }

            }

    }

//    this function computes and displays the computed output on the screen
    fun computeAndDisplay() {
//    get the selcted spinner values and textfield values
        val fromUnit = binding.fromConversion.selectedItem as String
        val toUnit = binding.toConversion.selectedItem as String
        val inputValueToText = binding.inputValue.text.toString()
//    set input value to double if inputToText is not empty and not null else set 0.00
        inputValue = if (inputValueToText.isNotEmpty() && inputValueToText.toDoubleOrNull() != null) {
            inputValueToText.toDouble()
        } else {
            0.0
        }
//    compute the valid input value to the desired unit using [computeValue] function
        val computedValue = computeValue(Unit.valueOf(fromUnit.uppercase()), Unit.valueOf(toUnit.uppercase()), inputValue)
// format the computed value to take four decimal places
        val formattedValue = "%.4f".format(computedValue)
//    set the output to show the computed value after formatti
        binding.outputValue.text = "$inputValueToText $fromUnit = $formattedValue $toUnit"
    }

//    function to convert input value to desired unit
private fun computeValue(from: Unit, to: Unit, value: Double): Double {
//        convert the value into meters
        val baseValue = value * from.factor
//        convert the value from meter to desired unit
        return baseValue / to.factor
    }


//  enum class defines the units and their conversion factors
//  values are set according to their conversion factors to meters
    enum class Unit(val factor: Double){
        METER(1.0),
        KILOMETER(1000.0),
        CENTIMETER(0.01),
        MILLIMETER(0.001),
        FOOT(0.3048),
        INCH(0.0254),
        YARD(0.9144),
        MILE(1609.3)
    }
}


