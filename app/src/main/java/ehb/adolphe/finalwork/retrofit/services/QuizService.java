package ehb.adolphe.finalwork.retrofit.services;

import ehb.adolphe.finalwork.model.Quiz;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuizService {
    @GET("courses/{id}/quiz")
    Call<Quiz> getQuizByCourseId(@Path("id") Long id);
}
