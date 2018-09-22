package app.com.salaty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TimingsActivity extends AppCompatActivity {

    @BindView(R.id.app_bar)
    Toolbar toolbar;
    @BindView(R.id.fajr_value)
    TextView fajrValue;
    @BindView(R.id.shoroq_value)
    TextView shoroqValue;
    @BindView(R.id.dohor_value)
    TextView dohorValue;
    @BindView(R.id.asr_value)
    TextView asrValue;
    @BindView(R.id.maghreb_value)
    TextView maghrebValue;
    @BindView(R.id.eisha_value)
    TextView eishaValue;
    private SalaTiming salaTiming;
    private String fajr, shoroq, dohr, asr, maghreb, eisha;
    private int day ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timings);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getData();
        initializeUI();
    }

    private void initializeUI() {
        fajrValue.setText(convertto12HoursFormat(fajr.substring(0,6)));
        shoroqValue.setText(convertto12HoursFormat(shoroq.substring(0,6)));
        dohorValue.setText(convertto12HoursFormat(dohr.substring(0,6)));
        asrValue.setText(convertto12HoursFormat(asr.substring(0,6)));
        maghrebValue.setText(convertto12HoursFormat(maghreb.substring(0,6)));
        eishaValue.setText(convertto12HoursFormat(eisha.substring(0,6)));
    }

    private void getData() {
        salaTiming = (SalaTiming) getIntent().getSerializableExtra("object");
        day = getIntent().getIntExtra("day",0);

        fajr = salaTiming.getData().get(day).getTimings().getFajr();
        shoroq = salaTiming.getData().get(day).getTimings().getSunrise();
        dohr = salaTiming.getData().get(day).getTimings().getDhuhr();
        asr = salaTiming.getData().get(day).getTimings().getAsr();
        maghreb = salaTiming.getData().get(day).getTimings().getMaghrib();
        eisha = salaTiming.getData().get(day).getTimings().getIsha();

    }

    public String convertto12HoursFormat(String time){
        int hour = Integer.parseInt(time.substring(0,2));
        int newHour = hour - 12;
        if(hour > 12){
            if (String.valueOf(newHour).length() == 2)
                return time.replace(String.valueOf(hour),String.valueOf(newHour))+" PM";
            else
            return "0"+time.replace(String.valueOf(hour),String.valueOf(newHour))+" PM";
        }
        else {
            return time+" AM";
        }
    }
}
