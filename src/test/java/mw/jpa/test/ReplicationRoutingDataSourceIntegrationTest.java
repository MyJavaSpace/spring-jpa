package mw.jpa.test;

import mw.jpa.test.config.WithRoutingDataSourceConfig;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

/**
 * Replication with {@link kr.pe.kwonnam.replicationdatasource.routingdatasource.ReplicationRoutingDataSource}.
 */
@ContextConfiguration(classes = {WithRoutingDataSourceConfig.class})
@DirtiesContext
public class ReplicationRoutingDataSourceIntegrationTest extends AbstractReplicationDataSourceIntegrationTest {
}
