package com.example.taskdemo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskdemo.AppDatabase
import com.example.taskdemo.CommonAdapter
import com.example.taskdemo.R
import com.example.taskdemo.data.ApiResult
import com.example.taskdemo.databinding.FragmentHomeBinding
import com.example.taskdemo.databinding.HomeFragmentItemBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HomeFragment : Fragment(),CommonAdapter.RecyclerViewInterface<HomeFragmentItemBinding> {

    lateinit var binding: FragmentHomeBinding
    private var list: List<ApiResult> = arrayListOf()
    private lateinit var commonadapter: CommonAdapter<HomeFragmentItemBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GlobalScope.launch {
            list = AppDatabase.getDatabase(requireContext()).dataDao().getAllData()
            Log.e("Ashish", list.toString())

        }
        commonadapter = CommonAdapter(this)
        binding.recycler.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
        binding.recycler.adapter = CommonAdapter(this)

        binding.getData.setOnClickListener {
            commonadapter = CommonAdapter(this)
            binding.recycler.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
            binding.recycler.adapter = CommonAdapter(this)
            binding.getData.visibility = View.GONE}

    }

    override fun onResume() {
        super.onResume()
    }

    override fun getViewBinding(viewGroup: ViewGroup, viewType: Int): HomeFragmentItemBinding {
        return HomeFragmentItemBinding.inflate(layoutInflater, viewGroup, false)
    }

    override fun getListCount(): Int {
        return list.size
    }

    override fun bindView(viewBind: HomeFragmentItemBinding, position: Int) {
        viewBind.apiData = list[position]
        viewBind.textView.setOnClickListener {
            val title = list[position].title
           val body =  list[position].body
            passData(title, body)
        }
        Log.e("vibhu",list[position].toString())
    }

    private fun passData(title:String, body:String) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle().apply {
            putString("title", title)
            putString("data", body)
        }

        detailsFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.relativeLayout, detailsFragment)
            .addToBackStack(null)
            .commit()
    }


}