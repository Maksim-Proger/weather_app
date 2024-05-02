package com.example.project2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project2.databinding.ActivityMainBinding
import com.example.project2.retrofit.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        binding.button.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch { // благодаря Main запуск осуществляется на основном потоке
                val api = retrofit.create(MainApi::class.java)
                val model = api.getWeatherData(
                    "71905cf4f2ab40da9e8142341222207", // надо сгенерировать свой
                    "Moscow",
                    "3",
                    "no",
                    "no"
                )
                binding.temp.text = model.current.temp_c.toString()
                binding.date.text = model.location.localtime
            }
        }
    }
}