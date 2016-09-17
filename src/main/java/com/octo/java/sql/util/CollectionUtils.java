package com.octo.java.sql.util;

import java.util.Collection;
import java.util.Iterator;

public class CollectionUtils {

  // prevent instantiation
  private CollectionUtils() {
  }

  /**
   * Null-safe check if the specified collection is empty.
   * <p>
   * Null returns true.
   *
   * @param coll
   *          the collection to check, may be null
   * @return true if empty or null
   */
  public static boolean isEmpty(Collection<?> coll) {
    return (coll == null || coll.isEmpty());
  }

  /**
   * Answers true if a predicate is true for at least one element of a collection.
   * <p>
   * A <code>null</code> collection or predicate returns false.
   *
   * @param collection
   *          the collection to get the input from, may be null
   * @param predicate
   *          the predicate to use, may be null
   * @return true if at least one element of the collection matches the predicate
   */
  public static boolean exists(Collection<?> collection, Predicate predicate) {
    if (collection != null && predicate != null) {
      for (Iterator<?> it = collection.iterator(); it.hasNext();) {
        if (predicate.evaluate(it.next())) {
          return true;
        }
      }
    }
    return false;
  }

  public interface Predicate {

    boolean evaluate(Object object);

  }
}
