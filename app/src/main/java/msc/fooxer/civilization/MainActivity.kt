package msc.fooxer.civilization

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.support.v4.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentById(R.id.fragment_layout)

        if (fragment == null) {
            fragment = createFragment()
            fragmentManager.beginTransaction()
                .add(R.id.fragment_layout, fragment)
                .commit()
        }
    }

    fun createFragment() : Fragment {
        val items: ArrayList<ListItem> = intent.getParcelableArrayListExtra("itemsarray")
        val bundle = Bundle()
        bundle.putParcelableArrayList("itemsarray", items)
        val fragment = FragmentList().newFragment()
        fragment.arguments = bundle
        return fragment
    }
}
