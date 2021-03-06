package com.framex.persistence.framexconfig;

import javax.persistence.*;

/**
 * @author lijie
 * @date 2017/10/1 22:54
 * @description
 */
@Entity
@Table(name = "framex_module", schema = "framex", catalog = "")
public class FramexModule {
    private String rowGuid;
    private String moduleName;
    private String moduleUri;
    private String moduleIp;
    private String modulePort;
    private String rpcPort;
    private String servicePackageName;
    private String version;

    @Id
    @Column(name = "RowGuid")
    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    @Basic
    @Column(name = "ModuleName")
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Basic
    @Column(name = "ModuleUri")
    public String getModuleUri() {
        return moduleUri;
    }

    public void setModuleUri(String moduleUri) {
        this.moduleUri = moduleUri;
    }

    @Basic
    @Column(name = "ModuleIp")
    public String getModuleIp() {
        return moduleIp;
    }

    public void setModuleIp(String moduleIp) {
        this.moduleIp = moduleIp;
    }

    @Basic
    @Column(name = "ModulePort")
    public String getModulePort() {
        return modulePort;
    }

    public void setModulePort(String modulePort) {
        this.modulePort = modulePort;
    }

    @Basic
    @Column(name = "servicePackageName")
    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    @Basic
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FramexModule that = (FramexModule) o;

        if (rowGuid != null ? !rowGuid.equals(that.rowGuid) : that.rowGuid != null) return false;
        if (moduleName != null ? !moduleName.equals(that.moduleName) : that.moduleName != null) return false;
        if (moduleUri != null ? !moduleUri.equals(that.moduleUri) : that.moduleUri != null) return false;
        if (moduleIp != null ? !moduleIp.equals(that.moduleIp) : that.moduleIp != null) return false;
        if (modulePort != null ? !modulePort.equals(that.modulePort) : that.modulePort != null) return false;
        if (rpcPort != null ? !rpcPort.equals(that.rpcPort) : that.rpcPort != null) return false;
        if (servicePackageName != null ? !servicePackageName.equals(that.servicePackageName) : that.servicePackageName != null)
            return false;
        return version != null ? version.equals(that.version) : that.version == null;
    }

    @Override
    public int hashCode() {
        int result = rowGuid != null ? rowGuid.hashCode() : 0;
        result = 31 * result + (moduleName != null ? moduleName.hashCode() : 0);
        result = 31 * result + (moduleUri != null ? moduleUri.hashCode() : 0);
        result = 31 * result + (moduleIp != null ? moduleIp.hashCode() : 0);
        result = 31 * result + (modulePort != null ? modulePort.hashCode() : 0);
        result = 31 * result + (rpcPort != null ? rpcPort.hashCode() : 0);
        result = 31 * result + (servicePackageName != null ? servicePackageName.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "RpcPort")
    public String getRpcPort() {
        return rpcPort;
    }

    public void setRpcPort(String rpcPort) {
        this.rpcPort = rpcPort;
    }
}
