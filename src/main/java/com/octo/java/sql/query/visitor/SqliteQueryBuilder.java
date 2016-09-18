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

import com.octo.java.sql.query.QueryException;

/**
 * <p>
 * SqliteQueryBuilder creates queries that can be used with the android's sqlite framework.
 * <p>
 * For example:
 * 
 * <pre>
 * SELECT column1,column2,column3,column4,column5
 * FROM table
 * WHERE (((column1 = ?) AND (column2 = ?)) AND (column3 = ?))
 * </pre>
 */
public class SqliteQueryBuilder extends DefaultQueryBuilder {

  /**
   * Always uses a question mark as placeholder instead of a dedicated variable name.
   */
  @Override
  protected void acceptOrVisitValue(final Object value, final String baseName) throws QueryException {
    if (value instanceof Visitable)
      ((Visitable) value).accept(this);
    else {
      addVariable(value, baseName);
      result.append("?");
    }
  }

  /**
   * Does not create variables for the columns.
   */
  @Override
  public void visitValue(final Object value) {
    result.append(value.toString());
  }

}
