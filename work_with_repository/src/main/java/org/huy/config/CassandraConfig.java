package org.huy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@EnableCassandraRepositories(basePackages = {"org.huy.repository"})
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.cassandra.contact-points}")
    private String contactPoints;

    @Value("${spring.cassandra.port}")
    private Integer port;

    @Value("${spring.cassandra.keyspace-name}")
    private String keyspace;

    @Value("${spring.cassandra.local-datacenter}")
    private String localDataCenter;

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


}
