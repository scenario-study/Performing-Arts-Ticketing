plugins {
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))
    implementation(project(":infra"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Swagger
    implementation("org.springdoc:springdoc-openapi:2.6.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.27")

    // Database
    runtimeOnly("com.mysql:mysql-connector-j:8.4.0")
    implementation("com.zaxxer:HikariCP")
    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    implementation("org.glassfish:jakarta.el:4.0.2")
}
