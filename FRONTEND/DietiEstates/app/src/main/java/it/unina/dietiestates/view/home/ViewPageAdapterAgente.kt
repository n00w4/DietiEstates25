package it.unina.dietiestates.view.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import it.unina.dietiestates.view.navigation.CalendarioAgenteNavFragment
import it.unina.dietiestates.view.navigation.CreaAnnuncioNavFragment
import it.unina.dietiestates.view.navigation.NotificheNavFragment
import it.unina.dietiestates.view.navigation.ProfiloNavFragment

class ViewPagerAdapterAgente(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CreaAnnuncioNavFragment()
            1 -> CalendarioAgenteNavFragment()
            2 -> NotificheNavFragment()
            3 -> ProfiloNavFragment()
            else -> ProfiloNavFragment() // Default case
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}