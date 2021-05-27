package com.example.core.di

import android.annotation.SuppressLint
import android.provider.Settings.Secure.ANDROID_ID
import android.provider.Settings.Secure.getString
import androidx.room.Room
import com.apollographql.apollo.ApolloClient
import com.example.core.BuildConfig
import com.example.core.data.MediaRepositoryImpl
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.room.MovieCatalogueDatabase
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.domain.repository.MediaRepository
import com.example.core.util.ANILIST_HOST_NAME
import com.example.core.util.BASE_ANILIST_URL
import com.example.core.util.DATABASE_NAME
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single {
        CertificatePinner.Builder()
            .add(ANILIST_HOST_NAME, "sha256/7qHkLUIeZ4bOk+eZzf30jnZDiVBaNpRXJh+llhx8xwg=")
            .add(ANILIST_HOST_NAME, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .add(ANILIST_HOST_NAME, "sha256/Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=")
            .build()
    }
    single {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .certificatePinner(get())
                .build()
        } else {
            OkHttpClient.Builder()
                .certificatePinner(get())
                .build()
        }
    }
    single {
        ApolloClient.builder()
            .serverUrl(BASE_ANILIST_URL)
            .okHttpClient(get())
            .build()
    }
}

@SuppressLint("HardwareIds")
val databaseModule = module {
    factory { get<MovieCatalogueDatabase>().mediasFavoriteDao() }
    single {
        val passphrase: ByteArray =
            SQLiteDatabase.getBytes(
                getString(
                    androidContext().contentResolver,
                    ANDROID_ID
                ).toCharArray()
            )
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieCatalogueDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<MediaRepository> { MediaRepositoryImpl(get(), get()) }
}