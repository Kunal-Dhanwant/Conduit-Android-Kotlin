![Logo](https://cloud.githubusercontent.com/assets/556934/25672246/9a20e960-2fe7-11e7-99d3-23652878a2c2.png)
# Android/Kotlin codebase containing real world examples (CRUD, auth, advanced patterns, etc) that adheres to the [RealWorld](https://github.com/gothinkster/realworld) spec and API.

This codebase was created to demonstrate a fully fledged fullstack application built with Kotlin including CRUD operations, authentication, routing, pagination, and more.

See how a Medium.com clone (called Conduit) is built using Kotlin in Android to connect to any other backend from https://realworld.io/.
For more information on how to this works with other backends, head over to the  [RealWorld](https://github.com/gothinkster/realworld) repo.

I've gone to great lengths to adhere to the latest community styleguides & best practices but had to adapt between the  [RealWorld](https://github.com/gothinkster/realworld) specification and general mobile layout of Medium.com.

# Development
This Project have been developed with [Android Studio](https://developer.android.com/studio/)

# Concepts
This RealWorld app tries to show the following Android concepts:

* 100% Kotlin Codebase
* MVVM (Model View ViewModel) Architecture
* LiveData
* Kotlin Coroutines
* Jetpack Navigation Architecture
Architecture
The project follows the general MVVM structure without any specifics.

# There are two modules in the project

*app* - The UI of the app. The main project that forms the APK

*api* - The REST API consumption library. Pure JVM library not Android-specific

# Other Backends
Obviously, this RealWorld app is a frontend app. But it can connect to all backends implementing the [RealWorld](https://github.com/gothinkster/realworld)  spec and API. To test you own backend implementation just change the URL in the settings dialog.
