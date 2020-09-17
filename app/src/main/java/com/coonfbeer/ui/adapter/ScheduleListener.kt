package com.coonfbeer.ui.adapter

import com.coonfbeer.model.Conference

interface ScheduleListener {

    fun  onConferenceClicked(conference : Conference, position: Int)

}