package com.framex.ipc;

import com.framex.core.constant.AbstractService;
import com.framex.core.constant.ServiceType;

public interface IpcInterface {
    void ipcRegisterService(AbstractService service, ServiceType type);

}
