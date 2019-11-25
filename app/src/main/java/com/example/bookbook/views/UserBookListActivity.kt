package com.example.bookbook.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.bookbook.R
import kotlinx.android.synthetic.main.activity_user_book_list.*

class UserBookListActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_book_list)

        val mAdapter = BookListPageAdapter(supportFragmentManager)
        viewPager.adapter = mAdapter

        tabLayout.setupWithViewPager(viewPager)

    }


    inner class BookListPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val NUM_ITEMS = 2

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> FavBooksFragment()
                1 -> Fragment()
                else -> Fragment()
            }
        }

        override fun getCount() = this.NUM_ITEMS

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> resources.getString(R.string.favorite_books)
                1 -> resources.getString(R.string.wishlist)
                else -> ""
            }
        }
    }
}
