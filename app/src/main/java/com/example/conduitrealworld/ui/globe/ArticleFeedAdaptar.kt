package com.example.conduitrealworld.ui.globe

import android.graphics.PorterDuff
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.conduitrealworld.R
import com.example.conduitrealworld.extensions.loadImage
import com.example.conduitrealworld.modules.entites.Article
import java.security.AccessController.getContext


class ArticleFeedAdaptar(private val onheartClicked:(Boolean,String) ->Boolean):
    androidx.recyclerview.widget.ListAdapter<Article, ArticleFeedAdaptar.ArticleViewHolder>(DiffUtil()) {




    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article= getItem(position)
        holder.bind(article)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_globe,parent,false)

        return  ArticleViewHolder(view)
    }



    inner class ArticleViewHolder(view:View) :RecyclerView.ViewHolder(view){



        val username = view.findViewById<TextView>(R.id.username)
        val date_tv = view.findViewById<TextView>(R.id.datetv)
        val image = view.findViewById<ImageView>(R.id.image)
        val title_tv = view.findViewById<TextView>(R.id.title_tv)
        val discription_tv = view.findViewById<TextView>(R.id.discription_tv)
        val heart = view.findViewById<ImageView>(R.id.Iv_thumb)
        val Tv_like = view.findViewById<TextView>(R.id.tv_like)



        fun bind(article: Article){

            username.text = article.author.username
            date_tv.text = article.createdAt
            title_tv.text= article.title
            discription_tv.text = article.description
            Tv_like.text = article.favoritesCount.toString()
            image.loadImage(article.author.image,true)


            val is_fav:Boolean = article.favorited
            Log.d("checking ----------",article.author.username)
            Log.d("checking ----------",article.favorited.toString())

            if(is_fav){
                heart.setImageResource(R.drawable.loved)
            }
            heart.setOnClickListener {
              val result= onheartClicked(is_fav,article.slug)
                if(result){
                    heart.setImageResource(R.drawable.love)
                    Tv_like.text = article.favoritesCount.toString()

                }else{
                    Tv_like.text = article.favoritesCount.toString()
                    heart.setImageResource(R.drawable.loved)
                }
            }






        }
    }


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
           return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {

            return oldItem.toString()==newItem.toString()
        }


    }
}

