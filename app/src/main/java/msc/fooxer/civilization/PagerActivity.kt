package msc.fooxer.civilization

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager

class PagerActivity : AppCompatActivity() {
    lateinit var items: ArrayList<ListItem>
    lateinit var pager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)


    }

    inner class PagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            val item: ListItem = items[position]
            val bundle: Bundle = Bundle()
            bundle.putParcelable("item",item)
            val fragment = BigItemFragment().newFragment()
            fragment.arguments = bundle
            return fragment

        }

        override fun getCount(): Int {
            return items.size
        }

    }
}
