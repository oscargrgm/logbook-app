# MySugr Logbook App

MySugr Logbook is an Android application that allows users to track their blood glucose levels
conveniently.

The app provides an interface for entering blood glucose values, selecting units (mmol/L or mg/dL),
and viewing the average blood glucose over time.

## Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Asynchronous Programming:** Kotlin Coroutines
- **Dependency Injection:** Dagger Hilt
- **Database:** Room
- **Architecture Pattern:** MVVM (Model-View-ViewModel)

## Features

- User can select a unit for their blood glucose value (mmol/L or mg/dL).
- User can enter a blood glucose value with a numeric keyboard into a text field.
- The unit label next to the text field reflects the selected unit.
- User can press "Save" to store the blood glucose value and update the average.
- Multiple blood glucose values can be tracked.
- Text input is cleared after a successful save.
- Previously entered values are displayed.
- Unit conversion is supported (1 mmol/L = 18.0182 mg/dL).

## Structure

The project is divided in different functionality-related modules. This will make our project to
escalate easier in case it grows.

Each one of these modules follow the same packaging structure, following the Clean Architecture
pattern. So that, we can find:

- _di_: Includes all the modules necessary to provide dependencies.
- _data_: Includes the Data layer classes, such as DAOs, entities or Repositories.
- _domain_: Includes the Domain layer classes, such as mappers, domain models, UseCases, and
  Repository interfaces.
- _ui_: Includes al the UI layer classes, such as Activities, ViewModels, or Compose components.

### app

This is the main module of the project. It contains all the classes that make the application work,
such as `MyApplication`, `AppDatabase`, or `AppModule`.

### core

This module is designed to include all those common classes that are used project-wide, such as the
project theme classes. It could include common models in case multiple modules use the same
(e.g. `UnitType`).

### logbook

This module is aimed to contain the whole Logbook functionality. The main classes
are `LogbookActivity`, `LogbookViewModel` and `LogbookScreen`. It also contains the necessary
classes to perform the intended operations, such as provide the average measure, entering records,
or display the tracking list.

## Getting Started

1. Open the project in Android Studio:
   Open Android Studio and select "Open an existing Android Studio project." Navigate to the
   repository
   and select the build.gradle file in the root directory.

2. Run the app:
   Connect an Android device or use an emulator and click the "Run" button in Android Studio.
