package com.framex.soa;

import com.framex.core.constant.ServiceType;
import com.framex.core.constant.SoaServiceInfo;

public interface SoaInterface {
    void soaRegisterService(SoaServiceInfo service, ServiceType type);
}
