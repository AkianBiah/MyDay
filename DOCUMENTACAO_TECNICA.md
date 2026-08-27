# 📚 Documentação Técnica - My Day

## 1. Introdução
O **My Day** é um assistente pessoal projetado para transformar a organização diária em uma experiência visualmente agradável e tecnicamente robusta. O objetivo principal é fornecer uma ferramenta de produtividade que integre tarefas, calendário e informações climáticas em um único ecossistema intuitivo, utilizando as tecnologias mais recentes do desenvolvimento Android.

## 2. Arquitetura do Sistema
O aplicativo segue o padrão arquitetural **MVVM (Model-View-ViewModel)**, garantindo a separação de responsabilidades e facilitando a testabilidade e manutenção.

- **View (Jetpack Compose):** Interface declarativa que reage às mudanças de estado.
- **ViewModel:** Gerencia o estado da UI e a lógica de negócio, sobrevivendo a mudanças de configuração.
- **Model:** Camada de dados que inclui repositórios e serviços de rede.
- **Navigation 3:** Utilizado para gerenciar a navegação entre telas de forma escalável e tipada.

## 3. Escolhas de Design (Bianca Style)
A interface foi construída seguindo a estética **Kawaii**, caracterizada por:
- **Paleta de Cores:** Uso predominante de tons pastéis (lavanda, rosa suave, azul céu).
- **Floating Cards:** Componentes de interface com cantos arredondados (32dp) e sombras suaves que dão a sensação de profundidade e leveza.
- **Temas Dinâmicos (Dia/Noite):** O app detecta o horário do sistema para alternar o fundo ilustrado:
    - **Modo Dia:** Ilustrações de Sol e nuvens flutuantes.
    - **Modo Noite:** Ilustrações de Lua e estrelas brilhantes.

## 4. Tecnologias Utilizadas
- **Kotlin:** Linguagem principal para toda a lógica de aplicação e interface.
- **Jetpack Compose:** Framework moderno para construção de UI nativa.
- **Navigation 3:** A evolução do sistema de navegação Android para Compose.
- **Retrofit:** Biblioteca para consumo da API de clima, tratando requisições HTTP de forma eficiente.
- **Coroutines & StateFlow:** Utilizados para operações assíncronas, como a atualização do relógio em tempo real e o carregamento de dados climáticos sem bloquear a thread principal.
- **Coil:** Carregamento de imagens e ícones meteorológicos de forma assíncrona.

## 5. Interoperabilidade e Requisitos Acadêmicos
Para atender aos requisitos de proficiência em linguagens clássicas, o projeto mantém a classe `Task.java` no pacote `com.example.myday.model.java`. 
- **Por que Java?** Demonstra que o sistema é capaz de integrar modelos de dados legados ou bibliotecas Java existentes, garantindo que o núcleo da lógica de negócio possa ser compartilhado entre diferentes módulos se necessário.

## 6. Processo de Desenvolvimento
1. **Planejamento:** Definição das funcionalidades core (Tarefas e Clima).
2. **Design UI:** Criação do "Bianca Style" e prototipagem dos componentes Kawaii.
3. **Estruturação Base:** Configuração do Navigation 3 e injeção de dependência via ViewModels.
4. **Implementação de Recursos:** Desenvolvimento das telas de CRUD de tarefas, integração com API de Clima e sistema de idiomas.
5. **Refinamento:** Adição dos efeitos dinâmicos de fundo e polimento visual dos cards.
6. **Documentação:** Criação deste guia técnico e atualização do README para entrega final.

---
*Este documento serve como guia para desenvolvedores e revisores técnicos do projeto My Day.*
