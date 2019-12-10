package com.example.bookbook.views.activities

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.bookbook.R
import com.example.bookbook.consts.Consts
import com.example.bookbook.consts.FirebaseConsts
import com.example.bookbook.entities.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_message.*


class MessageActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

    }

    override fun onStart() {
        super.onStart()

        messageCancel.setOnClickListener{
            finish()
        }


        messageSend.setOnClickListener{
            val database = FirebaseFirestore.getInstance()
            val user = FirebaseAuth.getInstance().currentUser

            database.collection(FirebaseConsts.USERS_COLLECTION).document(user!!.uid).update(FirebaseConsts.USER_TWEET_LIST, FieldValue.arrayUnion(comment.text.toString()))
//            finish()
        }
    }



}