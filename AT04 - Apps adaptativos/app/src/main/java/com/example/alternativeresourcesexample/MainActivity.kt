package com.example.alternativeresourcesexample

import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.OnConfigurationChangedProvider

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun increaseFont(v:View){
        var t:TextView=findViewById(R.id.textView)
        var densidadeDatela:Float= getResources().getDisplayMetrics().scaledDensity //Pegando as dimensões da tela
        var novotamanho:Float= (t.textSize/densidadeDatela +2) //Aumentando em 2sp a fonte
        t.setTextSize(novotamanho) //mudando o tamanho
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.v("PDM23","Entrei na mudança de configuração")
        //Esse método só é chamado se houver declaração no Android Manifest

        var bt= findViewById<Button>(R.id.button)
        //Confere se mudou para Landscape
        if (newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            bt.setBackgroundColor(Color.CYAN)
        }else{
            bt.setBackgroundColor(Color.LTGRAY)
        }
    }
}