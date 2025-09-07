package br.com.fiap.trampoja.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.trampoja.R

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
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

            Column(

            ) {
                Spacer(modifier = Modifier.height(32.dp))
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(R.drawable.avatar),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(size = 100.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Matheus Adriel",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(bottom = 4.dp)
                        )
                        Text("matheusadrieldsc@gmail.com", fontWeight = FontWeight.Normal, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
                Column {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Column {
                        Text("Informações Pessoais", fontWeight = FontWeight.Bold , style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                        ) {
                            Text("Telefone: ", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge
                            )
                            Text("(19)99723-2710", style = MaterialTheme.typography.bodyLarge)
                        }
                        Row(
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                        ) {
                            Text("Localização: ", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                            Text("São Paulo/SP", style = MaterialTheme.typography.bodyLarge)
                        }
                        Row {
                            Text(
                                buildAnnotatedString {
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("Biografia: ")
                                    }
                                    withStyle(
                                        style = SpanStyle(
                                            color = Color.DarkGray,
                                            fontStyle = FontStyle.Italic
                                        )
                                    ) {
                                        append("“Formado inicialmente em Design, descobri na tecnologia uma combinação ideal de criatividade e lógica, o que motivou minha transição de carreira. E atualmente, dedicado ao aprendizado contínuo, busco crescimento em um ambiente colaborativo e diverso que permita e impulsione o autodesenvolvimento, tanto intelectual quanto técnico.”")
                                    }

                                },
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Column {
                        Text("Inclusão e Diversidade", fontWeight = FontWeight.Bold , style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(8.dp))
                        Column {
                            val options = listOf(
                                "Pessoa com deficiência ♿",
                                "Refugiado 🌍",
                                "LGBTQIA+ 🏳️‍🌈",
                                "Mulher em STEM 👩‍🔬",
                                "Outros"
                            )
                            val checkedStates = remember { mutableStateMapOf<String, Boolean>() }
                            options.forEach { option ->
                                if (checkedStates[option] == null) checkedStates[option] = false
                            }
                            Column {
                                options.forEach { option ->
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Checkbox(
                                            checked = checkedStates[option] ?: false,
                                            onCheckedChange = { checkedStates[option] = it }
                                        )
                                        Text(option)
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Column {
                        Text("Preferências de Trabalho", fontWeight = FontWeight.Bold , style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                        ) {
                            Text("Tipo de vaga: ", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge
                            )
                            Text("Remoto", style = MaterialTheme.typography.bodyLarge)
                        }
                        Row(
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                        ) {
                            Text("Áreas de interesse: ", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                            Text("Desenvolvedor de Software", style = MaterialTheme.typography.bodyLarge)
                        }
                        Row(
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                        ) {
                            Text("Disponibilidade: ", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyLarge)
                            Text("Estágio", style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* ação aqui */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFCDD2),
                            contentColor = Color(0xFFB71C1C)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color(0xFFEF5350))
                    ) {
                        Text("Excluir conta")
                    }
                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(18),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
                    ) {
                        Text("Salvar", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfileScreenPreview() {
    ProfileScreen()
}
