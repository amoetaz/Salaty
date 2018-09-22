package app.com.salaty.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import app.com.salaty.ApiClient;
import app.com.salaty.SalaTiming;

public class TimingsViewmodel extends ViewModel {

    private static final String TAG = "TimingsViewmodel";
    private LiveData<SalaTiming> salaTiming;
    private ApiClient apiClient = new ApiClient();

    public LiveData<SalaTiming> getSalaTiming(String city, String country, int month, int year) {

        if (salaTiming == null){

            salaTiming = apiClient.getSalaTiming(city,country,month,year);
        }
        return salaTiming;
    }
}
