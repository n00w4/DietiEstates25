package it.unina.dietiestates.view.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import it.unina.dietiestates.view.profile.ProfiloFragment
import it.unina.dietiestates.view.search.RicercaFragment

class ViewPagerAdapterGestore(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

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

