package com.speertechnoproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GitUsersDetails (
    @SerializedName("login")
    @Expose
    val name : String,
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("avatar_url")
    @Expose
    val avatarUrl :String,

    @SerializedName("followers_url")
    @Expose
    val followersUrl : String,
    @SerializedName("following_url")
    @Expose
    val followingUrl :String,

    @SerializedName("followers")
    @Expose
    val followers : String,
    @SerializedName("following")
    @Expose
    val following :String


)

