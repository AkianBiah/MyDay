# 🌌 My Day - Celestial Alarm System 🌌

Transforme sua rotina em uma jornada mística. O **My Day** não é apenas um despertador; é o seu guia celestial diário, projetado para harmonizar suas tarefas com o ritmo do cosmos.

---

## 📑 Sobre o Projeto
O **My Day** nasceu da necessidade de transformar o ato de acordar e gerenciar tarefas em algo encantador. Abandonando as interfaces genéricas, ele adota uma estética mística e profunda em tons de roxo, com elementos celestiais que se transformam ao longo do dia.

O coração do app é o seu **Sistema de Alarme Celestial**, que utiliza tecnologias nativas do Android para garantir que você nunca perca o ritmo, seja para tomar um remédio, começar sua leitura diária ou simplesmente despertar para um novo dia.

---

## 🌟 Funcionalidades Mágicas

### ⏰ Relógio Celestial Dinâmico
Sua tela inicial respira com o tempo. O relógio interativo muda visualmente de acordo com o horário:
- **Ciclo Solar (AM):** Tons quentes, sol radiante e nuvens suaves para energizar sua manhã.
- **Ciclo Lunar (PM):** Tons de roxo profundo, lua mística e estrelas cintilantes para acalmar sua noite.

### 📅 Gestão de Rotinas Inteligentes
Vá além das listas de tarefas comuns:
- **Agendamento Avançado:** Configure rotinas com a opção "Apenas Fins de Semana" ou repetições diárias.
- **Feedback Visual:** Conclua tarefas e veja o app celebrar com você através de efeitos de partículas (Sparkles).
- **Alarmes de Sistema:** Integração profunda com o `AlarmManager` para precisão absoluta, mesmo com o app fechado.

### ☁️ Previsão do Tempo Mística
Saiba se as estrelas estarão visíveis ou se as nuvens dominam o céu. Integração em tempo real com dados meteorológicos para planejar seu dia com precisão.

### 🎨 Design
- **UI Encantadora:** Floating Cards com sombras suaves e bordas arredondadas (32dp).
- **Stickers Animados:** Elementos celestiais que trazem vida e personalidade à interface.
- **Experiência Fluida:** Navegação moderna e gestos intuitivos.

### 🌐 Conexão Global
O My Day fala a sua língua. Suporte completo para:
- 🇧🇷 Português
- 🇺🇸 Inglês
- 🇪🇸 Espanhol

---

## 🔌 Conectividade e APIs

Para fornecer uma experiência dinâmica e robusta, o My Day se conecta às seguintes tecnologias e serviços:

- **[OpenWeatherMap API](https://openweathermap.org/api):** A fonte mística dos dados meteorológicos em tempo real, fornecendo temperatura, condições e ícones dinâmicos.
- **[Retrofit 2](https://square.github.io/retrofit/):** O cliente HTTP responsável por orquestrar a comunicação com a API de clima de forma eficiente e segura.
- **[Moshi](https://github.com/square/moshi):** O conversor JSON de última geração que traduz as respostas da web para objetos Kotlin compreensíveis pelo app.
- **[Coil (Compose)](https://coil-kt.github.io/coil/compose/):** A biblioteca de carregamento de imagens rápida e leve, utilizada para exibir os ícones celestiais do clima.
- **Android System APIs:**
    - **AlarmManager:** Para agendamentos de alta precisão.
    - **NotificationManager:** Para alertas e comunicações com o usuário.
    - **BroadcastReceiver:** Para reagir a eventos do sistema, como o reinício do dispositivo.

---

## 🛠 Especificações Técnicas

O My Day foi construído com o que há de mais moderno no desenvolvimento Android nativo:

- **Linguagem:** Kotlin 100%
- **Interface:** Jetpack Compose (Declarativa e Reativa)
- **Arquitetura:** MVVM (Model-View-ViewModel) para separação clara de responsabilidades.
- **Banco de Dados:** Room Database para persistência offline robusta.
- **Navegação:** Jetpack Navigation 3 (a versão mais recente da biblioteca).
- **Rede:** Retrofit + Moshi para consumo de APIs externas.
- **Serviços de Sistema:** AlarmManager, BroadcastReceivers e Notificações Customizadas.

---

## 🚀 Como Executar a Magia

1.  Clone este repositório.
2.  Abra no **Android Studio Ladybug (2024.2.1)** ou superior.
3.  Sincronize o projeto com o **Gradle**.
4.  (Opcional) Adicione sua API Key do OpenWeather no arquivo `local.properties`.
5.  Execute no seu dispositivo ou emulador (Recomendado API 31+).

---

## 🌙 Créditos
Desenvolvido com paixão, magia e rigor técnico por **Bianca**. ✨

---
*"Que suas estrelas guiem seus passos e seus alarmes despertem seus sonhos."*
