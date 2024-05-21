package com.example.musicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class kayitolma : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayitolma)
        auth = FirebaseAuth.getInstance()
        val kayitolbutton = findViewById<Button>(R.id.button3)
        val email1 = findViewById<EditText>(R.id.emailText)
        val sifre1 = findViewById<EditText>(R.id.passwordText)

    kayitolbutton.setOnClickListener()
    {
    val email = email1.text.toString()
    val sifre = sifre1.text.toString()
    auth.createUserWithEmailAndPassword(email,sifre).addOnCompleteListener{ task ->
        if (task.isSuccessful)
        {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
}


}