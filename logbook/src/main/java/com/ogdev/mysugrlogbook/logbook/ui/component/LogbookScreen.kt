package com.ogdev.mysugrlogbook.logbook.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ogdev.mysugrlogbook.core.ui.MySugrLogbookTheme
import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import com.ogdev.mysugrlogbook.logbook.ui.LogbookViewModel
import com.ogdev.mysugrlogbook.logbook.ui.model.LogbookScreenState
import org.jetbrains.annotations.VisibleForTesting

@VisibleForTesting
const val AVERAGE_BLOOD_GLUCOSE_TEST_TAG = "averageBloodGlucoseTestTag"

@VisibleForTesting
const val BLOOD_GLUCOSE_MEASUREMENT_TEST_TAG = "bloodGlucoseMeasurementTestTag"

@VisibleForTesting
const val BLOOD_GLUCOSE_LIST_TEST_TAG = "bloodGlucoseListTestTag"

@Composable
fun LogbookScreen(
    modifier: Modifier = Modifier,
    viewModel: LogbookViewModel
) {
    val state = viewModel.state.collectAsState().value

    LogbookScreenContent(
        modifier = modifier,
        state = state,
        onUnitSelected = { unit -> viewModel.onUnitSelected(unit) },
        onValueUpdated = { value -> viewModel.onValueUpdated(value) },
        onSaveButtonClicked = { value, unit -> viewModel.saveBloodGlucose(value, unit) }
    )
}

@Composable
fun LogbookScreenContent(
    modifier: Modifier = Modifier,
    state: LogbookScreenState,
    onUnitSelected: (UnitType) -> Unit = {},
    onValueUpdated: (String) -> Unit = {},
    onSaveButtonClicked: (Float, UnitType) -> Unit = { _, _ -> }
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 24.dp)
    ) {
        AverageBloodGlucoseComponent(
            modifier = Modifier.testTag(AVERAGE_BLOOD_GLUCOSE_TEST_TAG),
            value = state.bloodGlucoseAverage,
            unit = state.bloodGlucoseUnit
        )

        BloodGlucoseMeasurementComponent(
            modifier = Modifier
                .padding(top = 8.dp)
                .testTag(BLOOD_GLUCOSE_MEASUREMENT_TEST_TAG),
            updatedValue = state.inputTextValue,
            selectedUnit = state.bloodGlucoseUnit,
            clearEnteredValue = state.clearEnteredValue,
            onUnitSelected = onUnitSelected,
            onValueUpdated = onValueUpdated,
            onSaveButtonClicked = onSaveButtonClicked
        )

        BloodGlucoseListComponent(
            modifier = Modifier
                .padding(top = 8.dp)
                .testTag(BLOOD_GLUCOSE_LIST_TEST_TAG),
            bloodGlucoseList = state.bloodGlucoseList
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun LogbookScreenContentPreview() {
    MySugrLogbookTheme {
        val state = LogbookScreenState(
            bloodGlucoseAverage = 12F,
            bloodGlucoseValue = 0F,
            bloodGlucoseUnit = UnitType.MGDL,
            bloodGlucoseList = listOf(
                BloodGlucose(12F, UnitType.MGDL),
                BloodGlucose(10F, UnitType.MGDL),
                BloodGlucose(14F, UnitType.MGDL)
            )
        )
        LogbookScreenContent(state = state)
    }
}