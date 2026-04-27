package com.example.baseandroidapp.data.users

import com.example.baseandroidapp.core.coroutine.IoDispatcher
import com.example.baseandroidapp.data.users.local.UserDao
import com.example.baseandroidapp.data.users.local.UserEntity
import com.example.baseandroidapp.data.users.remote.UsersApi
import com.example.baseandroidapp.domain.users.User
import com.example.baseandroidapp.domain.users.UsersRepository
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Offline-first implementation of [UsersRepository]: the UI observes the Room
 * cache, while [refresh] fetches fresh data from the network and updates it.
 */
@Singleton
class UsersRepositoryImpl @Inject constructor(
    private val api: UsersApi,
    private val dao: UserDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : UsersRepository {

    override fun observeUsers(): Flow<List<User>> =
        dao.observeAll().map { entities -> entities.map(UserEntity::toDomain) }

    override suspend fun refresh() = withContext(ioDispatcher) {
        val remote = api.getUsers()
        dao.upsertAll(remote.map { dto ->
            UserEntity(id = dto.id, name = dto.name, email = dto.email)
        })
    }
}
