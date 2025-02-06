package it.unina.dietiestates.view.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import it.unina.dietiestates.view.navigation.AmministratoreCreaUtenteNavFragment
import it.unina.dietiestates.view.navigation.ProfiloNavFragment

class ViewPagerAdapterAmministratore(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AmministratoreCreaUtenteNavFragment()
            1 -> ProfiloNavFragment()
            else -> ProfiloNavFragment() // Default case
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}