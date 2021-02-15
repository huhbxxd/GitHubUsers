package com.example.githubusers.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubusers.model.Model
import com.example.githubusers.R
import com.example.githubusers.base.BaseActivity
import com.example.githubusers.network.User
import com.example.githubusers.screens.repos.UserListRepos
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(R.layout.activity_main), MainView {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var presenter: UsersListPresenter
    private val model = Model()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        presenter = UsersListPresenter(model)
        presenter.onAttach(this)
        presenter.loadUsers()
    }

    override fun onUsersLoaded(users: List<User>) {
        recyclerView.adapter = RecyclerAdapterListUsers(users, ::onItemClick)
    }

    private fun onItemClick(login: String) {
        intent = Intent(this, UserListRepos::class.java)
        intent.putExtra("login", login)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
