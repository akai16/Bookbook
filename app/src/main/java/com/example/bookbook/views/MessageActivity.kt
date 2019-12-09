package com.example.bookbook.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookbook.R
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
    }
}