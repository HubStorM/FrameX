package com.framex.persistence.framexconfig;

import javax.persistence.*;

/**
 * @author lijie
 * @date 2017/9/10 14:57
 * @description
 */
@Entity
@Table(name = "framex_config", schema = "framex")
public class FramexConfig {
    private String rowGuid;
    private String configName;
    private String configValue;

    @Id
    @Column(name = "RowGuid")
    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    @Basic
    @Column(name = "ConfigName")
    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    @Basic
    @Column(name = "ConfigValue")
    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FramexConfig that = (FramexConfig) o;

        if (rowGuid != null ? !rowGuid.equals(that.rowGuid) : that.rowGuid != null) return false;
        if (configName != null ? !configName.equals(that.configName) : that.configName != null) return false;
        if (configValue != null ? !configValue.equals(that.configValue) : that.configValue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rowGuid != null ? rowGuid.hashCode() : 0;
        result = 31 * result + (configName != null ? configName.hashCode() : 0);
        result = 31 * result + (configValue != null ? configValue.hashCode() : 0);
        return result;
    }
}
