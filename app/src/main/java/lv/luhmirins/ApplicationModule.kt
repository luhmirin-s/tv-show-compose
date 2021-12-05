package lv.luhmirins

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lv.luhmirins.api.ApiModule
import javax.inject.Named

@Module(
    includes = [
        ApiModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Named("tmdbApiKey")
    fun tmdbApiKey(): String = BuildConfig.tmdbApiKey

    @Provides
    @Named("enableLogging")
    fun enableLogging(): Boolean = BuildConfig.DEBUG

}