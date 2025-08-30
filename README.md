# 🔐 JWT Authentication Template (Spring Boot)

A **Spring Boot 3.5.x starter template** with **JWT-based authentication and authorization** pre-configured.  
Use this template to **save setup time** for new projects – just clone, rename, configure, and start building business logic 🚀.

---

## ✨ Features
- ✅ Spring Boot **3.5.x**
- ✅ Java **21**
- ✅ **JWT Authentication** with Spring Security
- ✅ **MySQL** integration (Spring Data JPA)
- ✅ Input validation (`spring-boot-starter-validation`)
- ✅ Pre-seeded users (Admin & SuperAdmin)
- ✅ Clean boilerplate for quick start

---

## ⚡ Getting Started

### 1️⃣ Clone the Project
```bash
git clone https://github.com/AKSHAY-1505/SpringBoot-JWT-Auth-Template.git
cd jwt-auth-template
````

---

### 2️⃣ Reset Git Repository

Since this is a template, remove the existing git history:

```bash
rm -rf .git
git init
git add .
git commit -m "Initial commit - My Project"
```

> 🔗 Now connect to your own remote repository.

---

### 3️⃣ Rename the Project

#### A. Update `pom.xml`

Change project identifiers:

```xml
<groupId>com.yourname</groupId>
<artifactId>your-project-name</artifactId>
<name>your-project-name</name>
```

#### B. Update `application.properties`

```properties
spring.application.name=your-project-name
```

#### C. Refactor Package & Main Class (IntelliJ Recommended)

* Refactor package: `src/main/java/com/akshay/jwt_auth_template` → `com.yourname.projectname`
* Rename main class: `JwtAuthTemplateApplication.java` → `YourProjectNameApplication.java`

---

### 4️⃣ Configure Database

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
```

---

### 5️⃣ Configure JWT Secret Key

#### A. Generate a Strong Key

Use a secure random string (≥32 chars):

```
my_new_ultra_secure_secret_key
```

#### B. Encode with Base64

Use [Base64 Encoder](https://www.base64encode.org/) → Encode your key.
Example:

* Input: `my_new_ultra_secure_secret_key`
* Output: `bXlfbmV3X3VsdHJhX3NlY3VyZV9zZWNyZXRfa2V5`

#### C. Add to `application.properties`

```properties
security.jwt.secret-key=bXlfbmV3X3VsdHJhX3NlY3VyZV9zZWNyZXRfa2V5
```

---

## 📡 API Endpoints

### Roles Available

* 👤 **User**
* 🛠️ **Admin**
* 👑 **SuperAdmin**

---

### Authentication & User APIs

| Endpoint         | Method | Request Body                                      | Access Level           |
| ---------------- | ------ | ------------------------------------------------- | ---------------------- |
| `/auth/register` | POST   | `{ "email": "", "password": "", "fullName": "" }` | Public                 |
| `/auth/login`    | POST   | `{ "email": "", "password": "" }`                 | Public                 |
| `/admins`        | POST   | `{ "email": "", "password": "", "fullName": "" }` | SuperAdmin only        |
| `/users/me`      | GET    | None                                              | Any Authenticated User |
| `/users`         | GET    | None                                              | Admin, SuperAdmin      |

> 🔑 For protected routes, send JWT in header:
> `Authorization: Bearer <jwt_token>`

---

## 👥 Pre-Seeded Users

On first run, the app seeds two users:

| Role       | Email                  | Password | Access      |
| ---------- | ---------------------- | -------- | ----------- |
| Admin      | `admin@mail.com`       | `111111` | Admin APIs  |
| SuperAdmin | `super_admin@mail.com` | `111111` | Full Access |

---

## 📂 Recommended Workflow

1. Clone this template
2. Reset git & connect to your repo
3. Rename project (pom.xml + package refactor)
4. Configure DB & JWT secret
5. Start adding **business logic** 🎯

---

## 🔗 Git Setup Example

```bash
git remote add origin git@github.com:AKSHAY-1505/SpringBoot-JWT-Auth-Template.git
git branch -M develop
git push -u origin develop
```

---

💡 Happy Coding! ⚡
