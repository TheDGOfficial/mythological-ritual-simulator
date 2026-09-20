plugins {
    java
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.22"
    id("xyz.jpenilla.run-paper") version "3.1.0" 
}

group = "dev.practice"
version = "2.2.0"

dependencies {
    paperweight.paperDevBundle("26.2.build.+")
}

tasks {
    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(26))
    }

    runServer {
        minecraftVersion("26.2")

        downloadPlugins {
            hangar("ViaVersion", "5.12.1-SNAPSHOT+1065")
            hangar("ViaBackwards", "5.12.1-SNAPSHOT+631")
            hangar("BlockReports", "2.1")
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf("-Xlint:all", "-g", "-parameters"))
}
