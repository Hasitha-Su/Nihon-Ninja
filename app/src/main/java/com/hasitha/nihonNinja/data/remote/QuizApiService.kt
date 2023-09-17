package com.hasitha.nihonNinja.data.remote

import com.hasitha.nihonNinja.model.api.QuizResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface QuizApiService {

//    @GET("quizzes/{quizId}")
//    suspend fun getQuizById(@Path("quizId") quizId: Int): QuizResponse

    //Hard coded Id for testing purpose
    @GET("quizzes/quiz1.json")
    //suspend fun getQuizById(@Path("quizId") quizId: Int): QuizResponse
    suspend fun getQuizById(): QuizResponse

    // get a list of all quizzes
    @GET("quizzes")
    suspend fun getAllQuizzes(): Response<List<QuizResponse>>

}