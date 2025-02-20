group = "com.SReMake.common"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(starter.spring.boot.starter.web)
    api(starter.jimmer.spring.boot.starter)
    annotationProcessor(apt.lombok)
    compileOnly(apt.lombok)
}
