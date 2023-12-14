package com.ogdev.mysugrlogbook.logbook.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import com.ogdev.mysugrlogbook.logbook.domain.model.params.GetAverageBloodGlucoseParams
import com.ogdev.mysugrlogbook.logbook.domain.model.params.GetBloodGlucoseListParams
import com.ogdev.mysugrlogbook.logbook.domain.model.params.SaveBloodGlucoseParams
import com.ogdev.mysugrlogbook.logbook.domain.model.result.SaveBloodGlucoseResult
import com.ogdev.mysugrlogbook.logbook.domain.usecase.GetAverageBloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.usecase.GetBloodGlucoseList
import com.ogdev.mysugrlogbook.logbook.domain.usecase.SaveBloodGlucose
import com.ogdev.mysugrlogbook.logbook.ui.model.LogbookScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class LogbookViewModel @Inject constructor(
    private val saveBloodGlucose: SaveBloodGlucose,
    private val getBloodGlucoseList: GetBloodGlucoseList,
    private val getAverageBloodGlucose: GetAverageBloodGlucose
) : ViewModel() {

    val state: MutableStateFlow<LogbookScreenState> = MutableStateFlow(LogbookScreenState.EMPTY)

    init {
        getAverageBloodGlucose()
        getBloodGlucoseList()
    }

    fun onUnitSelected(unit: UnitType) {
        updateValueAccordingUnit(unit)

        getAverageBloodGlucose()
        getBloodGlucoseList()
    }

    fun onValueUpdated(value: String) {
        updateState { copy(inputTextValue = value) }
    }

    fun saveBloodGlucose(value: Float, unit: UnitType) = viewModelScope.launch(Dispatchers.IO) {
        val params = SaveBloodGlucoseParams(value, unit)
        when (val result = saveBloodGlucose.invoke(params)) {
            is SaveBloodGlucoseResult.Error -> {
                updateState { copy(inputErrorMessage = result.message) }
            }

            SaveBloodGlucoseResult.Success -> {
                updateState {
                    copy(
                        bloodGlucoseValue = value,
                        bloodGlucoseUnit = unit,
                        clearEnteredValue = true
                    )
                }
            }
        }
    }

    private fun getAverageBloodGlucose() = viewModelScope.launch(Dispatchers.IO) {
        val params = GetAverageBloodGlucoseParams(state.value.bloodGlucoseUnit)
        getAverageBloodGlucose.invoke(params)
            .onEach { updateState { copy(bloodGlucoseAverage = it) } }
            .launchIn(this)
    }

    private fun getBloodGlucoseList() = viewModelScope.launch(Dispatchers.IO) {
        val params = GetBloodGlucoseListParams(state.value.bloodGlucoseUnit)
        getBloodGlucoseList.invoke(params)
            .onEach { updateState { copy(bloodGlucoseList = it) } }
            .launchIn(this)
    }

    private fun updateValueAccordingUnit(unit: UnitType) {
        val inputValue: Float = runCatching {
            state.value.inputTextValue?.toFloat() ?: 0F
        }.getOrElse { 0F }

        val currentBloodGlucose = with(state.value) {
            BloodGlucose(inputValue, bloodGlucoseUnit)
        }

        val convertedValue = currentBloodGlucose.convertTo(unit)
        updateState {
            copy(
                inputTextValue = convertedValue.toString(),
                bloodGlucoseValue = convertedValue,
                bloodGlucoseUnit = unit
            )
        }
    }

    private fun updateState(reduce: LogbookScreenState.() -> LogbookScreenState) {
        state.compareAndSet(state.value, state.value.reduce())
    }
}