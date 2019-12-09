package com.example.bookbook.views

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookbook.R
import com.example.bookbook.consts.Consts
import com.example.bookbook.consts.FirebaseConsts
import com.example.bookbook.entities.User
import com.firebase.ui.auth.AuthMethodPickerLayout
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.core.FirestoreClient


class LoginActivity : AppCompatActivity() {

    val LOGIN_REQUEST_CODE = 7117

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Firebase Auth
        val mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            Toast.makeText(this, "Usuario já está logado", Toast.LENGTH_SHORT).show()
            goToProfilePage()
        }
        else {
            showSignInOptions()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOGIN_REQUEST_CODE) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                registerUserOnFireStore(user!!)
                goToProfilePage()

            } else {
                showSignInOptions()
            }
        }
    }

    // Go to Main Activity
    private fun goToProfilePage() {

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


    private fun registerUserOnFireStore(user: FirebaseUser) {

        val db = FirebaseFirestore.getInstance()
//        val newUser = hashMapOf(
//            "name" to user.displayName,
//
//        )

        val newUser = User(user.displayName!!)

        db.collection(FirebaseConsts.USERS_COLLECTION).document(user.uid)
            .set(newUser)
            .addOnSuccessListener{ Log.d(Consts.DEBUG_TAG, "User successfully added!") }
            .addOnFailureListener{ Log.d(Consts.DEBUG_TAG, "Error when registering user") }

    }


    private fun showSignInOptions() {

        // Sign in Providers
        val providers = listOf<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        // Customizables Login Page Layout
        val customLoginLayout = AuthMethodPickerLayout.Builder(R.layout.activity_login)
            .setGoogleButtonId(R.id.login_btn_google)
            .setEmailButtonId(R.id.login_btn_email)
            .setPhoneButtonId(R.id.login_btn_phone)
            .build()


        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.MyTheme)
            .setAuthMethodPickerLayout(customLoginLayout)
            .build(), LOGIN_REQUEST_CODE)
    }
}