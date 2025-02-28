package com.example.housekeeper.presentation.setting

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.housekeeper.R
import com.example.housekeeper.compose.SettingsButton

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    navigateToNewCategory: () -> Unit,
    navigateToNewTransaction: () -> Unit,
    navigateToPlan: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        SettingsButton(stringResource(R.string.add_account)) {}
        SettingsButton(stringResource(R.string.add_category)) { navigateToNewCategory() }
        SettingsButton(stringResource(R.string.add_limits)) { navigateToPlan() }
        SettingsButton(stringResource(R.string.add_transaction)) {navigateToNewTransaction()}
    }
}

@Preview
@Composable
fun ShowSettingScreen() {
    SettingsScreen(
        modifier = Modifier,
        navigateToNewCategory = {},
        navigateToNewTransaction = { }
    ) { }
}