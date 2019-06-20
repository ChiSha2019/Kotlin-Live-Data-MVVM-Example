package com.godlonton.livedataexample.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.godlonton.livedataexample.model.Blog

/**
 * Created on : June 16, 2019
 * Author     : Ryan Godlonton-Shaw
 */

class MainViewModel() : ViewModel() {

    val movieRepository= BlogRepository()
    val allBlog: LiveData<List<Blog>> get() = movieRepository.getMutableLiveData()

    override fun onCleared() {
        super.onCleared()
        movieRepository.completableJob.cancel()
    }
}