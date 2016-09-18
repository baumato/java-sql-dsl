/**
 * Copyright (C) 2016 Tobias Baumann <tobbaumann@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.octo.java.sql.query.visitor;

import static com.octo.java.sql.query.Query.c;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

import com.octo.java.sql.query.Query;
import com.octo.java.sql.query.SelectQuery;

public class SqliteQueryBuilderTest {
  
  @Test
  public void testShouldUseQuestionMarksInSelectQuery() throws Exception {
    
    SelectQuery query = Query.select("column1", "column2", "column3", "column4", "column5")
      .from("table")
      .where(c("column1")).eq("value1")
        .and(c("column2")).eq("value2")
        .and(c("column3")).eq("value3");
    
    String sql = query.toSql(new SqliteQueryBuilder());
    Map<String, Object> params = query.getParams();
    System.out.println(sql);
    String expected = "SELECT column1,column2,column3,column4,column5 "
        + "FROM table "
        + "WHERE (((column1 = ?) AND (column2 = ?)) AND (column3 = ?))";
    System.out.println(params);
    assertEquals(expected, sql);
    assertEquals("[value1, value2, value3]", params.values().toString());
  }
  
  
}
