package com.example.conduitrealworld.services

/*class AuthInterceptor: Interceptor {




    lateinit var  tokenManager: TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {


        val myApp = MyApp()
        tokenManager = TokenManager(myApp.getInstance())

        val request = chain.request().newBuilder()
        val token = tokenManager.getToken()
        request.addHeader("Authorization","Bearer$token")

        return  chain.proceed(request.build())

    }
}*/