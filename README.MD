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
- ✅ Java 17+ support with JUnit 5
- ✅ Easy-to-extend for new test layers or environments

---

### 📊 How to Run and View Reports Locally

```bash
# Run all tests and generate reports
./generate-allure-report.sh
```

- Merges results from both modules
- Generates unified HTML report in `merged-report/`
- Automatically opens the report in your browser

---

### ⚙️ CI Integration

GitHub Actions runs tests for both modules and publishes Allure reports as downloadable artifacts:

```yaml
- name: Upload Allure HTML report
  uses: actions/upload-artifact@v4
  with:
    name: ui-tests-allure-report
    path: ui-tests/target/site/allure-maven-plugin/
```

---

### 📁 Folder Structure

```
automation-project/
├── ui-tests/
│   └── target/site/allure-maven-plugin/index.html
├── api-tests/
│   └── target/site/allure-maven-plugin/index.html
├── merged-report/
│   └── index.html
├── generate-allure-report.sh
└── .github/workflows/ci.yml
```

---

Would you like me to add badges (build status, Java version, test coverage) or a setup guide for new contributors?
