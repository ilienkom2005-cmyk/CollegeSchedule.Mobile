package com.example.collegeschedule.data.repository

import com.example.collegeschedule.data.api.ScheduleApi
import com.example.collegeschedule.data.dto.ScheduleByDateDto

class ScheduleRepository(private val api: ScheduleApi) {
    suspend fun loadScheduleForHardcodedGroup(): List<ScheduleByDateDto> {
        return api.getSchedule(
            groupName = "ИС-12",
            start = "2026-01-12",
            end = "2026-01-17"
        )
    }
}