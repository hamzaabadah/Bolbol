package com.example.bolbol.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.bolbol.R
import com.example.bolbol.maintabsfragments.GroceriesFragment
import com.example.bolbol.maintabsfragments.PharmaciesFragment
import com.example.bolbol.maintabsfragments.RestaurantsFragment


private val TAB_TITLES = arrayOf(
    R.string.restaurants_tab,
    R.string.groceries_tab,
    R.string.pharmacies_tab
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    :FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {

        var fragment: Fragment? =null

        when (position) {
            0 -> fragment = RestaurantsFragment()
            1 -> fragment= GroceriesFragment()
            2 -> fragment = PharmaciesFragment()
        }

        return fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}