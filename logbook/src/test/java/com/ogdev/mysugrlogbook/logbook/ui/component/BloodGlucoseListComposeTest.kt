package com.ogdev.mysugrlogbook.logbook.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.ogdev.mysugrlogbook.logbook.domain.model.BloodGlucose
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import com.ogdev.mysugrlogbook.logbook.ui.rule.RobolectricActivityRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BloodGlucoseListComposeTest {

    @get:Rule(order = 1)
    val robolectricActivityRule = RobolectricActivityRule()

    @get:Rule(order = 2)
    val composeTestRule = createComposeRule()

    @Test
    fun verifyBloodGlucoseListComponentIsDisplayedCorrectlyWhenListIsNotEmpty() {
        with(composeTestRule) {
            setContent {
                BloodGlucoseListComponent(
                    bloodGlucoseList = listOf(
                        BloodGlucose(1F, UnitType.MMOL),
                        BloodGlucose(2F, UnitType.MMOL),
                        BloodGlucose(3F, UnitType.MGDL)
                    )
                )
            }

            onNodeWithTag(BLOOD_GLUCOSE_LIST_COLUMN_TEST_TAG).assertExists()
            onNodeWithTag(BLOOD_GLUCOSE_LIST_COLUMN_TEST_TAG).assertIsDisplayed()

            onNodeWithTag("1.0 mmol/L").assertExists()
            onNodeWithTag("1.0 mmol/L").assertIsDisplayed()
            onNodeWithTag("2.0 mmol/L").assertExists()
            onNodeWithTag("2.0 mmol/L").assertIsDisplayed()
            onNodeWithTag("3.0 mg/dL").assertExists()
            onNodeWithTag("3.0 mg/dL").assertIsDisplayed()
        }
    }

    @Test
    fun verifyBloodGlucoseListComponentIsDisplayedCorrectlyWhenListIsEmpty() {
        with(composeTestRule) {
            setContent {
                BloodGlucoseListComponent(bloodGlucoseList = emptyList())
            }

            onNodeWithTag(BLOOD_GLUCOSE_LIST_COLUMN_TEST_TAG).assertDoesNotExist()
        }
    }
}