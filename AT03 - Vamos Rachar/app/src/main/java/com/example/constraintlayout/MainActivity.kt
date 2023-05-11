package com.example.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import java.util.*
import android.content.Intent
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), TextWatcher, TextToSpeech.OnInitListener {
    private lateinit var tts: TextToSpeech
    private lateinit var edtConta: EditText
    private lateinit var edtPessoas: EditText
    private lateinit var tvResultado: TextView
    private lateinit var fabCompartilhar: FloatingActionButton
    private lateinit var btFalar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtConta = findViewById(R.id.edtConta)
        edtPessoas = findViewById(R.id.edtPessoas)
        tvResultado = findViewById(R.id.tvResultado)
        fabCompartilhar = findViewById(R.id.fabCompartilhar)
        btFalar = findViewById(R.id.btFalar)

        edtConta.addTextChangedListener(this)
        edtPessoas.addTextChangedListener(this)

        // Initialize TTS engine
        tts = TextToSpeech(this, this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//       Log.d("PDM23","Antes de mudar")
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//        Log.d("PDM23","Mudando")
    }

    override fun afterTextChanged(s: Editable?) {
        var valor: String = calculate()
        if (!valor.isEmpty()) {
            tvResultado.setText(valor+" para cada um")

            fabCompartilhar.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, "O valor da conta fica $valor para cada um")
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, "Compartilhar com:"))
            }

            btFalar.setOnClickListener {
                tts.speak("$valor", TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    override fun onDestroy() {
            // Release TTS engine resources
            tts.stop()
            tts.shutdown()
            super.onDestroy()
        }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // TTS engine is initialized successfully
            tts.language = Locale.getDefault()
            Log.d("PDM23","Sucesso na Inicialização")
        } else {
            // TTS engine failed to initialize
            Log.e("PDM23", "Failed to initialize TTS engine.")
        }
    }

    private fun calculate(): String {
        if (edtPessoas.text.toString().isEmpty())
            return ""

        var valorConta = if (edtConta.text.toString().isEmpty()) 0.0 else edtConta.text.toString().toDouble()
        var qtdPessoas = edtPessoas.text.toString().toInt()
        var resultado = if (qtdPessoas == 0 || qtdPessoas == 1) valorConta else valorConta / qtdPessoas

        // Round Double to 2 decimal places
        resultado = String.format("%.2f", resultado).toDouble()
        Log.d ("PDM23", "$resultado")

        return "$resultado"
    }
}

