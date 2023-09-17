package com.hasitha.nihonNinja.di

import android.content.Context
import androidx.room.Room
import com.hasitha.nihonNinja.constants.Constants.BASE_URL
import com.hasitha.nihonNinja.data.local.AppDatabase
import com.hasitha.nihonNinja.data.local.QuizDao
import com.hasitha.nihonNinja.data.remote.QuizApiService
import com.hasitha.nihonNinja.repository.QuestionRepository
import com.hasitha.nihonNinja.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, "NihonNinjaAppDB"
        ).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): QuizApiService {
        return retrofit.create(QuizApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuizDao(database: AppDatabase): QuizDao {
        return database.quizDao()
    }

    @Provides
    @Singleton
    fun provideQuizRepository(quizDao: QuizDao, apiService: QuizApiService): QuizRepository {
        return QuizRepository(quizDao, apiService)
    }

    @Provides
    @Singleton
    fun provideQuestionRepository(): QuestionRepository {
        return QuestionRepository()
    }
}