package com.speertechnoproject.ui.view.adapters

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.speertechnoproject.R
import com.speertechnoproject.ui.view.fragments.FollowersFragment
import com.speertechnoproject.ui.view.fragments.FollowingFragments

class SectionPagerAdapter(private val mCtx: Context, fm: FragmentManager,data : Bundle) : FragmentPagerAdapter(
    fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT

) {
    private var fragmentBunde : Bundle?= null

    init {
        fragmentBunde =  data
  }
    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.followers, R.string.following)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = FollowingFragments()
            1 -> fragment = FollowersFragment()

        }
        fragment?.arguments = this.fragmentBunde
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return mCtx.resources.getString(TAB_TITLES[position])
    }
}