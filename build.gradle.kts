plugins {
    java
    id("org.springframework.boot") version "3.3.0"
    id("io.spring.dependency-management") version "1.1.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    // подключи зависимость для RandomStringUtils
    implementation("org.apache.commons:commons-lang3:3.12.0")
    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
    implementation("org.slf4j:slf4j-api:2.0.7")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
