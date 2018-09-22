package app.com.salaty;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import app.com.salaty.viewmodel.TimingsViewmodel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private TimingsViewmodel timingsViewmodel;
    SalaTiming salaTimings;
    ApiService apiService;
    @BindView(R.id.app_bar)
    Toolbar toolbar;
    @BindView(R.id.buDone)
    Button button;
    @BindView(R.id.mainactivity_country)
    EditText countryName;
    @BindView(R.id.mainactivity_city)
    EditText cityName;
    @BindView(R.id.mylocation_fab)
    FloatingActionButton locationFab;
    int year , month , day;
    private FusedLocationProviderClient client;
    private static final String TAG = "MainActivity";

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getCurrentDate();
        timingsViewmodel = ViewModelProviders.of(this).get(TimingsViewmodel.class);
        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //requestData(cityName.getText().toString(),countryName.getText().toString());
                salaTimings = timingsViewmodel
                        .getSalaTiming(cityName.getText().toString(),countryName.getText().toString(),month,year).getValue();
                timingsViewmodel
                        .getSalaTiming(cityName.getText().toString(),countryName.getText().toString(),month,year
                        ).observe(MainActivity.this
                        , new Observer<SalaTiming>() {
                            @Override
                            public void onChanged(@Nullable SalaTiming salaTiming) {
                                Toast.makeText(MainActivity.this, salaTiming.getStatus(), Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(MainActivity.this,TimingsActivity.class);
                                intent.putExtra("object", salaTiming);
                                intent.putExtra("day",day);
                                startActivity(intent);
                            }
                        });


            }
        });

    }

    private void requestData(String city, String country) {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
        Call<SalaTiming> salaTiming = apiService.getSalaTiming( city,
                country,2, month,year);

        salaTiming.enqueue(new Callback<SalaTiming>() {
            @Override
            public void onResponse(Call<SalaTiming> call, Response<SalaTiming> response) {
                salaTimings = response.body();

                if (salaTimings != null) {
                    Intent intent = new Intent(MainActivity.this,TimingsActivity.class);
                    intent.putExtra("object", salaTimings);
                    intent.putExtra("day",day);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Please enter valid data", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<SalaTiming> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getCityNameFromLatLong(double latit , double longit) {
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latit, longit, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String city = addresses.get(0).getLocality();
        String country = addresses.get(0).getCountryName();
        requestData(city,country);
    }

    @OnClick(R.id.mylocation_fab)
    public void onViewClicked() {
        getSalatimingsByLatLong();
    }

    private void getSalatimingsByLatLong() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            return;
        }
        client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if(location!= null){

                    getCityNameFromLatLong(location.getLatitude(),location.getLongitude());
                }

            }
        });
    }


    private void getCurrentDate(){
        Calendar now = Calendar.getInstance();
        year = now.get(Calendar.YEAR);
        month = now.get(Calendar.MONTH);
        day = now.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cityName.setText("");
        countryName.setText("");
    }
}
