package com.example.bookbook.views.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.bookbook.R
import com.example.bookbook.consts.Consts
import com.example.bookbook.consts.FirebaseConsts
import com.example.bookbook.entities.Tweet
import com.example.bookbook.entities.User
import com.example.bookbook.viewmodel.ProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_message.*
import java.time.Instant
import java.time.format.DateTimeFormatter


class MessageActivity : AppCompatActivity() {

    val database = FirebaseFirestore.getInstance()
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        messageCancel.setOnClickListener {

            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        messageSend.setOnClickListener {
            val hasSend = sendTweet()

            if (hasSend) {
                setResult(Activity.RESULT_OK)
                finish()
            }
        }

    }


    private fun sendTweet(): Boolean{

        val user = mAuth.currentUser
        var hasSend = false
        val newTweet = Tweet()

        newTweet.text = comment.text.toString()

        if (newTweet.text.isBlank()) {
            Toast.makeText(this, "Por favor, digite alguma coisa", Toast.LENGTH_SHORT).show()
        }
        else {
            database.collection(FirebaseConsts.USERS_COLLECTION)
                .document(user!!.uid)
                .update(
                    FirebaseConsts.USER.TWEET_LIST,
                    FieldValue.arrayUnion(newTweet)
                )
            hasSend = true
        }

        return hasSend
    }

}