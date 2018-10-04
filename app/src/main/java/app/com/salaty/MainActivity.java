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
import android.util.Log;
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

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

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
    private static final String TAG = "MainActivityclass";

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
        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);

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
        String locality =  addresses.get(0).getLocality();
        String addressLine = addresses.get(0).getAddressLine(0);
        String subLocality = addresses.get(0).getSubLocality();
        Log.i(TAG, "getCityNameFromLatLong: "+" "+addressLine+" ");
        launchActivity(country,addressLine);
        Toast.makeText(this, country+" "+addressLine, Toast.LENGTH_SHORT).show();
    }

    private void launchActivity(String country,String city) {
        Intent intent = new Intent(MainActivity.this,TimingsActivity.class);
        intent.putExtra("day",day);
        intent.putExtra("year",year);
        intent.putExtra("month",month);
        intent.putExtra("country",country);
        intent.putExtra("city",city);
        startActivity(intent);
    }

    @OnClick({R.id.mylocation_fab,R.id.buDone})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.mylocation_fab:getSalatimingsByLatLong();break;
            case R.id.buDone : getSalatimingbycountryandcity();
            default:break;
        }

    }

    public void getSalatimingbycountryandcity(){
        launchActivity(countryName.getText().toString(),cityName.getText().toString());
    }

    private void getSalatimingsByLatLong() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            return;
        }
        client.getLastLocation()
                .addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
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
