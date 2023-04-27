package com.test.newsline.ui.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.test.newsline.R
import com.test.newsline.databinding.FragmentFilterBinding
import com.test.newsline.models.FilterObject
import com.test.newsline.ui.viewmodels.SharedViewModel
import com.test.newsline.utils.Constants


class FilterFragment(
    private val default: String = "all-sections"
) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFilterBinding
    private lateinit var dialog: BottomSheetDialog
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val sections = arrayOf(
        "All",
        "Business",
        "Arts",
        "Opinion",
        "U.S.",
        "Books",
        "Magazine",
        "Theater",
        "Well",
        "World"
    )
    private var filterObject = FilterObject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomDialogStyle)

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            sharedViewModel.filterObject.observe(viewLifecycleOwner) {
                if (it != null) {
                    filterObject = it
                    setDuration()
                    setupSection()
                }

            }
            applyMaterialButton.setOnClickListener {
                sharedViewModel.filterObject.postValue(filterObject)
                dismissAllowingStateLoss()
            }
            sectionsChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                val section =
                    (group.findViewById<Chip>(checkedIds.first()).text.toString().lowercase())
                if (section == Constants.ALL.lowercase())
                    filterObject.section = Constants.ALL_SECTIONS
                else
                    filterObject.section = section
            }
            durationChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                when (checkedIds.first()) {
                    R.id.today_chip -> {
                        filterObject.duration = "1"
                    }
                    R.id.week_chip -> {
                        filterObject.duration = "7"
                    }
                    else -> {
                        filterObject.duration = "30"
                    }
                }

            }
        }
        setDuration()

        setupSection()


    }

    private fun setupSection() {
        binding.sectionsChipGroup.removeAllViews()
        if (filterObject.section.isBlank()) {
            filterObject.section = Constants.ALL_SECTIONS
        }
        sections.forEach { section ->
            val chip = layoutInflater.inflate(
                R.layout.layout_type_chips,
                null,
                true
            ) as Chip

            chip.text = section


                chip.isChecked = section.lowercase() == filterObject.section.lowercase() || section.lowercase().contains(Constants.ALL.lowercase())


            binding.sectionsChipGroup.addView(chip)
        }
    }

    private fun setDuration() {
        if (filterObject.duration.isBlank())
            filterObject.duration = Constants.TODAY
        binding.apply {
            when (filterObject.duration) {
                Constants.TODAY -> {
                    todayChip.isChecked = true
                }
                Constants.WEEK -> {
                    weekChip.isChecked = true
                }
                Constants.MONTH -> {
                    monthChip.isChecked = true
                }
                else -> {
                    todayChip.isChecked = true
                }
            }
        }

    }

}