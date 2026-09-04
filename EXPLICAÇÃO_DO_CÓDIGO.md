# 💻 Explicação do Código-Fonte - My Day

Este documento fornece uma visão detalhada das camadas de código e das implementações técnicas que compõem o **My Day**.

---

## 1. Camada de Dados (`com.example.myday.data`)

### 📦 Entidades (Room)
- **`Alarm.kt` & `Routine.kt`:** Definem as tabelas do banco de dados SQLite. O `Alarm` armazena o horário (String HH:mm), o rótulo e se é exclusivo para fins de semana.
- **`AppDatabase.kt`:** A classe central que gerencia o banco de dados Room, utilizando `fallbackToDestructiveMigration()` para facilitar atualizações durante o desenvolvimento.

### 🛠 Repositórios
- **`AlarmRepository.kt`:** É o cérebro da lógica de agendamento. Ele não apenas salva no banco, mas interage com o `AlarmManager` do Android para programar o alarme no sistema operacional.
- **`WeatherRepository.kt`:** Gerencia a busca de dados meteorológicos via Retrofit, tratando estados de carregamento e erros.

---

## 2. Camada de Interface (UI - `com.example.myday.ui`)

### 🎨 Jetpack Compose (`Screens.kt` & `AppComponents.kt`)
- **`CelestialClock`:** Um componente customizado que calcula a posição do sol/lua e altera o gradiente de fundo dinamicamente usando `LaunchedEffect`.
- **`AppBackground`:** Um wrapper que aplica o estilo visual "místico" (glassmorphism e gradientes) em todas as telas.

### 🕹 ViewModels
- **`AlarmViewModel.kt` & `RoutineViewModel.kt`:** Mantêm o estado da UI reativo. Eles expõem `StateFlow`, garantindo que qualquer alteração no banco de dados reflita instantaneamente na tela sem a necessidade de recarregar a página.
- **`LanguageViewModel.kt`:** Gerencia a internacionalização (i18n) em tempo real, permitindo a troca de idioma sem reiniciar o app.

---

## 3. Serviços de Sistema (`com.example.myday.receiver`)

### 🔔 `AlarmReceiver.kt`
Este é um **BroadcastReceiver**. Sua função é "acordar" o aplicativo quando o alarme agendado no `AlarmManager` dispara.
- **Notificação:** Ele constrói e exibe uma notificação de alta prioridade com som e vibração.
- **Persistência:** Ele também intercepta o evento de `BOOT_COMPLETED` para reagendar todos os alarmes caso o celular seja reiniciado.

---

## 4. Injeção de Dependências (`ViewModelFactory.kt`)

Como o projeto preza pela leveza, utilizamos uma **Factory Manual**.
- **Função:** Ela cria instâncias dos ViewModels injetando as dependências necessárias (Repositórios e Daos), garantindo que os ViewModels não "saibam" como criar o banco de dados, seguindo o princípio de responsabilidade única.

---

## 5. Navegação (`com.example.myday.navigation`)

### 🗺 `MyDayNavHost.kt`
Utiliza a nova biblioteca **Navigation 3**.
- **Diferencial:** Gerencia a troca de telas através de uma `backStack` reativa, permitindo transições suaves entre a Home, a tela de Alarmes e as Configurações.

---
*Este documento serve como um guia para desenvolvedores que desejam entender a lógica por trás da magia do My Day.*
