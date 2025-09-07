package br.com.fiap.trampoja.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.trampoja.components.utils.getLocationEmoji
import br.com.fiap.trampoja.ui.theme.TrampojaTheme
import br.com.fiap.trampoja.components.utils.getTagEmoji

@Composable
fun JobCard(
    title: String,
    company: String,
    rating: Double,
    location: String,
    tag: String,
    tagColor: Color,
    onSaveClick: () -> Unit = {},
    onIgnoreClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Text(
                        text = buildAnnotatedString {
                            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("Empresa: ") }
                            withStyle(SpanStyle(fontWeight = FontWeight.Normal)) { append(company) }
                        },
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("Avaliação: ") }
                                withStyle(SpanStyle(fontWeight = FontWeight.Normal)) { append(rating.toString()) }
                            },
                            style = MaterialTheme.typography.titleMedium
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Avaliação",
                            tint = Color(0xFFFFC107),
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append("Localização: ") }
                                withStyle(SpanStyle(fontWeight = FontWeight.Normal)) {
                                    append("$location ${getLocationEmoji(location)}")
                                }
                            },
                            style = MaterialTheme.typography.titleMedium
                        )

                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(tagColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${getTagEmoji(tag)} $tag",
                        color = Color.White,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = onSaveClick) {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Salvar vaga",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onIgnoreClick) {
                    Icon(
                        imageVector = Icons.Default.Block,
                        contentDescription = "Ignorar vaga",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JobCardListPreview() {
    TrampojaTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            JobCard(
                title = "Gerente Administrativo",
                company = "Kofin",
                rating = 4.9,
                location = "São Paulo/SP",
                tag = "Afirmativa para pessoas com deficiência",
                tagColor = Color(0xFF1565C0)
            )
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
