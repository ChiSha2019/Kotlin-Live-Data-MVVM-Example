package com.godlonton.livedataexample.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.godlonton.livedataexample.model.Blog
import com.godlonton.livedataexample.networking.RestApiService
import kotlinx.coroutines.*
import retrofit2.HttpException

/**
 * Created on : June 16, 2019
 * Author     : Ryan Godlonton-Shaw
 */

class BlogRepository() {

    private var movies = mutableListOf<Blog>()
    private var mutableLiveData = MutableLiveData<List<Blog>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    private val thisApiCorService by lazy {
        RestApiService.createCorService()
    }

    fun getMutableLiveData():MutableLiveData<List<Blog>> {
        coroutineScope.launch {
            val request = thisApiCorService.getPopularBlog()
            withContext(Dispatchers.Main) {
                try {

                    val response = request.await()
                    val mBlogWrapper = response;
                    if (mBlogWrapper != null && mBlogWrapper.blog != null) {
                        movies = mBlogWrapper.blog as MutableList<Blog>;
                        mutableLiveData.value=movies;
                    }

                } catch (e: HttpException) {
                    // Log exception //

                } catch (e: Throwable) {
                    // Log error //)
                }
            }
        }
        return mutableLiveData;
    }
}