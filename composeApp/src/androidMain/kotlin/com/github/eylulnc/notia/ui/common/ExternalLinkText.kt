package com.github.eylulnc.notia.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.OpenInNew
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import com.github.eylulnc.notia.ui.theme.Spacing

@Composable
fun ExternalLinkText(
    text: String,
    linkText: String,
    url: String,
    modifier: Modifier = Modifier,
    showIcon: Boolean = true,
    style: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
) {
    val uriHandler = LocalUriHandler.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val annotatedString = buildAnnotatedString {
            withStyle(style = SpanStyle(color = style.color)) {
                append(text)
            }
            withLink(
                link = LinkAnnotation.Url(
                    url = url,
                    styles = null, // Using manual style below for better control
                    linkInteractionListener = {
                        uriHandler.openUri(url)
                    }
                )
            ) {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(linkText)
                }
            }
        }

        Text(
            text = annotatedString,
            style = style
        )

        if (showIcon) {
            Spacer(Modifier.width(Spacing.xs))
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.OpenInNew,
                contentDescription = null,
                modifier = Modifier.size(Spacing.m),
                tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
            )
        }
    }
}
