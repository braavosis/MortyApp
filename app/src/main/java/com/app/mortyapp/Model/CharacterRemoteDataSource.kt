package com.app.mortyapp

import com.app.mortyapp.Model.CharacterService
import com.app.mortyapp.Model.RetrofitServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRemoteDataSource {
    fun getCharacterList(networkResponse: NetworkResponse<List<Character>>) {
        val service = RetrofitServices.instance
            .create(CharacterService::class.java)
            .getCharacterList()

        service.enqueue(object : Callback<CharacterListResponse> {
            override fun onResponse(
                call: Call<CharacterListResponse>,
                response: Response<CharacterListResponse>
            ) {
                val resource = response.body()?.run {
                    if (results.isNotEmpty())
                        Resource(NetworkStatus.SUCCESS, results)
                    else
                        Resource(NetworkStatus.ERROR)
                } ?: run {
                    Resource(NetworkStatus.ERROR)
                }
                networkResponse.onResponse(resource)
            }

            override fun onFailure(call: Call<CharacterListResponse>, t: Throwable) {
                networkResponse.onResponse(Resource(NetworkStatus.ERROR, message = t.message))
            }
        })
    }


}

interface NetworkResponse<T> {
    fun onResponse(value: Resource<T>)
}