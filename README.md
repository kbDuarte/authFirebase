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
- Material Components

## Configuração do Firebase
1. Abra o [Firebase Console](https://console.firebase.google.com/) e crie ou selecione um projeto.
2. Registre um aplicativo Android com o ID `com.kduarte.authfirebase`.
3. Baixe o arquivo `google-services.json` e coloque-o na pasta `app/`.
4. No Firebase Console, acesse **Authentication > Método de login** e habilite **E-mail/senha**.
5. Abra o projeto no Android Studio, sincronize o Gradle e execute o aplicativo.

> O arquivo `google-services.json` não está incluído neste repositório porque é específico do projeto Firebase. O login depende desse arquivo e da ativação do provedor E-mail/senha.

## Documentação
- [FirebaseUI Auth para Android](https://firebase.google.com/docs/auth/android/firebaseui?hl=pt-br)
