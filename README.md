# Robot Management - Android Room CRUD Application

<div align="center">

[![Kotlin](https://img.shields.io/badge/Kotlin-v2.1.0-1F425F?style=flat&logo=kotlin&logoColor=white)](https://kotlinlang.org)  
[![Android Studio](https://img.shields.io/badge/Android_Studio-3DDC84?style=flat&logo=android-studio&logoColor=white)](https://developer.android.com/studio)

</div>

## Overview

This Android project is a simple **robot management application** that allows users to create, update, and delete robots with specific attributes. The app uses **Room** for local database management, **ViewModels** for UI-related data, and **Fragments** for navigation between different screens.

## Features

- **Create new robots** set custom name, purpose, and IQ.
- **Update existing robots** and change their attributes.
- **Delete robots** from the list as needed.
- **Offline support** – Data persists even when offline.
- **Utilizes ViewModel** to manage UI-related data in a lifecycle-conscious way.
- **Navigation components** for seamless transitions between fragments.

## How It Works

1. **Add robots** with attributes such as name, purpose (Killer, Teacher, Dancer or Caring one), and IQ.
2. **Display robot list** in a RecyclerView using list adapter.
3. **Update or delete robots** through the UI.
4. **Use Room for local data storage** and persist robot information even when the app is closed.
5. **Manage UI state** with ViewModels and Flow.

## Screenshots

<p align="center">
    <img src="docs/images/robot_list.png" width="200" alt="Robot List Screenshot 1">
    <img src="docs/images/build_robot.png" width="200" alt="Add Robot Screenshot 2">
</p>

## Technologies Used

- **Kotlin**
- **Room Database**
- **ViewModel**
- **Navigation Component**
- **Coroutines**
- **RecyclerView**
