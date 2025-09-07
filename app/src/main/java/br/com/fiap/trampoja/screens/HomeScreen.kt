package br.com.fiap.trampoja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.trampoja.R
import br.com.fiap.trampoja.components.JobCard
import br.com.fiap.trampoja.components.TrampojaTextField

@Composable
fun HomeScreen() {
    var search by remember { mutableStateOf("") }
    var filter by remember { mutableStateOf("") }
    var selectedJob by remember { mutableStateOf<Job?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp)
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
                    contentDescription = "Logo",
                    modifier = Modifier.size(30.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Icon(
                        imageVector = Icons.Outlined.Notifications,
                        contentDescription = "Notificações",
                        modifier = Modifier.size(30.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Column {
                TrampojaTextField(
                    value = search,
                    onValueChange = { search = it },
                    label = "Cargo, palavras-chave...",
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
                )

                TrampojaTextField(
                    value = filter,
                    onValueChange = { filter = it },
                    label = "Filtros",
                    modifier = Modifier.padding(bottom = 8.dp),
                    leadingIcon = { Icon(Icons.Filled.FilterAlt, contentDescription = null) }
                )
            }

            Column {
                Text(
                    text = "Vagas",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
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
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    JobCard(
                        title = "Engenheiro de Software Sênior",
                        company = "TechNova Solutions",
                        rating = 4.8,
                        location = "São Paulo/SP - Híbrido",
                        tag = "Afirmativa para mulheres na tecnologia",
                        tagColor = Color(0xFF8E24AA),
                        onClick = {
                            selectedJob = Job(
                                title = "Engenheiro de Software Sênior",
                                company = "TechNova Solutions",
                                rating = 4.8,
                                location = "São Paulo/SP - Híbrido",
                                description = "Procuramos um Engenheiro de Software Sênior para atuar...",
                                responsibilities = "Desenvolver aplicações escaláveis, apoiar arquitetura...",
                                requirements = "Graduação em TI, experiência sólida em backend...",
                                benefits = "Salário competitivo, plano de saúde, home office...",
                                tag = "Afirmativa para mulheres na tecnologia",
                                tagColor = Color(0xFF8E24AA)
                            )
                        }
                    )
                }

                item {
                    JobCard(
                        title = "Designer Gráfico",
                        company = "Kofin",
                        rating = 4.7,
                        location = "Remoto",
                        tag = "Afirmativa para pessoas negras",
                        tagColor = Color.Black,
                        onClick = {
                            selectedJob = Job(
                                title = "Designer Gráfico",
                                company = "Kofin",
                                rating = 4.7,
                                location = "Remoto",
                                description = "Atuar em design digital, criação de peças visuais...",
                                responsibilities = "Criação de materiais visuais, identidade visual...",
                                requirements = "Formação em design, domínio de ferramentas Adobe...",
                                benefits = "Vale-refeição, home office, plano de saúde...",
                                tag = "Afirmativa para pessoas negras",
                                tagColor = Color.Black
                            )
                        }
                    )
                }
            }
        }

        selectedJob?.let { job ->
            JobDetailDialog(
                job = job,
                onDismiss = { selectedJob = null }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    HomeScreen()
}
