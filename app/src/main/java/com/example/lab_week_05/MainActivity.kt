package com.example.lab_week_05

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab_week_05.api.CatApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

    //instantiating retrofit (not a good practice, only for mid semester)
    //better method: Model-View-ViewModel (MVVM)
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }
    //instantiating CatApiService
    private val catApiService by lazy {
        retrofit.create(CatApiService::class.java)
    }

    //get reference to the Textview with id "api_response"
    private val apiResponseView: TextView by lazy {
        findViewById<TextView>(R.id.api_response)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //using function
        getCatImageResponse()
    }

    //functions for using api
    private fun getCatImageResponse() {
        val call = catApiService.searchImages(1,"full") //format is for the size of the img
        //Call.enqueue calls specified query
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.e("MAIN_ACTIVITY", "Failed to get response", t)
            }

            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if(response.isSuccessful){
                    apiResponseView.text = response.body()
                }
                else {
                    Log.e("MAIN_ACTIVITY", "Failed to get respons\n" +
                    response.errorBody()?.string().orEmpty()
                    )
                }
            }
        })
    }
}