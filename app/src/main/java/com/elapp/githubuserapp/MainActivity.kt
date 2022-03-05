package com.elapp.githubuserapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.elapp.githubuserapp.databinding.ActivityMainBinding
import com.elapp.githubuserapp.model.User
import com.elapp.githubuserapp.presentation.ui.detail.UserDetailActivity
import com.elapp.githubuserapp.presentation.ui.home.UserAdapter
import com.elapp.githubuserapp.presentation.ui.home.listener.UserItemListener

class MainActivity : AppCompatActivity(), UserItemListener {

    private var _activityMainBinding: ActivityMainBinding? = null
    private val binding get() = _activityMainBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.elevation = 0F

        _activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_activityMainBinding?.root)
        
        showUserList()
    }

    private fun showUserList() {
        binding.rvGithubUser.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val userAdapter = UserAdapter(usersList)
        userAdapter.onItemClicked(this)
        binding.rvGithubUser.adapter = userAdapter
    }

    private val usersList: ArrayList<User>
        get() {
            val userFullName = resources.getStringArray(R.array.name)
            val username = resources.getStringArray(R.array.username)
            val userLocation = resources.getStringArray(R.array.location)
            val userRepository = resources.getStringArray(R.array.repository)
            val userCompany = resources.getStringArray(R.array.company)
            val userFollowing = resources.getStringArray(R.array.following)
            val userFollower = resources.getStringArray(R.array.followers)
            val userAvatar = resources.obtainTypedArray(R.array.avatar)

            val listUser = ArrayList<User>()
            for (i in userFullName.indices) {
                val user = User(
                    username[i],
                    userFullName[i],
                    userLocation[i],
                    userRepository[i],
                    userCompany[i],
                    userFollowing[i],
                    userFollower[i],
                    userAvatar.getResourceId(i, -1)
                )
                listUser.add(user)
            }
            return listUser
        }

    override fun onClicked(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra(UserDetailActivity.USER_EXTRA, user)
        startActivity(intent)
    }

}