package com.example.bodymassindex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bodymassindex.ui.theme.BodyMassIndexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BodyMassIndexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BodyMassIndex()
                }
            }
        }
    }
}

@Composable
fun BodyMassIndex(bmiViewModel : BmiViewModel = viewModel()) {
    Column (
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = stringResource(R.string.body_mass_index),
            fontSize = 24.sp,
            color= MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        )

        OutlinedTextField(
            value = bmiViewModel.heightInput,
            onValueChange ={ bmiViewModel.changeHeightInput(it.replace(",","."))} ,
            label = { Text(stringResource(R.string.height))},
            singleLine=true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = bmiViewModel.weightInput,
            onValueChange ={ bmiViewModel.changeWeightInput(it.replace(',','.'))} ,
            label = { Text(stringResource(R.string.weight))},
            singleLine=true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = stringResource(R.string.result,String.format("%.2f",bmiViewModel.bmi).replace(',','.')))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BodyMassIndexTheme {
        BodyMassIndex()
    }
}