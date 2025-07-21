package org.huy.config;

import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.config.TypedDriverOption;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.config.SessionBuilderConfigurer;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

//@Configuration
//@EnableCassandraRepositories(basePackages = {"org.huy.repository"})
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${spring.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.cassandra.port}")
    private Integer port;

    @Value("${spring.cassandra.local-datacenter}")
    private String localDataCenter;

    @Value("${spring.cassandra.pool.max-requests-per-connection}")
    private Integer maxRequestsPerConnection;

    @Value("${spring.cassandra.pool.heartbeat-interval}")
    private Integer heartBeatInterval;

    @Value("${spring.cassandra.request.timeout}")
    private Integer timeout;

    @Value("${spring.cassandra.consistency-level}")
    private String consistencyLevel;

    @Override
    protected String getKeyspaceName() {
        return this.keyspace;
    }

    @Override
    protected String getContactPoints() {
        return this.contactPoints;
    }

    @Override
    protected int getPort() {
        return this.port;
    }

    @Override
    protected String getLocalDataCenter() {
        return this.localDataCenter;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.NONE;
    }

    @Override
    public SessionBuilderConfigurer getSessionBuilderConfigurer() {
        // Cần xem xét lại
        DriverConfigLoader configLoader = DriverConfigLoader.programmaticBuilder()
                // CONNECTION POOL
                .withInt(TypedDriverOption.CONNECTION_MAX_REQUESTS.getRawOption(), this.maxRequestsPerConnection)
                .withDuration(TypedDriverOption.HEARTBEAT_INTERVAL.getRawOption(), Duration.ofSeconds(this.heartBeatInterval))
                // REQUEST TIMEOUT
                .withDuration(TypedDriverOption.REQUEST_TIMEOUT.getRawOption(), Duration.ofSeconds(this.timeout))
                // CONSISTENCY level
                .withString(TypedDriverOption.REQUEST_CONSISTENCY.getRawOption(), this.consistencyLevel)
                .build();

        return builder -> builder.withConfigLoader(configLoader);
    }
}
