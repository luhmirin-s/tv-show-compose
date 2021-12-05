package lv.luhmirins.api

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lv.luhmirins.BuildConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.File
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Named("baseUrl")
    fun baseUrl(): String = "https://api.themoviedb.org/"

    @Provides
    @Named("posterUrl")
    fun posterUrl(): String = "https://www.themoviedb.org/t/p/w440_and_h660_face"

    @Provides
    @Named("httpCacheDir")
    fun httpCacheDir(application: Application): File? = application.cacheDir

    @Provides
    @Singleton
    fun okHttpClient(
        @Named("httpCacheDir") cacheDir: File?,
        @Named("enableLogging") enableLogging: Boolean,
    ): OkHttpClient = OkHttpClient.Builder().apply {
        if (cacheDir != null) cache(Cache(cacheDir, (1024 * 1024 * 100).toLong())) // 100MB
        if (enableLogging) addInterceptor(
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        )
    }.build()

    @Provides
    fun moshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(Date::class.java, Rfc3339DateJsonAdapter())
        .build()

    @Provides
    @Singleton
    fun tmdbApi(
        @Named("baseUrl") baseUrl: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi,
    ): TmdbApi = Retrofit.Builder()
        .baseUrl(baseUrl)
        .callFactory(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create()

}