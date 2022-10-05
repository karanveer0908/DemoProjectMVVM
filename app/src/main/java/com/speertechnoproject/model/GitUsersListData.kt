package com.speertechnoproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GitUsersListData (
    @SerializedName("items")
    @Expose
    val items: ArrayList<GitUsersDetails>)
