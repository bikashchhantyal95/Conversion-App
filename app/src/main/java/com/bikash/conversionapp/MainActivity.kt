package com.bikash.conversionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            binding.outputValue.text = inputValue.toString()
        }


    }
}


