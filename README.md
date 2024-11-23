# **NowPayTask**

This is a sample Android project built using **Hilt**, **RecyclerView**, **ViewModel**, and **StateFlow** to demonstrate an architecture following **MVVM** pattern and using **Dependency Injection** for managing dependencies.

## **Project Overview**

This Android app provides a login screen where users can input their email and password, and upon successful login, they are navigated to a movie list screen. The movie list is fetched dynamically from a remote API and displayed in a **RecyclerView** using data provided by a **ViewModel**.

### **Key Features:**

- **Login Screen**: The user can input email and password, with proper validation.
- **Movie List Screen**: Displays a list of movies after a successful login.
- **ViewModel and StateFlow**: Movies are fetched and displayed using a ViewModel that reactively collects data.
- **Hilt Dependency Injection**: Dependency management handled by Hilt.
- **UI Tests**: Unit and UI tests using Espresso and Hilt.

---

## **Tech Stack**

- **Kotlin**: The language used for development.
- **Hilt**: Dependency Injection for managing and injecting dependencies.
- **StateFlow**: For managing reactive UI states.
- **ViewModel**: For managing UI-related data lifecycle-aware.
- **Espresso**: For writing UI tests.
- **JUnit**: For unit testing.

---

## **Architecture**

The project follows the **MVVM** (Model-View-ViewModel) architecture pattern:

- **Model**: Represents the data layer. In this case, the `MovieModel` holds information related to movies.
- **View**: The UI layer which includes activities and fragments (e.g., `LoginActivity` and `MovieListActivity`).
- **ViewModel**: Manages the data and logic for the UI components (e.g., `MoviesViewModel`).

---

## Screenshot

<img src="LoginScreen.png" width="300">|
<img src="MoviesList.png" width="300">|

---

## **Directory Structure**

Here is the directory structure for this project:

<img src="directory structure.png" width="300">|

---

## **Package Breakdown**

- **`app/`**: The main application module, is responsible for setting up the application and initializing Hilt.
  - **`NowPayApp`**: The application class that is annotated with `@HiltAndroidApp` to enable dependency injection using Hilt.

- **`presentation/`**: Contains all the UI-related components.
  - **`ui/`**: Houses the UI classes (activities, adapters, and ViewHolders).
    - **`LoginActivity`**: The activity responsible for the login screen where users enter email and password.
    - **`MovieListActivity`**: The activity responsible for displaying the movie list after login.
    - **`MoviesAdapter`**: The adapter used to bind the movie data to the `RecyclerView`.
    - **`MovieViewHolder`**: The ViewHolder for each item in the `RecyclerView`.
  - **`MoviesViewModel`**: The ViewModel is responsible for managing UI-related data, such as fetching the list of movies.

- **`domain/`**: Contains domain-related logic and models.
  - **`MovieModel`**: A domain model representing the movie data used in the application.

- **`data/`**: The data layer, is responsible for handling data management, dependency injection, and interacting with remote data sources.
  - **`di/`**: Contains the Hilt dependency injection modules.
    - **`MoviesModule`**: A module that provides dependencies related to movie functionality.
    - **`NetworkModule`**: A module that provides network-related dependencies, such as the API client.
  - **`remote/`**: The remote data source for fetching movie data from an API.
    - **`MoviesApi`**: The interface for making network requests related to movies.
    - **`MoviesResponseModel`**: The model that maps the response from the `MoviesApi`.
    - **`MoviesRepository`**: The repository that fetches data from the remote source and provides it to the ViewModel.

---

## **How It Works**

### 1. **Login Screen**:
   - The `LoginActivity` is the first screen where the user is prompted to enter their email and password. The data is validated, and if valid, the user is logged in.
   - The `MoviesViewModel` is used to handle the login process and validation.
   - Upon successful login, the `MovieListActivity` is launched to display the list of movies.

### 2. **Movie List Screen**:
   - The `MovieListActivity` displays a list of movies fetched from the `MoviesViewModel`.
   - The movies are loaded and observed in a `RecyclerView` using the `MoviesAdapter` and `MovieViewHolder` to display each movie item.
   - The movies are fetched asynchronously via the repository from the remote API.

### 3. **Dependency Injection**:
   - Hilt is used to handle dependency injection across the application.
   - The `MoviesModule` and `NetworkModule` provide the necessary dependencies like network clients, repositories, and the ViewModel.
   - `MoviesRepository` interacts with the remote data source to fetch movie data, and the data is passed through the `MoviesViewModel` to the UI.

### 4. **Flow and State Management**:
   - The `MoviesViewModel` uses `StateFlow` to manage the list of movies.
   - The `collectLatest` function is used in `MovieListActivity` to collect the latest movie data as it changes and updates the UI.
   - If the list is empty, the `RecyclerView` is hidden; otherwise, it is shown with the fetched movie list.

### 5. **UI Testing**:
   - The project includes UI tests using Espresso, where you can test the login functionality, input validation, and movie list display.
   - Unit tests ensure that the data layer (such as fetching movie data and handling API responses) works correctly, and UI tests check for proper navigation between activities and the display of the movie list.

### 6. **Network and Data Flow**:
   - The `MoviesApi` interface defines the endpoints to fetch movie data.
   - The `MoviesRepository` class uses this API to fetch the data and provides it to the `MoviesViewModel`.
   - The `MoviesResponseModel` maps the

---

## **Acknowledgements**
- **Hilt**: For easy and efficient Dependency Injection.
- **StateFlow**: For reactively managing UI-related state.
- **RecyclerView**: For list rendering.
- **Espresso**: For testing Android UI components.

