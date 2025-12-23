# Automation-Technical-Test

Welcome to my submission for the Automation Technical Test. This repository contains two distinct projects covering **Mobile** and **Web** automation solutions.

## 📂 Projects Overview

### 1. 📱 Mobile Automation
* **Folder:** `/weatherApp-mobile-automation`
* **Description:** An Appium-based framework for testing the Android Weather App.
* **Tech Stack:** Java (JDK 23), Appium 2.x, TestNG, Maven.

### 2. 🌐 Web Automation
* **Folder:** `/amazon-web-automation`
* **Description:** A Selenium-based framework for testing Amazon Egypt web scenarios.
* **Tech Stack:** Java (JDK 23), Selenium WebDriver, TestNG, Maven.

---

## 🚀 Getting Started

Please navigate to the specific project folder for detailed **Setup** and **Execution** instructions.

### To Run the Mobile Tests:
```bash
    cd weatherApp-mobile-automation
    # Follow the README.md inside this folder
```
To Run the Web Tests:
```bash
    cd amazon-web-automation
    # Follow the README.md inside this folder
```
---
## 📊 Test Execution Reports

For your convenience, the execution reports and **video recordings** of the test runs have been collected in the root **`Test-Reports`** folder.

* **📱 Mobile Automation:**
    * **Report:** `./Test-Reports/Mobile-Report/mobile-report.html`
    * **Video:** `./Test-Reports/Mobile-Report/mobile-execution.mp4`

* **🌐 Web Automation:**
    * **Report:** `./Test-Reports/Web-Report/web-report.html`
    * **Video:** `./Test-Reports/Web-Report/web-execution.mp4`

> **Note:** These are reports from my local execution. If you run the tests yourself, new reports will be generated inside each project's respective `test-output` folder.
---
## 🌳 Project Directory Structure

Here is the high-level organization of the repository:

```text
Automation-Technical-Test/
│
├── weatherApp-mobile-automation/       # [Mobile Project]
│   ├── src/
│   │   ├── main/java/screens/            
│   │   ├── main/java/actions/
│   │   ├── main/java/screenFactory/
│   │   ├── test/java/base/
│   │   └── test/java/tests/            
│   ├── configs/                        
│   │   ├── data/AndroidDesiredCapabilities.properties            
│   │   └── elementLocators/      
│   ├── test-output/        
│   ├── pom.xml                         
│   └── README.md                       
│
├── amazon-web-automation/              # [Web Project]
│   ├── src/
│   │   ├── main/java/pages/            
│   │   ├── main/java/utils/
│   │   └── test/java/tests/          
│   ├── test-report/  
│   ├── pom.xml                         
│   └── README.md  
│
├── Test-Reports/                      
│   ├── Mobile-Report/                  
│   │   ├── mobile-report.html                
│   │   └── mobile-execution.mp4       
│   └── Web-Report/                     
│       ├── web-report.html               
│       └── web-execution.mp4                                    
│
└── README.md                          
