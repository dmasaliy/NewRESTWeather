package com.example.restweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restweather.models.Forecast;
import com.example.restweather.models.WeatherRequest;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements WeatherCallback {

    public static final String APP_ID = "4a817815ddffc203e7ef99476becf971";
    Button getCurrentForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCurrentForecast = findViewById(R.id.current_forecact);
        getCurrentForecast.setOnClickListener(v -> {
            getForecast();
        });
    }

    @Override
    public void onWeatherResponse(Forecast forecast) {
        Log.d("onWeatherResponse", forecast.toString());
        StringBuilder builder = new StringBuilder();
        builder.append(forecast.getMain().getTemp());
        builder.append('\n');
        builder.append(forecast.getWeather().get(0).getDescription());
        ((TextView) findViewById(R.id.textView)).setText(builder.toString());
        Picasso.get().load("http://openweathermap.org/img/w/"+forecast.getWeather().get(0).getIcon() +".png").into((ImageView) findViewById(R.id.imageWeather));
    }

    @Override
    public void onWeatherResponseError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    void getForecast() {
        String city = ((EditText) findViewById(R.id.city)).getText().toString();
        if (!TextUtils.isEmpty(city))
            WeatherManager.getInstance().getCurrentWeather(new WeatherRequest(city, APP_ID), this);
    }
}