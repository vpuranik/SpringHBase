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

import org.apache.hadoop.hbase.client.HTable;

/**
 * Defines methods to get HTable and close HTable. Modeled after DataSource interface in JDBC.
 *
 * @author Vaibhav Puranik
 * @version $Id: HBaseCallback.java 3429 2009-07-21 02:10:28Z vaibhav $
 */
public interface HBaseCallback {

    /**
     * Gets called by HbaseTemplate.execute with an active HTable for the given tablename.
     * @param htable htable instance for the given tablename.
     * @return a result object, or null if none
     * @throws Exception
     */
    Object doInHbase(HTable htable) throws Exception;
}
