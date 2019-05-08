package msc.fooxer.civilization

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v4.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentById(R.id.fragment_layout) // сюда впихнем фрагмент

        if (fragment == null) { // если фрагмента нет - создаем его
            fragment = createFragment()
            fragmentManager.beginTransaction()
                .add(R.id.fragment_layout, fragment)
                .commit()
        }
    }

    fun createFragment() : Fragment {
        val items: ArrayList<ListItem> = intent.getParcelableArrayListExtra("itemsarray") //получаем из интента массив
        val bundle = Bundle() // временное хранилище
        bundle.putParcelableArrayList("itemsarray", items) // передаем массив хранилище
        val fragment = FragmentList().newFragment()
        fragment.arguments = bundle // хранилище в фрагмент
        return fragment
    }
}
