package com.example.lovertips.home.feed.providers

import com.example.lovertips.login.PreferenceHelper
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Headers

import com.github.kittinunf.fuel.json.responseJson

class FeedDataProvider{
    private var preferenceHelper: PreferenceHelper? = null

      fun getFeed(userToken:String){
          try {
          Fuel.get("http://192.168.1.86/lovertips/api/posts/")
              .header("Content-Type", "application/json")
              .header("token", userToken)
              .responseJson { request1, response1, result1 ->
                  //tvGetResponse!!.text = result.get().content
              }
          } catch (e: Exception) {
              //tvGetResponse!!.text = e.message
          } finally {
              // progress!!.dismiss()
          }
          /*val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
              override fun getAcceptedIssuers(): Array<X509Certificate>? = null
              override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) = Unit
              override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) = Unit
          })

          val sslContext = SSLContext.getInstance("SSL").apply {
              init(null, trustAllCerts, java.security.SecureRandom())
          }.socketFactory


          val (_, trustedResp, trustedResult) = "http://192.168.56.1/lovertips/api/login".httpGet().response()
          assert(trustedResp.statusCode != 200)
          val (bytes, error) = trustedResult
          assert(bytes == null)
          FuelManager.instance.socketFactory = sslContext*/
          //FuelManager.instance.proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("192.168.56.1/"))

          /*Fuel.post("http://192.168.56.1/lovertips/api/login")
            .body("{ \"email\" : \"hb@bjn.jbh21\", \"password\" : \"\" }")
            .response { request, response, result ->
                when(result){
                    is Result.Failure->{
                        println(response)
                      // Log.i("ErrorMsg", result.getException().message)
                    }
                    is Result.Success ->{
                        //println(response)
                        val (bytes, error) = result
                        if (bytes != null) {
                            println("[response bytes] ${String(bytes)}")
                        }
                    }
                }

            }*/
    }


    fun postFeed(){


    }

    fun getAllPosts() {
        try {
            //progress!!.show

            val token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC9sb3ZlcnRpcHMuY29tIiwiYXVkIjoiaHR0cDpcL1wvbG92ZXJ0aXBzLmNvbSIsImV4cCI6MTU3NzY1MzU1OSwidXNlciI6eyJpZCI6ImY0NWI4NTU1LTI1ZTctNDdjYS1iYWNkLWE3ODJiZGZlMjEyMyIsInRva2VuIjoieVRCS2I5cThFbiJ9fQ.hh6DLNkJ0aamTZCbNO_jqAaAb0UXK5b5Kt8NNSkVlQQ"
            Fuel.get("http://192.168.1.86/lovertips/api/posts/")

                .header("Content-Type", "application/json")
                .header(Headers.AUTHORIZATION, token)

                /*.authentication()
                .bearer(token)*/
                .responseJson { request, response, result ->
                    println(request)
                    println(response)
                //tvGetResponse!!.text = result.get().content
            }
        } catch (e: Exception) {
            //tvGetResponse!!.text = e.message
        } finally {
           // progress!!.dismiss()
        }
    }

    /*fun httpPostJson(view: View) {
        try {
            progress!!.show()
            Fuel.post("api/post_sample.php", listOf("version_index" to "1")).responseJson { request, response, result ->
                tvPostResponse!!.text = result.get().content
            }
        } catch (e: Exception) {
            tvPostResponse!!.text = e.message
        } finally {
            progress!!.dismiss()
        }
    }*/
}
