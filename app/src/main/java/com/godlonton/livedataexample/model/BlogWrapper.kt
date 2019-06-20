package com.godlonton.livedataexample.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Created on : June 16, 2019
 * Author     : Ryan Godlonton-Shaw
 */

@JsonClass(generateAdapter = true)
data class BlogWrapper (

    @Json(name="data")
    var blog: MutableList<Blog>? = null,
    @Json(name="error")
    var error: Boolean? = null,
    @Json(name="message")
    var message: String? = null,
    @Json(name="status")
    var status: String? = null

)
