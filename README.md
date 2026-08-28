# 🌸 My Day - Sistema de Gerenciamento de Tarefas 🌸

## 📑 Resumo do Projeto
O **My Day** é uma aplicação móvel profissional desenvolvida para a plataforma Android, projetada para otimizar a organização diária com uma estética "Kawaii" refinada (Bianca Style). A aplicação integra funcionalidades de produtividade, acompanhamento temporal e dados meteorológicos em tempo real, agora com suporte multi-idioma e interface dinâmica.

## 🏛 Decisão Arquitetural: Evolução Java para Kotlin/Compose
Este projeto foi concebido sob uma filosofia de modernização técnica sem abdicar das bases fundamentais da engenharia de software Android.

> [!IMPORTANT]
> **Interoperabilidade Java-Kotlin:** O projeto demonstra proficiência na linguagem **Java** através da implementação de modelos de dados nativos na classe [Task.java](file:///C:/Users/Usuário/AndroidStudioProjects/MyDay/app/src/main/java/com/example/myday/model/java/Task.java). Esta abordagem atende requisitos acadêmicos e prova a plena compatibilidade do sistema com componentes Java legados ou corporativos.

> [!NOTE]
> **Modernização com Jetpack Compose:** A camada de interface de usuário (UI) utiliza **Jetpack Compose** e **Kotlin**, utilizando **Navigation 3** para um fluxo de telas moderno e eficiente.

## 🌟 Funcionalidades Principais
- 🎨 **Style UI:** Interface com tons pastéis, efeito de **Floating Cards** (sombras suaves e bordas de 32dp) e elementos decorativos encantadores.
- 🌅 **Céu Ilustrado Dinâmico:** Fundo que se adapta ao horário do dia:
    - **Dia:** Sol e nuvens suaves.
    - **Noite:** Lua e estrelas cintilantes.
- 🧭 **Navegação Estruturada:** Divisão clara entre tela de Início (Home), Gestão de Tarefas (Tasks), Clima (Weather) e Calendário.
- ✅ **Gestão de Tarefas:** Operações CRUD completas para atividades diárias com feedback visual.
- ☁️ **Clima em Tempo Real:** Consumo de API via Retrofit para dados meteorológicos precisos.
- 🌐 **Multi-idioma:** Suporte nativo para Português (PT), Inglês (EN) e Espanhol (ES).

## 🛠 Especificações Técnicas
- **Ambiente:** Android Studio Ladybug+.
- **Arquitetura:** MVVM (Model-View-ViewModel).
- **Navegação:** Jetpack Navigation 3.
- **Async:** Kotlin Coroutines & StateFlow.
- **Network:** Retrofit & Coil (Imagens).

## 🚀 Instruções de Execução
1. Importar o projeto no **Android Studio**.
2. Sincronizar os arquivos do **Gradle**.
3. Compilar e executar o módulo `:app` (API 31+ recomendado).

---
*Desenvolvido com rigor técnico e atenção aos detalhes estéticos por Bianca.*
