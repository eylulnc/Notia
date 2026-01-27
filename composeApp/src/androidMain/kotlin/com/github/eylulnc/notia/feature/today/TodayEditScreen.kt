package com.github.eylulnc.notia.feature.today

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.theme.*

@Composable
fun TodayEditScreen(
    initialText: String?,
    onSave: (String) -> Unit
) {
    var textFieldValue by remember {
        mutableStateOf(
            TextFieldValue(
                text = initialText.orEmpty(),
                selection = TextRange(initialText.orEmpty().length)
            )
        )
    }
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
                text = stringResource(R.string.today_prefix),
                fontSize = FontSizes.title,
                fontWeight = FontWeight.Bold,
                color = Charcoal
            )

            Spacer(Modifier.height(Spacing.l))

            BasicTextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                textStyle = TextStyle(
                    fontSize = FontSizes.title,
                    color = Charcoal,
                    fontWeight = FontWeight.Medium
                ),
                decorationBox = { innerTextField ->
                    if (textFieldValue.text.isBlank()) {
                        Text(
                            text = stringResource(R.string.enter_intention_placeholder),
                            color = CharcoalSoft,
                            fontSize = FontSizes.title
                        )
                    }
                    innerTextField()
                }
            )
        }

        Button(
            onClick = { onSave(textFieldValue.text.trim()) },
            enabled = textFieldValue.text.isNotBlank(),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.l)
                .height(Spacing.buttonHeight)
        ) {
            Text(
                text = stringResource(R.string.save_focus),
                color = Cream,
                fontSize = FontSizes.button,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
