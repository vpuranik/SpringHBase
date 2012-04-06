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

import org.apache.hadoop.hbase.client.Result;

/**
 * Defines methods to get a Result from the given scan. Modeled after DataSource interface in JDBC.
 *
 * @author Vaibhav Puranik
 * @version $Id: HBaseCallback.java 5623 2010-05-27 21:27:39Z ken $
 */
public interface RowCallback {

    /**
     * Gets called by HbaseTemplate.executeInRow with an active Scanner for the given scan.
     * @param result Result instance for the given scan.
     * @return a result object, or null if none
     * @throws Exception
     */
    void doInRow(Result result) throws Exception;
}
