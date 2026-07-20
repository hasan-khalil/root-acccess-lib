package com.example.firepassword.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
    ) {
        Text(text)
    }

}

@Preview(showBackground = true)
@Composable
fun PrimaryButton(){
    PrimaryButton(
        text = "Create Account",
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        enabled = true
    )
}