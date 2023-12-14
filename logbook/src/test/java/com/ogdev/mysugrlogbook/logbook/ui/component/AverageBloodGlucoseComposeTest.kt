package com.ogdev.mysugrlogbook.logbook.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.ogdev.mysugrlogbook.logbook.domain.model.UnitType
import com.ogdev.mysugrlogbook.logbook.ui.rule.RobolectricActivityRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class AverageBloodGlucoseComposeTest {

    @get:Rule(order = 1)
    val robolectricActivityRule = RobolectricActivityRule()

    @get:Rule(order = 2)
    val composeTestRule = createComposeRule()

    @Test
    fun verifyAverageBloodGlucoseIsShownWhenAverageIsNotZero() {
        with(composeTestRule) {
            setContent {
                AverageBloodGlucoseComponent(value = 12.50F, unit = UnitType.MMOL)
            }

            onNodeWithTag(AVERAGE_BLOOD_GLUCOSE_TEXT_TEST_TAG).apply {
                assertExists()
                assertIsDisplayed()
                assertTextEquals("Your average is 12.5 mmol/L.")
            }
        }
    }

    @Test
    fun verifyAverageBloodGlucoseIsShownWhenAverageIsZero() {
        with(composeTestRule) {
            setContent {
                AverageBloodGlucoseComponent(value = 0F, unit = UnitType.MGDL)
            }

            onNodeWithTag(AVERAGE_BLOOD_GLUCOSE_TEXT_TEST_TAG).apply {
                assertExists()
                assertIsDisplayed()
                assertTextEquals("Your average is 0.0 mg/dL.")
            }
        }
    }
}