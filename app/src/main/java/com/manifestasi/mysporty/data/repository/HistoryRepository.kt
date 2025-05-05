package com.manifestasi.mysporty.data.repository

import android.util.Log
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.data.model.HistoryExcersise
import com.manifestasi.mysporty.data.model.HistoryExcersise2
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val auth: Auth,
    private val postgrest: Postgrest
) {
    fun insertHistoryExcersise(
        image_name: String,
        name: String,
        repetisi: Int
    ): Flow<Resource<HistoryExcersise2>> = flow {
        emit(Resource.Loading)
        try {
            val user = auth.currentUserOrNull()
            if (user?.id == null){
                Log.e("HistoryRepository.insertHistoryExcersise", "User not found")
                emit(Resource.Error("User not found"))
                return@flow
            }

            val result = postgrest.from("history").insert(HistoryExcersise2(
                user_id = user.id,
                name = name,
                img_name = image_name,
                repetisi = repetisi
            )){
                select()
            }.decodeSingle<HistoryExcersise2>()

            emit(Resource.Success(result))
        } catch (e: Exception){
            Log.e("HistoryRepository.insertHistoryExcersise", e.message.toString())
            emit(Resource.Error(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    fun getHistoryExcersise(): Flow<Resource<List<HistoryExcersise2>>> = flow {
        emit(Resource.Loading)

        try {
            val user = auth.currentUserOrNull()
            if (user?.id == null){
                emit(Resource.Error("User not found"))
                return@flow
            }

            val result = postgrest.from("history").select() {
                filter {
                    eq("user_id", user.id)
                }
            }.decodeList<HistoryExcersise2>()
            emit(Resource.Success(result))
        } catch (e: Exception){
            emit(Resource.Error(e.message.toString()))
            Log.e("HistoryRepository.getHistoryExcersise", e.message.toString())
        }
    }.flowOn(Dispatchers.IO)
}