package com.ogdev.mysugrlogbook.logbook.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ogdev.mysugrlogbook.core.ui.MySugrLogbookTheme
import com.ogdev.mysugrlogbook.logbook.R
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import org.jetbrains.annotations.VisibleForTesting

@VisibleForTesting
const val AVERAGE_BLOOD_GLUCOSE_TEXT_TEST_TAG = "averageBloodGlucoseTextTestTag"

@Composable
fun AverageBloodGlucoseComponent(
    modifier: Modifier = Modifier,
    value: Float,
    unit: UnitType
) = Box(
    modifier = modifier
        .fillMaxWidth()
        .padding(8.dp),
    contentAlignment = Alignment.TopCenter
) {
    val resId = if (unit == UnitType.MMOL) {
        R.string.blood_glucose_mmol_liter_label
    } else {
        R.string.blood_glucose_mg_dl_label
    }
    val label = stringResource(id = resId)

    Text(
        text = stringResource(R.string.average_blood_glucose_label, value, label),
        modifier = Modifier.testTag(AVERAGE_BLOOD_GLUCOSE_TEXT_TEST_TAG)
    )
}

@Composable
@Preview(showBackground = true)
private fun AverageBloodGlucoseComponentPreview() {
    MySugrLogbookTheme {
        AverageBloodGlucoseComponent(
            value = 12F,
            unit = UnitType.MGDL
        )
    }
}
