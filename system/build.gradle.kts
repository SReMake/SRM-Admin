group = "com.SReMake"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(utils.hutool.all)
    implementation(starter.spring.boot.starter.web)
    implementation(starter.jimmer.spring.boot.starter)
    runtimeOnly(utils.jimmer.client.swagger)
    annotationProcessor(apt.jimmer)
}