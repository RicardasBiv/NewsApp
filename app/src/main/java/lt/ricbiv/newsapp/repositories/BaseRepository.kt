package lt.ricbiv.newsapp.repositories

import android.util.Log
import com.squareup.moshi.Moshi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lt.ricbiv.newsapp.api.Resource
import lt.ricbiv.newsapp.api.responses.ErrorResponse
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): Resource<T> {

        return withContext(Dispatchers.IO) {
            try {
                Log.d("Debug","Hit")
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful) {
                    Log.d("Debug","IsSuccessful Hit")
                    Resource.Success(data = response.body()!!)
                } else {

                    Log.d("Debug","Else Hit")
                    val errorResponse: ErrorResponse? = convertErrorBody(response.errorBody())
                    Resource.Error(errorMessage = errorResponse?.message ?: "Something went wrong")
                }

            } catch (e: HttpException) {
                Log.d("Debug","Http Hit")
                Resource.Error(errorMessage = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                Log.d("Debug","IO hit")
                Resource.Error("Please check your network connection")
            } catch (e: Exception) {
                Log.d("Debug","Exeption hit")
                e.printStackTrace()
                Resource.Error(errorMessage = "Something went wrong")
            }
        }
    }

    private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
        return try {
            errorBody?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}