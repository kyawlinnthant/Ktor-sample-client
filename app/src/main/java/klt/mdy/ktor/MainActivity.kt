package klt.mdy.ktor

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import klt.mdy.ktor.data.remote.ApiService
import klt.mdy.ktor.data.remote.dto.ArticleData
import klt.mdy.ktor.ui.theme.KtorTheme

class MainActivity : ComponentActivity() {

    private val client = ApiService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val news = produceState<List<ArticleData>>(
                initialValue = emptyList(),
                producer = {
                    value = client.getTopHeadlines().articles
                }
            )

            LaunchedEffect(key1 = true) {
                val status = client.getTopHeadlines().status
                Log.e("status",status)
            }

            KtorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Greeting(news.value.size.toString())
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = name)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    KtorTheme {
        Greeting(BuildConfig.API_KEY)
    }
}