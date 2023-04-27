package com.test.newsline

import com.test.newsline.networks.RetrofitHelper
import com.test.newsline.repositories.ArticleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.runners.Parameterized.Parameters

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RetrofitTest {
    @Test
    fun testRetrofitBaseUrl() {
        //Get an instance of Retrofit
        val instance = RetrofitHelper.retrofit
        //Assert that, Retrofit's base url matches to our BASE_URL
        assert(instance.baseUrl().toUrl().toString() == BuildConfig.BASE_URL)
    }


}
