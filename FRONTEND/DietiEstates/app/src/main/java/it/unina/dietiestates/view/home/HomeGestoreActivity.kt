package it.unina.dietiestates.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import it.unina.dietiestates.R
import com.google.android.material.tabs.TabLayout

class HomeGestoreActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var myViewPagerAdapter: ViewPagerAdapterGestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gestore_home_page)

        tabLayout = findViewById(R.id.tab_layout_gestore)
        viewPager2 = findViewById(R.id.view_pager_gestore)
        myViewPagerAdapter = ViewPagerAdapterGestore(this)
        viewPager2.adapter = myViewPagerAdapter
        viewPager2.isUserInputEnabled = false

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewPager2.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { // Vuoto
            }

            override fun onTabReselected(tab: TabLayout.Tab?) { // Vuoto
            }
        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.getTabAt(position)?.select()
            }
        })
    }
}