package com.example.firepassword.ui.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {

    var visible by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        visualTransformation =
            if (visible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
        trailingIcon = {
            TextButton(
                onClick = {
                    visible = !visible
                },
                modifier = Modifier
            ){
                Text(
                    text = if (visible) "Hide" else "Show"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordFieldPreview(){
    PasswordField(
        value = "ali",
        onValueChange = {},
        label = "Password",
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    )
}