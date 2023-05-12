package com.dauto.moongarden.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.dauto.moongarden.MainViewModel
import com.dauto.moongarden.databinding.FragmentMonthBinding


class MonthFragment : Fragment() {
    private val sharedViewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentMonthBinding? = null
    private val binding: FragmentMonthBinding
        get() = _binding ?: throw RuntimeException("FragmentMonthBinding is not exist")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("viewCheck", "month create")
        _binding = FragmentMonthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.getMonthInfo("may")
        sharedViewModel.moonMonth.observe(viewLifecycleOwner){
            with(binding){
               textViewDescription.text=it.month.description
                favorableTextView.text=it.month.dayGoodBad.favorable
                unfavorableTextView.text=it.month.dayGoodBad.unfavorable
                cucumerTV.text=it.month.daysForSowing.cucumbers
                eggplantTv.text=it.month.daysForSowing.pepperEggplant
                cabbageTV.text=it.month.daysForSowing.cabbage
                garlicTV.text=it.month.daysForSowing.garlic
                rootVegetablesTv.text=it.month.daysForSowing.rootVegetables
                tomatoTV.text=it.month.daysForSowing.tomato
                onionTV.text=it.month.daysForSowing.onion
                differentGreenTv.text=it.month.daysForSowing.differentGreens
                annualsTV.text=it.month.flowers.annuals
                twoyearFlowersTv.text=it.month.flowers.twoyearAndLongterm
                bulbousTV.text=it.month.flowers.bulbousAndTuberous
                fruitTreeTV.text=it.month.seedlings.fruitTrees
                grapeTV.text=it.month.seedlings.grape
                gooseberryTV.text=it.month.seedlings.gooseberry
                raspberryTV.text=it.month.seedlings.raspberry
                strawberryTV.text=it.month.seedlings.strawberry
                rootingDiggingTV.text=it.month.seedlings.rooting_digging
                graftingTV.text=it.month.seedlings.grafting


            }
        }
    }

    companion object {

        fun newInstance() =
            MonthFragment().apply {

            }
    }
    override fun onDestroy() {
        Log.d("viewCheck", "month destroy")
        super.onDestroy()
        _binding = null
    }
}