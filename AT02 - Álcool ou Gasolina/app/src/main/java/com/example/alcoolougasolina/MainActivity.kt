package com.example.alcoolougasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch

class MainActivity : AppCompatActivity() {
    internal var percentual:Double = 0.0
    internal var precoAlcool: Double = 0.0
    internal var precoGasolina: Double = 0.0

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("percentual",percentual)
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("PDM23", "No onCreate, $percentual")

        val editTextGasolina: EditText = findViewById(R.id.edAlcool)
        val editTextAlcool: EditText = findViewById(R.id.edGasolina)
        val switchPercentual: Switch = findViewById(R.id.swPercentual)
        val buttonCalcular: Button = findViewById(R.id.btCalcular)

        if (savedInstanceState != null) {
            percentual = savedInstanceState.getDouble("percentual")
        }

        switchPercentual.setOnCheckedChangeListener({ _, isChecked ->
            percentual = if (isChecked) 0.75 else 0.7
            Log.d("PDM23", "Percentual mudou para $percentual")
        })

        buttonCalcular.setOnClickListener(View.OnClickListener {
            precoGasolina = if (editTextGasolina.text.toString()
                    .isEmpty()
            ) 0.0 else editTextGasolina.text.toString().toDouble()
            precoAlcool = if (editTextAlcool.text.toString()
                    .isEmpty()
            ) 0.0 else editTextAlcool.text.toString().toDouble()

            var percentualGasolina: Double = percentual * precoGasolina

            // Round Double to 2 decimal places
            precoGasolina = String.format("%.2f", precoGasolina).toDouble()
            precoAlcool = String.format("%.2f", precoAlcool).toDouble()
            percentualGasolina = String.format("%.2f", percentualGasolina).toDouble()

            var combustivelRentavel: String =
                if (precoAlcool == percentualGasolina) "alcool" else "gasolina"

            Log.d("PDM23", "No editTextGasolina, $precoGasolina")
            Log.d("PDM23", "No editTextAlcool, $precoAlcool")
            Log.d("PDM23", "No switchPercentual, $percentual")
            Log.d("PDM23", "O melhor combustivel, $combustivelRentavel")
        })
    }
    override fun onResume(){
        super.onResume()
        Log.d("PDM23","No onResume, $percentual")
    }
    override fun onStart(){
        super.onStart()
        Log.d("PDM23","No onStart, $percentual")
    }
    override fun onPause(){
        super.onPause()
        Log.d("PDM23","No onPause, $percentual")
    }
    override fun onStop(){
        super.onStop()
        Log.d("PDM23","No onStop, $percentual")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d("PDM23","No onDestroy, $percentual")
    }
}