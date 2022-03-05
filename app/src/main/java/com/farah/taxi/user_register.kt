package com.farah.taxi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import kotlinx.android.synthetic.main.activity_user_register.*
import org.json.JSONObject

class user_register : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)
        //  val submit = findViewById(R.id.submit) as MaterialButton


        val progress = findViewById(R.id.progress1) as ProgressBar
        progress.visibility = View.GONE

        //set the loopj to get the api and put them in form of json key and value
        submit.setOnClickListener {
            val fname1 = fname.text
            val others1 = others.text


            val gender1 = gender.text

            val phone1 = phone.text

            val password1 = password.text

            val city1 = city.text






            progress.visibility = View.VISIBLE



            if (fname1!!.length == 0) {
                progress.visibility = View.GONE
                fname.setError("Empty name")
            }
            if (gender1!!.length == 0) {
                progress1.visibility = View.GONE
                gender.setError("Empty name")
            }





            else if (phone1!!.length == 0) {
                progress1.visibility = View.GONE
                phone.setError("Empty phone")
            }  else if (password1!!.length == 0) {
                progress1.visibility = View.GONE
                password.setError("Empty phone")
            }
            else if (city1!!.length == 0) {
                progress1.visibility = View.GONE
                city.setError("Empty phone")
            }
            else {
                val client = AsyncHttpClient(true, 80, 443)
                val jsonParams = JSONObject()
                jsonParams.put("first_name", fname.text.toString())
                jsonParams.put("others", others.text.toString())

                jsonParams.put("gender", gender.text.toString())

                jsonParams.put("phone", phone.text.toString())

                jsonParams.put("password", password.text.toString())

                jsonParams.put("city", city.text.toString())



                //convert json params to string but the format is still key and value
                val data = StringEntity(jsonParams.toString())

                client.post(
                    this,
                    "https://modcom.pyhonanywhere.com/api/rider",
                    data,
                    "application/json",
                    object : JsonHttpResponseHandler() {
                        override fun onSuccess(
                            statusCode : Int,
                            headers : Array<out Header>?,
                            response : JSONObject?
                        ) {
                            Toast.makeText(applicationContext, "" + response, Toast.LENGTH_LONG)
                                .show()

                            val i = Intent(applicationContext, DriverHomeActivity::class.java)
                            startActivity(i)
                        } //end on success

                        override fun onFailure(
                            statusCode : Int,
                            headers : Array<out Header>?,
                            throwable : Throwable?,
                            errorResponse : JSONObject?
                        ) {
                            Toast.makeText(
                                applicationContext,
                                "" + errorResponse,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }//end response handler

                )//end post
            }
        }
    }
}