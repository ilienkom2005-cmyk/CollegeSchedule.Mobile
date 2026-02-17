package com.example.collegeschedule.data.dto

data class ScheduleByDateDto (
    val lessonDate: String, // e.g., "2026-01-12"
    val weekday: String,   // e.g., "Понедельник"
    val lessons: List<LessonDto>
)