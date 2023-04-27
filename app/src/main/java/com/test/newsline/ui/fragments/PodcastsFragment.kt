package com.test.newsline.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.newsline.databinding.FragmentPodcastsBinding

class PodcastsFragment : Fragment() {

    private lateinit var binding: FragmentPodcastsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPodcastsBinding.inflate(inflater, container, false)
        return binding.root
    }
}