package ehb.adolphe.finalwork.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroHelper {
    public static Retrofit getInstance(){
        return new Retrofit.Builder()
                .baseUrl("http://10.0.0.17:8080/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
