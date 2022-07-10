package com.gabo.recyvlerviewhw.database.dao

import androidx.room.*
import com.gabo.recyvlerviewhw.database.entity.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addUser(user: User)

    @Query("DELETE FROM users WHERE id=:id")
    fun deleteUser(id: Int)

    @Update()
    fun updateUser(user: User)

    @Query("SELECT EXISTS(SELECT*FROM Users WHERE id=:id)")
    fun userAlreadyExist(id: Int): Boolean

    @Query("SELECT * FROM users")
    fun getUsers(): List<User>

}