package com.speertechnoproject.ui.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.speertechnoproject.BaseActivity
import com.speertechnoproject.R
import com.speertechnoproject.databinding.SearchbarActivityBinding
import com.speertechnoproject.model.GitUsersDetails
import com.speertechnoproject.ui.view.adapters.GitUsesAdapter
import com.speertechnoproject.utils.AppConstants
import com.speertechnoproject.viewmodel.SearchBarViewModel


class SearchBarActivity : BaseActivity<SearchbarActivityBinding>(R.layout.searchbar_activity) {

    private lateinit var bindingSearchAB: SearchbarActivityBinding

    private lateinit var viewModel: SearchBarViewModel

    private lateinit var adapter: GitUsesAdapter

    override fun initComponents(savedInstanceState: Bundle?, binding: SearchbarActivityBinding) {


        initializeViewmodel(binding)
    }

    private fun initializeViewmodel(binding: SearchbarActivityBinding) {
        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory( )).get(SearchBarViewModel::class.java)
        bindingSearchAB = binding
        adapter = GitUsesAdapter()
        bindingSearchAB.searchViewModel = viewModel




        setAdapterAndFetchData()




    }

    private fun setAdapterAndFetchData() {



        viewModel.getSearchUsers().observe(this@SearchBarActivity) {
            if (it != null) {

                adapter.setandUpdateList(it)
            }
        }




        adapter.onItemClickCallback(object : GitUsesAdapter.OnItemClickCallBack {

            override fun onIemClicked(data: GitUsersDetails) {
                super.onIemClicked(data)
                Intent(this@SearchBarActivity, UserDetailsActivity::class.java).also {
                    it.putExtra(AppConstants.EXTRAUSERNAME, data.name)
                    startActivity(it)
                }

            }
        })




        bindingSearchAB.apply {
            rvGitUsers.layoutManager = LinearLayoutManager(this@SearchBarActivity)
            rvGitUsers.setHasFixedSize(true)
            rvGitUsers.adapter = adapter



            etUsername.addTextChangedListener(object : TextWatcher {

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    if (s.length >=3){
                        searchGitUser()
                    }
                }
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

                }
                override fun afterTextChanged(s: Editable) {
                }
            })

        }

    }

    fun searchGitUser() {
        bindingSearchAB.apply {
            val query = etUsername.text.toString().trim()
            if (query.isNullOrEmpty())
                return
            else {
                viewModel.fetchSearchedUsers(query)
            }
        }
    }
}