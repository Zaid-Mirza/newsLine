package com.test.newsline.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.newsline.databinding.RowArticleItemBinding
import com.test.newsline.models.Article

class ArticlesAdapter(
    private val orderItems: List<Article?>,
    private val context: Context,
    val listener: (Article?) -> Unit
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    private lateinit var binding: RowArticleItemBinding

    override fun getItemCount(): Int {
        return orderItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RowArticleItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.onBind(orderItems[position])

    }


    inner class ViewHolder(val binding: RowArticleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(article: Article?) {
            binding.apply {
                sectionMaterialTextView.text = article?.section
                articleLineMaterialTextView.text = article?.title
                articleByMaterialTextView.text = article?.byline
                publishedDateMaterialTextView.text = article?.publishedDate
                article?.media?.firstOrNull()?.mediaImages?.firstOrNull()?.let {
                    if (it.format?.lowercase()?.contains("thumbnail")== true)
                        Glide.with(context).load(it.url).into(articleImage)

                }



                itemView.setOnClickListener {
                    listener(article)
                }
            }


        }


    }


}