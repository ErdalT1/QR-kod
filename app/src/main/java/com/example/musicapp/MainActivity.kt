package com.example.musicapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.compose.runtime.saveable.autoSaver
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var firebaseDB: Firebase
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth=FirebaseAuth.getInstance()
        val kullaniciadi = findViewById<EditText>(R.id.emailText)
        val sifre = findViewById<EditText>(R.id.passwordText)

        val button = findViewById<Button>(R.id.button2)
        val button1= findViewById<Button>(R.id.button)

        val db = Firebase.firestore


        button1.setOnClickListener()
        {
               auth.signInWithEmailAndPassword(kullaniciadi.text.toString(), sifre.text.toString()).addOnCompleteListener { task->
                   if(task.isSuccessful)
                   {
                       val guncelKullanici= auth.currentUser?.email.toString()
                       Toast.makeText(this, "hoşgeldiniz", Toast.LENGTH_SHORT).show()

                       val intent = Intent(this,anasayfaact::class.java)
                       startActivity(intent)
                       finish()
                   }
               }.addOnFailureListener { exception->
                   Toast.makeText(this, exception.localizedMessage, Toast.LENGTH_SHORT).show()
               }
            val userMap = hashMapOf(
                "userName" to kullaniciadi.text.toString()
            )
            db.collection("de").document("yezL4kT2YyiX4BvpXr5U")
                .set(userMap)
                .addOnSuccessListener {
                    Toast.makeText(this, "Belge başarıyla güncellendi", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Güncelleme başarısız: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
                    Log.d("AQW",e.localizedMessage)
                }

        }
        button.setOnClickListener()
        {


                val intent = Intent(this, kayitolma::class.java)
                startActivity(intent)

        }
    }

}