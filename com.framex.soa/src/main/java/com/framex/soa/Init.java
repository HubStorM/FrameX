package com.framex.soa;

import com.framex.persistence.framexconfig.Configuration;
import com.framex.persistence.framexconfig.ConfigurationHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lijie
 * @date 2017/9/24 23:04
 * @description 框架入口
 */
public abstract class Init {

    public static final Logger log = LoggerFactory.getLogger(Init.class);

    public boolean init(String classPathFileName){
        ClassPathResource configurationResource = new ClassPathResource(classPathFileName);
        try(InputStream input = configurationResource.getInputStream()){
            Yaml yaml = new Yaml();
            Configuration config = yaml.loadAs(input, Configuration.class);
            ConfigurationHolder.setConfiguration(config);
        } catch (IOException e) {
            log.debug("Parsing framex configuration file error.");
            e.printStackTrace();
            return false;
        }
        return true;
    };

    abstract boolean customProcedure();
}
