package com.example.bookbook.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookbook.R
import com.example.bookbook.consts.Consts
import com.example.bookbook.views.fragments.UserProfileFragment
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val userId = intent.extras!!.getString(Consts.EXTRA_USER_ID)!!
        val profileFrag = UserProfileFragment.getInstance(userId)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_fragment, profileFrag)
            .commit()
    }

}
