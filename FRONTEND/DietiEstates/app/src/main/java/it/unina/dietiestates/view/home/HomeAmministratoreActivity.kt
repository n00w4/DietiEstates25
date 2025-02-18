package it.unina.dietiestates.view.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import it.unina.dietiestates.R
import com.google.android.material.tabs.TabLayout

class HomeAmministratoreActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var myViewPagerAdapter: ViewPagerAdapterAmministratore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.amministratore_home_page)

        tabLayout = findViewById(R.id.tab_layout_amministratore)
        ViewCompat.setOnApplyWindowInsetsListener(tabLayout) { v, insets ->
            val bottomInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            v.setPadding(v.paddingLeft, v.paddingTop, v.paddingRight, bottomInset)
            insets
        }
        viewPager2 = findViewById(R.id.view_pager_amministratore)
        myViewPagerAdapter = ViewPagerAdapterAmministratore(this)
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