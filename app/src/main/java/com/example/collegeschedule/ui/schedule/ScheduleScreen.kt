package com.example.collegeschedule.ui.schedule

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.collegeschedule.data.dto.ScheduleByDateDto
import com.example.collegeschedule.data.network.RetrofitInstance
import com.example.collegeschedule.data.repository.ScheduleRepository
import com.example.collegeschedule.utils.getWeekDateRange

@Composable
fun ScheduleScreen() {
    var schedule by remember { mutableStateOf<List<ScheduleByDateDto>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {

            val repository = ScheduleRepository(RetrofitInstance.api)
            val (start, end) = getWeekDateRange()
            schedule = repository.loadScheduleForHardcodedGroup()
        } catch (e: Exception) {
            error = e.message
            e.printStackTrace()
        } finally {
            loading = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            loading -> {
                CircularProgressIndicator()
            }
            error != null -> {
                Text(text = "Ошибка: $error")
            }
            else -> {
                ScheduleList(data = schedule)
            }
        }
    }
}