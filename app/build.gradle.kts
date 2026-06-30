plugins {
    `java-library`
    checkstyle
    jacoco
    `maven-publish`
    id("org.sonarqube") version "7.3.1.8318"
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

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}

sonar {
    properties {
        property("sonar.projectKey", "bedrevpaul23_java-project-78")
        property("sonar.organization", "bedrevpaul23")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.named("sonar") {
    dependsOn(tasks.jacocoTestReport)
}

tasks.register("install") {
    dependsOn("publishToMavenLocal")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
