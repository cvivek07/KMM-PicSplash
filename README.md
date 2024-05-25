# 💎 Compose Multiplatform - PicSplash App


# Application Scope
This is a simple application that let users browse, search and save beautiful images. The data is dynamically sourced from the [Unsplash Api](https://unsplash.com/documentation).

🗡️ This demonstrates modern Android development with Koin, Coroutines, Flow, Jetpack (Ktor, ViewModel), and Material Design based on Clean Architecture.

The app has a few screens located in multiple feature modules:

- Images list screen - displays staggered list of images.
- Image detail screen - display information about the selected image.
- Search photos screen - display a list of topics, collections. Users can search images.
- Saved photos screen - display list of images item which are saved/liked by the user.
- Profile screen - display user information.
  <br/><br/>
- 
<p>
  <img src="misc/image/1.png" width="200" height="450"  alt=""/>
  <img src="misc/image/2.png" width="200" height="450"  alt=""/>
  <img src="misc/image/3.png" width="200" height="450"  alt=""/>
  <img src="misc/image/4.png" width="200" height="450"  alt=""/>
  <img src="misc/image/5.png" width="200" height="450"  alt=""/>
  <img src="misc/image/6.png" width="200" height="450"  alt=""/>
  <img src="misc/image/7.png" width="200" height="450"  alt=""/>
</p>

## Tech-Stack
This project takes advantage of best practices and many popular libraries and tools in the Android ecosystem. Most of
the libraries are in the stable version unless there is a good reason to use non-stable dependency.

* Tech-stack
    * [100% Kotlin](https://kotlinlang.org/)
        + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
        + [Kotlin Flow](https://kotlinlang.org/docs/flow.html) - data flow across all app layers, including views
        + [Kotlin Symbol Processing](https://kotlinlang.org/docs/ksp-overview.html) - enable compiler plugins
        + [Kotlin Serialization](https://kotlinlang.org/docs/serialization.html) - parse [JSON](https://www.json.org/json-en.html)
    * [Ktor](https://ktor.io/) - networking
    * [Jetpack](https://developer.android.com/jetpack)
        * [Compose](https://developer.android.com/jetpack/compose) - modern, native UI kit
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when
          lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related
          data in a lifecycle-aware way
        * [Room Database](https://developer.android.com/kotlin/multiplatform/room) - store offline cache
    * [Koin](https://insert-koin.io/) - dependency injection (dependency retrieval)
* Modern Architecture
    * [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
    * Single activity architecture
    * MVVM + MVI (presentation layer)
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture)
      ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
      , [Kotlin Flow](https://kotlinlang.org/docs/flow.html)
    * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
* UI
    * Reactive UI
    * [Jetpack Compose](https://developer.android.com/jetpack/compose) - modern, native UI kit (used for Fragments)
    * [Material Design 3](https://m3.material.io/) - application design system providing UI components


## Architecture
**KMP-PicSplash-App** is based on the MVVM architecture and the Repository pattern, which follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).

![architecture](misc/image/figure0.png)

The overall architecture of **KMP-PicSplash-App** is composed of two layers; the UI layer and the data layer. Each layer has dedicated components and they have each different responsibilities, as defined below:

**Pokedex** was built with [Guide to app architecture](https://developer.android.com/topic/architecture), so it would be a great sample to show how the architecture works in real-world projects.


### Architecture Overview

![architecture](misc/image/figure1.png)

- Each layer follows [unidirectional event/data flow](https://developer.android.com/topic/architecture/ui-layer#udf); the UI layer emits user events to the data layer, and the data layer exposes data as a stream to other layers.
- The data layer is designed to work independently from other layers and must be pure, which means it doesn't have any dependencies on the other layers.

With this loosely coupled architecture, you can increase the reusability of components and scalability of your app.

### UI Layer

![architecture](misc/image/figure2.png)

The UI layer consists of UI elements to configure screens that could interact with users and [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that holds app states and restores data when configuration changes.

### Data Layer

![architecture](misc/image/figure3.png)

The data Layer consists of repositories, which include business logic, such as querying data from the local database and requesting remote data from the network. It is implemented as an offline-first source of business logic and follows the [single source of truth](https://en.wikipedia.org/wiki/Single_source_of_truth) principle.<br>

**KMP-PicSplash-App** is an offline-first app is an app that is able to perform all, or a critical subset of its core functionality without access to the internet.
So users don't need to be up-to-date on the network resources every time and it will decrease users' data consumption. For further information, you can check out [Build an offline-first app](https://developer.android.com/topic/architecture/data-layer/offline-first).

## Find this repository useful ? :heart:
<p>Support it by giving a ⭐ to this repository.</p>

## Author - Vivek Choudhary

<p>
  Linkedin: https://www.linkedin.com/in/vivek-choudhary-4686b477/
</p>


