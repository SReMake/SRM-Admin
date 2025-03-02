group = "com.SReMake"
version = "0.0.1-SNAPSHOT"

dependencies {
    api(project(":model"))
    annotationProcessor(apt.jimmer)
    api(starter.jimmer.spring.boot.starter)
    implementation(starter.spring.boot.starter.security)
}