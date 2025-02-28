package com.example.housekeeper.presentation.add_transaction

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import com.example.housekeeper.R
import com.example.housekeeper.domain.model.Expense
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddTransactionScreen(account: Expense?, category: Expense?, modifier: Modifier = Modifier) {
    val viewModel: AddTransactionViewModel = koinViewModel()
    val sum = remember { mutableDoubleStateOf(0.0) }
    val currentAccount = remember { mutableStateOf(account) }
    val currentCategory = remember { mutableStateOf(category) }
    if (account != null) {
        viewModel.setAccount(account)
    }
    if (category != null) {
        viewModel.setCategory(category)
    }
    Column(modifier = modifier.fillMaxWidth()) {
        Button(
            modifier = modifier
                .align(Alignment.End)
                .padding(dimensionResource(R.dimen.classic_padding)),
            onClick = { viewModel.addTransaction() },
            enabled = ((currentAccount.value != null) || (currentCategory.value != null))
        ) {
            Text(stringResource(R.string.save))
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(R.dimen.classic_padding)
                ),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                modifier = modifier
                    .padding(
                        dimensionResource(R.dimen.classic_padding)
                    )
                    .clickable {
                    },
                text = currentAccount.value?.name ?: stringResource(R.string.empty_account)
            )

            Text(text = currentCategory.value?.name ?: stringResource(R.string.empty_account))
        }
        TextField(
            value = if (sum.doubleValue == 0.0) "" else sum.doubleValue.toString(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { input ->
                viewModel.setSum(input)
                sum.doubleValue = input.toDouble()
            }
        )

    }
}
