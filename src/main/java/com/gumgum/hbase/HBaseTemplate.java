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

import java.util.Iterator;

import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

/**
 * Helper class that simplifies Hbase data access code. This class will call HTablePool's getTable and
 * closeHtable methods before and after your action.
 *
 * @author Vaibhav Puranik
 * @version $Id: HBaseTemplate.java 11124 2012-01-27 23:05:57Z vaibhav $
 */
public class HBaseTemplate {

    private HTablePool hTablePool;

    public HBaseTemplate(HTablePool hTablePool) {
        this.hTablePool = hTablePool;
    }

    public Object execute(String tablename, TableCallback action) {
        Object result = null;
        HTableInterface hTable = hTablePool.getTable(tablename);
        try {
            result = action.doWithTable(hTable);
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in execute method:", e);
        } finally {
            if (hTable != null) {
                try {
		    hTable.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;
    }

    public void executeWithScan(String tableName, final Scan scan, final RowCallback action) {
        this.execute(tableName, new TableCallback() {
            @Override
            public Object doWithTable(HTableInterface hTable) throws Exception {
                ResultScanner scanner = hTable.getScanner(scan);
                try {
                    Iterator<Result> it = scanner.iterator();
                    while (it.hasNext()) {
                        action.doInRow(it.next());
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Exception occured in executeWithScan method:", e);
                } finally {
                    if (scanner != null) {
                        scanner.close();
                    }
                }
                return null;
            }
        });
    }

    public HTablePool getHTablePool() {
        return hTablePool;
    }

    public void setHTablePool(HTablePool tablePool) {
        hTablePool = tablePool;
    }
}

