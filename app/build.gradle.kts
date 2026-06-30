plugins {
    `java-library`
    checkstyle
}

group = "hexlet.code"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.14.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

checkstyle {
    toolVersion = "10.21.4"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
