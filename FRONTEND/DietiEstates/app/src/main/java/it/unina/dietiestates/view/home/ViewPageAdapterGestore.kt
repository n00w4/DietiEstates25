package it.unina.dietiestates.view.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import it.unina.dietiestates.view.profile.GestoreCreaAgenteFragment
import it.unina.dietiestates.view.profile.GestoreModificaPwdAdminFragment
import it.unina.dietiestates.view.profile.ProfiloFragment

class ViewPagerAdapterGestore(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GestoreCreaAgenteFragment()
            1 -> GestoreModificaPwdAdminFragment()
            2 -> ProfiloFragment()
            else -> ProfiloFragment() // Default case
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}

