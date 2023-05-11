package com.example.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity(), TextWatcher, TextToSpeech.OnInitListener {
    private lateinit var tts: TextToSpeech
    private lateinit var edtConta: EditText
    private lateinit var edtPessoas: EditText
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtConta = findViewById(R.id.edtConta)
        edtPessoas = findViewById(R.id.edtPessoas)
        tvResultado = findViewById(R.id.tvResultado)

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
//        Log.d ("PDM23", "Depois de mudar")
//        Log.d ("PDM23", s.toString())
        tvResultado.setText(calculate())
    }

    fun clickFalar(v: View){

        tts.speak("Oi Sumido", TextToSpeech.QUEUE_FLUSH, null, null)


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

        return String.format("RS %.2f\npara cada um", resultado)
    }
}

