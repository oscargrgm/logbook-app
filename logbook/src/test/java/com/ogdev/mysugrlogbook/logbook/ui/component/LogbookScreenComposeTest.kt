package com.ogdev.mysugrlogbook.logbook.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import com.ogdev.mysugrlogbook.logbook.ui.model.LogbookScreenState
import com.ogdev.mysugrlogbook.logbook.ui.rule.RobolectricActivityRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class LogbookScreenComposeTest {

    @get:Rule(order = 1)
    val robolectricActivityRule = RobolectricActivityRule()

    @get:Rule(order = 2)
    val composeTestRule = createComposeRule()

    @Test
    fun verifyLogbookScreenIsDisplayedCorrectly() {
        with(composeTestRule) {
            val state = LogbookScreenState(
                bloodGlucoseAverage = 10F,
                bloodGlucoseValue = 15F,
                bloodGlucoseUnit = UnitType.MMOL,
                bloodGlucoseList = listOf(
                    BloodGlucose(5F, UnitType.MMOL),
                    BloodGlucose(15F, UnitType.MMOL)
                )
            )

            setContent { LogbookScreenContent(state = state) }

            onNodeWithTag(AVERAGE_BLOOD_GLUCOSE_TEST_TAG).assertExists()
            onNodeWithTag(AVERAGE_BLOOD_GLUCOSE_TEST_TAG).assertIsDisplayed()
            onNodeWithTag(BLOOD_GLUCOSE_MEASUREMENT_TEST_TAG).assertExists()
            onNodeWithTag(BLOOD_GLUCOSE_MEASUREMENT_TEST_TAG).assertIsDisplayed()
            onNodeWithTag(BLOOD_GLUCOSE_LIST_TEST_TAG).assertExists()
        }
    }
}

