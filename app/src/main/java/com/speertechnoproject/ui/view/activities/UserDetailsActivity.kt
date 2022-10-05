package com.speertechnoproject.ui.view.activities

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.speertechnoproject.BaseActivity
import com.speertechnoproject.R
import com.speertechnoproject.databinding.UserdetailsActivityBinding
import com.speertechnoproject.ui.view.adapters.SectionPagerAdapter
import com.speertechnoproject.utils.AppConstants
import com.speertechnoproject.viewmodel.UserDetailViewModel

class UserDetailsActivity :BaseActivity<UserdetailsActivityBinding>(R.layout.userdetails_activity) {

    private lateinit var bindingUserDetails: UserdetailsActivityBinding

    private lateinit var viewModel: UserDetailViewModel


    override fun initComponents(savedInstanceState: Bundle?, binding: UserdetailsActivityBinding) {



        initializeViewModelFetchData(binding)
    }

    private fun initializeViewModelFetchData(binding: UserdetailsActivityBinding) {

        viewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)

        bindingUserDetails =binding
        var mUserName = intent.getStringExtra(AppConstants.EXTRAUSERNAME)

        val bundle = Bundle()
        bundle.putString(AppConstants.EXTRAUSERNAME,mUserName)
        viewModel.setGitUserDetails(mUserName!!)


        viewModel.getGitUserDetails().observe(this) {

            if (it != null) {

                binding.apply {
                    tvName.setText(it.name)
                    tvFollowers.setText(it.followers + "followers")
                    tvFollowing.setText(it.following + "following")
                    Glide.with(this@UserDetailsActivity).load(it.avatarUrl)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop().into(ivUserProfile)
                }
            }
        }


        val sectionPagerAdapter = SectionPagerAdapter(this@UserDetailsActivity,
            supportFragmentManager,bundle)


        binding.apply {
            viewPager.adapter= sectionPagerAdapter
            tablayout.setupWithViewPager(viewPager)
        }
    }
}