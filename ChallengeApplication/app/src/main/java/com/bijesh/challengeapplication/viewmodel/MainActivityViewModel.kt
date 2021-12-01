package com.bijesh.challengeapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bijesh.challengeapplication.models.DogBreeds
import com.bijesh.challengeapplication.models.desmodels.SelectedBreed
import com.bijesh.challengeapplication.models.desmodels.SelectedBreedItem
import com.bijesh.challengeapplication.repos.MainRepository
import retrofit2.Call
import retrofit2.Response


class MainActivityViewModel constructor(private val repository: MainRepository): ViewModel(){

    val dogList = MutableLiveData<List<DogBreeds>>()
    val selectedBreedList = MutableLiveData<List<SelectedBreedItem>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllDogBreeds(){
        val response = repository.getAllBreed()

        response.enqueue(object : retrofit2.Callback<List<DogBreeds>> {
            override fun onResponse(call: Call<List<DogBreeds>>, response: Response<List<DogBreeds>>) {
                dogList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<DogBreeds>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getSelectedDogBreeds(breedName:String){
        val response = repository.getSelectedBreed(breedName)

        response.enqueue(object : retrofit2.Callback<List<DogBreeds>> {
            override fun onResponse(call: Call<List<DogBreeds>>, response: Response<List<DogBreeds>>) {
                dogList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<DogBreeds>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun getSelectedDogBreedsById(breedId:Int){
        val response = repository.getSelectedBreedById(breedId)

//        response.enqueue(object : retrofit2.Callback<List<SelectedBreedItem>> {
//            override fun onResponse(call: Call<List<SelectedBreedItem>>, response: Response<List<SelectedBreedItem>>) {
//                selectedBreedList.postValue(response.body())
//            }
//
//            override fun onFailure(call: Call<List<SelectedBreedItem>>, t: Throwable) {
//                errorMessage.postValue(t.message)
//            }
//        })

        response.enqueue(object : retrofit2.Callback<List<SelectedBreedItem>>{
            override fun onResponse(
                call: Call<List<SelectedBreedItem>>,
                response: Response<List<SelectedBreedItem>>
            ) {
                selectedBreedList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<SelectedBreedItem>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }




}