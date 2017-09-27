package com.framex.persistence.framexconfig;

import javax.persistence.*;

/**
 * @author lijie
 * @date 2017/9/10 14:57
 * @description
 */
@Entity
@Table(name = "framex_module", schema = "framex")
public class FramexModule {
    private String rowGuid;
    private String moduleName;
    private String moduleUri;
    private String moduleIp;
    private String modulePort;
    private String servicePackageName;

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
    @Column(name = "ServicePackageName")
    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = rowGuid != null ? rowGuid.hashCode() : 0;
        result = 31 * result + (moduleName != null ? moduleName.hashCode() : 0);
        result = 31 * result + (moduleUri != null ? moduleUri.hashCode() : 0);
        result = 31 * result + (moduleIp != null ? moduleIp.hashCode() : 0);
        result = 31 * result + (modulePort != null ? modulePort.hashCode() : 0);
        return result;
    }
}
