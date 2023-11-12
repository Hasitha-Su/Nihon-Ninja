package com.hasitha.nihonNinja.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.hasitha.nihonNinja.model.api.QuizResponse
import com.hasitha.nihonNinja.model.api.QuizResult

/**
 * Retrofit API service interface for quiz-related API requests.
 */
interface QuizApiService {

    /**
     * Sends an HTTP GET request to retrieve quiz data.
     *
     * @return A [QuizResponse] object representing quiz data.
     */
    @GET("/quizzes/allQuizes")
    suspend fun getQuizById(): QuizResponse

    /**
     * Sends an HTTP POST request to save a quiz result.
     *
     * @param quizResult The [QuizResult] object representing the quiz result to be saved.
     * @return A [Response] object containing a String response (typically indicating success or failure).
     */
    @POST("/quizzes/saveQuizResult")
    suspend fun saveQuizResult(@Body quizResult: QuizResult): Response<String>

}