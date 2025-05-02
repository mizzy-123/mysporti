package com.manifestasi.mysporty.data.repository

import android.util.Log
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.data.model.DataExercise
import com.manifestasi.mysporty.data.model.Profile
import com.manifestasi.mysporty.util.Validation
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

    fun updateProfileUser(
        height: Int,
        weight: Int,
        age: Int,
        firstName: String,
        lastName: String
    ): Flow<Resource<Profile>> = flow {
        emit(Resource.Loading)
        try {
            val user = auth.currentUserOrNull()
            if (user == null) {
                emit(Resource.Error("User not found"))
                return@flow
            }

            val result = database.from("profile").update(
                {
                    set("first_name", firstName)
                    set("last_name", lastName)
                    set("height", height)
                    set("weight", weight)
                    set("age", age)
                }
            ) {
                select()
                filter {
                    eq("user_id", user.id)
                }
            }.decodeSingleOrNull<Profile>()

            if (result != null) {
                emit(Resource.Success(result))
            } else {
                emit(Resource.Error("Failed to update profile"))
            }
        } catch (e: Exception){
            Log.e("ExcersiseRepository.updateProfileUser", e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun getProfileUser(): Flow<Resource<Profile>> = flow {
        emit(Resource.Loading)
        try {
            val user = auth.currentUserOrNull()
            Log.d("ExcersiseRepository.getProfileUser", user?.id.toString())
            if (user == null) {
                emit(Resource.Error("User not found"))
                return@flow
            }

            val result = database.from("profile")
                .select() {
                    filter {
                        eq("user_id", user.id)
                    }
                }
                .decodeSingleOrNull<Profile>()

            if (result != null) {
                emit(Resource.Success(result))
                Log.d("ExcersiseRepository.getProfileUser", result.toString())
            } else {
                emit(Resource.Error("Profile not found"))
            }

        } catch (e: Exception){
            Log.e("ExcersiseRepository.getProfileUser", e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun logout(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            auth.signOut()

            emit(Resource.Success(true))
        } catch (e: Exception){
            Log.e("ExcersiseRepository.logout", e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

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

            val errorMessage = when {
                firstName.isEmpty() -> "Firstname tidak boleh kosong"
                lastName.isEmpty() -> "Lastname tidak boleh kosong"
                email.isEmpty() -> "Email tidak boleh kosong"
                password.isEmpty() -> "Password tidak boleh kosong"
                else -> null
            }

            if (errorMessage != null) {
                emit(Resource.Error(errorMessage))
                return@flow
            }

            auth.signUpWith(Email){
                this.email = email
                this.password = password
            }
            val user = auth.currentUserOrNull()

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
            emit(Resource.Error("Something Wrong"))
        }
    }.flowOn(Dispatchers.IO)

    fun login(
        email: String,
        password: String
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        try {
            val errorMessage = when {
                email.isEmpty() -> "Email tidak boleh kosong"
                password.isEmpty() -> "Password tidak boleh kosong"
                !Validation.isValidEmail(email) -> "Email tidak valid"
                else -> null
            }

            if (errorMessage != null) {
                emit(Resource.Error(errorMessage))
                return@flow
            }

            auth.signInWith(Email){
                this.email = email
                this.password = password
            }

            emit(Resource.Success(true))
        } catch (e: Exception){
            Log.e("ExcersiseRepository.login", e.message.toString())
            emit(Resource.Error("Something Wrong"))
        }
    }.flowOn(Dispatchers.IO)

    companion object {
        const val DATABASE_PROFILE = "profile"
    }
}