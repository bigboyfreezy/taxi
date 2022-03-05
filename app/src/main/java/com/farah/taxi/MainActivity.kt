package com.farah.taxi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.farah.taxi.databinding.ActivityDriverHomeBinding
import com.farah.taxi.databinding.ActivityMainBinding
import com.farah.taxi.models.driver
import com.farah.taxi.models.user
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)






        binding.register.setOnClickListener {
            val name = binding.name.text.toString()
            val lname= binding.lname.text.toString()
            val phone = binding.phone.text.toString()
            addUser(name,lname,phone)





        }



    }




    private fun addUser(name : String?,lname: String? ,phone: String? ){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        val User = user(name,lname,phone)
        mDbRef.child("users").push().setValue(User)

    }




}