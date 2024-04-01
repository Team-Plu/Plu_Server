plugins {
    kotlin("jvm")
}
tasks.jar {
    enabled = true
}

tasks.bootJar {
    enabled = false
}

val queryDslVersion = "5.0.0" // QueryDSL Version Setting

dependencies {
    implementation(project(":plu-common"))

    // DB
    runtimeOnly("com.mysql:mysql-connector-j")

    // JPA
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    // QueryDSL
    api("com.querydsl:querydsl-jpa:${queryDslVersion}:jakarta")
    kapt("com.querydsl:querydsl-apt:${queryDslVersion}:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
    implementation(kotlin("stdlib-jdk8"))
}

/**
 * QueryDSL Build Options
 */
val querydslDir = "build/querydsl/generated"

sourceSets {
    getByName("main").kotlin.srcDirs(querydslDir)
}

tasks.withType<JavaCompile> {
    options.generatedSourceOutputDirectory.set(file(querydslDir))
}

tasks.named("clean") {
    doLast {
        file(querydslDir).deleteRecursively()
    }
}
repositories {
    mavenCentral()
}
kotlin {
    jvmToolchain(17)
}
