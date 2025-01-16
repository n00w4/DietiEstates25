package com.example.myapplication.view.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import com.example.myapplication.view.profile.ProfiloFragment
import com.example.myapplication.view.search.RicercaFragment

class ViewPagerAdapterCliente(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RicercaFragment()
            1 -> ProfiloFragment()
            else -> RicercaFragment() // Default case
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}