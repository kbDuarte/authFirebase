package com.kduarte.authfirebase

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.kduarte.authfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { result ->
        val response: IdpResponse? = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            showHome()
        } else {
            val message = if (response == null) {
                "Login cancelado."
            } else {
                "Não foi possível entrar. Verifique os dados e tente novamente."
            }
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            // O usuário pode tentar novamente ao abrir o fluxo de login.
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener { signOut() }

        if (FirebaseAuth.getInstance().currentUser == null) {
            launchSignIn()
        } else {
            showHome()
        }
    }

    private fun launchSignIn() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .build()

        signInLauncher.launch(signInIntent)
    }

    private fun showHome() {
        val user = FirebaseAuth.getInstance().currentUser
        binding.tvWelcome.text = getString(
            R.string.welcome_user,
            user?.email ?: "usuário"
        )
        binding.homeContent.visibility = View.VISIBLE
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                binding.homeContent.visibility = View.GONE
                Toast.makeText(this, "Você saiu da sua conta.", Toast.LENGTH_SHORT).show()
                launchSignIn()
            }
    }
}
