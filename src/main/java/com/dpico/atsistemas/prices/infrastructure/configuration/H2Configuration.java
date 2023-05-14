package com.dpico.atsistemas.prices.infrastructure.configuration;

import org.springframework.boot.autoconfigure.domain.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;

@Configuration
@EnableJpaRepositories(basePackages = "com.dpico.atsistemas.prices.infrastructure.repository.h2")
@ConfigurationProperties("spring.datasource")
@EntityScan(basePackages = "com.dpico.atsistemas.prices.infrastructure.dbo")
public class H2Configuration {}
