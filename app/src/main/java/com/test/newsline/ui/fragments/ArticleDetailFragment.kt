package com.test.newsline.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.test.newsline.R
import com.test.newsline.databinding.FragmentArticleDetailBinding


class ArticleDetailFragment : Fragment() {
    private lateinit var binding: FragmentArticleDetailBinding
    private val args: ArticleDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =
            FragmentArticleDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupArticleDetail()

    }

    private fun setupArticleDetail() {
        val article = args.article
        article.media?.firstOrNull()?.mediaImages?.filter { it.format?.contains("440") == true }
            ?.firstOrNull()?.let {
            Glide.with(requireContext()).load(it.url).into(binding.thumbnailAppCompatImageView)
        }
        binding.apply {
            articleTitleMaterialTextView.text = article.title
            articleabstractMaterialTextView.text = article.abstract
            publishedDateMaterialTextView.text = article.publishedDate

        }
    }

}