/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.proxy.backend.text.distsql.ral.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.distsql.parser.statement.ral.common.ShowDistSQLStatement;
import org.apache.shardingsphere.proxy.backend.communication.jdbc.connection.JDBCConnectionSession;
import org.apache.shardingsphere.proxy.backend.response.header.ResponseHeader;
import org.apache.shardingsphere.proxy.backend.text.TextProtocolBackendHandler;
import org.apache.shardingsphere.proxy.backend.text.distsql.ral.common.show.ShowStatementExecutor;
import org.apache.shardingsphere.proxy.backend.text.distsql.ral.common.show.ShowStatementExecutorFactory;

import java.sql.SQLException;
import java.util.Collection;

/**
 * Show dist sql backend handler.
 */
@RequiredArgsConstructor
@Getter
public final class ShowDistSQLBackendHandler implements TextProtocolBackendHandler {
    
    private final ShowDistSQLStatement sqlStatement;
    
    private final JDBCConnectionSession connectionSession;
    
    private ShowStatementExecutor showStatementExecutor;
    
    @Override
    public ResponseHeader execute() throws SQLException {
        showStatementExecutor = ShowStatementExecutorFactory.newInstance(sqlStatement, connectionSession);
        return showStatementExecutor.execute();  
    }
    
    @Override
    public boolean next() throws SQLException {
        return showStatementExecutor.next();
    }
    
    @Override
    public Collection<Object> getRowData() throws SQLException {
        return showStatementExecutor.getQueryResponseRow().getData();
    }
}
