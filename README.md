# 🚀 Backend System: Bot Interaction, Rate Limiting & Notification Engine

## 📌 Overview

This project is a Spring Boot backend system that simulates bot interactions on user posts.
The focus is on handling **high concurrency**, enforcing **strict rate limits**, and delivering **smart notifications** using Redis.

The system is designed with real-world backend principles:

* Concurrency-safe operations
* Stateless architecture
* Efficient notification batching

---

## ⚙️ Tech Stack

* **Spring Boot** – Backend framework
* **PostgreSQL** – Persistent database
* **Redis** – In-memory data store (rate limiting, cooldowns, queues)
* **Docker** – Runs Redis & PostgreSQL
* **Postman** – API testing

---

## 🧠 System Architecture

```
Controller → Service Layer → Redis → PostgreSQL
```

* **Controller** → Handles HTTP requests
* **Service Layer** → Business logic
* **Redis** → Real-time control (rate limits, cooldown, queue)
* **PostgreSQL** → Stores final data (posts, comments)

> 🔑 **Insight:** Redis acts as a *gatekeeper*, PostgreSQL as the *source of truth*.

---

## ⚔️ Key Features

### 1. ✅ Concurrency-Safe Bot Limit

* Maximum **100 bot comments per post**
* Implemented using Redis **INCR (atomic operation)**

> 🔥 Guarantees no race condition even under heavy concurrent requests

---

### 2. ⏱️ Rate Limiting (HTTP 429)

* When bot limit exceeds:

```http
HTTP 429 TOO MANY REQUESTS
```

* Prevents system overload and spam

---

### 3. 🔔 Notification Engine (Smart Batching)

* First bot interaction → **instant notification**
* Subsequent interactions (within cooldown) → **queued in Redis**
* Scheduler processes and sends summary:

```
"Bot 1000 and 5 others interacted with your post"
```

---

### 4. 🧠 Stateless Design

* No in-memory storage used
* All runtime data stored in Redis:

    * Counters
    * Cooldowns
    * Notification queues

---

### 5. 🛡️ Data Integrity

* Redis validates before DB write
* PostgreSQL stores only valid data

---

## 🧰 Redis Usage

| Feature        | Redis Structure |
| -------------- | --------------- |
| Bot limit      | String (INCR)   |
| Virality score | String (INCR)   |
| Cooldown       | Key with TTL    |
| Notifications  | List (queue)    |

---

## 🚀 How to Run

### 1. Start Services

```bash
docker-compose up -d
```

### 2. Run Backend

```bash
mvn spring-boot:run
```

### 3. Test APIs

Import the provided **Postman Collection** and run requests.

---

## 🧪 API Demo

---

### 🔹 Add Bot Comment

```http
POST http://localhost:8080/api/posts/5/comments
```

#### 📦 Request Body

```json
{
  "authorId": 1000,
  "authorType": "BOT",
  "content": "Sample bot interaction",
  "depthLevel": 1
}
```

---

### ✅ Expected Behavior

| Scenario        | Result               |
| --------------- | -------------------- |
| First request   | Instant notification |
| Within cooldown | Added to Redis queue |
| After scheduler | Batched notification |

---

## 🚫 Rate Limit Example (429)

---

### 📌 Request (after 100 bot comments)

```http
POST http://localhost:8080/api/posts/5/comments
```

```json
{
  "authorId": 2000,
  "authorType": "BOT",
  "content": "Exceeding limit",
  "depthLevel": 1
}
```

---

### ❌ Response

```json
{
  "message": "Bot limit exceeded (max 100)",
  "status": 429
}
```

---

> 🔥 This confirms strict enforcement using Redis atomic operations.

---

## 📂 Project Structure

```
src/
docker-compose.yml
postman_collection.json
README.md
```

---

## 🧠 Key Takeaways

* Redis enables **real-time decision making**
* Atomic operations prevent race conditions
* Stateless design improves scalability
* Batching improves user experience

---

## 📌 Final Note

This project demonstrates practical backend design involving:

* Concurrency control
* Rate limiting
* Stateless architecture
* Real-time processing with Redis

---

👉 KRISHNA
