package com.example.sj20191228_01_api.datas

import org.json.JSONObject
import java.io.Serializable

class User : Serializable {
    var id = -1 // Int임을 명시 + id가 -1 이라면 제대로 파싱이 안 되어있다는 구별
    var loginId = ""
    var name = ""
    var phoneNum = ""
    var memo = ""

    companion object {
        //        jsonObject를 기반으로 => User 변수로 변환 기능.
        fun getUserFromJson(json: JSONObject) {
            val parsedUser =User()
//            기본데이터 => json 변수에서 따온 값으로 대체
            parsedUser.id = json.getInt("id")
            parsedUser.loginId = json.getString("login_Id")
            parsedUser.name = json.getString("name")
            parsedUser.phoneNum = json.getString("phoneNum")
            parsedUser.memo = json.getString("memo")

        }


    }
}