/**
* Copyright 2012 GumGum Inc.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.gumgum.hbase;

import java.util.Properties;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.beans.factory.FactoryBean;

/**
 * Creates HBase Configuration for the given HBase master.
 * @author Vaibhav Puranik
 * @version $Id: HBaseConfigurationFactoryBean.java 9392 2011-07-19 23:19:39Z ken $
 */
public class HBaseConfigurationFactoryBean implements FactoryBean {

    /**
     * You can set various hbase client properties.
     * hbase.zookeeper.quorum must be set.
     */
    private Properties hbaseProperties;

    @Override
    public Object getObject() throws Exception {
        if (hbaseProperties != null) {
            Configuration config = HBaseConfiguration.create();
            Set<String> propertyNames = hbaseProperties.stringPropertyNames();
            for (String propertyName : propertyNames) {
                config.set(propertyName, hbaseProperties.getProperty(propertyName));
            }
            return config;
        } else {
            throw new RuntimeException("hbase properties cannot be null");
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class getObjectType() {
        return HBaseConfiguration.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Properties getHbaseProperties() {
        return hbaseProperties;
    }

    public void setHbaseProperties(Properties hbaseProperties) {
        this.hbaseProperties = hbaseProperties;
    }
}
