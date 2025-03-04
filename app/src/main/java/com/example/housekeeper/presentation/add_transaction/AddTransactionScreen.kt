package com.example.housekeeper.presentation.add_transaction

import android.util.Log
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.housekeeper.R
import com.example.housekeeper.domain.model.Expense
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddTransactionScreen(
    account: Expense?,
    category: Expense?,
    navigateHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: AddTransactionViewModel = koinViewModel()
    val sum = remember { mutableDoubleStateOf(0.0) }
    val currentAccount = remember { mutableStateOf(account) }
    val currentCategory = remember { mutableStateOf(category) }
    val accounts = viewModel.observeAccountsLiveData().observeAsState(mutableListOf())
    val categories = viewModel.observeCategoriesLiveData().observeAsState(mutableListOf())
    val showAccounts = remember { mutableStateOf(false) }
    val showCategories = remember { mutableStateOf(false) }
    if (account != null) {
        viewModel.setAccount(account)
    }
    if (category != null) {
        viewModel.setCategory(category)
    }
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            modifier = modifier
                .align(Alignment.End)
                .padding(dimensionResource(R.dimen.classic_padding)),
            onClick = {
                viewModel.addTransaction()
                navigateHome()
            },
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
                        viewModel.showAccounts()
                        showAccounts.value = true
                    },
                text = currentAccount.value?.name ?: stringResource(R.string.empty_account)
            )


            Text(
                modifier = modifier
                    .padding(
                        dimensionResource(R.dimen.classic_padding)
                    )
                    .clickable {
                        viewModel.showCategories()
                        showCategories.value = true
                    },
                text = currentCategory.value?.name ?: stringResource(R.string.empty_account)
            )
        }
        TextField(
            value = if (sum.doubleValue > 0.0) sum.doubleValue.toString() else "",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { input ->
                try {
                    sum.doubleValue = input.toDouble()
                    viewModel.setSum(input)
                } catch (e: Exception) {
                    Log.e("SumInput", e.toString())
                }
            }
        )
    }
    if (showAccounts.value) {
        ExpensesDialog(
            content = accounts.value,
            onClick = { item ->
                viewModel.setAccount(item)
                currentAccount.value = item
            },
            onDismissRequest = { showAccounts.value = !showAccounts.value },
            modifier = modifier
        )
    }
    if (showCategories.value) {
        ExpensesDialog(
            content = categories.value,
            onClick = { item ->
                viewModel.setCategory(item)
                currentCategory.value = item
            },
            onDismissRequest = { showCategories.value = !showCategories.value },
            modifier = modifier
        )
    }
}

