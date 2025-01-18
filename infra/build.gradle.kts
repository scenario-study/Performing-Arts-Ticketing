plugins {
    kotlin("plugin.spring")
    kotlin("plugin.jpa") version "1.9.25"
    kotlin("kapt")

    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}

val queryDslVersion: String by extra

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5:2.18.0")

    // querydsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")
}

val generated = file("src/main/generated")
tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory = generated
}

sourceSets {
    main {
        kotlin.srcDirs += file("${projectDir}/build/generated")
    }
}

tasks.named("clean") {
    doLast {
        generated.deleteRecursively()
    }
}


kapt {
    generateStubs = true
}
