package com.gabo.recyvlerviewhw

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.gabo.recyvlerviewhw.adapter.UsersAdapter
import com.gabo.recyvlerviewhw.database.Database
import com.gabo.recyvlerviewhw.database.entity.User
import com.gabo.recyvlerviewhw.databinding.ActivityUsersBinding

class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding
    private lateinit var db: Database
    private lateinit var adapter: UsersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(this, Database::class.java, "User Database")
            .allowMainThreadQueries().build()

        adapter = UsersAdapter(
            clickEdit = { user -> editUser(user) },
            clickDelete = { user ->
                removeUser(user)
                adapter.setData(db.userDao().getUsers())
            }
        )

        with(binding) {
            rvUsers.adapter = adapter
            rvUsers.layoutManager = LinearLayoutManager(this@UsersActivity)
            adapter.setData(db.userDao().getUsers())
            btnAdd.setOnClickListener {
                startActivity(Intent(this@UsersActivity, UserActivity::class.java))
            }
        }
    }

    private fun removeUser(user: User) {
        val msg: String
        if (db.userDao().userAlreadyExist(user.id)) {
            db.userDao().deleteUser(user.id)
            msg = "User removed Successfully"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        } else {
            msg = "Something went wrong"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun editUser(user: User) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("userToEdit", user)
        startActivity(intent)
    }

    override fun onRestart() {
        super.onRestart()
        adapter.setData(db.userDao().getUsers())
    }
}