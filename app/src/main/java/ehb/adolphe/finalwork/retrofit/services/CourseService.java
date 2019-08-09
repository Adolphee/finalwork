package ehb.adolphe.finalwork.retrofit.services;

import java.util.List;

import ehb.adolphe.finalwork.model.Course;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CourseService {

    @GET("courses")
    Call<List<Course>> getAll();

    @GET("courses/{id}")
    Call<Course> getCourseById(@Path("id") Long id);

    @POST("courses")
    Call<Course> create(@Body Course course);

    @PUT("courses/{id}")
    Call<Course> update(@Path("id") Long id, @Body Course course);

    @DELETE("courses/{id}")
    Call<Course> delete(@Path("id") Long id );
}
