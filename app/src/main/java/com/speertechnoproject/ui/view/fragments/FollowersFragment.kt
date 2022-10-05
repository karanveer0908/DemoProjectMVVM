package com.speertechnoproject.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.speertechnoproject.R
import com.speertechnoproject.databinding.FragmentFollowBinding
import com.speertechnoproject.model.GitUsersDetails
import com.speertechnoproject.ui.view.activities.UserDetailsActivity
import com.speertechnoproject.ui.view.adapters.GitUsesAdapter
import com.speertechnoproject.utils.AppConstants
import com.speertechnoproject.viewmodel.FollowersViewModel

class FollowersFragment : Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null

    private val binding get() = _binding!!

    private var fragmentViewModel: FollowersViewModel? = null

    private var adapter: GitUsesAdapter? = null

    private lateinit var mUsername: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initalizeViewModel(view)


    }

    private fun initalizeViewModel(view: View) {


        val args = arguments
        mUsername = args!!.getString(AppConstants.EXTRAUSERNAME).toString()

        _binding = FragmentFollowBinding.bind(view)

        fragmentViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(FollowersViewModel::class.java)


        setAdapterAndFetchData()
    }

    private fun setAdapterAndFetchData() {
        adapter = GitUsesAdapter()

        adapter!!.notifyDataSetChanged()
        _binding?.apply {
            rvFollowers.setHasFixedSize(true)
            rvFollowers.layoutManager = LinearLayoutManager(activity)
            rvFollowers.adapter = adapter
        }


        fragmentViewModel!!.fetchListOfFollowers(mUsername)
        fragmentViewModel!!.getListofFollowers().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter!!.setandUpdateList(it)
            }
        })

        adapter?.onItemClickCallback(object : GitUsesAdapter.OnItemClickCallBack {

            override fun onIemClicked(data: GitUsersDetails) {
                super.onIemClicked(data)
                Intent(activity, UserDetailsActivity::class.java).also {
                    it.putExtra(AppConstants.EXTRAUSERNAME, data.name)
                    startActivity(it)
                }

            }
        })
    }


}