package mw.jpa.rwdb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {
    private Logger log = LoggerFactory.getLogger(ReplicationRoutingDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceType = TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? "read" : "write";
        log.info("current dataSourceType : {}", dataSourceType);
        return dataSourceType;
    }
}
