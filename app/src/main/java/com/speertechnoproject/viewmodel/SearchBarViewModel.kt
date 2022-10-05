package com.speertechnoproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.speertechnoproject.api.RetrofitClient
import com.speertechnoproject.model.GitUsersDetails
import com.speertechnoproject.model.GitUsersListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchBarViewModel : ViewModel() {

    val gitUserList = MutableLiveData<ArrayList<GitUsersDetails>>()


    fun fetchSearchedUsers(query: String) {

        RetrofitClient.apiInstance.fetchSearchedUser(query)
            .enqueue(object : Callback<GitUsersListData> {
                override fun onResponse(
                    call: Call<GitUsersListData>,
                    response: Response<GitUsersListData>
                ) {

                    if (response.isSuccessful) {
                        gitUserList.postValue(response.body()?.items)
                    }

                }

                override fun onFailure(call: Call<GitUsersListData>, t: Throwable) {
                    Log.e("failure", t.toString())
                }
            })
    }

    fun getSearchUsers(): LiveData<ArrayList<GitUsersDetails>> {
        return gitUserList
    }
}