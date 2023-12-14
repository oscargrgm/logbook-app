package com.ogdev.mysugrlogbook.logbook.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ogdev.mysugrlogbook.core.ui.MySugrLogbookTheme
import com.ogdev.mysugrlogbook.logbook.R
import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import org.jetbrains.annotations.VisibleForTesting

@VisibleForTesting
const val BLOOD_GLUCOSE_LIST_COLUMN_TEST_TAG = "bloodGlucoseListColumnTestTag"

@Composable
fun BloodGlucoseListComponent(
    modifier: Modifier = Modifier,
    bloodGlucoseList: List<BloodGlucose> = emptyList()
) {
    if (bloodGlucoseList.isEmpty().not()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {

            LazyColumn(
                modifier = Modifier.testTag(BLOOD_GLUCOSE_LIST_COLUMN_TEST_TAG)
            ) {
                items(items = bloodGlucoseList) {
                    BloodGlucoseItem(bloodGlucose = it)
                }
            }
        }
    }
}

@Composable
fun BloodGlucoseItem(
    modifier: Modifier = Modifier,
    bloodGlucose: BloodGlucose
) = Box(modifier = modifier.fillMaxWidth()) {
    val resId = if (bloodGlucose.unit == UnitType.MMOL) {
        R.string.blood_glucose_mmol_liter_label
    } else {
        R.string.blood_glucose_mg_dl_label
    }
    val label = stringResource(id = resId)
    val text = "${bloodGlucose.value} $label"

    Text(
        text = text,
        modifier = Modifier.testTag(text)
    )
}

@Composable
@Preview(showBackground = true, apiLevel = 33)
private fun BloodGlucoseItemPreview() {
    MySugrLogbookTheme {
        BloodGlucoseItem(bloodGlucose = BloodGlucose(value = 12F, unit = UnitType.MGDL))
    }
}

@Composable
@Preview(showBackground = true)
private fun BloodGlucoseListComponentPreview() {
    MySugrLogbookTheme {
        BloodGlucoseListComponent(
            bloodGlucoseList = listOf(
                BloodGlucose(value = 12F, unit = UnitType.MGDL),
                BloodGlucose(value = 16F, unit = UnitType.MGDL),
                BloodGlucose(value = 8F, unit = UnitType.MGDL),
                BloodGlucose(value = 10F, unit = UnitType.MGDL),
                BloodGlucose(value = 12F, unit = UnitType.MGDL),
            )
        )
    }
}
