package com.hasitha.nihonNinja.data.remote

import com.hasitha.nihonNinja.model.api.QuizResponse
import com.hasitha.nihonNinja.model.api.QuizResult
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface QuizApiService {

//    @GET("quizzes/{quizId}")
//    suspend fun getQuizById(@Path("quizId") quizId: Int): QuizResponse

    //Hard coded Id for testing purpose
//    @GET("/quizzes/quiz/1")
    @GET("/quizzes/allQuizes")
    //suspend fun getQuizById(@Path("quizId") quizId: Int): QuizResponse
    suspend fun getQuizById(): QuizResponse

    // get a list of all quizzes
//    @GET("/quizzes/allQuizes")
//    suspend fun getAllQuizzes(): Response<List<QuizResponse>>

    @POST("/quizzes/saveQuizResult")
    suspend fun saveQuizResult(@Body quizResult: QuizResult): Response<String>

}