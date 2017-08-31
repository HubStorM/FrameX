package com.framex.core.constant;

import java.util.Objects;

public class One2OneSyncService extends SyncAbstractService {

    @Override
    public ServiceType getSelfType() {
        return ServiceType.ONE2ONE_SYNC;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + serviceGuid.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object that) {
        if(this == that)
            return true;
        if(!(that instanceof AbstractService))
            return false;
        AbstractService service = (AbstractService) that;
        return Objects.equals(serviceGuid, service.getServiceGuid());
    }
}
