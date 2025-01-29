plugins {
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}

dependencies {
    implementation(project(":common"))
    
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework:spring-tx")
    compileOnly("org.springframework:spring-context")
    compileOnly("org.springframework:spring-web")
}
