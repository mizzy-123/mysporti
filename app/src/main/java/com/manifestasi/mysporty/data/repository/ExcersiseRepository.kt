package com.manifestasi.mysporty.data.repository

import android.util.Log
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.data.model.DataExercise
import com.manifestasi.mysporty.data.model.Profile
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExcersiseRepository @Inject constructor(
    private val auth: Auth,
    private val database: Postgrest
) {

    fun isLoggedIn(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            val isLoggedIn = auth.currentUserOrNull() != null

            auth.refreshCurrentSession()

            emit(Resource.Success(isLoggedIn))
        } catch (e: Exception){
            Log.e("ExcersiseRepository.isLoggedIn", e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ): Flow<Resource<Boolean>>  = flow {
        emit(Resource.Loading)
        try {

            val user = auth.signUpWith(Email){
                this.email = email
                this.password = password
            }

            val userId = user?.id
            requireNotNull(userId) { "User ID is null after sign up" }

            database.from(DATABASE_PROFILE).insert(Profile(
                user_id = userId,
                first_name = firstName,
                last_name = lastName
            ))

            emit(Resource.Success(true))
        } catch (e: Exception){
            Log.e("ExcersiseRepository.register", e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun login(
        email: String,
        password: String
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            auth.signInWith(Email){
                this.email = email
                this.password = password
            }

            emit(Resource.Success(true))
        } catch (e: Exception){
            Log.e("ExcersiseRepository.login", e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        const val DATABASE_PROFILE = "profile"
    }
}