package com.ileitelabs.core.data.remote.di

import com.ileitelabs.repotrends.foundation.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        if (BuildConfig.ACCESS_TOKEN.isNotBlank()) {
            request
                .addHeader(
                    "Authorization",
                    BuildConfig.ACCESS_TOKEN
                )
                .addHeader("X-GitHub-Api-Version", "2022-11-28")
        }
        return chain.proceed(request.build())
    }
}