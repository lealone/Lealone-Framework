/*
 * Copyright Lealone Database Group.
 * Licensed under the Server Side Public License, v 1.
 * Initial Developer: zhh
 */
package org.lealone.plugins.bench.tpcc;

import org.lealone.plugins.bench.tpcc.codefutures.TpccLoad;

public class PostgreSQLTpccLoad {
    public static void main(String[] args) {
        System.setProperty("tpcc.config", "postgresql/tpcc.properties");
        TpccLoad.main(args);
    }
}
