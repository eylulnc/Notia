package com.github.eylulnc.notia.feature.today

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.common.NotiaSecondaryButton
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.Spacing

private const val MAX_CHARACTER_LIMIT = 160

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
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Spacing.l, vertical = Spacing.xl)
        ) {
            Text(
                text = stringResource(R.string.today_prefix),
                fontSize = FontSizes.midTitle,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(Modifier.height(Spacing.l))

            BasicTextField(
                value = textFieldValue,
                onValueChange = {
                    if (it.text.length <= MAX_CHARACTER_LIMIT) {
                        textFieldValue = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                textStyle = TextStyle(
                    fontSize = FontSizes.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Medium
                ),
                decorationBox = { innerTextField ->
                    if (textFieldValue.text.isBlank()) {
                        Text(
                            text = stringResource(R.string.enter_intention_placeholder),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = FontSizes.bodyLarge
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(Modifier.height(Spacing.m))

            Text(
                text = "${textFieldValue.text.length}/$MAX_CHARACTER_LIMIT",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodySmall,
                color = if (textFieldValue.text.length >= MAX_CHARACTER_LIMIT) {
                    MaterialTheme.colorScheme.error
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }

        NotiaSecondaryButton(
            onClick = { onSave(textFieldValue.text.trim()) },
            enabled = textFieldValue.text.isNotBlank(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.l)
        ) {
            Text(
                text = stringResource(R.string.save_focus),
                fontSize = FontSizes.button,
            )
        }
    }
}