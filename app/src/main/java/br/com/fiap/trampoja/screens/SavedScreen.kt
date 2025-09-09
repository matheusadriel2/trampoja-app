package br.com.fiap.trampoja.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.trampoja.R
import br.com.fiap.trampoja.components.JobCard
import br.com.fiap.trampoja.viewmodel.FavoritesViewModel

@Composable
fun SavedScreen(
    vm: FavoritesViewModel = viewModel()
) {
    val favorites by vm.favorites.collectAsState()

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

        if (favorites.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Nenhuma vaga favoritada ainda.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favorites, key = { it.id }) { fav ->
                    JobCard(
                        title = fav.title,
                        company = fav.company,
                        rating = 4.5,
                        location = fav.location,
                        tag = "Favorito",
                        tagColor = MaterialTheme.colorScheme.primary,
                        onClick = { /* TODO: abrir detalhe da vaga */ },
                        onSaveClick = { vm.remove(fav.id) },
                        onIgnoreClick = { vm.remove(fav.id) },
                        isSaved = true
                    )
                }
            }
        }
    }
}
