# 📚 Documentação Técnica - My Day (Celestial Alarm)

## 1. Introdução
O **My Day** é um Sistema de Alarme Celestial projetado para transformar a rotina de despertar em uma experiência mística e eficiente. Ao contrário de apps de tarefas convencionais, o My Day foca na gestão de tempo através de alarmes precisos e rotinas inteligentes, utilizando uma interface expressiva e componentes de sistema de baixo nível para máxima confiabilidade.

## 2. Arquitetura do Sistema
O aplicativo segue o padrão **MVVM**, expandido para lidar com serviços de sistema e persistência local.

- **View (Jetpack Compose):** UI declarativa que utiliza animações fluidas e temas dinâmicos baseados no formato 12h.
- **ViewModel:** Orquestra a lógica de alarmes, conversão de horários (AM/PM) e estados da UI.
- **Model (Room):** Banco de dados SQLite para armazenamento de rotinas, dias da semana e preferências.
- **System Services:** 
    - **AlarmManager:** Responsável pelo agendamento de eventos no sistema Android.
    - **BroadcastReceiver:** Escuta os gatilhos do sistema para iniciar notificações de áudio e atualizações de estado.

## 3. Escolhas de Design (Bianca Style)
A interface foi projetada sob a estética "Bianca Style", que une sofisticação e misticismo:
- **Paleta de Cores (Dualidade Celestial):**
    - **Modo Dia (Rose Quartz):** Utiliza tons de quartzo rosa e pastéis, transmitindo suavidade e clareza.
    - **Modo Noite (Amethyst & Midnight):** Foca em tons de ametista, violeta e preto profundo, criando uma atmosfera mística e relaxante.
- **Floating Cards & Glassmorphism:** Elementos de interface com bordas arredondadas (32dp) e transparências que criam um efeito de profundidade, assemelhando-se a vidros flutuantes.
- **Relógio Celestial:** O ponto focal da aplicação, que alterna entre o Sol (AM) e a Lua (PM) com gradientes dinâmicos que acompanham o ciclo natural do tempo.

## 4. Tecnologias Utilizadas
- **Kotlin:** Linguagem base para toda a lógica e UI.
- **Jetpack Compose:** Framework para construção de interface moderna.
- **Room Persistence:** Para gerenciamento local de rotinas e alarmes.
- **AlarmManager API:** Para agendamento exato de notificações e despertadores.
- **BroadcastReceivers:** Para interceptar alarmes e gerenciar notificações de áudio em background.
- **Navigation 3:** Gerenciamento de navegação entre Home, Rotinas e Clima.
- **Coroutines & StateFlow:** Gerenciamento de fluxos de dados assíncronos e reatividade da UI.

## 5. Gestão de Rotinas
O sistema permite uma configuração granular das rotinas:
- **CRUD de Rotinas:** Possibilidade de criar, editar e agora **excluir** rotinas existentes.
- **Lógica Temporal:** Suporte ao formato 12h (AM/PM) em toda a aplicação.
- **Agendamento Inteligente:** Implementação de filtros como "Weekend Only", permitindo que o usuário define alarmes específicos para sábados e domingos de forma simples.

## 6. Processo de Desenvolvimento
1. **Transição de Escopo:** Migração de "Tarefas" para "Alarmes" como núcleo do app.
2. **Implementação do Room:** Estruturação das entidades de `Routine` e `Alarm`.
3. **Integração com Sistema:** Configuração do `AlarmManager` e permissões de alarmes exatos.
4. **Refatoração Visual:** Implementação do tema Roxo Escuro e componentes de vidro (Glassmorphism).
5. **Lógica AM/PM:** Ajuste do relógio central e seletores de horário para o padrão 12h.
6. **Polimento:** Adição de stickers animados e efeitos sonoros para os alarmes ativos.

## 7. Compatibilidade de Dispositivos
Para garantir a melhor experiência e segurança, o My Day possui as seguintes especificações de compatibilidade:

- **Versão Mínima do Android:** Android 7.0 (API nível 24 - Nougat) ou superior.
- **Versão Alvo (Target):** Otimizado para Android 15 (API nível 37).
- **Tipos de Dispositivo:**
    - **Smartphones:** Interface responsiva adaptada para diversos tamanhos de tela.
    - **Tablets:** Layout adaptável que aproveita o espaço adicional para exibir os "Floating Cards" de forma organizada.
- **Requisitos de Sistema:**
    - Suporte a `SCHEDULE_EXACT_ALARM` para despertar com precisão.
    - Conexão com a Internet (apenas para a funcionalidade de clima em tempo real).

---
*Este documento reflete a versão "Celestial Alarm" do projeto My Day.*
