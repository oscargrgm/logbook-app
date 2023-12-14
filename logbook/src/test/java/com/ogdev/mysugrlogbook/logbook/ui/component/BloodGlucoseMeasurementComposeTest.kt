package com.ogdev.mysugrlogbook.logbook.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import com.ogdev.mysugrlogbook.logbook.ui.rule.RobolectricActivityRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class BloodGlucoseMeasurementComposeTest {

    @get:Rule(order = 1)
    val robolectricActivityRule = RobolectricActivityRule()

    @get:Rule(order = 2)
    val composeTestRule = createComposeRule()

    @Test
    fun verifyBloodGlucoseMeasurementComponentIsDisplayedCorrectly() {
        with(composeTestRule) {
            setContent {
                BloodGlucoseMeasurementComponent(selectedUnit = UnitType.MMOL)
            }

            onNodeWithText("Add measurement").assertIsDisplayed()
            onNodeWithTag(RADIO_BUTTON_GROUP_TEST_TAG).assertIsDisplayed()
            onNodeWithTag(INPUT_TEXT_COMPONENT_TEST_TAG).assertIsDisplayed()
            onNodeWithTag(SAVE_BUTTON_TEST_TAG).assertIsDisplayed()
        }
    }
}