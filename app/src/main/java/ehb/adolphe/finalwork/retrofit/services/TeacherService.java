package ehb.adolphe.finalwork.retrofit.services;

import java.util.List;

import ehb.adolphe.finalwork.model.Teacher;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TeacherService {

    @GET("teachers")
    Call<List<Teacher>> getAll();

    @GET("teachers/{id}")
    Call<Teacher> getTeacherById(@Path("id") Long id);

    @POST("teachers")
    Call<Teacher> create(@Body Teacher teacher);

    @PUT("teachers/{id}")
    Call<Teacher> update(@Path("id") Long id , @Body Teacher teacher);

    @DELETE("teachers/{id}")
    Call<Teacher> delete(@Path("id") Long id);
}
