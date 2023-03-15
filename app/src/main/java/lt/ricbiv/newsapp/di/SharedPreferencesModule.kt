package lt.ricbiv.newsapp.di

import android.content.Context
import android.content.SharedPreferences
import at.favre.lib.armadillo.Armadillo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lt.ricbiv.newsapp.utils.Settings
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Singleton
    @Provides
    fun provideSecurePreferences(@ApplicationContext context: Context): SharedPreferences {
        //creating with Armadillo to have possibility to add encryption for sharedPreferences
        return Armadillo.create(context, Settings.PREFERENCES)
            .encryptionFingerprint(context)
            .build()
    }
}