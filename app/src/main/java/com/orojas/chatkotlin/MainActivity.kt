package com.orojas.chatkotlin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.orojas.chatkotlin.databinding.ActivityMainBinding
import com.orojas.chatkotlin.fragments.FragmentChats
import com.orojas.chatkotlin.fragments.FragmentProfile
import com.orojas.chatkotlin.fragments.FragmentUsers


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        if(firebaseAuth.currentUser == null){
            goLoginOptions()
        }

        // Default ViewFragment
        viewFragmentProfile()

        binding.buttomNV.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.item_profile->{
                    // Visualizar el Perfil
                    viewFragmentProfile()
                    true
                }
                R.id.item_users->{
                    // Visualizar los Usuarios
                    viewFragmentUsers()
                    true
                }
                R.id.item_chats->{
                    // Visualizar los Chats
                    viewFragmentChats()
                    true
                }
                else->{
                    false
                }
            }
        }
    }

    private fun goLoginOptions() {
        startActivity(Intent(applicationContext, LoginActivityOption::class.java))
    }

    private fun viewFragmentProfile(){
        binding.tvTitle.text = "Perfil"
        val fragment = FragmentProfile()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentFL.id, fragment, "Fragment Profile")
        fragmentTransaction.commit()
    }

    private fun viewFragmentUsers(){
        binding.tvTitle.text = "Usuarios"
        val fragment = FragmentUsers()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentFL.id, fragment, "Fragment Users")
        fragmentTransaction.commit()
    }

    private fun viewFragmentChats(){
        binding.tvTitle.text = "Chats"
        val fragment = FragmentChats()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.fragmentFL.id, fragment, "Fragment Chats")
        fragmentTransaction.commit()
    }
}