package com.example.dreamwallpaper.domain.error_handling

import retrofit2.Response
import com.example.dreamwallpaper.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

abstract class ResponseWrapper {

    suspend fun <T : Any> responseWrap(apiCall: suspend () -> Response<T>): Result<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<T> = apiCall()
                if (response.isSuccessful) {
                    Result.Success(data = response.body()!!)
                } else {
                    Result.Error(message = "Something went wrong")
                }
            } catch (e: HttpException) {
                Result.Error(message = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                Result.Error(message = "Please check your network connection")
            } catch (e: Exception) {
                Result.Error(message = "Something went wrong")
            }
        }
    }

}