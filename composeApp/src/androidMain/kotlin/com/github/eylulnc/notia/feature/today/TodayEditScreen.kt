package com.github.eylulnc.notia.feature.today

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.github.eylulnc.notia.R
import com.github.eylulnc.notia.ui.theme.FontSizes
import com.github.eylulnc.notia.ui.theme.Spacing

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
                fontSize = FontSizes.title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
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
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.Medium
                ),
                decorationBox = { innerTextField ->
                    if (textFieldValue.text.isBlank()) {
                        Text(
                            text = stringResource(R.string.enter_intention_placeholder),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
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
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.l)
                .height(Spacing.buttonHeight)
        ) {
            Text(
                text = stringResource(R.string.save_focus),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = FontSizes.button,
            )
        }
    }
}