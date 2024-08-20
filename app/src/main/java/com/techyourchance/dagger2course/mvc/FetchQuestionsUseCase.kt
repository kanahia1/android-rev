package com.techyourchance.dagger2course.mvc

import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.Question
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class FetchQuestionsUseCase(private val stackoverflowApi: StackoverflowApi) {

    suspend fun fetchQuestions(): Result{
        return withContext(Dispatchers.IO){
            try {
                val response = stackoverflowApi.lastActiveQuestions(20)
                if (response.isSuccessful && response.body() != null) {
                    return@withContext Result.Success(response.body()!!.questions)
                } else {
                    return@withContext Result.Failure
                }
            } catch (t: Throwable) {
                if (t !is CancellationException) {
                    return@withContext Result.Failure
                } else {
                    throw t
                }
            }
        }
    }

    sealed class Result{
        class Success(val questions: List<Question>): Result()
        object Failure : Result()
    }

}