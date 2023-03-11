package lt.ricbiv.newsapp.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lt.ricbiv.newsapp.BuildConfig
import lt.ricbiv.newsapp.api.interceptor.ApiKeyInterceptor
import lt.ricbiv.newsapp.api.services.NewsApiService
import okhttp3.Credentials
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideNewsApi () : NewsApiService{
        val httpClient = OkHttpClient.Builder()
            .callTimeout(360, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
        val dispatcher = Dispatcher()
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        dispatcher.maxRequests = 1
        dispatcher.maxRequestsPerHost = 1
        httpClient.dispatcher(dispatcher)
        //hardcoded key
        httpClient.addInterceptor(ApiKeyInterceptor("1a16811dd25348b891db420dc03bb8ad"))
        httpClient.addInterceptor(logInterceptor)

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(NewsApiService::class.java)


    }
}