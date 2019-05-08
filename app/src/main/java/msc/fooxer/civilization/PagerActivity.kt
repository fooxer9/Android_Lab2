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
        items = intent.getParcelableArrayListExtra("items") //получаем свой массив
        item_name = intent.getStringExtra("item_name") //получаем имя объекта, для контроля позиции
        val fm: FragmentManager = supportFragmentManager // общение фрагментов происходит чз менеджер
        val adapter = PagerAdapter(fm)
        pager.adapter = adapter

        for (i in 0 until items.size) // вот эта настройка пэйджера для вывода нужного итема
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
            val bundle: Bundle = Bundle() // создаем временное хранилище данных
            bundle.putParcelable("item",item) // кладем нужный объект
            val fragment = BigItemFragment().newFragment()
            fragment.arguments = bundle // передаем в новый фрагмент
            return fragment

        }

        override fun getCount(): Int {
            return items.size
        }

    }

}
