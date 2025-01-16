package com.example.myapplication.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity

class ViewPagerAdapterAgente(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RicercaFragment()      //TODO: sostituisci con i fragment giusti i casi 0, 1, default
            1 -> ProfiloFragment()
            2 -> ProfiloFragment()
            else -> RicercaFragment() // Default case
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}