# Shopping List App

This is a simple shopping list app built using Android and Jetpack Compose, following the MVVM architectural pattern.

## Functionality

The app allows users to:

* Add new shopping list items with names and quantities.
* Mark items as checked/unchecked.
* Delete items from the list.
* Persist the shopping list data using Room, so it's available even after the app is closed and reopened.

## Architecture

The app follows the Model-View-ViewModel (MVVM) architecture:

* **Model:** Represents the data of the app. It includes:
    * `ShoppingItemEntity`: A data class representing a shopping list item, stored in the Room database.
    * `ShoppingListDatabase`: An abstract class representing the Room database.
    * `ShoppingItemDao`: An interface defining methods for accessing and manipulating shopping list items in the database.
* **View:** Represents the UI of the app. It's built using Jetpack Compose and displays the shopping list items, allowing users to interact with them.
* **ViewModel:** Acts as an intermediary between the View and the Model. It holds the UI state, handles user interactions, and interacts with the Model to fetch and update data.

## Technologies Used

* **Jetpack Compose:** A modern UI toolkit for Android used to build the app's UI.
* **Room Persistence Library:** Used for data persistence, allowing the app to store and retrieve shopping list items in a local SQLite database.
* **Kotlin Coroutines:** Used for asynchronous operations, such as database interactions, to avoid blocking the main thread.
* **Flows:** Used to observe data changes from the database and update the UI accordingly.
* **LiveData:** Used to observe data changes and update the UI automatically, ensuring lifecycle awareness.
* **ViewModel:** Provides a lifecycle-aware container for UI state and logic.
* **Hilt:** A dependency injection library for Android, used to manage dependencies and simplify object creation.

## MVVM Approach

The MVVM architecture promotes separation of concerns and testability:

* The View is responsible for displaying data and handling user interactions.
* The ViewModel holds the UI state and logic, interacting with the Model to fetch and update data.
* The Model represents the data and provides access to it through DAOs.

This separation makes it easier to test each component independently and improves the maintainability of the code.

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

## Contributing

Contributions are welcome! Please feel free to open issues or pull requests.

## License

This project is licensed under the MIT License.