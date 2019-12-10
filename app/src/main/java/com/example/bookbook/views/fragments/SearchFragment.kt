package com.example.bookbook.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.bookbook.R
import com.example.bookbook.api.BookService
import kotlinx.android.synthetic.main.activity_user_book_list.*
import kotlinx.android.synthetic.main.fragment_search_page.*

class SearchFragment : Fragment() {

    private val SEARCH_FRAG_COUNT = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_page, container, false)
    }

    override fun onStart() {
        super.onStart()

        // Set View Pager and Tab Layout
        val mAdapter = SearchPageAdapter(activity!!.supportFragmentManager)
        search_view_pager.adapter = mAdapter

        search_tab_layout.setupWithViewPager(search_view_pager)
    }


    inner class SearchPageAdapter(fragManager: FragmentManager) :
        FragmentPagerAdapter(fragManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> SearchBookFragment()
                1 -> SearchBookFragment()
                else -> Fragment()
            }

        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Books"
                1 -> "Users"
                else -> null
            }
        }

        override fun getCount() = SEARCH_FRAG_COUNT

    }

}

