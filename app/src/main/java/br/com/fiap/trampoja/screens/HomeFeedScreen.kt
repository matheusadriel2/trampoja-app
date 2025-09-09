package br.com.fiap.trampoja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.trampoja.R
import br.com.fiap.trampoja.components.JobCard
import br.com.fiap.trampoja.di.Job
import br.com.fiap.trampoja.viewmodel.JobsUiState
import br.com.fiap.trampoja.viewmodel.JobsViewModel
import br.com.fiap.trampoja.viewmodel.JobsViewModelFactory
import br.com.fiap.trampoja.viewmodel.FavoritesViewModel
import br.com.fiap.trampoja.data.model.JobSummary

@Composable
fun HomeFeedScreen() {
    val favVm: FavoritesViewModel = viewModel()
    val vm: JobsViewModel = viewModel(factory = JobsViewModelFactory())
    val state by vm.state.collectAsState()

    var query by remember { mutableStateOf("") }
    var selectedJob by remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(Unit) { vm.load() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
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
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            }
        }

        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
                vm.debouncedSearch(it)
            },
            label = { Text("Cargo, palavras-chave...") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { Text("Filtros") },
            leadingIcon = { Icon(Icons.Filled.FilterAlt, contentDescription = null) },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
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

        when (val s = state) {
            is JobsUiState.Loading -> {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            is JobsUiState.Error -> {
                Text(
                    text = s.message,
                    color = MaterialTheme.colorScheme.error
                )
            }
            is JobsUiState.Success -> {
                val jobs = s.jobs
                val filtered = if (query.isBlank()) jobs else jobs.filter {
                    it.title.contains(query, true) ||
                            it.company.contains(query, true) ||
                            it.location.contains(query, true)
                }

                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    itemsIndexed(filtered) { index, job ->
                        val summary = JobSummary(
                            id = "$index:${job.title}-${job.company}",
                            title = job.title,
                            company = job.company,
                            location = job.location,
                            salary = null,
                            remote = false
                        )
                        val isSaved = favVm.isFavorite(summary.id)

                        JobCard(
                            title = job.title,
                            company = job.company,
                            rating = job.rating,
                            location = job.location,
                            tag = job.tag,
                            tagColor = job.tagColor,
                            onClick = { selectedJob = job },
                            onSaveClick = { favVm.toggle(summary) },
                            isSaved = isSaved
                        )
                    }
                }
            }
            JobsUiState.Idle -> Unit
        }
    }

    selectedJob?.let { job ->
        JobDetailDialog(job = job, onDismiss = { selectedJob = null })
    }
}
