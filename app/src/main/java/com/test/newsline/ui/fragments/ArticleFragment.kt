package com.test.newsline.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
import com.test.newsline.ui.viewmodels.SharedViewModel
import com.test.newsline.utils.AppEnums
import com.test.newsline.utils.Constants
import com.test.newsline.utils.ViewUtil.gone
import com.test.newsline.utils.ViewUtil.show

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private lateinit var articleViewModel: ArticleViewModel
    private var articlesAdapter: ArticlesAdapter? = null
    private var articles = arrayListOf<Article>()
    private var sections = listOf<String>()

    private val sharedViewModel: SharedViewModel by activityViewModels()

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
            filterAppCompatImageButton.setOnClickListener {
                openFilter()
            }
            recordLayout.recordsRecyclerView.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
        sharedViewModel.filterObject.observe(viewLifecycleOwner) {
            if (it != null) {
                fetchArticles(it.section ?: "", it.duration)
            }
        }
        fetchArticles(Constants.ALL_SECTIONS, Constants.TODAY)
    }

    private fun fetchArticles(section: String, days: String) {
        binding.recordLayout.animationViewBig.show()
        binding.recordLayout.recordsRecyclerView.gone()
        articleViewModel.getArticles(section, days).observe(viewLifecycleOwner) {

            when (it?.status) {
                AppEnums.Status.LOADING -> {}
                AppEnums.Status.SUCCESS -> {
                    binding.recordLayout.animationViewBig.gone()
                    binding.recordLayout.recordsRecyclerView.show()
                    articles.clear()

                    articles.addAll(it.data?.results ?: emptyList())
                    sections = articles.mapNotNull { it.section }.toList()
                    setupArticles()
                }
                AppEnums.Status.ERROR -> {
                    binding.recordLayout.animationViewBig.gone()
                    articles.clear()
                    setupArticles()
                }
                else -> {}
            }
        }
    }

    private fun openFilter() {
        val filterFragment = FilterFragment()
        filterFragment.show(childFragmentManager, "FILTER")
    }

    private fun setupArticles() {

        binding.recordLayout.apply {


            recordsRecyclerView.adapter = ArticlesAdapter(articles, requireContext()) {
                it?.let {
                    findNavController().navigate(
                        ArticleFragmentDirections.actionNavArticlesToArticleDetailFragment(
                            it
                        )
                    )
                }
            }

            emptyTextView.text = getString(R.string.noArticleFound_msg)
            emptyTextView.visibility = if (articles.isEmpty()) VISIBLE else GONE


        }
    }
}