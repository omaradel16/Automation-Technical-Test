# 🛒 Amazon EG Web Automation Framework

A scalable and robust test automation framework built with **Selenium WebDriver** and **Java** to automate complex user journeys on [Amazon Egypt](https://www.amazon.eg/).

## 🛠️ Technology Stack
* **Language:** Java (JDK 23)
* **Build Tool:** Maven 3.9.9
* **Web Automation:** Selenium WebDriver 4.x
* **Test Runner:** TestNG
* **Browser:** Google Chrome (Managed automatically via Selenium Manager)

---

## ⚙️ Prerequisites
Ensure your environment is set up with:

1. **Google Chrome Browser.**
2. **Install JDK 23**
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
3. **Install Maven 3.9.9**
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

---

## 📥 Setup & Installation

1.  **Clone the Repository**
    Clone the main technical test repository and navigate to the web automation folder:
    ```bash
    git clone [https://github.com/YOUR_USERNAME/Automation-Technical-Test.git](https://github.com/YOUR_USERNAME/Automation-Technical-Test.git)
    cd Automation-Technical-Test/amazon-web-automation
    ```

2.  **Build the project**
    ```bash
    mvn clean install
    ```
---

## 🏃‍♂️ How to Execute Tests

### Run via Maven
Open a terminal in the `amazon-web-automation` folder and run:
```bash
  mvn clean test
```

## 📊 Test Reports

Test execution reports are automatically generated and saved in the **`test-report`** folder located in the project root.

* **Directory:** `./test-report/`
* **File Structure:** A new folder is created for every run (e.g., `amazon_2025-12-22_19-30-00.html`).

**To view the results:**
1.  After the test finishes, navigate to the `test-report` folder.
2.  Open the generated `amazon_.html` file in any web browser (Chrome, Edge, etc.) to see the report.