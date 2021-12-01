package com.bijesh.challengeapplication.repos

import com.bijesh.challengeapplication.services.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllBreed () = retrofitService.getDogBreeds()

    fun getSelectedBreed(breedName:String) = retrofitService.getDogBreed(breedName)

    fun getSelectedBreedById(breedId : Int) = retrofitService.getDogBreedById(breedId)

}