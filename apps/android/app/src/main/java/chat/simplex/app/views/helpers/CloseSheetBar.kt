package chat.simplex.app.views.helpers

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import chat.simplex.app.R
import chat.simplex.app.ui.theme.*

@Composable
fun CloseSheetBar(close: (() -> Unit)?, endButtons: @Composable RowScope.() -> Unit = {}) {
  Column(
    Modifier
      .fillMaxWidth()
      .heightIn(min = AppBarHeight)
      .padding(horizontal = AppBarHorizontalPadding),
  ) {
    Row(
      Modifier
        .padding(top = 4.dp), // Like in DefaultAppBar
      content = {
        Row(
          Modifier.fillMaxWidth().height(TextFieldDefaults.MinHeight),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          NavigationButtonBack(onButtonClicked = close)
          Row {
            endButtons()
          }
        }
      }
    )
  }
}

@Composable
fun AppBarTitle(title: String, withPadding: Boolean = true) {
  val padding = if (withPadding)
    PaddingValues(start = DEFAULT_PADDING, end = DEFAULT_PADDING, bottom = DEFAULT_PADDING)
  else
    PaddingValues(bottom = DEFAULT_PADDING)
  Text(
    title,
    Modifier
      .fillMaxWidth()
      .padding(padding),
    overflow = TextOverflow.Ellipsis,
    style = MaterialTheme.typography.h1
  )
}

@Composable
fun ColumnScope.AppBarTitleCentered(title: String, withPadding: Boolean = true) {
  Text(
    title,
    Modifier
      .padding(bottom = if (withPadding) DEFAULT_PADDING * 1.5f else 0.dp)
      .align(Alignment.CenterHorizontally),
    overflow = TextOverflow.Ellipsis,
    style = MaterialTheme.typography.h1,
    color = MaterialTheme.colors.primary
  )
}

@Preview(showBackground = true)
@Preview(
  uiMode = Configuration.UI_MODE_NIGHT_YES,
  showBackground = true,
  name = "Dark Mode"
)
@Composable
fun PreviewCloseSheetBar() {
  SimpleXTheme {
    CloseSheetBar(close = {})
  }
}
