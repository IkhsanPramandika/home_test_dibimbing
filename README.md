# Integrated QA Automation Framework (Web UI & API)

Project ini adalah framework automasi pengujian terintegrasi yang mencakup **Web UI Testing** (GitHub Gist) dan **API Testing** (Reqres.in). Dibangun menggunakan Java dengan pola desain **Page Object Model (POM)** untuk memastikan kode rapi, mudah dirawat, dan *scalable*.

## 🚀 Teknologi yang Digunakan

Bahasa Pemrograman: Java 17
Automation UI: Selenium WebDriver
Automation API: Rest-Assured
Test Framework:  TestNG
Build Tool: Gradle
Library Pendukung: WebDriverManager (untuk manajemen driver otomatis)

## 📁 Struktur Folder

```text
src/
├── main/java/
│   └── utils/
│       └── ConfigReader.java       # Utility untuk membaca file .properties
├── test/java/
│   ├── base/
│   │   ├── BasePage.java          # Wrapper fungsi Selenium (Wait, Click, dsb)
│   │   └── BaseTest.java          # Setup & Teardown browser
│   ├── pages/                     # Page Object Model (POM)
│   │   ├── LoginPage.java         # Elemen & Aksi halaman Login GitHub
│   │   └── GistPage.java          # Elemen & Aksi manajemen Gist
│   └── tests/
│       ├── GistTest.java          # Test Case UI (Create, Update, Delete Gist)
│       └── ReqresApiTest.java     # Test Case API (CRUD User)
└── test/resources/
    └── config.properties          # Konfigurasi URL, API Key, & Akun Test
