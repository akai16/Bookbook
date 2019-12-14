package com.example.bookbook.views.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.bookbook.R
import com.example.bookbook.consts.Consts
import com.example.bookbook.entities.User
import com.example.bookbook.views.fragments.UserBookListFragment
import kotlinx.android.synthetic.main.activity_user_book_list.*

class UserBookListActivity : FragmentActivity() {

    var favBookList: List<String> = listOf()
    var wishList: List<String> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_book_list)

        // Set View Pager and Tab Layout
        val mAdapter = BookListPageAdapter(supportFragmentManager)
        viewPager.adapter = mAdapter

        tabLayout.setupWithViewPager(viewPager)

        val user = intent.getSerializableExtra(Consts.EXTRA_USER_DATA) as User
        this.favBookList = user.favBooks
        this.wishList = user.wishList

    }


    inner class BookListPageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val NUM_ITEMS = 2

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> UserBookListFragment.newInstance(favBookList)
                1 -> UserBookListFragment.newInstance(wishList)
                else -> Fragment()
            }
        }

        override fun getCount() = this.NUM_ITEMS

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> resources.getString(R.string.favorite_books)
                1 -> resources.getString(R.string.wishlist)
                else -> null
            }
        }
    }
}
