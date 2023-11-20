RepoTrends

O aplicativo enumera os repositórios mais populares no GitHub que utilizam a linguagem Kotlin e têm o maior número de stars.

<h2>Arquitetura</h2>

O projeto segue a arquitetura MVVM (Model-View-ViewModel). Sua estrutura é organizada com uma abordagem modular, onde cada funcionalidade é encapsulada em seu próprio módulo. Além da modularização por funcionalidade, também há uma segregação adicional por meio de módulos do tipo foundation (core/navigation/designsystem).

<h2>Módulos</h2>

![Untitled Diagram drawio](https://github.com/Igorhleite/android-repo-stars/assets/56982442/a35469ff-aaf5-4d78-a85c-c31d2a66c080)


O projeto está dividido nos seguintes módulos:

▶app: O módulo principal da aplicação Android.

▶feature:home: O módulo de funcionalidade Home exibe uma lista dos repositórios com mais stars no GitHub na linguagem kotlin.

▶feature:detail: O módulo de funcionalidade Detail exibe os detalhes de um repositório especifico.

▶foundation:core: O módulo central contém utilitários compartilhados, extensões e funcionalidades comuns.

▶foundation:navigation: O módulo de navegação fornece deep links e componentes de navegação.

▶foundation:designsystem: O módulo de interface do usuário contém componentes de IU personalizados e temas.

<h2>Tecnologias Utilizadas</h2>

* [Coil - Image Loader](https://coil-kt.github.io/coil/getting_started/)
* [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=pt-br)
* [Retrofit](https://square.github.io/retrofit/)
* [Gson](https://github.com/google/gson)
* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [Room](https://developer.android.com/training/data-storage/room)
* [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview?hl=pt-br)
* [Remote Mediator](https://developer.android.com/reference/kotlin/androidx/paging/RemoteMediator?hl=pt-br)
* [Mockito](https://mockk.io/)
* [Kotlinx-Couroutines-Test](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-test/)


<h2>Como Começar</h2>

Para executar o projeto localmente, siga estas etapas:

Clone o repositório:

```
git clone https://github.com/Igorhleite/android-repo-stars.git
```
<h4>Opcional</h4>

Abra o projeto no Android Studio.

<b>Abra o arquivo 'token.properties' localizado na raiz do projeto e atribua um token de acesso válido (gerado pelo GitHub) à variável ACCESS_TOKEN.</b>

Observação: A API do GitHub tem uma restrição de 10 requisições por minuto (60 por hora) para acesso não autenticado.

Compile e execute o aplicativo em um emulador ou dispositivo físico.
