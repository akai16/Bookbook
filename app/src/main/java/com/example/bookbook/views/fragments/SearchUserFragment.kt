package com.example.bookbook.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bookbook.R
import com.example.bookbook.consts.Consts
import com.example.bookbook.consts.FirebaseConsts
import com.example.bookbook.entities.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_search_user.*

class SearchUserFragment : Fragment() {

    var userList = mutableListOf<User>()

    lateinit var db: FirebaseFirestore
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_user, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Firebase Instances
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_search_user.setOnClickListener {
            listUsers(edit_user_search.text.toString())
        }

    }


    private fun listUsers(queryString: String) {

        this.db
            .collection(FirebaseConsts.USERS_COLLECTION)
            .whereGreaterThanOrEqualTo("name", queryString)
            .get()
            .addOnSuccessListener {
                for (user in it) {
                    this.userList.add(User.convertToUser(user))
                }
            }
            .addOnFailureListener {
                Log.d(Consts.DEBUG_TAG, "SearchUserFragment -> Fail")
            }


    }

}

