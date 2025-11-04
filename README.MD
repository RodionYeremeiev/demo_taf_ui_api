Here’s a clean and professional `README.md` tailored for your modular Maven automation project with Allure reporting and GitHub Actions CI:

---

## 🚀 Automation Framework: UI + API Testing

This project is a scalable, modular test automation framework built with Maven. It supports both UI and API testing, complete with Allure reporting and GitHub Actions integration.

---

### 📦 Modules

| Module     | Purpose                          | Tech Stack                     |
|------------|----------------------------------|--------------------------------|
| `ui-tests` | Browser-based UI automation      | Selenide + JUnit 5             |
| `api-tests`| REST API validation              | Rest Assured + JUnit 5         |

---

### 🧪 Features

- ✅ Modular Maven structure for clean separation
- ✅ Allure HTML reports generated after each test run
- ✅ GitHub Actions CI pipeline with report artifacts
- ✅ Java 21 support with JUnit 5
- ✅ Easy-to-extend for new test layers or environments

---

### 📊 How to Run and View Reports Locally

```bash
# Run all tests and generate reports
./generate-allure-report.sh

!!! IMPORTANT !!!
before run update ceresentials used in command:
mvn clean verify -Dheadless=false -Dui.standard.user=standard_user -Dui.valid.password=your_valid_password -Dui.locked.user=your_locked_user_name -Dapi.key=your_valid_api_key;
```

- Merges results from both modules
- Generates unified HTML report in `merged-report/`
- Automatically opens the report in your browser

# after report is closed Press <Ctrl+C> to stop started Allure Server and exit

---

# To run specific module individually:
- use Run UI or API Tests pipeline (defined in test-run.yml) for cpecific module and branch
- via mvn command (UI): mvn -f ui-tests/pom.xml clean test -Dheadless=true -Dui.standard.user=${{ secrets.STD_USER_NAME }} -Dui.valid.password=${{ secrets.STD_PASSWORD }} -Dui.locked.user=${{ secrets.LOCKED_USER_NAME }};
- via mvn command (API): mvn -f api-tests/pom.xml clean test -Dapi.key=${{ secrets.VALID_API_KEY }};

### ⚙️ CI Integration

GitHub Actions runs tests for both modules and publishes Allure reports as downloadable artifacts:
 - Run UI or API Tests pipeline (defined in test-run.yml)
 - Automatic matrix pipeline execute test in parallel mode for both modules for verification of pull request or push to master (defined in ci.yml)

---

### 📁 Folder Structure

```
automation-project/
├── ui-tests/
│   └── src/main/java
│   │   └── src/main/java/pages (POM)
│   │   └── src/main/java/util (Utils)
│   └── src/test/java (UI Test Classes)
│   └── src/main/resources
│   
├── api-tests/
│   └── src/main/java
│   │   └── src/main/java/model (Entity models)
│   └── src/test/java (API Test Classes)
│   └── src/main/resources
│   
├── merged-report/
│   └── index.html
├── generate-allure-report.sh
└── .github/workflows/ci.yml
└── .github/workflows/test-run.yml
```

---
