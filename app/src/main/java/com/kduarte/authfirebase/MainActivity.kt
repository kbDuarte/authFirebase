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
            showLoginFallback()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogout.setOnClickListener { signOut() }
        binding.btnLogin.setOnClickListener { launchSignIn() }

        if (FirebaseAuth.getInstance().currentUser == null) {
            launchSignIn()
        } else {
            showHome()
        }
    }

    private fun launchSignIn() {
        binding.homeContent.visibility = View.GONE
        binding.loginContent.visibility = View.GONE

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

    private fun showLoginFallback() {
        binding.homeContent.visibility = View.GONE
        binding.loginContent.visibility = View.VISIBLE
    }

    private fun showHome() {
        val user = FirebaseAuth.getInstance().currentUser
        binding.loginContent.visibility = View.GONE
        binding.tvWelcome.text = getString(
            R.string.welcome_user,
            user?.email ?: "usuário"
        )
        binding.homeContent.visibility = View.VISIBLE
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Você saiu da sua conta.", Toast.LENGTH_SHORT).show()
                    launchSignIn()
                } else {
                    Toast.makeText(this, "Não foi possível sair. Tente novamente.", Toast.LENGTH_LONG).show()
                }
            }
    }
}
