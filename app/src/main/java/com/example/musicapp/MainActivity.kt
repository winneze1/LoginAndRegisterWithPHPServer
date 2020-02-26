package com.example.musicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.musicapp.Common.Common
import com.example.musicapp.Model.APIResponse
import com.example.musicapp.Retrofit.IMyAPI
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    internal lateinit var mService : IMyAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init service
        mService = Common.api

        //Event
        txt_register.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }

        btn_login.setOnClickListener {
            authenticateUser(email.text.toString(), password.text.toString())
        }
    }

    private fun authenticateUser(email: String, password: String) {
        mService.loginUser(email, password)
            .enqueue(object :Callback<APIResponse> {
                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity,t.message,Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if (response.body()!!.error){
                        Toast.makeText(this@MainActivity,response.body()!!.error_msg,Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(this@MainActivity,"Login success",Toast.LENGTH_SHORT).show()
                    }
                }

            })
    }
}
