package com.scheduler.thescheduler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*


@SpringBootApplication
class TheSchedulerApplication

fun main(args: Array<String>) {
	runApplication<TheSchedulerApplication>(*args)
}

@RestController
class ScheduleController {
	val tomorrow: LocalDate = LocalDate.now().plusDays(1)
	val labDays = arrayOf("20230418", "20230509", "20230606", "20230627")

	fun getDayStringNew(): String {
		val day = tomorrow.dayOfWeek
		return day.getDisplayName(TextStyle.FULL, Locale.getDefault())
	}

	fun response(day: String): String {
		if(labDays.contains(tomorrow.toString().replace("-", ""))) {
			return "Yes, but it's an early half day because of school"
		} else {
			when (day) {
				"Monday" -> return "No, she's busy at school on Mondays"
				"Tuesday" -> return "Yes, she works most Tuesdays full day"
				"Wednesday" -> return "Yes, she works full day on Wednesdays"
				"Thursday" -> return "No, she's busy at school on Thursdays"
				"Friday" -> return "Yes, but probably only a half day and from the comfort of her home"
				"Saturday" -> return "No, she's smart enough to not work on weekends"
				"Sunday" -> return "No, she's smart enough to not work on weekends"
				else -> {
					return "Did you break my app, Dominik? >.<"
				}
			}
		}
	}

	@GetMapping("/scheduler")
	fun index() = "Is Alisa working tomorrow? " + response(getDayStringNew())
}
