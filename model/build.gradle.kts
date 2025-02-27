group = "com.SReMake"
version = "0.0.1-SNAPSHOT"


dependencies {
    implementation(starter.spring.boot.starter.security)
    implementation(jimmer.sql)

    annotationProcessor(apt.jimmer)
    annotationProcessor(apt.lombok)
    compileOnly(apt.lombok)
}