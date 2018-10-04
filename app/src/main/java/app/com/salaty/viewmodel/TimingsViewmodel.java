package app.com.salaty.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;

import app.com.salaty.ApiClient;
import app.com.salaty.SalaTiming;

public class TimingsViewmodel extends ViewModel {

    private LiveData<SalaTiming> salaTiming;
    private ApiClient apiClient = new ApiClient();

    public LiveData<SalaTiming> getSalaTiming(String city, String country, int month, int year) {

        if (salaTiming == null){

            salaTiming = apiClient.getSalaTiming(city,country,month,year);
        }
        return salaTiming;
    }


}
