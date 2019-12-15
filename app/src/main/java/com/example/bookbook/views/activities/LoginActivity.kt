package com.example.bookbook.views.activities

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


class LoginActivity : AppCompatActivity() {

    val LOGIN_REQUEST_CODE = 7117

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Firebase Auth
        val mAuth = FirebaseAuth.getInstance()

        if (mAuth.currentUser != null) {
            goToProfilePage()
        }
        else {
            showSignInOptions()
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOGIN_REQUEST_CODE) {

            if (resultCode == Activity.RESULT_OK) {
                val user = FirebaseAuth.getInstance().currentUser
                registerUserOnFireStore(user!!)
                goToProfilePage()

            }

            else {
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

        val newUser = User(user.displayName!!)
        newUser.image = user.photoUrl.toString()

        db.collection(FirebaseConsts.USERS_COLLECTION).document(user.uid)
            .set(newUser)
            .addOnSuccessListener{ Log.d(Consts.DEBUG_TAG, "User successfully added!") }
            .addOnFailureListener{ Log.d(Consts.DEBUG_TAG, "Error when registering user") }

        // Set Username keywords for search

        val keywords = mutableListOf<String>()
        var name = ""
        for (letter in user.displayName!!) {
            name += letter.toLowerCase()
            keywords.add(name)
        }

        db.collection(FirebaseConsts.USERS_COLLECTION).document(user.uid)
            .update(FirebaseConsts.USER.NAME_KEYWORDS, keywords)

    }


    private fun showSignInOptions() {

        // Sign in Providers
        val providers = listOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
        )

        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.MyTheme)
            .build(), LOGIN_REQUEST_CODE)
    }
}