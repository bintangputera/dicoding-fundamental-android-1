package com.elapp.githubuserapp.presentation.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.elapp.githubuserapp.databinding.ActivityUserDetailBinding
import com.elapp.githubuserapp.model.User

class UserDetailActivity : AppCompatActivity() {

    private var _activityUserDetailBinding: ActivityUserDetailBinding? = null
    private val binding get() = _activityUserDetailBinding!!

    companion object {
        const val USER_EXTRA = "user_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _activityUserDetailBinding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(_activityUserDetailBinding?.root)

        supportActionBar?.elevation = 0f
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Detail User"

        val user = intent.getParcelableExtra<User>(USER_EXTRA)
        user?.let { getDetailUser(it) }


        binding.btnShare.setOnClickListener {
            val intentSetup = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Share user : ${user?.username}")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(intentSetup, null)
            startActivity(shareIntent)
        }
    }

    private fun getDetailUser(user: User) {
        with(binding) {
            txFullName.text = user.name
            txUsername.text = user.username
            imgUserAvatar.setImageResource(user.avatar)
            txFollowerCount.text = user.followers
            txFollowingCount.text = user.following
            txRepositoryCount.text = user.repository
            txLocation.text = user.location
            txCompany.text = user.company
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}