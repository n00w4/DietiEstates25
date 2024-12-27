package com.example.myapplication.view

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity

class ViewPagerAdapterCliente(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RicercaFragment()
            1 -> ProfiloUtenteFragment()
            else -> RicercaFragment() // Default case
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}