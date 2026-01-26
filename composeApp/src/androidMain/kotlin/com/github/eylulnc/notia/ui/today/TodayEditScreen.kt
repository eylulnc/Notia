package com.github.eylulnc.notia.ui.today

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.eylulnc.notia.ui.theme.*

@Composable
fun TodayEditScreen(
    initialText: String?,
    onSave: (String) -> Unit
) {
    var text by remember { mutableStateOf(initialText.orEmpty()) }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundLight)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Spacing.l, vertical = Spacing.xl)
        ) {
            Text(
                text = "Today…",
                fontSize = FontSizes.title,
                fontWeight = FontWeight.Bold,
                color = Charcoal
            )

            Spacer(Modifier.height(Spacing.l))

            BasicTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                textStyle = TextStyle(
                    fontSize = FontSizes.title,
                    color = Charcoal,
                    fontWeight = FontWeight.Medium
                ),
                decorationBox = { innerTextField ->
                    if (text.isBlank()) {
                        Text(
                            text = "Enter your daily intention…",
                            color = CharcoalSoft,
                            fontSize = FontSizes.title
                        )
                    }
                    innerTextField()
                }
            )
        }

        Button(
            onClick = { onSave(text.trim()) },
            enabled = text.isNotBlank(),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.l)
                .height(56.dp)
        ) {
            Text(
                text = "Save Focus",
                color = Cream,
                fontSize = FontSizes.button,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
