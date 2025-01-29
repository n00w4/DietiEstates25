package it.unina.dietiestates.view.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import it.unina.dietiestates.view.navigation.CreaUtenteNavFragment
import it.unina.dietiestates.view.navigation.ModificaPwdNavFragment
import it.unina.dietiestates.view.navigation.ProfiloNavFragment

class ViewPagerAdapterGestore(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CreaUtenteNavFragment()
            1 -> ModificaPwdNavFragment()
            2 -> ProfiloNavFragment()
            else -> ProfiloNavFragment() // Default case
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}

