package app.com.salaty;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("calendarByCity")
    Call<SalaTiming> getSalaTiming(
              @Query("city") String city,
              @Query("country") String country,
              @Query("method") int method,
              @Query("month") int month,
              @Query("year") int year);
}
