plugins {
    java
    war
}

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    testImplementation(libs.junit.junit)
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.json:json:20230227")
    implementation("com.google.code.gson:gson:2.8.9")
    implementation("org.hibernate:hibernate-core:5.6.14.Final")
    implementation("org.hibernate:hibernate-entitymanager:5.6.14.Final")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.springframework.security:spring-security-crypto:5.7.7")
}

group = "org.example"
version = "1.0-SNAPSHOT"

tasks.test {
    useJUnitPlatform()
}

tasks.war {
    from("C:/Users/Ксеня/Desktop/Web/lab4/lab4_new/web")
}