package com.technoxcode.assignmenta

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class SecondScreen : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: RecycleViewAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.products_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        getAllData()

    }

    private fun getAllData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        // Make API call to fetch products
        val call = retrofit.getProducts() // Added a variable to hold the API call

        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        Log.d("data", data.toString())
                        val products = data.products // Extracting the list of products
                        myAdapter = RecycleViewAdaptor(baseContext, products)
                        recyclerView.adapter = myAdapter // Set the adapter to the RecyclerView
                    } else {
                        // Handle null response
                        Log.e("Response Error", "Null response received")
                    }
                } else {
                    // Handle error
                    val errorMessage = response.message()
                    Log.e("Response Error", "Error: $errorMessage")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                // Handle failure
                Log.e("Call Failure", "Error: ${t.message}")
            }
        })
    }
}