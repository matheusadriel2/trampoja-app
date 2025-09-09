package br.com.fiap.trampoja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import br.com.fiap.trampoja.R
import br.com.fiap.trampoja.components.JobCard
import br.com.fiap.trampoja.components.TrampojaTextField
import br.com.fiap.trampoja.di.Job

@Composable
fun HomeScreen() {
    var query by remember { mutableStateOf("") }
    var selectedJob by remember { mutableStateOf<Job?>(null) }

    val allJobs = remember {
        listOf(
            Job(
                title = "Engenheiro(a) de Software",
                company = "TechNova",
                rating = 4.8,
                location = "São Paulo/SP • Híbrido",
                description = "Procuramos Engenheiro(a) para atuar em produtos digitais...",
                responsibilities = "Desenvolver aplicações escaláveis...",
                requirements = "Kotlin, Compose, REST...",
                benefits = "Plano de saúde, VR, Home office...",
                tag = "Afirmativa para mulheres",
                tagColor = Color(0xFF8E24AA)
            ),
            Job(
                title = "Designer Gráfico",
                company = "Kofin",
                rating = 4.7,
                location = "Remoto",
                description = "Criação de peças visuais, identidade...",
                responsibilities = "Criar materiais visuais...",
                requirements = "Figma/Adobe, portfólio...",
                benefits = "VR, home office, plano de saúde...",
                tag = "Afirmativa para pessoas negras",
                tagColor = Color.Black
            )
        )
    }

    val filteredJobs = remember(query, allJobs) {
        val q = query.trim()
        if (q.isBlank()) allJobs else allJobs.filter { j ->
            j.title.contains(q, true) ||
                    j.company.contains(q, true) ||
                    j.description.contains(q, true) ||
                    j.tag.contains(q, true) ||
                    j.location.contains(q, true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
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
                Icon(Icons.Outlined.Notifications, contentDescription = "Notificações", modifier = Modifier.size(30.dp))
                Icon(Icons.Default.Menu, contentDescription = "Menu", modifier = Modifier.size(30.dp))
            }
        }

        TrampojaTextField(
            value = query,
            onValueChange = { query = it },
            label = "Cargo, palavras-chave...",
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) }
        )

        Spacer(Modifier.height(8.dp))

        TrampojaTextField(
            value = "",
            onValueChange = {},
            label = "Filtros",
            leadingIcon = { Icon(Icons.Filled.FilterAlt, contentDescription = null) }
        )

        Spacer(Modifier.height(12.dp))

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

        Spacer(Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredJobs) { job ->
                JobCard(
                    title = job.title,
                    company = job.company,
                    rating = job.rating,
                    location = job.location,
                    tag = job.tag,
                    tagColor = job.tagColor,
                    onClick = { selectedJob = job }
                )
            }
        }
    }

    selectedJob?.let { job ->
        JobDetailDialog(job = job, onDismiss = { selectedJob = null })
    }
}
