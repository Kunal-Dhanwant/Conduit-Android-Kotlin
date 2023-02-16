package com.example.conduitrealworld

import com.example.conduitrealworld.modules.Request.SignupRequest
import com.example.conduitrealworld.modules.entites.SignupData
import com.example.conduitrealworld.services.ConduitApi
import com.example.conduitrealworld.services.ConduitAuthApi
import com.example.conduitrealworld.services.RetroFitHelper
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import retrofit2.create
import kotlin.random.Random


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    val conduitAPI = RetroFitHelper.getInstance().create(ConduitApi::class.java)
    val conduitAuthApi= RetroFitHelper.authApi


    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }



    @Test
    fun `GET articles`() {
        runBlocking {


            val articles = conduitAPI.getAllArticles()

            assertNotNull(articles.body()?.articles)
        }
    }


    @Test
    fun `POST users - create user`() {
        val userCreds =SignupData("kunal33@gmail","kk","kud")
        runBlocking {
            val resp = conduitAPI.signupUser(SignupRequest(userCreds))
            assertEquals(userCreds.username, resp.body())
        }
    }


    @Test
    fun `GET feedarticles`() {
        runBlocking {


            val feedarticles = conduitAuthApi.getfeedArticles()

            assertNotNull(feedarticles.body()?.articles)
        }
    }
}