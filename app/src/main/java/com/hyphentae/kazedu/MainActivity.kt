import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyphentae.kazedu.ui.theme.KazEDUTheme
import com.hyphentae.kazedu.R

 // Data class for a program item.
data class Program(
    val name: String,
    val description: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KazEDUTheme {
                // Remember the currently selected navigation index.
                var selectedIndex by remember { mutableIntStateOf(0) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavBar(
                            selectedIndex = selectedIndex,
                            onItemSelected = { selectedIndex = it }
                        )
                    }
                ) { innerPadding ->
                    // Display content based on the selected navigation item.
                    when (selectedIndex) {
                        0 -> ProgramListScreen() // Changed from SingleScreenExample() to ProgramListScreen()
                        1 -> Greeting("Поддержка", Modifier.padding(innerPadding))
                        2 -> Greeting("Страна", Modifier.padding(innerPadding))
                        3 -> Greeting("Кабинет", Modifier.padding(innerPadding))
                        else -> Greeting("Кабинет", Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavBar(selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text(text = stringResource(R.string.main)) },
            selected = selectedIndex == 0,
            onClick = { onItemSelected(0) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Info, contentDescription = "Support") },
            label = { Text(text = stringResource(R.string.support)) },
            selected = selectedIndex == 1,
            onClick = { onItemSelected(1) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Star, contentDescription = "Country") },
            label = { Text(text = stringResource(R.string.country)) },
            selected = selectedIndex == 2,
            onClick = { onItemSelected(2) }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text(text = stringResource(R.string.profile)) },
            selected = selectedIndex == 3,
            onClick = { onItemSelected(3) }
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name",
        modifier = modifier
    )
}

@Composable
fun ProgramListScreen() {
    // Example list of programs.
    val programs = listOf(
        Program("Математика", "Описание математической программы"),
        Program("Физика", "Описание физической программы"),
        Program("Химия", "Описание химической программы")
    )

    // LazyColumn to display the list.
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Specify the parameter name to choose the correct overload.
        items(items = programs) { program ->
            // Each item is wrapped in a Card with clickable modifier.
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        // Handle click (e.g., navigate to another screen).
                    }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = program.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = program.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Button(
                        onClick = {
                            // Handle button click (e.g., navigate to another screen).
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) { }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    KazEDUTheme {
        ProgramListScreen()
    }
}
