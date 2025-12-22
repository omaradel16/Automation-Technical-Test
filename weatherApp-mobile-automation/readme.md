# Weather App Automation Framework

A robust, scalable, and maintainable test automation framework for the Android Weather App. This project utilizes **Appium 2.x**, **Java 23**, and **Maven** to automate critical UI workflows.

## 🛠️ Technology Stack
* **Language:** Java (JDK 23)
* **Build Tool:** Maven 3.9.9
* **Mobile Automation:** Appium 2.x (UiAutomator2 Driver)
* **Test Runner:** TestNG
* **Reporting:** ExtentReports

---

## ⚙️ Prerequisites
Before running the tests, ensure your environment is set up with the following:

1. **Install JDK 23**
   * Download JDK 23 from [Oracle's website](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://jdk.java.net/23/)
   * Run the installer and follow the installation wizard
   * Set JAVA_HOME environment variable:
       ```
       JAVA_HOME "C:\path\to\jdk-23"
       ```
   * Verify installation:
      ```bash
      java -version
      ```
2. **Install Maven 3.9.9**
   * Download Maven 3.9.9 from [Apache Maven website](https://maven.apache.org/download.cgi)
   * Extract the archive to your desired location
   * Set MAVEN_HOME environment variable:
       ```
       MAVEN_HOME "C:\path\to\apache-maven-3.9.9\bin"
       ```
   * Verify installation:
      ```bash
      mvn -version
      ```
3.  **Android Studio & SDK:**
    * **Install Android Studio:**
        * During installation, ensure the **"Android SDK"** and **"Android SDK Platform-Tools"** components are selected.

    * **Set the `ANDROID_HOME` Environment Variable:**
        * Variable Name: `ANDROID_HOME`
        * Variable Value: `C:\Users\YOUR_USER\AppData\Local\Android\Sdk` (or your installation path)
    * **Update the System `Path` Variable:**
        * Add the following entries to your system `Path`:
            ```
            %ANDROID_HOME%\platform-tools
            %ANDROID_HOME%\tools
            %ANDROID_HOME%\tools\bin
            ```
    * **Verify Installation:**
      Open a new terminal and run: `adb version`

4.  **Appium 2.0+:**
    * Install via Node.js: `npm install -g appium`
    * Install UiAutomator2 driver: `appium driver install uiautomator2`

---

## 📥 Setup & Installation

1.  **Clone the Repository**
    Clone the main technical test repository and navigate to the mobile automation folder:
    ```bash
    git clone [https://github.com/YOUR_USERNAME/Automation-Technical-Test.git](https://github.com/YOUR_USERNAME/Automation-Technical-Test.git)
    cd Automation-Technical-Test/weatherApp-mobile-automation
    ```

2.  **Build the project**
    ```bash
    mvn clean install
    ```

3.  **Device Setup**
    #### **Option A: Android Emulator (Recommended)**
    * **Create/Launch:** Use **Pixel 7 Pro** with **Android 14.0** (API 34).

    #### **Option B: Physical Device**
    If running on a real phone, please follow these configuration steps:
    1.  **Enable Developer Options:**
        * Turn on **USB Debugging**.
        * Turn on **Install via USB** (Required for Appium interactions).
    2.  **Update Configuration File:**
        * Navigate to: `configs/data/AndroidDesiredCapabilities.properties`)
        * Update `platformVersion` to your device's Android version (e.g., `13.0`).
        * Update `udid` with your device ID (get this by running `adb devices` in terminal).
---

## 🏃‍♂️ How to Execute Tests

### 1. Start Appium Server
Open a terminal and start the Appium server on the default port:
```bash
appium 
```

### 2. Run Tests via Maven
Open a separate terminal in the project root and run:
```bash
mvn clean test
```
---
## 📊 Test Reports

Test execution reports are automatically generated and saved in the **`test-output`** folder located in the project root.

* **Directory:** `./test-output/`
* **File Structure:** A new folder is created for every run (e.g., `WeatherApp_2025-12-22_19-30-00.html`).

**To view the results:**
1.  After the test finishes, navigate to the `test-output` folder.
2.  Open the generated `WeatherApp_.html` file in any web browser (Chrome, Edge, etc.) to see the report.