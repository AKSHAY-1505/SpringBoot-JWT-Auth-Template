# JWT Authentication Template (Spring Boot)

This is a **Spring Boot JWT Authentication Starter Template** that provides a fully functional setup for **JWT-based login and authentication** out of the box.  

The main goal of this project is to save setup time for future Spring Boot projects.  
Instead of rewriting JWT authentication every time, you can clone this repo, rename the project, configure the database, and directly work on the business logic.

---

## 🚀 Features
- Spring Boot 3.5.x
- Java 21
- JWT Authentication with Spring Security
- MySQL Database integration (via Spring Data JPA)
- Validation support (`spring-boot-starter-validation`)
- Ready-to-use boilerplate for quick start

---

## 🛠️ Getting Started

### 1. Clone the Project
```
git clone https://github.com/your-username/jwt-auth-template.git
cd jwt-auth-template
```

---

### 2. Remove Existing Git Repository

Since this is a template, you’ll want to delete the existing git history before starting your own project:

```
rm -rf .git
git init
git add .
git commit -m "Initial commit - My Project"
```

Now you can connect your project to a new remote repository (Google it)

---

### 3. Change the Project Name

#### A. Change Project Name in pom.xml

The template project is currently named **`jwt-auth-template`** (`groupId: com.akshay`, `artifactId: jwt-auth-template`).  
To rename it for your use case, update the following:

**`pom.xml`**

Update the `<groupId>` and `<artifactId>` and `<name>` fields:
```
<groupId>com.yourname</groupId>
<artifactId>your-project-name</artifactId>
<name>your-project-name</name>
```

artifactId and name are usually your project name separated by '-'

#### B. Change Project Name in application.properties

change the value of `spring.application.name` in **`application.properties`**

#### C. Change the Package Names
Use IntelliJ for the following steps
- Navigate to `src/main/java/com/akshay/jwt_auth_template`
- Right Click on `com.akshay.jwt_auth_template`, refactor → Rename → All Directories -> Provide your Project Name
- Right Click on `JwtAuthTemplateApplication.java`, refactor → Rename → "YourProjectNameApplication"

---

### 4. Database Configuration

Database connection settings are configured in the **`application.properties`** file (located in `src/main/resources`).
👉 Update the values (`your_database`, `your_db_username`, `your_db_password`) according to your local or production setup.

---

### 5. Replace your private key used for JWT in **`application.properties`**

To change the **JWT secret key** in your Spring Boot project, generate a new private key string, encode it using base64 (with https://www.base64encode.org/), and update it in your `application.properties` file as described below

#### A. Choose a New Secret Key
Decide on a strong, random string for your new secret key—for example:
```
my_new_ultra_secure_secret_key
```
Use at least 32 random characters (letters, numbers, symbols) for good security.[4]

***

#### B. Encode the Key with base64

- Open https://www.base64encode.org/
- Paste your new secret key in the "Encode to Base64" field.
- Click the "Encode" button.
- Copy the generated base64 string from the result box.

Example input:
```
my_new_ultra_secure_secret_key
```
Example output:
```
bXlfbmV3X3VsdHJhX3NlY3VyZV9zZWNyZXRfa2V5
```

***

#### C. Add the Encoded Key to `application.properties`

Replace the `security.jwt.secret-key` property value:

```properties
security.jwt.secret-key=bXlfbmV3X3VsdHJhX3NlY3VyZV9zZWNyZXRfa2V5
```

---

## 📦 Endpoints in Template for Testing

Below are the **key details and endpoints** for your Spring Boot JWT authentication project, including roles, HTTP methods, request bodies, and authorization requirements:

***

## Roles Used

- **User**
- **Admin**
- **SuperAdmin**

***

## API Endpoints

### Register a New User
- **POST** `/auth/register`
- **Request Body**:
  ```json
  { "email": "", "password": "", "fullName": "" }
  ```
- **Access**: Public (no authentication required)

***

### Login (Obtain JWT Token)
- **POST** `/auth/login`
- **Request Body**:
  ```json
  { "email": "", "password": "" }
  ```
- **Access**: Public (no authentication required)
- **Response**: JWT token for authenticated access

***

### Create Admins (SuperAdmins only)
- **POST** `/admins`
- **Request Body**:
  ```json
  { "email": "", "password": "", "fullName": "" }
  ```
- **Access**: Allowed only for **SuperAdmins**
- **Authorization**: JWT Bearer token in `Authorization` header

***

### Get Current User Info
- **GET** `/users/me`
- **Request Body**: None
- **Access**: Any authenticated **User, Admin, or SuperAdmin**
- **Authorization**: JWT Bearer token in `Authorization` header

***

### Get All Users (For Admins & SuperAdmins)
- **GET** `/users`
- **Request Body**: None
- **Access**: Allowed only for **Admins** and **SuperAdmins**
- **Authorization**: JWT Bearer token in `Authorization` header

***

**Note:**  
For all endpoints requiring authentication, include the JWT token in the request:
```
Authorization: Bearer <jwt_token>
```

***

## Pre-seeded Users

When the project starts, **two users are automatically created in the database using DB seed logic** for easier initial access and administration:


- **Admin**
    - Email: `admin@mail.com`
    - Password: `111111`
    - Role: Admin

- **Super Admin**
    - Email: `super_admin@mail.com`
    - Password: `111111`
    - Role: SuperAdmin

***

This automatic seeding ensures the application is immediately accessible for both **admin** and **super admin** testing.

## 🗂️ Recommended Project Flow

1. Clone this template.  
2. Reset git history & connect to a new remote repo.  
3. Rename the project (pom.xml + packages).  
4. Configure database in `application.properties`.  
5. Start adding **business logic and features** on top of JWT auth. 🚀

---

Happy Coding! ⚡

