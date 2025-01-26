package it.unina.dietiestates.view.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import it.unina.dietiestates.view.navigation.CreaAnnuncioNavFragment
import it.unina.dietiestates.view.navigation.ProfiloNavFragment
import it.unina.dietiestates.view.profile.ProfiloFragment

class ViewPagerAdapterAgente(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CreaAnnuncioNavFragment()      //TODO: sostituisci con i navfragment giusti i casi 0, 1, default
            1 -> ProfiloFragment()
            2 -> ProfiloFragment()
            3 -> ProfiloNavFragment()
            else -> ProfiloNavFragment() // Default case
        }
    }

    override fun getItemCount(): Int {
        return 4
    }
}