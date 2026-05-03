# 🧪 Spring Boot Testing Guide (JUnit + Mockito)

This project demonstrates how to use **JUnit 5 and Mockito** for testing a Spring Boot application with layered architecture (Controller → Service → Repository).

---

# 🚀 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- JUnit 5
- Mockito
- Spring Boot Test

---

# 📂 Project Layers

---

# 🧠 Testing Types in this Project

## 1️⃣ Unit Testing
- Tests individual methods
- No Spring context loaded
- Uses **JUnit + Mockito**

👉 Example:
- Service layer testing

---

## 2️⃣ Repository Testing
- Tests database layer
- Uses **@DataJpaTest**
- Uses H2 in-memory database

---

## 3️⃣ Controller Testing
- Tests REST APIs
- Uses **@WebMvcTest**
- Uses MockMvc

---

## 4️⃣ Integration Testing
- Tests full application flow
- Uses **@SpringBootTest**

---

# 🧪 JUnit 5 Annotations

## 🔹 @Test
Defines a test method.

```java
@Test
void testMethod() {}
