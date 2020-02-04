package br.com.clinic.boot;

import br.com.clinic.application.utils.DefaultPatternLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {
        "br.com.clinic.api",
        "br.com.clinic.application",
        "br.com.clinic.configuration",
        "br.com.clinic.domain",
        "br.com.clinic.gateway"
})
@EntityScan("br.com.clinic.domain")
@EnableJpaRepositories("br.com.clinic.application")
public class ClinicApp {

    private static final DefaultPatternLogger LOG = DefaultPatternLogger.getLogger(ClinicApp.class);

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(ClinicApp.class);
        final Environment env = app.run(args).getEnvironment();

        LOG.info(String.format("\n\n-----------------------------------------------------" +
                "\n APPLICATION %s RUNNING!!!" +
                "\n On Port: %s" +
                "\n With Profile: %s" +
                "\n-----------------------------------------------------\n",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                Arrays.deepToString(env.getActiveProfiles())));

    }

}
