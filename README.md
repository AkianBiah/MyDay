# 🌸 My Day - Sistema de Gerenciamento de Tarefas 🌸

## 📑 Resumo do Projeto
O **My Day** é uma aplicação móvel profissional desenvolvida para a plataforma Android, projetada para otimizar a organização diária com uma estética "Kawaii" refinada. A aplicação integra funcionalidades de produtividade, acompanhamento temporal e dados meteorológicos em tempo real.

## 🏛 Decisão Arquitetural: Evolução Java para Kotlin/Compose
Este projeto foi concebido sob uma filosofia de modernização técnica sem abdicar das bases fundamentais da engenharia de software Android.

> [!IMPORTANT]
> **Interoperabilidade Java-Kotlin:** O projeto demonstra proficiência na linguagem **Java** através da implementação de modelos de dados nativos (pacote `com.example.myday.model.java`). Esta abordagem prova a plena compatibilidade do sistema com componentes Java, permitindo que a lógica de negócio central permaneça acessível e robusta.

> [!NOTE]
> **Modernização com Jetpack Compose:** A camada de interface de usuário (UI) utiliza **Jetpack Compose** e **Kotlin**, alinhando-se estritamente às diretrizes atuais do Google. Esta escolha técnica reflete o padrão contemporâneo da indústria, priorizando interfaces reativas e declarativas.

## 🌟 Funcionalidades Principais
- 🎨 **Interface Extreme Kawaii:** UI totalmente reformulada com tons pastéis (rosa/roxo), formas de "nuvem" (bubble shapes) e elementos decorativos de corações e estrelas para uma experiência visual única e encantadora.
- ✅ **Gestão de Tarefas:** Implementação de operações CRUD para atividades diárias com feedback visual intuitivo.
- ☁️ **Integração de Serviços:** Consumo de APIs REST via Retrofit para dados climáticos em tempo real.
- 📱 **Design Adaptativo:** Suporte a diferentes formatos de tela através de layouts adaptativos.

## 🛠 Especificações Técnicas
- **Ambiente de Desenvolvimento:** Android Studio (Versão Ladybug ou superior).
- **Sistema de Automação:** Gradle (Kotlin DSL).
- **Linguagens:** Java (Modelos de Dados) e Kotlin (UI/Lógica).
- **Arquitetura:** MVVM (Model-View-ViewModel) com Navigation 3.

## 🚀 Instruções de Execução
1. Importar o projeto no **Android Studio**.
2. Sincronizar os arquivos do **Gradle**.
3. Compilar e executar o módulo `:app` em um dispositivo com API 31 ou superior para suporte total a cores dinâmicas.

---
*Desenvolvido com rigor técnico e atenção aos detalhes estéticos.*
