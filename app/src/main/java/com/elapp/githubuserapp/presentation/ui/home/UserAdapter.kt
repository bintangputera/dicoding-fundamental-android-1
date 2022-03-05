package com.elapp.githubuserapp.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elapp.githubuserapp.databinding.UserItemBinding
import com.elapp.githubuserapp.model.User
import com.elapp.githubuserapp.presentation.ui.home.listener.UserItemListener

class UserAdapter(private val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private lateinit var listener: UserItemListener

    fun onItemClicked(mListener: UserItemListener) {
        this.listener = mListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val userItemBinding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(userItemBinding)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        userList[position].let { user ->
            holder.bind(user)
            holder.itemView.setOnClickListener {
                listener.onClicked(user)
            }
        }
    }

    override fun getItemCount(): Int = userList.size

    inner class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                txFullName.text = user.name
                txUsername.text = user.username
                imgUser.setImageResource(user.avatar)
            }
        }
    }

}