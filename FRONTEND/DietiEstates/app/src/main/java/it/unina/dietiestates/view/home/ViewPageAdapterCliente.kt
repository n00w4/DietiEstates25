package it.unina.dietiestates.view.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import it.unina.dietiestates.view.profile.ProfiloFragment
import it.unina.dietiestates.view.search.RicercaHomeFragment

class ViewPagerAdapterCliente(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RicercaHomeFragment()
            1 -> ProfiloFragment()
            else -> RicercaHomeFragment() // Default case
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}