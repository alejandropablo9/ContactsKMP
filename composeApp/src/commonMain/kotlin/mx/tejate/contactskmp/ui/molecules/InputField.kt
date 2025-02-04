package mx.tejate.contactskmp.ui.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import mx.tejate.contactskmp.ui.atoms.TextAtom

@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    error: String? = null,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { TextAtom(placeholder, color = MaterialTheme.colorScheme.primary) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = visualTransformation
        )
        error?.let {
            TextAtom(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.fillMaxWidth()
                    .padding(4.dp)
            )
        }
    }
}