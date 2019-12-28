package com.example.sj20191228_01_api

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setValues()
        setupEvents()
    }

    override fun setupEvents() {
        logOutBtn.setOnClickListener {
            val alert = AlertDialog.Builder(mContext)

            alert.setTitle("LogOut")
            alert.setMessage("정말 로그아웃 할래요?")
            alert.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
                Toast.makeText(mContext, "로그아웃", Toast.LENGTH_SHORT).show()
            })
            alert.setNegativeButton("취소", null) // 취소는 눌러도 아무일도 없도록

            alert.show()

        }
    }

    override fun setValues() {
    }
}
