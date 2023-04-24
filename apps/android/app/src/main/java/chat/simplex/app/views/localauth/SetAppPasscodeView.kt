package chat.simplex.app.views.localauth

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import chat.simplex.app.R
import chat.simplex.app.views.helpers.DatabaseUtils.ksAppPassword
import chat.simplex.app.views.helpers.generalGetString

@Composable
fun SetAppPasscodeView(
  submit: () -> Unit,
  cancel: () -> Unit,
  close: () -> Unit
) {
  val passcode = rememberSaveable { mutableStateOf("") }
  var enteredPassword by rememberSaveable { mutableStateOf("") }
  var confirming by rememberSaveable { mutableStateOf(false) }

  @Composable
  fun SetPasswordView(title: String, submitLabel: String, submitEnabled: (((String) -> Boolean))? = null, submit: () -> Unit) {
    BackHandler {
      close()
      cancel()
    }
    PasscodeView(passcode, title = title, submitLabel = submitLabel, submitEnabled = submitEnabled, submit = submit) {
      close()
      cancel()
    }
  }

  if (confirming) {
    SetPasswordView(
      generalGetString(R.string.confirm_passcode),
      generalGetString(R.string.confirm_verb),
      submitEnabled = { pwd -> pwd == enteredPassword }
    ) {
      if (passcode.value == enteredPassword) {
        ksAppPassword.set(passcode.value)
        enteredPassword = ""
        passcode.value = ""
        close()
        submit()
      }
    }
  } else {
    SetPasswordView(generalGetString(R.string.new_passcode), generalGetString(R.string.save_verb)) {
      enteredPassword = passcode.value
      passcode.value = ""
      confirming = true
    }
  }
}
