package com.dauto.domain.usecase

import com.dauto.domain.MoonCalendarRepository
import com.dauto.domain.moonentity.MonthAndDays

class GetMoonMonthUseCase(private val moonCalendarRepository: MoonCalendarRepository) {

    suspend operator fun invoke(monthId: String): MonthAndDays {
        return moonCalendarRepository.getMoonMonth(monthId)
    }
}