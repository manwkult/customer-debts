package co.com.evertec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaAuditing
@EnableCaching
@EnableAsync
public class MainApplication {

    @CacheEvict(value = {"CustomerDebt"}, allEntries = true)
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class);
    }
}