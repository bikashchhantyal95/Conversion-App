package com.bikash.conversionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bikash.conversionapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var inputValue: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Set up Spinner adapters
        val unitAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.conversion_types,
            android.R.layout.simple_spinner_item
        )
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.fromConversion.adapter = unitAdapter
        binding.toConversion.adapter = unitAdapter

        binding.button.setOnClickListener(){
            inputValue = binding.inputValue.text.toString().toDouble()
            
        }
        binding.fromConversion.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        binding.toConversion.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                }
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }


    }

    fun computeValue(from: Unit, to: Unit, value: Double): Double {
//        convert the value into meters
        val baseValue = value * from.factor
//        convert the value from meter to desired unit
        return baseValue / to.factor
    }


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


