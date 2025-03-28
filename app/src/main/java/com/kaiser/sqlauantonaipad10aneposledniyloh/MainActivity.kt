package com.kaiser.sqlauantonaipad10aneposledniyloh

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaiser.sqlauantonaipad10aneposledniyloh.ui.theme.Belka
import com.kaiser.sqlauantonaipad10aneposledniyloh.ui.theme.SQLAUAntonaIpad10ANePosledniyLohTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    init {
        context = this
    }

    companion object{
        private var context:MainActivity ?= null
        fun getContext():Context{
            return context!!.applicationContext
        }
    }
    val arrayMockBelka = ArrayList<Belka>().apply {
        add(Belka(123, "Black", "Murka"))
        add(Belka(124, "Black", "Murka"))
        add(Belka(125, "Black", "Murka"))
    }


    val belkaMutableState = MutableStateFlow<List<Belka>>(arrayMockBelka)
    val _belkaState = belkaMutableState.asStateFlow()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SQLAUAntonaIpad10ANePosledniyLohTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {

        var belkaText by remember{ mutableStateOf("")}
        var id by remember{ mutableStateOf("")}
        var arrayBelka = _belkaState.collectAsState().value


        Column(
            modifier=Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = belkaText, onValueChange = {it -> belkaText=it})
            Button(onClick = {
                GlobalScope.launch {
                    SingletoneDB.db.belkaDAO().insertBelka(Belka(0, "Black", belkaText))
                    belkaMutableState.value = SingletoneDB.db.belkaDAO().grtAllBelka()
                }
            }) {
                Text(text = "Insert Belka")
            }
            TextField(value = id, onValueChange = {it -> id=it})
            Button(onClick = {
                GlobalScope.launch {
                    SingletoneDB.db.belkaDAO().updateBelka(Belka(id.toInt(), "White", belkaText))
                    belkaMutableState.value = SingletoneDB.db.belkaDAO().grtAllBelka()
                }
            }) {
                Text(text = "Update Belka")
            }
            TextField(value = id, onValueChange = {it -> id=it})
            Button(onClick = {
                GlobalScope.launch {
                    SingletoneDB.db.belkaDAO().deleteBelka(Belka(id.toInt(), "Black", belkaText))
                    belkaMutableState.value = SingletoneDB.db.belkaDAO().grtAllBelka()
                }
            }) {
                Text(text = "Delete Belka")
            }


            LazyColumn {
                items(arrayBelka){belka ->
                    Card(
                        modifier = Modifier.size(200.dp, 100.dp)
                    ) {
                        Text(text = belka.id.toString())
                        Text(text = belka.name)
                        Text(text = belka.taleColor)
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        SQLAUAntonaIpad10ANePosledniyLohTheme {
            Greeting("Android")
        }
    }
}

