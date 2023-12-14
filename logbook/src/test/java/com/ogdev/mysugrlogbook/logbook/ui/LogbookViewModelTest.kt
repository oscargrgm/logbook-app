package com.ogdev.mysugrlogbook.logbook.ui

import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import com.ogdev.mysugrlogbook.logbook.domain.usecase.GetAverageBloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.usecase.GetBloodGlucoseList
import com.ogdev.mysugrlogbook.logbook.domain.usecase.SaveBloodGlucose
import com.ogdev.mysugrlogbook.logbook.ui.model.LogbookScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class LogbookViewModelTest {

    private val saveBloodGlucose: SaveBloodGlucose = mock()
    private val getBloodGlucoseList: GetBloodGlucoseList = mock()
    private val getAverageBloodGlucose: GetAverageBloodGlucose = mock()

    private val viewModel = LogbookViewModel(
        saveBloodGlucose,
        getBloodGlucoseList,
        getAverageBloodGlucose
    )

    @Test
    fun `WHEN ViewModel is initialised THEN state is EMPTY`() {
        val viewModel = LogbookViewModel(
            saveBloodGlucose,
            getBloodGlucoseList,
            getAverageBloodGlucose
        )

        assertEquals(LogbookScreenState.EMPTY, viewModel.state.value)
    }

    @Test
    fun `WHEN unit is selected THEN ViewModel state is updated`() {
        viewModel.onUnitSelected(UnitType.MMOL)

        val result = viewModel.state.value.bloodGlucoseUnit

        assertEquals(UnitType.MMOL, result)
    }

    @Test
    fun `WHEN unit is selected THEN ViewModel invoke GetAverageBloodGlucose AND SaveBloodGlucose`() {
        viewModel.onUnitSelected(UnitType.MMOL)

        verify(getAverageBloodGlucose, atLeastOnce()).invoke(any())
        verify(getBloodGlucoseList, atLeastOnce()).invoke(any())
    }
}

