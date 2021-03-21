plugins {
    kotlin("jvm") version "1.4.21"
    id("au.com.dius.pact") version "4.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val kotlin_version by extra { "1.4.21" }
val jacksonVersion by extra { "2.10.3" }
val ktorVersion by extra { "1.4.1" }

repositories {
    jcenter()
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlin_version")
    implementation("org.koin:koin-ktor:2.0.1")
    implementation("io.ktor:ktor-server-netty:1.2.6")
    implementation("com.github.Cloudmersive:Cloudmersive.APIClient.Java:v3.62")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonVersion")
    implementation("io.ktor:ktor-jackson:1.2.6")

    //consumer dependency
    testImplementation("au.com.dius:pact-jvm-consumer-junit5:4.0.10")
    testImplementation("au.com.dius.pact.consumer:junit:4.1.0")

    //provider dependency
    testImplementation("au.com.dius:pact-jvm-provider-junit5:4.0.10")

    testImplementation("io.mockk:mockk:1.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")

    api("ch.qos.logback:logback-classic:1.2.3")
}

tasks {

    create("consumerContractTest", Test::class) {
        useJUnitPlatform()
        filter {
            includeTestsMatching("br.com.pact.accountservice.contract.consumer.*")
            includeTestsMatching("br.com.pact.signupservice.contract.consumer.*")
        }
    }

    pactPublish { dependsOn("consumerContractTest") }

    test {
        useJUnitPlatform()
        systemProperties["pact.rootDir"] = "$buildDir/pacts"
    }

    pact {
        publish {
            pactBrokerUrl = "http://localhost:9292"
        }
    }
}

