package com.oneasad.currencyapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oneasad.currencyapp.data.currencyList
import com.oneasad.currencyapp.ui.theme.CurrencyAppTheme

@Composable
fun CurrencyLayout(
    modifier: Modifier = Modifier,
    viewModel: CurrencyViewModel
) {
    val usdIndex = currencyList.indexOfFirst { it.code == "USD" }
    val pkrIndex = currencyList.indexOfFirst { it.code == "PKR" }
    val state = viewModel.uiState.collectAsState()

    var fromCurrencyDropdownExpanded by remember { mutableStateOf(false) }
    var toCurrencyDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Currency Converter",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp),
            color = MaterialTheme.colorScheme.primary
        )

        // Dropdown for From Currency
        DropdownSelector(
            label = "From",
            selectedItem = state.value.fromCurrency,
            items = state.value.allCurrencies,
            onItemSelected = { viewModel.updateFromCurrency(it) },
            expanded = fromCurrencyDropdownExpanded,
            onExpandedChange = { fromCurrencyDropdownExpanded = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown for To Currency
        DropdownSelector(
            label = "To",
            selectedItem = state.value.toCurrency,
            items = state.value.allCurrencies,
            onItemSelected = { viewModel.updateToCurrency(it) },
            expanded = toCurrencyDropdownExpanded,
            onExpandedChange = { toCurrencyDropdownExpanded = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = state.value.amount.toString(),
            onValueChange = { viewModel.updateAmount(it) },
            label = { Text("Amount") },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp)),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { viewModel.convert() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "Convert",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
        ResultCard(state.value.result)
    }
}


@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    CurrencyAppTheme {
        val viewModel = CurrencyViewModel()
        CurrencyLayout(viewModel = viewModel)
    }
}
