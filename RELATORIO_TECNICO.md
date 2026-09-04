# 📝 Relatório Técnico de Desenvolvimento - My Day (Celestial Alarm)

## 1. Visão Geral
O **My Day** é uma aplicação Android nativa que transcende a funcionalidade de um simples gerenciador de tarefas, posicionando-se como um **Sistema de Alarme Celestial**. O projeto foca em oferecer uma experiência de usuário (UX) encantadora e mística, aliada a uma robustez técnica que utiliza componentes de baixo nível do sistema Android para garantir pontualidade e confiabilidade.

---

## 2. Escolhas Tecnológicas e Justificativas

### 2.1. Arquitetura: MVVM (Model-View-ViewModel)
Adotamos o padrão **MVVM** para garantir uma separação clara entre a lógica de negócio e a interface do usuário. 
- **Benefício:** Facilita a testabilidade e a manutenção do código, permitindo que a UI (Compose) reaja automaticamente às mudanças de estado nos ViewModels.

### 2.2. Interface: Jetpack Compose
A escolha do **Jetpack Compose** como framework de UI foi estratégica.
- **Justificativa:** A natureza declarativa do Compose permitiu a implementação rápida do "Bianca Style", com gradientes complexos, sombras suaves e animações celestiais que seriam significativamente mais difíceis de manter em XML tradicional.

### 2.3. Persistência: Room Database
Para o armazenamento de rotinas e alarmes, utilizamos o **Room**.
- **Solução:** O Room atua como uma camada de abstração sobre o SQLite, oferecendo verificação de consultas em tempo de compilação e integração nativa com Kotlin Coroutines (Flow), permitindo atualizações da UI em tempo real quando os dados no banco mudam.

### 2.4. Comunicação: Retrofit & Moshi
A integração com a API **OpenWeatherMap** é feita via **Retrofit**.
- **Escolha:** O Retrofit é o padrão da indústria para redes em Android. Combinado com o **Moshi** para a conversão de JSON, garantimos uma comunicação eficiente e segura com o servidor meteorológico.

---

## 3. Processo de Desenvolvimento e Evolução

O desenvolvimento do My Day seguiu um ciclo iterativo de modernização:

1.  **Fase de Fundação:** Inicialmente concebido como um gestor de tarefas simples, o projeto estabeleceu a base com Kotlin e Room.
2.  **Pivô de Escopo (Sistema Celestial):** Identificamos que a "mágica" do app residia no despertar. O foco mudou para alarmes, introduzindo o `AlarmManager`.
3.  **Refatoração Bianca Style:** A UI foi completamente redesenhada para adotar tons de roxo profundo e elementos celestiais dinâmicos (Sol/Lua).
4.  **Estabilização Técnica:** Implementação de uma `ViewModelFactory` manual para gerenciar dependências sem a sobrecarga de frameworks de DI (Dependency Injection) mais pesados, mantendo o projeto leve e didático.

---

## 4. Desafios e Soluções Adotadas

### 4.1. Precisão de Alarmes em Background
**Desafio:** Versões modernas do Android possuem restrições severas de bateria que podem atrasar alarmes.
**Solução:** Implementamos o `AlarmManager.setExactAndAllowWhileIdle`, garantindo que o alarme dispare no momento exato, mesmo que o dispositivo esteja em modo Doze. Utilizamos `BroadcastReceivers` para acordar o app e disparar as notificações sonoras.

### 4.2. Injeção de Dependências Manual
**Desafio:** Necessidade de passar repositórios para os ViewModels sem usar bibliotecas como Hilt ou Koin (para manter o projeto simples).
**Solução:** Criamos uma `ViewModelFactory` centralizada que provê as instâncias necessárias de `AppDatabase` e `Repository` para cada tela, garantindo que o ciclo de vida dos dados seja respeitado.

### 4.3. Gerenciamento de Conflitos (VCS)
**Desafio:** Durante o desenvolvimento colaborativo ou em múltiplas frentes, surgiram conflitos complexos de Git (non-fast-forward).
**Solução:** Utilizamos técnicas avançadas de `git rebase` para linearizar o histórico e resolver conflitos manualmente, garantindo que a versão final no GitHub fosse limpa e funcional.

---

## 5. Conclusão
O desenvolvimento do **My Day** demonstra como a engenharia de software pode se unir ao design artístico. Ao escolher tecnologias modernas como Compose e Room, e enfrentar desafios de sistema com APIs nativas do Android, o projeto resultou em um produto final que é tecnicamente sofisticado, performático e visualmente único.

---
*Relatório gerado em 04 de Setembro de 2026.*
