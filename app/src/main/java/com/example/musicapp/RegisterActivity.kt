package com.example.musicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.musicapp.Common.Common
import com.example.musicapp.Model.APIResponse
import com.example.musicapp.Retrofit.IMyAPI
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    internal lateinit var mService : IMyAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //init service
        mService = Common.api

        txt_login.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
        }

        btn_register.setOnClickListener {
            createNewUser(name.text.toString(), email.text.toString(), password.text.toString())
        }
    }

    private fun createNewUser(name: String, email: String, password: String) {
        mService.registerUser(name, email, password)
            .enqueue(object : Callback<APIResponse>{
                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity,t.message,Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    Toast.makeText(this@RegisterActivity, "Register Success"+response.body()!!.uid, Toast.LENGTH_SHORT).show()
                }

            })
    }
}
