package com.test.newsline

import com.test.newsline.repositories.ArticleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class ArticleTest(private val section: String, private val days: String) {


    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("all-sections", "7"),
            arrayOf("arts", "10"),
            arrayOf("business", "30")
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun verifyArticlesResult() = runTest {
        val rep = ArticleRepository()
        rep.getArticles(section, days) {
            assert(it.data?.results != null)
        }
    }
}