package it.unina.dietiestates.view.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import it.unina.dietiestates.view.navigation.ProfiloNavFragment
import it.unina.dietiestates.view.navigation.RicercaNavFragment

class ViewPagerAdapterCliente(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RicercaNavFragment()
            1 -> ProfiloNavFragment()
            else -> RicercaNavFragment()
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}