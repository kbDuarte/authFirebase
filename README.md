# AuthFirebase — Kotlin + FirebaseUI

Aplicativo Android de exemplo com autenticação usando FirebaseUI Auth.

## Funcionalidades
- Exibe a tela de autenticação quando não há sessão ativa.
- Permite entrar com e-mail e senha.
- Permite cadastrar novos usuários pela opção de criação de conta da FirebaseUI.
- Após autenticar, mostra uma tela inicial com o e-mail da conta.
- O botão **Sair** encerra a sessão e retorna ao fluxo de login.

## Tecnologias
- Kotlin
- Android View Binding
- Firebase Authentication
- FirebaseUI Auth
- Firebase BoM 35.1.0
- Material Components

## Configuração
O arquivo `app/google-services.json` deste projeto corresponde ao aplicativo Android registrado no Firebase com o identificador `com.kduarte.authfirebase`.

1. Abra o projeto no Android Studio e sincronize o Gradle.
2. No Firebase Console, acesse **Authentication > Método de login** e habilite **E-mail/senha**.
3. Execute o aplicativo.

## Documentação
- [FirebaseUI Auth para Android](https://firebase.google.com/docs/auth/android/firebaseui?hl=pt-br)
- [Configurar o Firebase em apps Android](https://firebase.google.com/docs/android/setup)
