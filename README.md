<p align="center">
    <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/flutter/flutter-original.svg" alt="Flutter" width="200" height="55"/>
</p>

<h1 align="center">🚀 Enviart - Delivery Tracking App</h1>

<p align="center">
  Aplicación móvil desarrollada con <b>Flutter</b> para el rastreo y gestión de envíos de mercancía en tiempo real.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Flutter-3.10-02569B?logo=flutter" alt="Flutter"/>
  <img src="https://img.shields.io/badge/Dart-3.0-0175C2?logo=dart" alt="Dart"/>
  <img src="https://img.shields.io/badge/License-MIT-blue.svg" alt="License"/>
  <img src="https://img.shields.io/badge/Platform-Android%20|%20iOS%20|%20Web-brightgreen" alt="Platforms"/>
</p>

---

## 🧱 Tecnologías Utilizadas

<p align="center">
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/flutter/flutter-original.svg" alt="Flutter" width="55" height="55"/>
  <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/dart/dart-original.svg" alt="Dart" width="55" height="55"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/android/android-original.svg" alt="Android" width="55" height="55"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/apple/apple-original.svg" alt="iOS" width="55" height="55"/>
  <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/chrome/chrome-original.svg" alt="Web" width="55" height="55"/>
</p>

---

## ⚙️ Requisitos Previos

Antes de ejecutar el proyecto asegúrate de tener instalado:

- [Flutter SDK 3.10.3 o superior](https://flutter.dev/docs/get-started/install)
- [Dart SDK](https://dart.dev/get-dart)
- [Git](https://git-scm.com/)
- Un IDE como **Android Studio**, **VS Code** o **IntelliJ IDEA**
- Emulador de Android/iOS o dispositivo físico

---

## 🔧 Instalación y Configuración

### 1️⃣ Clonar el Repositorio

```bash
git clone https://github.com/tu-usuario/Enviart-Flutter.git
cd Enviart-Flutter/app
```

### 2️⃣ Instalar Dependencias

```bash
flutter pub get
```

### 3️⃣ Configurar Dispositivo

**Para Android:**
```bash
flutter devices  # Verifica los dispositivos disponibles
flutter run      # Ejecuta en el dispositivo seleccionado
```

**Para iOS:**
```bash
flutter run -d ios  # Requiere macOS
```

**Para Web:**
```bash
flutter run -d chrome
```

---

## 🚀 Ejecución del Proyecto

### Modo Debug (desarrollo):

```bash
flutter run
```

### Modo Release (producción):

```bash
flutter run --release
```

### Compilar APK para Android:

```bash
flutter build apk --split-per-abi
```

### Compilar App Bundle para Play Store:

```bash
flutter build appbundle
```

---

## 📱 Plataformas Soportadas

| Plataforma | Estado |
|-----------|--------|
| Android | ✅ Completamente soportada |
| iOS | ✅ Completamente soportada |
| Web | ✅ Completamente soportada |
| Windows | ✅ Completamente soportada |
| macOS | ✅ Completamente soportada |
| Linux | ✅ Completamente soportada |

---

## 📁 Estructura del Proyecto

```
lib/
├── main.dart                 # Punto de entrada
├── data/
│   ├── app_colors.dart      # Constantes de colores
│   └── mock_data.dart       # Datos de prueba
├── screens/
│   ├── home_screen.dart     # Pantalla de inicio
│   ├── list_screen.dart     # Lista de envíos
│   └── detail_screen.dart   # Detalle de envío
└── widgets/
    └── status_color_util.dart  # Utilidades

assets/
├── images/
│   └── Logo.avif
└── icon/
    └── Logo.png

pubspec.yaml                 # Configuración del proyecto
```

---

## 🎨 Características Principales

✨ **Pantalla de Inicio**
- Carga asincrónica con FutureBuilder
- Diseño elegante con gradiente
- Logo y descripción de la app

📦 **Lista de Envíos**
- ListView.builder para lista dinámica
- Tarjetas con información del envío
- Códigos de color por estado

📋 **Detalle de Envío**
- Información completa del paquete
- TextField para registrar novedades
- TextEditingController para manejo de entrada
- Estados visuales con colores

🎯 **Navegación**
- Navigator con rutas anónimas
- Transiciones suaves entre pantallas
- Botones para volver

---

## 🧪 Widgets Utilizados

### Arquitectura de Layouts
- ✅ **Row** - Disposición horizontal
- ✅ **Column** - Disposición vertical
- ✅ **Expanded** - Expande widgets
- ✅ **Flexible** - Widgets flexibles

### Entrada de Datos
- ✅ **TextField** - Campos de texto
- ✅ **TextEditingController** - Control de entrada

### Listas
- ✅ **ListView.builder** - Listas dinámicas

### Decoración
- ✅ **Container** - Con gradientes, sombras, bordes
- ✅ **BoxDecoration** - Decoración avanzada
- ✅ **LinearGradient** - Gradientes personalizados

### Navegación
- ✅ **Navigator** - Gestión de rutas
- ✅ **MaterialPageRoute** - Rutas anónimas

### Recursos
- ✅ **Image.asset** - Carga de imágenes
- ✅ **Assets** - Configuración en pubspec.yaml

---

## 🔄 Flujo de Navegación

```
PantallaInicio (Carga)
       ↓
PantallaInicio (Principal)
       ↓
PantallaRastreoEnvios (Lista)
       ↓
PantallaDetalle (Formulario)
```

---

## 📦 Dependencias Principales

```yaml
flutter:
  sdk: flutter

cupertino_icons: ^1.0.8
flutter_launcher_icons: ^0.13.1
```

---

## 🧰 Comandos Útiles

| Comando | Descripción |
|---------|-------------|
| `flutter pub get` | Descarga dependencias |
| `flutter run` | Ejecuta en debug |
| `flutter run --release` | Ejecuta en release |
| `flutter build apk` | Genera APK |
| `flutter build appbundle` | Genera App Bundle |
| `flutter clean` | Limpia el proyecto |
| `flutter pub upgrade` | Actualiza dependencias |
| `flutter analyze` | Analiza código |

---

## 🎯 Requisitos del Proyecto Cumplidos

- ✅ 3 pantallas navegables
- ✅ Row, Column, Expanded, Flexible
- ✅ Navigator y rutas anónimas
- ✅ TextField y TextEditingController
- ✅ ListView.builder
- ✅ Container con propiedades avanzadas
- ✅ Assets configurados correctamente
- ✅ APK compilada exitosamente

