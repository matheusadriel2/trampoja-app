package br.com.fiap.trampoja.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.trampoja.R
import br.com.fiap.trampoja.components.SocialButton
import br.com.fiap.trampoja.components.TrampojaTextField
import br.com.fiap.trampoja.ui.theme.TrampojaTheme

@Composable
fun LoginScreen(
    onLogin: () -> Unit = {},
    onGoToRegister: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logotipo TrampoJA",
                modifier = Modifier.size(120.dp).padding(bottom = 32.dp)
            )
            Text("Entrar", fontSize = 20.sp, modifier = Modifier.align(Alignment.Start).padding(bottom = 4.dp))

            var email by remember { mutableStateOf("") }
            var senha by remember { mutableStateOf("") }
            var lembrarSenha by remember { mutableStateOf(false) }

            TrampojaTextField(value = email, onValueChange = { email = it }, label = "E-mail", keyboardType = KeyboardType.Email)
            TrampojaTextField(value = senha, onValueChange = { senha = it }, label = "Senha", isPassword = true)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                Checkbox(
                    checked = lembrarSenha,
                    onCheckedChange = { lembrarSenha = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        uncheckedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                    )
                )
                Text(text = "Lembrar senha", color = MaterialTheme.colorScheme.onSurface)
            }

            Button(
                onClick = { onLogin() },
                modifier = Modifier.fillMaxWidth().height(48.dp),
                shape = RoundedCornerShape(18),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
            ) { Text("Continuar", color = MaterialTheme.colorScheme.onPrimary) }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text("Ou", modifier = Modifier.padding(horizontal = 20.dp))
                HorizontalDivider(modifier = Modifier.weight(1f))
            }

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                SocialButton(R.drawable.google, "Google")
                SocialButton(R.drawable.facebook, "Facebook")
                SocialButton(R.drawable.apple, "Apple")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Esqueceu sua senha?", color = MaterialTheme.colorScheme.onSurface, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurface)) { append("Não tem conta? ") }
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) { append("Cadastre-se") }
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().clickable { onGoToRegister() }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginPreview() {
    TrampojaTheme { LoginScreen() }
}
