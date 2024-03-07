package com.example.booksreview.configuration;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;


@ContextConfiguration(initializers = TestContainersDatabaseConfiguration.Initializer.class)
public class TestContainersDatabaseConfiguration {

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        private static final Logger LOGGER = LoggerFactory.getLogger(Initializer.class);

        static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:12");

        private static void initContainer() {
//            Startables.deepStart(Stream.of(postgreSQLContainer)).join();
            postgreSQLContainer
                    .withInitScript("scripts/integrationtestinitscript.sql")
                    .start();
        }

        private static Map<String, Object> createConnectionConfiguration() {
            return Map.of(
                    "spring.datasource.url", postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username", postgreSQLContainer.getUsername(),
                    "spring.datasource.password", postgreSQLContainer.getPassword()
            );
        }

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            /* Inicialize o contêiner que contém o PostgreSQL */
            initContainer();

            /* Recuperar as configurações de ambiente do contexto do Spring */
            ConfigurableEnvironment environment = applicationContext.getEnvironment();

            /* Criar configuração do testContainers para o Spring reconhecer */
            MapPropertySource testContainersProperties =
                    new MapPropertySource("testContainers", createConnectionConfiguration());

            /* Adiciona as propriedades de configuração do banco dinamicamente */
            environment.getPropertySources().addFirst(testContainersProperties);
        }
    }
}