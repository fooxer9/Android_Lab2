package msc.fooxer.civilization

import android.app.ListActivity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.widget.Toast
import java.security.AccessController
import java.util.ArrayList

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        LoadDataTask(this).execute()
    }



    inner class LoadDataTask(context: Context) : AsyncTask<Void, Void, List<ListItem>>() {
        val context = context
        override fun doInBackground(vararg params: Void): List<ListItem> {

            return JSONParser().getJSON()
        }

        override fun onPreExecute() {
            super.onPreExecute() // перед выполнением таска
            if (context != null && !isNetworkAvailable(context)) {
                Toast.makeText(context, "The connection is lost", Toast.LENGTH_LONG).show() //вывод сообщения о соединении с интернетом
            } else Toast.makeText(context, "The connection is ok", Toast.LENGTH_LONG).show()
        }
        override fun onPostExecute(items: List<ListItem>) {

            if (items.isNotEmpty()) Toast.makeText(context,"Data is downloaded", Toast.LENGTH_LONG).show() // сообщение о том, что данные загружены
            SystemClock.sleep(2000)
            val mItems = items as ArrayList<ListItem>
            val i = Intent(baseContext, MainActivity::class.java)
            i.putParcelableArrayListExtra("itemsarray", mItems)
            startActivity(i)
            finish()
        }
    }
}

private fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnectedOrConnecting
}
