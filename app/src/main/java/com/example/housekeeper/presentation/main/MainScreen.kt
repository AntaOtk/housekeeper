package com.example.housekeeper.presentation.main


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.housekeeper.R
import com.example.housekeeper.domain.model.Expense
import com.example.housekeeper.presentation.main.compose.ExpensesItem
import com.example.housekeeper.presentation.main.compose.SpendingItem
import org.koin.androidx.compose.koinViewModel


@Composable
fun MainScreen(
    navigateToTransaction: (Expense?, Expense?) -> Unit,
) {
    val viewModel: MainViewModel = koinViewModel()
    val modifier = Modifier
    viewModel.getAccounts()
    viewModel.getCategories()
    val accounts = viewModel.observeAccountLiveData().observeAsState(mutableListOf())
    val categories = viewModel.observeCategoryLiveData().observeAsState(mutableListOf())
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.classic_padding)),
            text = stringResource(R.string.app_name),
        )
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.classic_padding)),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(R.color.color_9),
            ),
        ) {
            Column(modifier.padding(dimensionResource(R.dimen.medium_dimen)))
            {
                Text(stringResource(R.string.accounts))
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.small_dimen))
                )
                for (item in accounts.value)
                    SpendingItem(item)
            }
        }
        Spacer(
            Modifier
                .fillMaxWidth()
                .height(dimensionResource(R.dimen.classic_padding))
        )
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.classic_padding)),
            shape = RoundedCornerShape(10.dp),
        ) {
            Column(modifier.padding(dimensionResource(R.dimen.medium_dimen)))
            {
                Text(stringResource(R.string.expenses))
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.small_dimen))
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    items(categories.value) { item ->
                        ExpensesItem(item, { arg1, arg2 -> navigateToTransaction(arg1, arg2) })
                    }

                }
            }
        }
    }
}


@Preview
@Composable
fun ShowSpendingItem() {
    MainScreen(navigateToTransaction = { _, _ -> })
}
