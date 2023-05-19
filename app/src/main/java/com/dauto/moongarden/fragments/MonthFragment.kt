package com.dauto.moongarden.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dauto.domain.moonentity.MonthAndDays
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
        _binding = FragmentMonthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.moonMonth.observe(viewLifecycleOwner) {
            initViews(it)
        }
    }

    private fun initViews(monthAndDays: MonthAndDays) {
        with(binding) {
            textViewDescription.text = monthAndDays.month.description
            favorableTextView.text = monthAndDays.month.dayGoodBad.favorable
            unfavorableTextView.text = monthAndDays.month.dayGoodBad.unfavorable
            cucumerTV.text = monthAndDays.month.daysForSowing.cucumbers
            rootVegetablesTv.text = monthAndDays.month.daysForSowing.rootVegetables
            tomatoTV.text = monthAndDays.month.daysForSowing.tomato
            differentGreenTv.text = monthAndDays.month.daysForSowing.differentGreens
            twoyearFlowersTv.text = monthAndDays.month.flowers.twoyearAndLongterm
            bulbousTV.text = monthAndDays.month.flowers.bulbousAndTuberous
            initSeedlingsOrHide(monthAndDays)
            initRowOrHide(monthAndDays)
        }
    }

    private fun initRowOrHide(monthAndDays: MonthAndDays) {
        val garlic = monthAndDays.month.daysForSowing.garlic
        val onion = monthAndDays.month.daysForSowing.onion
        val pepper =monthAndDays.month.daysForSowing.pepperEggplant
        val cabbage =monthAndDays.month.daysForSowing.cabbage
        val annuals =monthAndDays.month.flowers.annuals
        with(binding) {
            if (garlic.isEmpty()) {
                garlicRow.visibility = View.GONE
            } else {
                garlicRow.visibility = View.VISIBLE
                garlicTV.text = garlic
            }
            if (onion.isEmpty()) {
                onionRow.visibility = View.GONE
            } else {
                onionRow.visibility = View.VISIBLE
                onionTV.text = onion
            }
            if (annuals.isEmpty()) {
                annualsRow.visibility = View.GONE
            } else {
                annualsRow.visibility = View.VISIBLE
                annualsTV.text = annuals
            }
            if (pepper.isEmpty()) {
                pepperRow.visibility = View.GONE
            } else {
                pepperRow.visibility = View.VISIBLE
                eggplantTv.text = pepper
            }
            if (cabbage.isEmpty()) {
                cabbageRow.visibility = View.GONE
            } else {
                cabbageRow.visibility = View.VISIBLE
                cabbageTV.text = cabbage
            }
        }
    }

    private fun initSeedlingsOrHide(monthAndDays: MonthAndDays) {
        with(binding) {
            if (monthAndDays.month.seedlings.isEmpty) {
                seedlingsTable.visibility = View.GONE
                seedlingsTitleTextView.visibility = View.GONE
            } else {
                seedlingsTable.visibility = View.VISIBLE
                seedlingsTitleTextView.visibility = View.VISIBLE
                fruitTreeTV.text = monthAndDays.month.seedlings.fruitTrees
                grapeTV.text = monthAndDays.month.seedlings.grape
                gooseberryTV.text = monthAndDays.month.seedlings.gooseberry
                raspberryTV.text = monthAndDays.month.seedlings.raspberry
                strawberryTV.text = monthAndDays.month.seedlings.strawberry
                rootingDiggingTV.text = monthAndDays.month.seedlings.rooting_digging
                graftingTV.text = monthAndDays.month.seedlings.grafting
            }
        }
    }

    companion object {

        fun newInstance() =
            MonthFragment().apply {

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}