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

class UserDetailViewModel :ViewModel(){

    val gitUserDetails = MutableLiveData<GitUsersDetails>()

    fun setGitUserDetails(username :String){

        RetrofitClient.apiInstance.getUserDetais(username).
        enqueue(object : Callback<GitUsersDetails>{
            override fun onResponse(
                call: Call<GitUsersDetails>,
                response: Response<GitUsersDetails>
            ) {

                if (response.isSuccessful){
                    gitUserDetails.postValue(response.body())
                }

            }

            override fun onFailure(call: Call<GitUsersDetails>, t: Throwable) {

            }
        })

    }

    fun getGitUserDetails():LiveData<GitUsersDetails>{
        return  gitUserDetails
    }

}
