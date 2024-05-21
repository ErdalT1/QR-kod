package com.example.musicapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator

class anasayfaact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anasayfaact)

        val button = findViewById<Button>(R.id.tarama_button)
        button.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "QR kodunu tarayınız", Toast.LENGTH_SHORT).show()
            val integrator = IntentIntegrator(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setPrompt("QR Kodu Tarayıcısı")
            integrator.setBeepEnabled(false)
            integrator.setOrientationLocked(false)
            integrator.setBarcodeImageEnabled(true)
            integrator.initiateScan()
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Log.d("MainActivity", "Tarama iptal edildi")
            } else {
                Log.d("MainActivity", "Taranan QR Kodu: " + result.contents)
                // Taranan QR kodunun sitesine yönlendir
                openWebsite(result.contents)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun openWebsite(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}