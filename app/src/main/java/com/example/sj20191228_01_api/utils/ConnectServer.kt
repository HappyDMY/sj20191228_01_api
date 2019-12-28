package com.example.sj20191228_01_api.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ConnectServer {
    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    companion object {
        //        어느 서버에 연결할지 서버의 주소 (도메인 - www.naver.com / IP)를 변수로 저장.
        val BASE_URL = "http://192.168.0.10:5000"

        //        [로그인 요청 기능] 함수로 구현
        fun postRequestLogin(context: Context, inputId: String, inputPw: String, handler: JsonResponseHandler?) {
            val client = OkHttpClient()
            // 기본주소 (BASE_URL) + 기능주소 /auth 의 조합으로 서버에 찾아갈 주소를 명시
            val url = "${BASE_URL}/auth"
            // 서버에 찾아갈때 들고갈 데이터를 추가
            val formBody = FormBody.Builder()
                .add("login_id", inputId)
                .add("password", inputPw)
                .build()

            val request = Request.Builder()
                .url(url)
//                    헤더로 담아야 하는 데이터가 있다면 .header("이름", 값)으로 추가해 줘야함
//                .header("X-Http-Token", inputId)
                .post(formBody)
                .build()

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("서버연결 실패",e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()!!.string() //서버에서 내려중 응답을 String으로 저장.
                    val json = JSONObject(body) //String으로 저장된 응답을 JSON으로 가공처리
//                    json변수의 내용을 분석해서 상황에 따른 처리를 할 수 있음

                    handler?.onResponse(json)
                    Log.d("서버연결 성공","연결성공")
                }

            })
        }

        fun getRequestMyInfo(context: Context, jsonResponseHandler: JsonResponseHandler?){
            val client = OkHttpClient()
//            get방식에 맞는 URL 생성
//            => 파라미터가 전부 주소에 노출 되도록 작성해야함.

//            urlBuilder => 단계별로 가공해서 완성하는 개념 : Builder
            val urlBuilder = HttpUrl.parse("${BASE_URL}/my_info")!!.newBuilder()
//            urlBuilder.addEncodedQueryParameter("device_token", "")

//            url빌더를 이용해 첨부된 파라미터를 활용해서 url String으로 저장
            val url = urlBuilder.build().toString()

            val request = Request.Builder()
                .url(url)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
//            get 방식은 제일 기본이 되는 API 통신방식 => 메소드를 별도로 명시 X
                .build()

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("서버연결 실패",e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()!!.string() //서버에서 내려중 응답을 String으로 저장.
                    val json = JSONObject(body) //String으로 저장된 응답을 JSON으로 가공처리
//                    json변수의 내용을 분석해서 상황에 따른 처리를 할 수 있음

                    jsonResponseHandler?.onResponse(json)
                    Log.d("서버연결 성공","연결성공")
                }

            })


        }

    }
}