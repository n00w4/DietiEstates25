package it.unina.dietiestates.view.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import it.unina.dietiestates.view.profile.ProfiloFragment

class ViewPagerAdapterAmministratore(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfiloFragment()      //TODO: sostituisci con i navfragment giusti i casi 0, default
            1 -> ProfiloFragment()
            else -> ProfiloFragment() // Default case
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}