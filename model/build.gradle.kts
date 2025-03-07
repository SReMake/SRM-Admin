group = "com.SReMake"
version = "0.0.1-SNAPSHOT"


dependencies {
    implementation(starter.spring.boot.starter.security)
    implementation(jimmer.sql)
    annotationProcessor(aptAndKsp.jimmer)
    annotationProcessor(aptAndKsp.lombok)
    compileOnly(aptAndKsp.lombok)
}