package br.com.fiap.trampoja.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.com.fiap.trampoja.components.utils.getTagEmoji

data class Job(
    val title: String,
    val company: String,
    val rating: Double,
    val location: String,
    val description: String,
    val responsibilities: String,
    val requirements: String,
    val benefits: String,
    val tag: String,
    val tagColor: Color
)

@Composable
fun JobDetailDialog(
    job: Job,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth(0.90f)   // ocupa quase toda largura
                .fillMaxHeight(0.90f), // ocupa quase toda altura
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()) // 🔹 tudo dentro do scroll
            ) {
                Text(
                    text = job.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                )

                Text("Empresa: ${job.company}", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Avaliação: ${job.rating}", fontWeight = FontWeight.Bold)
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Avaliação",
                        tint = Color(0xFFFFC107),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(18.dp)
                    )
                }

                Spacer(Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Localização: ${job.location}", fontWeight = FontWeight.Bold)
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Localização",
                        tint = Color.Red,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .size(18.dp)
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.primary
                )

                Text("Descrição:", fontWeight = FontWeight.Bold)
                Text(job.description, modifier = Modifier.padding(bottom = 24.dp))

                Text("Responsabilidades:", fontWeight = FontWeight.Bold)
                Text(job.responsibilities, modifier = Modifier.padding(bottom = 24.dp))

                Text("Requisitos:", fontWeight = FontWeight.Bold)
                Text(job.requirements, modifier = Modifier.padding(bottom = 24.dp))

                Text("Benefícios:", fontWeight = FontWeight.Bold)
                Text(job.benefits, modifier = Modifier.padding(bottom = 24.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { /* salvar */ },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = Color.White),

                        ) {
                        Text("Salvar")
                    }
                    Button(
                        onClick = { /* candidatar */ },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text("Candidatar-se")
                    }
                }

                Spacer(Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(job.tagColor, shape = RoundedCornerShape(8.dp))
                        .padding(vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${getTagEmoji(job.tag)} ${job.tag}",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
