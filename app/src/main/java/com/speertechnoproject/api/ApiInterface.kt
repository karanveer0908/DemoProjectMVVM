package com.speertechnoproject.api

import com.speertechnoproject.model.GitUsersDetails
import com.speertechnoproject.model.GitUsersListData
import com.speertechnoproject.utils.AppConstants.FETCHUSERFOLLOWERS
import com.speertechnoproject.utils.AppConstants.FETCHUSERFOLLOWING
import com.speertechnoproject.utils.AppConstants.HEADERAUTHSTRING
import com.speertechnoproject.utils.AppConstants.SEARCHPARTICULARUSER
import com.speertechnoproject.utils.AppConstants.SEARCHUSERAPI
import com.speertechnoproject.utils.AppConstants.SEARCHUSERPARAM
import com.speertechnoproject.utils.AppConstants.USERNAMEPARAM
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET(SEARCHUSERAPI)
        @Headers(HEADERAUTHSTRING)
    fun fetchSearchedUser(@Query(SEARCHUSERPARAM)query: String):Call<GitUsersListData>

    @GET(SEARCHPARTICULARUSER)
    @Headers(HEADERAUTHSTRING)
    fun getUserDetais(@Path(USERNAMEPARAM)query: String):Call<GitUsersDetails>

    @GET(FETCHUSERFOLLOWERS)
    @Headers(HEADERAUTHSTRING)
    fun fetchFollowings(@Path(USERNAMEPARAM)query: String):Call<ArrayList<GitUsersDetails>>

    @GET(FETCHUSERFOLLOWING)
    @Headers(HEADERAUTHSTRING)
    fun fetchFollowers(@Path(USERNAMEPARAM)query: String):Call<ArrayList<GitUsersDetails>>

}