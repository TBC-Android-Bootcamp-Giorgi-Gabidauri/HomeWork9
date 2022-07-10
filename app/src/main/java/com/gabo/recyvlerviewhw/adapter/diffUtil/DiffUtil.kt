package com.gabo.recyvlerviewhw.adapter.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.gabo.recyvlerviewhw.database.entity.User

class DiffUtil(private val oldList: List<User>?, private val newList: List<User>?) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList?.size ?: 0
    }

    override fun getNewListSize(): Int {
        return newList?.size ?: 0
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList?.get(oldItemPosition)?.id ?: 0) == (newList?.get(newItemPosition)?.id ?: 0)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            (oldList?.get(oldItemPosition)?.id ?: 0) != (newList?.get(newItemPosition)?.id
                ?: 0) -> false
            (oldList?.get(oldItemPosition)?.name ?: 0) != (newList?.get(newItemPosition)?.name
                ?: 0) -> false
            (oldList?.get(oldItemPosition)?.surname ?: 0) != (newList?.get(newItemPosition)?.surname
                ?: 0) -> false
            (oldList?.get(oldItemPosition)?.email ?: 0) != (newList?.get(newItemPosition)?.email
                ?: 0) -> false
            else -> true

        }
    }

}