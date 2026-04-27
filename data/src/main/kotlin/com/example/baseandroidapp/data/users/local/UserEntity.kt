package com.example.baseandroidapp.data.users.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.baseandroidapp.domain.users.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
) {
    fun toDomain(): User = User(id = id, name = name, email = email)

    companion object {
        fun fromDomain(user: User): UserEntity =
            UserEntity(id = user.id, name = user.name, email = user.email)
    }
}
