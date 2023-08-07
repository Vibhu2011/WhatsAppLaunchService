package com.example.taskdemo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskdemo.R
import com.example.taskdemo.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = arguments?.getString("title")
        val bodyData = arguments?.getString("data")

        // Use the data as needed
        if (!title.isNullOrEmpty()) {
            Log.e("vibhu", "Title: " + title + "body :" +bodyData)
            binding.textView.text = bodyData
            binding.titleTV.text = title

        }

    }
}