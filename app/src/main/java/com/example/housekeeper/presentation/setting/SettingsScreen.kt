package com.example.housekeeper.presentation.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.housekeeper.R
import com.example.housekeeper.compose.SettingsButton

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        SettingsButton(stringResource(R.string.add_account)) {}
        SettingsButton(stringResource(R.string.add_category)) {}
        SettingsButton(stringResource(R.string.add_limits)) { }
        SettingsButton(stringResource(R.string.add_account)) {}
    }
}