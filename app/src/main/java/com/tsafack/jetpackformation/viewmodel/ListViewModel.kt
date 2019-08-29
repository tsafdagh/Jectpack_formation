package com.tsafack.jetpackformation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tsafack.jetpackformation.model.DogBreed
import com.tsafack.jetpackformation.model.DogDatabase
import com.tsafack.jetpackformation.model.DogsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application): BaseViewModel(application) {

    private val dogsService = DogsApiService()
    private val disposable = CompositeDisposable()

    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){

        refrechFromRemote()
    }

    private fun refrechFromRemote() {
        loading.value = true
        disposable.add(
            dogsService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object:DisposableSingleObserver<List<DogBreed>>(){
                    override fun onSuccess(dogList: List<DogBreed>) {
                        storeDogsLocally(dogList)
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun dogsRetrieved(dogList:List<DogBreed>){
        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }

    private fun storeDogsLocally(list:List<DogBreed>){
        launch {
            val dao=DogDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()
            val result=dao.inserAll(*list.toTypedArray())
            var i=0
            while (i<list.size){
                list[i].uuid=result[i].toInt()
                i++
            }
            dogsRetrieved(list)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}