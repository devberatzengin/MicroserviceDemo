package org.devberatzengin.starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.devberatzengin") // 🔍 Tüm servis ve controller'ları tara
@EnableJpaRepositories(basePackages = "org.devberatzengin.repository") // 💾 Repositories katmanını zorla ayağa kaldır kanka
@EntityScan(basePackages = "org.devberatzengin.model")
@Slf4j
public class PatientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientServiceApplication.class, args);
    }

}
