package com.gabo.recyvlerviewhw

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.gabo.recyvlerviewhw.database.Database
import com.gabo.recyvlerviewhw.database.entity.User
import com.gabo.recyvlerviewhw.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var db: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(this, Database::class.java, "User Database")
            .allowMainThreadQueries().build()

        val userToEdit = intent.extras?.getParcelable<User>("userToEdit")
        with(binding) {
            btnSave.setOnClickListener {
                if (userToEdit == null) {
                    addUser(
                        tietName.text.toString(),
                        tietSurname.text.toString(),
                        tietEmail.text.toString()
                    )
                } else {
                    updateUser(
                        userToEdit,
                        tietName.text.toString(),
                        tietSurname.text.toString(),
                        tietEmail.text.toString()
                    )
                }
            }
            userToEdit?.let {
                tietName.setText(it.name)
                tietSurname.setText(it.surname)
                tietEmail.setText(it.email)
            }
            intent.removeExtra("userToEdit")
        }
    }

    private fun addUser(name: String, surname: String, email: String) {
        db.userDao().addUser(User(name = name, surname = surname, email = email))
        val msg = "User added successfully"
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun updateUser(user: User, name: String, surname: String, email: String) {
        val msg: String
        if (db.userDao().userAlreadyExist(user.id)) {
            db.userDao()
                .updateUser(User(id = user.id, name = name, surname = surname, email = email))
            msg = "User updated successfully"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        } else {
            msg = "User does not exist"
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}
