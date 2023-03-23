package com.example.movieappmad23.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    errMsg: String = "",
    isValid: Boolean,
    singleLine: Boolean = true,
    onChange: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        singleLine = singleLine,
        modifier = modifier.fillMaxWidth(),
        onValueChange = {
            onChange(it)
        },
        label = { Text(text = label) },
        isError = !isValid
    )
    if (errMsg.isNotEmpty()){
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = errMsg,
            fontSize = 14.sp,
            color = MaterialTheme.colors.error
        )
    }
}