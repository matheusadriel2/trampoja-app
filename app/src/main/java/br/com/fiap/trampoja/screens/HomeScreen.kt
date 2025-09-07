package br.com.fiap.trampoja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.trampoja.R
import br.com.fiap.trampoja.ui.theme.TrampojaTheme
import br.com.fiap.trampoja.components.TrampojaTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import br.com.fiap.trampoja.components.JobCard

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.logo2),
                    contentDescription = "Logo alternativo",
                    modifier = Modifier.size(30.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Ícone de notificações",
                        modifier = Modifier.size(30.dp),
                    )
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Ícone de menu",
                        modifier = Modifier.size(30.dp),
                    )
                }
            }

            Column {
                var search by remember { mutableStateOf("") }
                var filter by remember { mutableStateOf("") }

                TrampojaTextField(
                    value = search,
                    onValueChange = { search = it },
                    label = "Cargo, palavras-chave...",
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Pesquisar")
                    }
                )

                TrampojaTextField(
                    value = filter,
                    onValueChange = { filter = it },
                    label = "Filtros",
                    modifier = Modifier.padding(bottom = 8.dp),
                    leadingIcon = {
                        Icon(Icons.Filled.FilterAlt, contentDescription = "Filtro")
                    }
                )
            }

            Column {
                Text(
                    text = "Vagas",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                )

                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp) // espaço entre cards
            ) {
                item {
                    JobCard(
                        title = "Gerente Administrativo",
                        company = "Kofin",
                        rating = 4.9,
                        location = "São Paulo/SP",
                        tag = "Afirmativa para pessoas com deficiência",
                        tagColor = Color(0xFF1565C0)
                    )
                }
                item {
                    JobCard(
                        title = "Designer Gráfico",
                        company = "Kofin",
                        rating = 4.7,
                        location = "Remoto",
                        tag = "Afirmativa para pessoas negras",
                        tagColor = Color.Black
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true) @Composable private fun HomeScreenPreview() { TrampojaTheme { HomeScreen() } }