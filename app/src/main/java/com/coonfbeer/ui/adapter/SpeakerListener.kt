package com.coonfbeer.ui.adapter

import com.coonfbeer.model.Speaker

interface SpeakerListener {
    fun onSpeakerClicked(speaker: Speaker, position: Int )
}