package lv.luhmirins

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module(
    includes = [
    ]
)
@InstallIn(SingletonComponent::class)
object ApplicationModule {
    @Provides
    @Named("enableLogging")
    fun enableLogging(): Boolean = BuildConfig.DEBUG

}