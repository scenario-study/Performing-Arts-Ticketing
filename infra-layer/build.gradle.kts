plugins {
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("plugin.jpa")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.25")

    implementation(project(":domain-layer:performance-context"))
    implementation(project(":domain-layer:seat-context"))
    implementation(project(":domain-layer:user-context"))

    // jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // mysql
    runtimeOnly("com.mysql:mysql-connector-j")
}

tasks {
    bootJar {
        enabled = false
    }
}
