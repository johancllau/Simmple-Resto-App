package com.neox.restoapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neox.restoapp.model.Cafe
import com.neox.restoapp.service.network.CafeService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

import io.reactivex.schedulers.Schedulers


class MainActivityViewModel : ViewModel() {

    private val cafeService = CafeService()
    private val disposable = CompositeDisposable()

    val cafe = MutableLiveData<Cafe>()

    fun fetchDataCafe() {
        disposable.add(
            cafeService.getCafe()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Cafe>() {
                    override fun onSuccess(t: Cafe) {
                        cafe.value = t
                    }

                    override fun onError(e: Throwable) {
                        Log.i("error : ", e.toString())
                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}