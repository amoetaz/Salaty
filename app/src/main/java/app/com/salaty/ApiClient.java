package app.com.salaty;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String TAG = "ApiClientClass";
    public static final String BaseUrl = "http://api.aladhan.com/v1/";
    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

        if(retrofit == null){
            retrofit =new Retrofit.Builder().baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static ApiService getApiService(){
        return getRetrofit().create(ApiService.class);
    }

    public LiveData<SalaTiming> getSalaTiming(String city, String country, int month, int year){

        final MutableLiveData<SalaTiming> salaTiming = new MutableLiveData<>();

        ApiService apiService = ApiClient.getApiService();

        Call<SalaTiming> call = apiService.getSalaTiming(city, country, 2, month, year);

        call.enqueue(new Callback<SalaTiming>() {
            @Override
            public void onResponse(@NonNull Call<SalaTiming> call, @NonNull Response<SalaTiming> response) {
                SalaTiming st = response.body();
                salaTiming.setValue(st);
            }

            @Override
            public void onFailure(Call<SalaTiming> call, Throwable t) {
                Log.i(TAG, "onFailure: "+t.getMessage());

            }
        });

        return salaTiming;
    }

}
