group = "com.sreMake.common"
version = "0.0.1-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    implementation(starter.spring.boot.starter.web)
    implementation(utils.hutool.all)
    implementation(cloudServices.dysmsapi20180501)
    implementation(cloudServices.tencentcloud.sdk.java.sms)
    annotationProcessor(aptAndKsp.lombok)
    compileOnly(aptAndKsp.lombok)

}
