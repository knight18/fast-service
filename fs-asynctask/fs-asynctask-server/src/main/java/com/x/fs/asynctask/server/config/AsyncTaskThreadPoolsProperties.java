package com.x.fs.asynctask.server.config;

import com.x.fs.asynctask.server.dto.PrivateProperties;
import com.x.fs.asynctask.server.dto.PublicProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author x
 */
@Configuration
@ConfigurationProperties(prefix = "fs.asynctask")
public class AsyncTaskThreadPoolsProperties {

    private PublicProperties publicProperties;

    private List<PrivateProperties> privateProperties;

    public PublicProperties getPublicProperties() {
        return publicProperties;
    }

    public void setPublicProperties(PublicProperties publicProperties) {
        this.publicProperties = publicProperties;
    }

    public List<PrivateProperties> getPrivateProperties() {
        return privateProperties;
    }

    public void setPrivateProperties(List<PrivateProperties> privateProperties) {
        this.privateProperties = privateProperties;
    }
}
