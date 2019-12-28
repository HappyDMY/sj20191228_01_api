package com.example.sj20191228_01_api

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sj20191228_01_api.utils.ConnectServer
import com.example.sj20191228_01_api.utils.ContextUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
        logoutBtn.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)

            alert.setTitle("LogOut")
            alert.setMessage("정말 로그아웃 할래요?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(mContext, "로그아웃", Toast.LENGTH_SHORT).show()

                ContextUtil.setUserToken(mContext,"")

                val intent = Intent(mContext, LoginActivity::class.java)
                startActivity(intent)

                finish()
            })
            alert.setNegativeButton("취소", null) // 취소는 눌러도 아무일도 없도록

            alert.show()

        }
    }

    override fun setValues() {
        ConnectServer.getRequestMyInfo(mContext, object : ConnectServer.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
                Log.d("서버정보 응답", json.toString())

                val code = json.getInt("code")
                if (code == 200){
                    val data = json.getJSONObject("data")
                    val user = data.getJSONObject("user")

                    val userName = user.getString("name")
                    val userPhoneNum = user.getString("phone")

                    nameTxt.text = userName
                    phoneTxt.text = userPhoneNum


                }else{
                    Toast.makeText(mContext, "서버에 문제가 있습니다.", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}
