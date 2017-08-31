module com.framex.ipc {
    requires com.framex.core;
    requires zookeeper;
    requires curator.recipes;
    requires curator.client;
    requires curator.framework;
    provides com.framex.ipc.IpcInterface with com.framex.ipc.DefaultIpc;
}