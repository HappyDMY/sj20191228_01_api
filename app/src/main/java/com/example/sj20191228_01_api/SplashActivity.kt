package com.example.sj20191228_01_api

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.sj20191228_01_api.utils.ContextUtil

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
    }

    override fun setValues() {
        openPropertActivity()
    }

    fun openPropertActivity(){
        Handler().postDelayed({

            if (ContextUtil.getUserToken(mContext) == "") {
//            로그인에 토큰이 저장되지 않았다 => 새로 로그인 할 필요가 있다.
                val intent = Intent(mContext, LoginActivity::class.java)
                startActivity(intent)
                finish()

            } else {
//            로그인 토큰이 빈칸이 아니다 => 저정된 토큰이 있다.
//            로그인 화면이 아니라 메인화면으로 보내주자
                val intent = Intent(mContext, MainActivity::class.java)
                startActivity(intent)
                finish()

            }


        }, 15000)

    }


}
