import android.app.ProgressDialog
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.viewinterop.AndroidView
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.Loading

@Composable
actual fun WebView(modifier: Modifier, link: String) {
    var isLoading by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                if (isLoading) {
                    ProgressDialog(it).apply {
                        // TODO
                    }
                }
                WebView(it).apply {
                    settings.javaScriptEnabled = true
                    layoutParams =
                        ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT)

                    webViewClient =
                        object : WebViewClient() {

                            override fun onPageStarted(
                                view: WebView?,
                                url: String?,
                                favicon: Bitmap?
                            ) {
                                isLoading = true
                            }

                            override fun onPageFinished(view: WebView?, url: String?) {
                                isLoading = false
                            }
                        }
                    loadUrl(link)
                }
            },
            update = {})
    }
}