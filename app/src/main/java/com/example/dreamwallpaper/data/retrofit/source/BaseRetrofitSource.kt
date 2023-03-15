package com.example.dreamwallpaper.data.retrofit.source

import com.example.dreamwallpaper.data.retrofit.RetrofitConfig
import com.example.dreamwallpaper.domain.error_handling.BackendException
import com.example.dreamwallpaper.domain.error_handling.ConnectionException
import com.example.dreamwallpaper.domain.error_handling.ParseBackendResponseException
import com.example.dreamwallpaper.util.Result
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

open class BaseRetrofitSource @Inject constructor(
    retrofitConfig: RetrofitConfig
) {

    private val errorAdapter = retrofitConfig.moshi.adapter(ErrorResponseBody::class.java)

    suspend fun <T : Any> wrapRetrofitExceptions(
        execute: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.Success(body)
            } else {
                Result.Error(data = null, message = response.message())
            }
        } catch (e: HttpException) {
            return Result.Error(null, e.message)
        } catch (e: BackendException) {
            return Result.Error(null, e.message)
        } catch (e: ConnectionException) {
            return Result.Error(null, e.message)
        } catch (e: IOException) {
            return Result.Error(null, e.message)
        }
    }

    private fun createBackendException(e: HttpException): Exception {
        return try {
            val errorBody = errorAdapter.fromJson(
                e.response()!!.errorBody()!!.string()
            )
            BackendException(e.response()!!.code(), errorBody!!.error)
        } catch (e: Exception) {
            throw ParseBackendResponseException(e)
        }
    }

    class ErrorResponseBody(
        val error: String
    )
}