package com.example.sj20191228_01_api

import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {
//        로그인버튼이 눌리면
//        1. ID입력이 빈칸이라면 "ID를 입력해주세요." 토스트 출력
//        2. PW 입력이 8글자 미만이라면 "비번이 너무 짧습니다." 토스트 출력
//        3. 둘다 괸찮다면 별개로 분기만 준비.
        loginBtn.setOnClickListener {

            if (idEdt.text==null || idEdt.text.toString()==""){
                Toast.makeText(this,"ID를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pwEdt.text.length<8){
                Toast.makeText(this,"비번이 너무 짧습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "정상입력이라 로그인이 시도 되어야 합니다.", Toast.LENGTH_SHORT).show()


        }
    }

    override fun setValues() {
    }
}
