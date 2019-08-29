package com.tsafack.jetpackformation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsafack.jetpackformation.model.DogBreed

class ListViewModel: ViewModel() {

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        val dog1 = DogBreed("1", "Cord1", "15 Years", "BreadGroup", "Temperamento", "breadFor", "url Image")
        val dog2 = DogBreed("2", "Cord2", "7 Years",  "BreadGroup", "Temperamento", "breadFor", "url Image")
        val dog3 = DogBreed("3", "Cord3", "5 Years", "BreadGroup", "Temperamento", "breadFor", "url Image")
        val dog4 = DogBreed("4", "Cord4", "20 Years", "BreadGroup", "Temperamento", "breadFor", "url Image")

        val dogList = arrayListOf(dog1, dog2, dog3, dog4)

        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }
}