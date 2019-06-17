package com.godlonton.livedataexample.viewmodel


import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.godlonton.livedataexample.model.Blog

/**
 * Created on : June 16, 2019
 * Author     : Ryan Godlonton-Shaw
 */

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val movieRepository= BlogRepository()
    val allBlog: LiveData<List<Blog>> get() = movieRepository.getMutableLiveData()

}