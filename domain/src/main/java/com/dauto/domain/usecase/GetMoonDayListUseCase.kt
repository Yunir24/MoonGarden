package com.dauto.domain.usecase

import com.dauto.domain.MoonCalendarRepository
import com.dauto.domain.moonentity.MoonDay

class GetMoonDayListUseCase(private val moonCalendarRepository: MoonCalendarRepository) {

    suspend operator fun invoke(month: String): List<MoonDay> {
        return moonCalendarRepository.getMoonDayList(month)
    }

}