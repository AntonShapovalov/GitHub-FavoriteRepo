# GitHubFavoriteRepo

#### Android application concept to display Square Inc. repositories with possibility add local bookmarks 

Project demonstrates how to use AndroidX, Navigation Component, Kotlin, Android Architecture Components, Dagger, RxJava and Retrofit to get remote data and add local bookmarks.
To keep things simple, app always loads only Square Inc. repositories.

#### Structure:

1. Repositories list screen - shows list of Square Inc. repositories
2. Repository details screen - displays repository description and button "add to favorites"

#### How to install

Please use [app-release.apk](./app/release/) file

#### Used language and libraries
 * [Navigation component](https://developer.android.com/guide/navigation) - handles all navigation aspects, allows to avoid boilerplate code with fragments transaction, backstack etc.
 * [Kotlin](https://kotlinlang.org/docs/tutorials/kotlin-android.html) - primary project language
 * [Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html) - the core of [MVVM](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) pattern
 * [RxJava](https://github.com/ReactiveX/RxJava) - simple way to manage data chains
 * [Dagger](https://google.github.io/dagger/) - dependency injection framework
 * [Retrofit](http://square.github.io/retrofit/) - to perform API call
