package mx.tejate.contactskmp.ui.templates

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.tejate.contactskmp.core.utils.rememberBitmapFromBytes
import mx.tejate.contactskmp.ui.atoms.ButtonAtom
import mx.tejate.contactskmp.ui.molecules.AddPhoto
import mx.tejate.contactskmp.ui.molecules.ContactPhoto
import mx.tejate.contactskmp.ui.molecules.InputField
import mx.tejate.contactskmp.ui.uiModel.ContactUI
import mx.tejate.contactskmp.ui.uiModel.FormInputChanged
import mx.tejate.contactskmp.ui.uiModel.FormInputValidation

@Composable
fun ContactsFormTemplate(
    contactUI: ContactUI?,
    formInputValidation: FormInputValidation,
    modifier: Modifier = Modifier,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onPhoto: () -> Unit,
    onValueChange: (FormInputChanged) -> Unit,
) {
    val sizePhoto = 150.dp
    val hasPhoto = contactUI?.photo?.let {
        rememberBitmapFromBytes(it)
    }
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            /**
             * MARK: Photo
             */
            hasPhoto?.let {
                ContactPhoto(
                    photo = it,
                    modifier = Modifier.size(sizePhoto)
                        .clickable {
                            onPhoto.invoke()
                        }
                )
            } ?: AddPhoto {
                onPhoto.invoke()
            }

            /**
             * MARK: Input First Name
             */

            Spacer(Modifier.size(16.dp))

            InputField(
                value = contactUI?.name ?: "",
                placeholder = "First Name",
                error = formInputValidation.firstNameError,
                onValueChange = {
                    onValueChange.invoke(
                        FormInputChanged.OnFirstNameChanged(it)
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            /**
             * MARK: Input Last Name
             */

            Spacer(Modifier.size(16.dp))

            InputField(
                value = contactUI?.lastName ?: "",
                placeholder = "Last Name",
                error = formInputValidation.lastNameError,
                onValueChange = {
                    onValueChange.invoke(
                        FormInputChanged.OnLastNameChanged(it)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )

            /**
             * MARK: Input Phone Number
             */

            Spacer(Modifier.size(16.dp))

            InputField(
                value = contactUI?.phone ?: "",
                placeholder = "Phone Number",
                error = formInputValidation.phoneNumberError,
                onValueChange = {
                    onValueChange.invoke(
                        FormInputChanged.OnPhoneNumberChanged(it)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )

            /**
             * MARK: Input email
             */

            Spacer(Modifier.size(16.dp))

            InputField(
                value = contactUI?.email ?: "",
                error = formInputValidation.emailError,
                placeholder = "Email",
                onValueChange = {
                    onValueChange.invoke(
                        FormInputChanged.OnEmailChanged(it)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )

            /**
             * MARK: Save
             */

            Spacer(Modifier.size(16.dp))

            ButtonAtom(
                label = "Save",
                modifier = Modifier.fillMaxWidth()
            ) {
                onValueChange.invoke(
                    FormInputChanged.OnFirstNameChanged(contactUI?.name.orEmpty())
                )
                onValueChange.invoke(
                    FormInputChanged.OnLastNameChanged(contactUI?.lastName.orEmpty())
                )
                onValueChange.invoke(
                    FormInputChanged.OnPhoneNumberChanged(contactUI?.phone.orEmpty())
                )
                onValueChange.invoke(
                    FormInputChanged.OnEmailChanged(contactUI?.email.orEmpty())
                )
                if (!formInputValidation.hasError) {
                    onSave.invoke()
                }
            }
        }

        IconButton(
            onClick = {
                onBack.invoke()
            }
        ) {
            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Close",
                modifier = Modifier.size(24.dp)
            )
        }
    }

}