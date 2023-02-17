package com.example.conduitrealworld.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.conduitrealworld.Utils.NetworkResult
import com.example.conduitrealworld.modules.Request.CreateArticleRequest
import com.example.conduitrealworld.modules.Response.ArticleResponse
import com.example.conduitrealworld.modules.Response.ArticlesResponse
import com.example.conduitrealworld.modules.Response.UserResponse
import com.example.conduitrealworld.modules.entites.Article
import com.example.conduitrealworld.modules.entites.ArticleData
import com.example.conduitrealworld.services.ConduitApi
import com.example.conduitrealworld.services.ConduitAuthApi
import com.example.conduitrealworld.services.RetroFitHelper
import org.json.JSONObject
import retrofit2.Response

class ConduitApiRepository(private val conduitApi: ConduitApi) {



    private  val mutable_articles = MutableLiveData<List<Article>>()

    val articles : LiveData<List<Article>>
        get() = mutable_articles




    suspend fun fetch_global_feed_articles(){
        val result = conduitApi?.getAllArticles("1000")


        if(result?.body()!=null){

            mutable_articles.postValue(result.body()?.articles)

        }
    }

    private val conduitAuthApi = RetroFitHelper.authApi
    private  val _feed = MutableLiveData<List<Article>>()
    val feed :LiveData<List<Article>>
    get() =  _feed


    private val _article= MutableLiveData<ArticleResponse>()
    val articledata :LiveData<ArticleResponse>
    get() = _article

    suspend fun favouritearticle(slug:String){

        val result= conduitAuthApi.favoriteArticle(slug)
        if(result?.body()!=null){
            _article.postValue(result?.body())
        }

    }
    suspend fun unfavouritearticle(slug:String){

        val result= conduitAuthApi.unfavoriteArticle(slug)
        if(result?.body()!=null){
            _article.postValue(result?.body())
        }

    }


     suspend fun  fetch_myfeed(){
         val result = conduitAuthApi?.getfeedArticles()
         if(result?.body()!=null){
             _feed.postValue(result.body()?.articles)
         }
     }

    suspend fun fetchglobalarticle_afterlogin(){
        val result = conduitAuthApi?.getAllArticlesafterlogin("1000")


        if(result?.body()!=null){

            mutable_articles.postValue(result.body()?.articles)

        }

    }



    private val _artcilelivedata = MutableLiveData<NetworkResult<ArticleResponse>>()
    val articlelivedata :LiveData<NetworkResult<ArticleResponse>>
    get() =  _artcilelivedata

    suspend fun  createarticle(articleData: ArticleData){
        _artcilelivedata.postValue(NetworkResult.Loading())
        val response = conduitAuthApi.createarticle(CreateArticleRequest(articleData))
        Log.d("CONDUIT-----------",response.body().toString())
        handelResponse(response)


    }


    private fun handelResponse(response: Response<ArticleResponse>) {
        if (response.isSuccessful && response.body() != null) {

            _artcilelivedata.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {

            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _artcilelivedata.postValue(NetworkResult.Error(message = errorObj.getString("errors")))
        } else {

            _artcilelivedata.postValue(NetworkResult.Error(message = "Something went wrong"))

        }
    }








}

