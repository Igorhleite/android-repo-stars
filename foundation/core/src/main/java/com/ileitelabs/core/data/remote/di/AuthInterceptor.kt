package com.ileitelabs.core.data.remote.di

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer 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"
            )
            .addHeader("X-GitHub-Api-Version", "2022-11-28")
            .build()

        return chain.proceed(request)
    }
}