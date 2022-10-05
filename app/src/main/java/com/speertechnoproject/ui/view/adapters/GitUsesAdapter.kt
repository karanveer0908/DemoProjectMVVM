package com.speertechnoproject.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.speertechnoproject.databinding.GituserItemBinding
import com.speertechnoproject.model.GitUsersDetails

class GitUsesAdapter : RecyclerView.Adapter<GitUsesAdapter.GitUserViewHolder>() {

    private val mgitUserList = ArrayList<GitUsersDetails>()

    private var onItemCLickCallBack: OnItemClickCallBack? = null

    fun setandUpdateList(gitUserList: ArrayList<GitUsersDetails>) {

        mgitUserList.clear()
        mgitUserList.addAll(gitUserList)
        notifyDataSetChanged()
    }

    fun onItemClickCallback(monItemClickCallBack: OnItemClickCallBack) {
        this.onItemCLickCallBack = monItemClickCallBack
    }


    inner class GitUserViewHolder(val binding: GituserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(gitUser: GitUsersDetails) {

            binding.root.setOnClickListener {
                onItemCLickCallBack?.onIemClicked(gitUser)
            }


            binding.apply {
                Glide.with(itemView).load(gitUser.avatarUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop().into(ivAvatrar)
                tvUsername.setText(gitUser.name)
            }



        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitUserViewHolder {

        val view = GituserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: GitUserViewHolder, position: Int) {
        holder.bind(mgitUserList[position])
    }

    override fun getItemCount(): Int {
        return mgitUserList.size
    }

     interface OnItemClickCallBack {

        fun onIemClicked(data: GitUsersDetails) {

        }

    }

}

