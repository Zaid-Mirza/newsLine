package com.test.newsline.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.newsline.R
import com.test.newsline.adapters.ArticlesAdapter
import com.test.newsline.databinding.FragmentArticleBinding
import com.test.newsline.models.Article
import com.test.newsline.repositories.ArticleRepository
import com.test.newsline.ui.viewmodels.ArticleViewModel
import com.test.newsline.ui.viewmodels.ArticleViewModelFactory
import com.test.newsline.utils.AppEnums

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private lateinit var articleViewModel: ArticleViewModel
    private var articlesAdapter: ArticlesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        // Article repository should be injected using a DI , directly initialized for sake of assessment
        val articleViewModelFactory = ArticleViewModelFactory(ArticleRepository())
        articleViewModel =
            ViewModelProvider(this, articleViewModelFactory)[ArticleViewModel::class.java]

        binding.apply {
            recordLayout.recordsRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        fetchArticles("all-sections", "7")
    }

    private fun fetchArticles(section: String, days: String) {
        articleViewModel.getArticles(section, days).observe(viewLifecycleOwner) {

            when (it?.status) {
                AppEnums.Status.LOADING -> {}
                AppEnums.Status.SUCCESS -> {
                    setupArticles(it.data?.results)
                }
                AppEnums.Status.ERROR -> {}
                else -> {}
            }
        }
    }

    private fun setupArticles(articles: ArrayList<Article>?) {

        binding.recordLayout.apply {

            articles?.let {
                recordsRecyclerView.adapter = ArticlesAdapter(articles, requireContext()) {
                    it?.let {
                        findNavController().navigate(
                            ArticleFragmentDirections.actionNavArticlesToArticleDetailFragment(
                                it
                            )
                        )
                    }
                }
            }
            emptyTextView.text = getString(R.string.noArticleFound_msg)
            emptyTextView.visibility = if (articles.isNullOrEmpty()) VISIBLE else GONE


        }
    }
}