package msc.fooxer.civilization

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.activity_pager.*
import kotlin.text.equals as equals1

class PagerActivity : AppCompatActivity() {
    lateinit var items: ArrayList<ListItem>
    lateinit var pager: ViewPager
    lateinit var item_name: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)
        pager = findViewById(R.id.viewPager)
        items = intent.getParcelableArrayListExtra("items")
        item_name = intent.getStringExtra("item_name")
        val fm: FragmentManager = supportFragmentManager
        val adapter = PagerAdapter(fm)
        pager.adapter = adapter

        for (i in 0 until items.size)
        {
            if (items[i].name == item_name) {
                viewPager.currentItem = i
                break
            }
        }

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
