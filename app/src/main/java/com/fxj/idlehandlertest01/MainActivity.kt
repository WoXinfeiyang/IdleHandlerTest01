package com.fxj.idlehandlertest01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.MessageQueue
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var btn: Button?=null

    private var handler:Handler= Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn=findViewById(R.id.btn)
        btn?.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                this@MainActivity.handler.postDelayed(object:Runnable{
                    override fun run() {
                        Log.d(MainActivity::class.java.simpleName,"Hander post Runnable,CurrentThread Name=${Thread.currentThread().name}")
                    }
                },1000)
            }

        })

        Looper.myQueue().addIdleHandler(object:MessageQueue.IdleHandler{
            override fun queueIdle(): Boolean {
                Log.d(MainActivity::class.java.simpleName,"##queueIdle##CurrentThread Name=${Thread.currentThread().name}")
                return true
            }

        })
    }
}