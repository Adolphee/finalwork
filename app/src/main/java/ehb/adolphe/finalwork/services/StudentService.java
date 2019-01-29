package ehb.adolphe.finalwork.services;

import java.util.List;

import ehb.adolphe.finalwork.model.Student;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentService {

    @GET("students")
    Call<List<Student>> getAll();

    @GET("students/{id}")
    Call<Student> getStudentById(@Path("id") Long id);

    @GET("student/search/{query}")
    Call<Student> getAllStudentsByUsernameContaining(@Path("name") String name);

    @POST("students")
    Call<Student> create(@Body Student student);

    @PUT("students/{id}")
    Call<Student> update(@Path("id") Long id , @Body Student student);

    @DELETE("students/{id}")
    Call<Student> delete(@Path("id") Long id);

}
