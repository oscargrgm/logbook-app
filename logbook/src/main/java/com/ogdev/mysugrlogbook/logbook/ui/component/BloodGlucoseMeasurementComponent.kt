package com.ogdev.mysugrlogbook.logbook.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ogdev.mysugrlogbook.core.ui.MySugrLogbookTheme
import com.ogdev.mysugrlogbook.logbook.R
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import org.jetbrains.annotations.VisibleForTesting

@VisibleForTesting
const val RADIO_BUTTON_GROUP_TEST_TAG = "radioButtonGroupTestTag"

@VisibleForTesting
const val RADIO_BUTTON_MMOL_TEST_TAG = "radioButtonMmolTestTag"

@VisibleForTesting
const val RADIO_BUTTON_MGDL_TEST_TAG = "radioButtonMgdlTestTag"

@VisibleForTesting
const val INPUT_TEXT_COMPONENT_TEST_TAG = "inputTextComponentTestTag"

@VisibleForTesting
const val INPUT_TEXT_FIELD_TEST_TAG = "inputTextFieldTestTag"

@VisibleForTesting
const val SAVE_BUTTON_TEST_TAG = "saveButtonTestTag"

@Composable
fun BloodGlucoseMeasurementComponent(
    modifier: Modifier = Modifier,
    updatedValue: String? = null,
    selectedUnit: UnitType,
    clearEnteredValue: Boolean = false,
    onUnitSelected: (UnitType) -> Unit = {},
    onValueUpdated: (String) -> Unit = {},
    onSaveButtonClicked: (Float, UnitType) -> Unit = { _, _ -> }
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        var inputValue: String by remember { mutableStateOf("") }
        updatedValue?.let { inputValue = it }
        if (clearEnteredValue) inputValue = ""

        var unitSelected: UnitType by remember { mutableStateOf(selectedUnit) }

        Text(text = stringResource(R.string.blood_glucose_measurement_add_measurement_title))

        RadioButtonGroup(
            modifier = Modifier
                .padding(top = 8.dp)
                .testTag(RADIO_BUTTON_GROUP_TEST_TAG),
            selectedUnit = selectedUnit,
            onUnitSelected = { unit ->
                unitSelected = unit
                onUnitSelected(unit)
            }
        )

        InputTextComponent(
            modifier = Modifier.testTag(INPUT_TEXT_COMPONENT_TEST_TAG),
            inputValue = inputValue,
            unit = unitSelected,
            onValueChange = {
                inputValue = it
                onValueUpdated(it)
            }
        )

        Button(
            onClick = { onSaveButtonClicked(inputValue.toFloat(), unitSelected) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .testTag(SAVE_BUTTON_TEST_TAG)
        ) {
            Text(text = stringResource(R.string.blood_glucose_measurement_save_button))
        }
    }
}

@Composable
fun RadioButtonGroup(
    modifier: Modifier = Modifier,
    selectedUnit: UnitType,
    onUnitSelected: (UnitType) -> Unit = { _ -> }
) {
    var rememberSelectedUnit: UnitType? by remember { mutableStateOf(selectedUnit) }

    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = rememberSelectedUnit == UnitType.MMOL,
                onClick = {
                    rememberSelectedUnit = UnitType.MMOL
                    onUnitSelected(UnitType.MMOL)
                },
                modifier = Modifier.testTag(RADIO_BUTTON_MMOL_TEST_TAG)
            )
            Text(
                text = stringResource(R.string.blood_glucose_mmol_liter_label),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        Row(
            modifier = Modifier.padding(top = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = rememberSelectedUnit == UnitType.MGDL,
                onClick = {
                    rememberSelectedUnit = UnitType.MGDL
                    onUnitSelected(UnitType.MGDL)
                },
                modifier = Modifier.testTag(RADIO_BUTTON_MGDL_TEST_TAG)
            )
            Text(
                text = stringResource(R.string.blood_glucose_mg_dl_label),
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun InputTextComponent(
    modifier: Modifier = Modifier,
    inputValue: String,
    unit: UnitType,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = inputValue,
            modifier = Modifier
                .wrapContentWidth()
                .testTag(INPUT_TEXT_FIELD_TEST_TAG),
            label = { Text(text = stringResource(R.string.blood_glucose_measurement_input_text_label)) },
            onValueChange = { it: String -> onValueChange(it) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Text(
            text = getUnitLabel(unit),
            modifier = Modifier.padding(start = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Clip
        )
    }
}

@Composable
private fun getUnitLabel(unit: UnitType): String {
    val id = if (unit == UnitType.MMOL) {
        R.string.blood_glucose_mmol_liter_label
    } else {
        R.string.blood_glucose_mg_dl_label
    }
    return stringResource(id = id)
}

@Composable
@Preview(showBackground = true)
private fun BloodGlucoseMeasurementComponentPreview() {
    MySugrLogbookTheme {
        BloodGlucoseMeasurementComponent(selectedUnit = UnitType.MGDL)
    }
}