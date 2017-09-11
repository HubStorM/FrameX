package com.framex.persistence;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@Configurable
@ComponentScan("com.framex")
@ImportResource("spring-persistence.xml")
public class PersistentConfig {

}
