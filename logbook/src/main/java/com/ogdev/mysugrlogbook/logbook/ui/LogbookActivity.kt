package com.ogdev.mysugrlogbook.logbook.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ogdev.mysugrlogbook.core.ui.MySugrLogbookTheme
import com.ogdev.mysugrlogbook.logbook.ui.component.LogbookScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogbookActivity : ComponentActivity() {

    private val viewModel: LogbookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MySugrLogbookTheme { LogbookScreen(viewModel = viewModel) }
        }
    }
}