package com.soft.chat.config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import org.cognitor.cassandra.migration.Database;
import org.cognitor.cassandra.migration.MigrationConfiguration;
import org.cognitor.cassandra.migration.MigrationRepository;
import org.cognitor.cassandra.migration.MigrationTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class CassandraMigrationConfig {
    private final String migrationSciptsLocation;
    private final String keyspaceName;
    private final String localDatacenter;
    private final String address;
    private final int port;

    public CassandraMigrationConfig(@Value("${cassandra.migration.script-locations}")
                                    String migrationSciptsLocation,
                                    @Value("${spring.data.cassandra.keyspace-name}")
                                    String keyspaceName,
                                    @Value("${spring.data.cassandra.local-datacenter}")
                                    String localDatacenter,
                                    @Value("${spring.data.cassandra.contact-points}")
                                    String address,
                                    @Value("${spring.data.cassandra.port}")
                                    int port) {
        this.migrationSciptsLocation = migrationSciptsLocation;
        this. keyspaceName = keyspaceName;
        this.localDatacenter = localDatacenter;
        this.address = address;
        this.port = port;
        performMigration();
    }

    private void performMigration() {
        MigrationConfiguration migrationConfig = new MigrationConfiguration()
                .withKeyspaceName(keyspaceName);
        CqlSession cqlSession = new CqlSessionBuilder()
//                .withKeyspace(keyspaceName)
//                .withLocalDatacenter(localDatacenter)
//                .addContactPoint(new InetSocketAddress(address, port))
                .build();
        Database database = new Database(cqlSession, migrationConfig);
        MigrationRepository migrationRepository = new MigrationRepository(migrationSciptsLocation);
        MigrationTask migration = new MigrationTask(database, migrationRepository);
        migration.migrate();
    }
}
