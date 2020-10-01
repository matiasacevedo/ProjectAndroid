package com.coonfbeer.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.coonfbeer.R
import com.coonfbeer.model.Conference
import com.coonfbeer.ui.adapter.ScheduleAdapter
import com.coonfbeer.ui.adapter.ScheduleListener
import com.coonfbeer.viewmodel.ScheduleViewModel
import kotlinx.android.synthetic.main.fragment_schedule.*


class ScheduleFragment : Fragment(), ScheduleListener {

    private lateinit var viewModel : ScheduleViewModel
    private lateinit var scheduleAdapter : ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        viewModel.refresh()
        scheduleAdapter = ScheduleAdapter(this)

        rvSchedule.apply{
            layoutManager = LinearLayoutManager(view.context,
                                                LinearLayoutManager.VERTICAL,
                                                false)
            adapter = scheduleAdapter
            observerViewModel()
        }
    }

    //controla la data que trae desde la base de datos
    fun observerViewModel(){
        viewModel.listSchedule.observe(viewLifecycleOwner, Observer<List<Conference>>{
                schedule -> scheduleAdapter.updateData(schedule)
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean>{
            if ( it != null){
                rlBaseSchedule.visibility = View.INVISIBLE
            }
        })
    }

    override fun onConferenceClicked(conference: Conference, position: Int) {
        val bundle = bundleOf("conference" to conference)
        findNavController().navigate(R.id.scheduleDetailDialogFragment)
    }
}