//package org.huy.config;
//
//import com.datastax.oss.driver.api.core.CqlSession;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.cassandra.SessionFactory;
//import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
//import org.springframework.data.cassandra.config.SchemaAction;
//import org.springframework.data.cassandra.config.SessionFactoryFactoryBean;
//import org.springframework.data.cassandra.core.CassandraOperations;
//import org.springframework.data.cassandra.core.CassandraTemplate;
//import org.springframework.data.cassandra.core.convert.CassandraConverter;
//import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
//import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
//import org.springframework.data.cassandra.core.mapping.SimpleUserTypeResolver;
//import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
//
//import java.net.InetAddress;
//import java.net.InetSocketAddress;
//import java.net.UnknownHostException;
//import java.util.Objects;
//
//@Configuration
//@EnableCassandraRepositories(value = "org.huy.repository")
//public class RawCassandraConfig {
//
//    @Value("${spring.cassandra.keyspace-name}")
//    private String keyspace;
//
//    @Value("${spring.cassandra.contact-points}")
//    private String contactPoints;
//
//    @Value("${spring.cassandra.port}")
//    private Integer port;
//
//    @Value("${spring.cassandra.local-datacenter}")
//    private String localDataCenter;
//
////    @Bean
////    public CqlSession cqlSession() throws UnknownHostException {
////
////        InetAddress address = InetAddress.getByName("localhost");
////        return CqlSession.builder()
////                .withKeyspace("my-keyspace")
////                .addContactPoint(new InetSocketAddress(address, 9042))
////                .withLocalDatacenter("datacenter1").build();
////    }
//
//    @Bean
//    public CqlSessionFactoryBean cqlSessionFactory() {
//        CqlSessionFactoryBean factory = new CqlSessionFactoryBean();
//        factory.setContactPoints(Objects.requireNonNullElse(this.contactPoints, "localhost"));
//        factory.setPort(Objects.requireNonNullElse(this.port, 9042));
//        factory.setLocalDatacenter(this.localDataCenter);
//        factory.setKeyspaceName(this.keyspace);
//
//        return factory;
//    }
//
//    @Bean
//    public SessionFactoryFactoryBean sessionFactoryFactory(CqlSession session, CassandraConverter converter) {
//        SessionFactoryFactoryBean factoryFactory = new SessionFactoryFactoryBean();
//        factoryFactory.setSession(session);
//        factoryFactory.setConverter(converter);
//        factoryFactory.setSchemaAction(SchemaAction.NONE);
//
//        return factoryFactory;
//    }
//
//    @Bean
//    public CassandraMappingContext mappingContext() {
//        return new CassandraMappingContext();
//    }
//
//    @Bean
//    public CassandraConverter converter(CqlSession cqlSession, CassandraMappingContext mappingContext) {
//        MappingCassandraConverter cassandraConverter = new MappingCassandraConverter(mappingContext);
//        cassandraConverter.setUserTypeResolver(new SimpleUserTypeResolver(cqlSession));
//        return cassandraConverter;
//    }
//
//    @Bean
//    public CassandraOperations cassandraTemplate(SessionFactory sessionFactory, CassandraConverter converter) {
//        return new CassandraTemplate(sessionFactory, converter);
//    }
//
//}
