package com.speertechnoproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.speertechnoproject.api.RetrofitClient
import com.speertechnoproject.model.GitUsersDetails
import com.speertechnoproject.model.GitUsersListData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {

    val listOfFollowers = MutableLiveData<ArrayList<GitUsersDetails>>()

    val listOfFollowing= MutableLiveData<ArrayList<GitUsersDetails>>()


    fun fetchListOfFollowers(username: String) {
        RetrofitClient.apiInstance.fetchFollowings(username)
            .enqueue(object : Callback<ArrayList<GitUsersDetails>> {


                override fun onResponse(
                    call: Call<ArrayList<GitUsersDetails>>,
                    response: Response<ArrayList<GitUsersDetails>>
                ) {
                    if (response.isSuccessful) {
                        listOfFollowers.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<GitUsersDetails>>, t: Throwable) {

                }

            })

    }
    fun fetchListOfFollowing(username: String) {
        RetrofitClient.apiInstance.fetchFollowers(username)
            .enqueue(object : Callback<ArrayList<GitUsersDetails>> {


                override fun onResponse(
                    call: Call<ArrayList<GitUsersDetails>>,
                    response: Response<ArrayList<GitUsersDetails>>
                ) {
                    if (response.isSuccessful) {
                        listOfFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<GitUsersDetails>>, t: Throwable) {

                }

            })

    }

    fun getListofFollowers(): LiveData<ArrayList<GitUsersDetails>> {
        return listOfFollowers

    }
    fun getListofFollowings(): LiveData<ArrayList<GitUsersDetails>> {
        return listOfFollowing

    }
}