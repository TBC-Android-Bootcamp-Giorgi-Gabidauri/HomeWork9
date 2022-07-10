package com.gabo.recyvlerviewhw.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gabo.recyvlerviewhw.database.entity.User
import com.gabo.recyvlerviewhw.databinding.UserBinding

class UsersAdapter(
    private val clickEdit: (User) -> Unit,
    private val clickDelete: (User) -> Unit
) :
    RecyclerView.Adapter<UsersAdapter.UserVH>() {
    private var list: List<User>? = emptyList()


    inner class UserVH(binding: UserBinding) : RecyclerView.ViewHolder(binding.root) {
        private val name = binding.tvName
        private val surname = binding.tvSurname
        private val email = binding.tvEmail
        private val edit = binding.btnEdit
        private val delete = binding.btnDelete
        fun bind(
            model: User, clickEdit: (User) -> Unit, clickDelete: (User) -> Unit
        ) {
            name.text = model.name
            surname.text = model.surname
            email.text = model.email
            edit.setOnClickListener { clickEdit(model) }
            delete.setOnClickListener { clickDelete(model) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVH {
        val binding = UserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserVH(binding)
    }

    override fun onBindViewHolder(holder: UserVH, position: Int) {
        val item = list!![position]
        holder.bind(item, clickEdit, clickDelete)
    }

    override fun getItemCount(): Int = list?.size!!

    fun setData(newUserList: List<User>) {
        val diffUtil =
            com.gabo.recyvlerviewhw.adapter.diffUtil.DiffUtil(oldList = list, newList = newUserList)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        list = newUserList
        diffResult.dispatchUpdatesTo(this)
    }
}