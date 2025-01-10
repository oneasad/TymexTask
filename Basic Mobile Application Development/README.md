---

# Currency Converter App

A modern, real-time **Currency Converter App** that uses the **Exchange Rate API** to provide up-to-date currency conversion rates. The app is built using **Kotlin** and follows best practices with a clean architecture, including **ViewModel**, **UIState**, and a **repository pattern**. It uses **Ktor** for API communication and includes a dropdown menu for selecting source (`From`) and target (`To`) currencies.

---

## **Features**
- **Real-Time Conversion**: Fetches live exchange rates using the Exchange Rate API.
- **Currency Dropdowns**: Allows users to select `From` and `To` currencies using dropdown menus.
- **Reactive UI**: Displays conversion results dynamically as inputs change.
- **ViewModel & UIState**: Ensures separation of concerns and reactive UI updates.
- **Ktor HTTP Client**: For efficient API communication.
- **Repository Pattern**: Centralized data access with separation of data sources.
- **Error Handling**: Graceful error messages for API failures or invalid input.

---

## **Screenshots**
*(Add screenshots of the app UI once available)*

---

## **Tech Stack**
- **Programming Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **API Communication**: Ktor Client
- **Dependency Injection**: Hilt/Dagger (optional)
- **UI Components**: Jetpack Compose
- **State Management**: UIState with LiveData or StateFlow
- **API**: Exchange Rate API (or any chosen currency API)

---

## **How It Works**

1. **Dropdown Menu for Currency Selection**:
   - Two dropdown menus allow users to select the `From` and `To` currencies.
   - Currencies are fetched from the API or a predefined list.

2. **Live Conversion**:
   - Users input an amount, and the app fetches the latest exchange rate.
   - The converted value is displayed in real-time.

3. **Architecture**:
   - **ViewModel** handles the logic and maintains the UIState.
   - **CurrencyRepository** fetches data from the API using the Ktor client.
   - **UIState** communicates loading, success, or error states to the UI.

---

## **Project Structure**

```
CurrencyConverterApp/
├── data/
│   ├── Currency.kt           # Contains Currency data class, currency list, ResponseDto
│   ├── CurrencyRepo.kt       # CurrencyRepository for API interaction
├── presentation/
│   ├── Components.kt         # Dropdown menu and a result card
│   ├── CurrencyLayout.kt     # Main screen of the app
│   ├── CurrencyViewmodel/    # ViewModel to manage UIState and business logic
│   ├── CurrencyState.kt      # The UI state class

```

---
