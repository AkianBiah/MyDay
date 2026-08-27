# Relatório Técnico de Desenvolvimento: Projeto My Day

## 1. Introdução
Este relatório documenta as escolhas técnicas e a arquitetura do software **My Day**, uma ferramenta de gerenciamento de tarefas para dispositivos Android. O objetivo primordial foi criar uma aplicação que unisse robustez técnica, conformidade com os padrões da indústria e uma interface de usuário altamente engajadora.

## 2. Decisão Arquitetural: Evolução de Java para Kotlin/Compose
Um dos pilares deste projeto é a demonstração de proficiência em linguagens fundamentais enquanto se adota a modernização necessária.

### 2.1. O Papel do Java
Reconhecendo a importância histórica e a robustez da linguagem **Java** no ecossistema Android, o projeto inclui modelos de dados nativos em Java (`com.example.myday.model.java.Task`). Esta escolha técnica visa:
- Demonstrar a **interoperabilidade** bidirecional entre Java e Kotlin.
- Garantir que a lógica de negócio possa ser mantida ou expandida por desenvolvedores Java sem fricção.
- Seguir os requisitos acadêmicos de proficiência em Java.

### 2.2. Adoção do Jetpack Compose (Kotlin)
A camada de UI foi desenvolvida em Kotlin utilizando Jetpack Compose. Esta decisão é justificada por:
- **Padrão Oficial:** O Google designou o Kotlin como linguagem prioritária e o Compose como o framework de UI moderno para Android.
- **Produtividade:** A natureza declarativa do Compose reduz a complexidade do código de interface em comparação com os layouts XML tradicionais.

## 3. Infraestrutura de Build e Ferramental
O projeto está estritamente alinhado com as exigências de ferramentas modernas:
- **Gradle:** Utilizado para gestão de dependências e automação de build, empregando as melhores práticas de scripts (Version Catalogs).
- **Android Studio:** Desenvolvido e testado na versão Ladybug, garantindo compatibilidade com os recursos mais recentes da IDE.

## 4. Conclusão
O **My Day** representa um equilíbrio entre a tradição do Java e a inovação do Kotlin/Compose. A arquitetura híbrida assegura que o projeto seja não apenas moderno e funcional, mas também tecnicamente fundamentado nas bases sólidas da programação Java.
