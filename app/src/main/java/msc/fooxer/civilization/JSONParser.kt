package msc.fooxer.civilization

import android.util.Log

import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONException

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.ArrayList
import android.content.ContentValues.TAG


val DATA_URL: String = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json"
class JSONParser {

    // handle the response
    fun getJSON() : ArrayList<ListItem> {

        val items = ArrayList<ListItem>()

        val url: URL
        var response = StringBuilder()
        try {
            url =
                URL("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json")
        } catch (e: MalformedURLException) {
            throw IllegalArgumentException("Invalid URL")
        }
        var conn: HttpURLConnection? = null
            try {
               conn = url.openConnection() as HttpURLConnection
                conn.doOutput = false
                conn.doInput = true
                conn.requestMethod = "GET"
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
                val status = conn.responseCode
                if (status != 200) {
                    throw IOException("Post failed with error code $status")
                } else {
                    val instream = BufferedReader(InputStreamReader(conn.inputStream))

                    var inputLine: String? = instream.readLine()
                    while ((inputLine) != null) {
                        response.append(inputLine)
                        inputLine = instream.readLine()
                    }
                    instream.close()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                conn?.disconnect()
                val jsonString = response.toString().trim()
                Log.i(TAG, "Received JSON: $jsonString")
                try {
                    val jsonBody = JSONArray(jsonString)
                    parseItems(items, jsonBody)
                } catch (je: JSONException) {
                    Log.e(TAG, "Failed to parse JSON", je)
                }

            }

            return items
        }

    @Throws(JSONException::class)
    private fun parseItems(items: MutableList<ListItem>, jsonArray: JSONArray) {
        for (i in 1 until jsonArray.length()) {
            val jsonObjectItem = jsonArray.getJSONObject(i)
            var tmp: String?
            if (jsonObjectItem.has("helptext")) {
                tmp = (jsonObjectItem.getString("helptext"))
            } else tmp = null
            val item = ListItem((jsonObjectItem.getString("graphic")), (jsonObjectItem.getString("name")), tmp)
            items.add(item)
        }
    }
}
