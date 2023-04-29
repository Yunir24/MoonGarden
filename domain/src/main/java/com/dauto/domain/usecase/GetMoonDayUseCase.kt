package com.dauto.domain.usecase

import com.dauto.domain.MoonCalendarRepository
import com.dauto.domain.moonentity.MoonDay

class GetMoonDayUseCase(private val moonCalendarRepository: MoonCalendarRepository) {

    suspend operator fun invoke(dayId: String): MoonDay{
        return moonCalendarRepository.getMoonDay(dayId)
    }
}