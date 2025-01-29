# Performing Arts Ticketing (공연 티켓팅 플랫폼)
### Tech Stack
- Language: Kotlin
- Framework: Spring Boot 3.3
- DB : MySQL
- Persistence: JPA, QueryDSL


<br><br>

### Directory Structure



#### batch module
```
batch/
├── job/                   # Job 관련 클래스
├── scheduler/             # 스케줄링 관련 클래스
├── step/                  # Step 관련 클래스
├── task/                  # Tasklet 또는 Chunk 기반 작업 클래스
└── BatchApplication.kt    # 메인 애플리케이션
```
