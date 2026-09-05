# 🌌 My Day - Celestial Alarm System 🌌

## 📑 Resumo do Projeto
O **My Day** evoluiu de um simples gerenciador de tarefas para um **Sistema de Alarme Celestial** sofisticado para Android. Com uma estética Roxo Escuro e Místico, a aplicação foca em rotinas avançadas e despertar personalizado, integrando o ciclo solar e lunar diretamente na experiência do usuário.

## 🏛 Decisão Arquitetural: Alarme e Rotinas
O projeto utiliza componentes fundamentais do ecossistema Android para garantir precisão e confiabilidade nos despertadores.

> [!IMPORTANT]
> **Gestão de Alarmes de Sistema:** O My Day utiliza o `AlarmManager` para agendar despertadores precisos que funcionam mesmo quando o app está em segundo plano, coordenados por `BroadcastReceiver` para disparar notificações e áudios no momento exato.

> [!NOTE]
> **Modernização com Jetpack Compose & Room:** A interface é 100% **Jetpack Compose**, enquanto o armazenamento de rotinas e configurações é gerenciado via **Room Database**, garantindo persistência robusta.

## 🌟 Funcionalidades Principais
- 🎨 **Style UI:** Interface encantadora com tons pastéis e místicos, efeito de **Floating Cards** (sombras suaves e bordas de 32dp) e elementos decorativos.
- ⏰ **Relógio Celestial Dinâmico:** Exibição do tempo no formato **12h (AM/PM)** com transições visuais que se adaptam ao horário:
    - **Dia:** Sol radiante e nuvens suaves.
    - **Noite:** Lua mística e estrelas cintilantes.
- 📅 **Gestão de Rotinas e Alarmes:**
    - Criação, edição e exclusão de rotinas personalizadas.
    - Lógica de agendamento inteligente via `AlarmManager`, incluindo a opção **"Weekend Only"** (Apenas Fins de Semana).
- 🔔 **Notificações e Áudio:** Sistema de alerta sonoro e notificações integradas para garantir o cumprimento das rotinas.
- ✅ **Gestão de Tarefas:** Operações CRUD completas para atividades diárias com feedback visual.
- ☁️ **Clima em Tempo Real:** Integração com APIs meteorológicas via Retrofit para dados precisos.
- 🌐 **Multi-idioma:** Suporte nativo para Português (PT), Inglês (EN) e Espanhol (ES).

## 🛠 Especificações Técnicas
- **Ambiente:** Android Studio Ladybug+.
- **Arquitetura:** MVVM (Model-View-ViewModel).
- **Persistência:** Room Database.
- **Serviços de Sistema:** AlarmManager & BroadcastReceivers.
- **Navegação:** Jetpack Navigation 3.
- **UI:** Jetpack Compose com Motion Graphics.

## 🚀 Instruções de Execução
1. Importar o projeto no **Android Studio**.
2. Sincronizar os arquivos do **Gradle**.
3. Adicionar as permissões de `SCHEDULE_EXACT_ALARM` se necessário.
4. Compilar e executar o módulo `:app` (API 31+ recomendado).

---
*Desenvolvido com magia e rigor técnico por Bianca.*
