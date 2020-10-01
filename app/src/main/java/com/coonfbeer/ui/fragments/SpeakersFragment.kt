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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.coonfbeer.R
import com.coonfbeer.model.Speaker
import com.coonfbeer.ui.adapter.SpeakerAdapter
import com.coonfbeer.ui.adapter.SpeakerListener
import com.coonfbeer.viewmodel.SpeakerViewModel
import kotlinx.android.synthetic.main.fragment_speakers.*


class SpeakersFragment : Fragment(), SpeakerListener{

    private lateinit var viewModel : SpeakerViewModel
    private lateinit var speakerAdapter : SpeakerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_speakers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SpeakerViewModel::class.java)
        viewModel.refresh()
        speakerAdapter = SpeakerAdapter(this)

        rvSpeaker.apply{
            layoutManager = GridLayoutManager(context, 2)

            adapter = speakerAdapter
            observerViewModel()
        }
    }

    //controla la data que trae desde la base de datos
    fun observerViewModel(){
        viewModel.listSpeaker.observe(viewLifecycleOwner, Observer<List<Speaker>>{
                speaker -> speaker.let {
                    speakerAdapter.updateData(speaker)
                }
        })

        viewModel.isLoading.observe(viewLifecycleOwner, Observer<Boolean>{
            if ( it != null){
                rlBaseSpeaker.visibility = View.INVISIBLE
            }
        })
    }

    override fun onSpeakerClicked(speaker: Speaker, position: Int) {
        val bundle = bundleOf("speaker" to speaker)
        findNavController().navigate(R.id.speakerDetailDialogFragment, bundle)
    }
}