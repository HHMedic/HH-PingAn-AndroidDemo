package com.example.pingandemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.hhmedic.android.sdk.HHDoctor
import com.hhmedic.android.sdk.config.HHSDKOptions
import com.hhmedic.android.sdk.listener.HHLoginListener
import com.hhmedic.android.sdk.module.permission.PermissionUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSdk()

        PermissionUtils.askForPermissions(this)

        findViewById<Button>(R.id.test_sdk).setOnClickListener {


            doLogin()
        }
    }



    private fun initSdk(){


        val options = HHSDKOptions("10284")

        options.dev = true //测试环境

        HHDoctor.init(this,options)
    }



    private fun doLogin(){

        val token = "B0E86BAB0AF0F9E08B8A02969FD7CB553F0D04F68EA2608F6783B874E4F50EEF"

        HHDoctor.login(this,token,object :HHLoginListener{

            override fun onSuccess() {

                doCall()
            }

            override fun onError(tips: String?) {

                showError(tips)
            }


        })
    }


    private fun showError(tips:String?){

        Toast.makeText(this,tips ?: "",Toast.LENGTH_LONG).show()
    }

    private fun doCall(){

        val token = "B0E86BAB0AF0F9E08B8A02969FD7CB553F0D04F68EA2608F6783B874E4F50EEF"

        HHDoctor.call(this,token,null)
    }

}