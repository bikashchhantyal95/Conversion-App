package com.bikash.conversionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
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
            var fromUnit = binding.fromConversion.selectedItem as String
            var toUnit = binding.toConversion.selectedItem as String
        }
        binding.fromConversion.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    var selectedUnit = parent?.getItemAtPosition(position).toString()
                    binding.outputValue.text = selectedUnit.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
        binding.toConversion.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    var selectedUnit = parent?.getItemAtPosition(position).toString()
                    binding.outputValue.text = selectedUnit.toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
    }

}


