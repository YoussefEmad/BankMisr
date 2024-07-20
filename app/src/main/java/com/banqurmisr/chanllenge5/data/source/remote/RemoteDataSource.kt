package com.banqurmisr.chanllenge5.data.source.remote

import com.banqurmisr.chanllenge5.data.models.RemoteMovieModel
import com.banqurmisr.chanllenge5.domain.utils.Resource
import com.google.gson.Gson
import org.json.JSONObject
import javax.inject.Inject


class RemoteDataSource @Inject
constructor(val modelApi: MovieApi) : IRemoteDataSource{

    override suspend fun getMovies(endpoint: String,page: Int): Resource<RemoteMovieModel> {

        val response = modelApi.getMovies(endpoint,page)

        return if (response.isSuccessful && response.body() != null){
           Resource.success(response.body()!!)
        }else{
            val error = response.errorBody()?.string()

            Resource.error(Exception(getStatusMessage(error)))
        }
    }

    private fun getStatusMessage(jsonString: String?): String {
        try {
            val jsonObject = JSONObject(jsonString)
            val statusMessage = jsonObject.optString("status_message", "No message found")
            return statusMessage
        } catch (e: Exception) {
            e.printStackTrace()
            return "Error parsing JSON"
        }
    }
}