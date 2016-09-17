package com.octo.java.sql.util;

public class ArrayUtils {

  // prevent instantiation
  private ArrayUtils() {
  }

  /**
   * <p>
   * Checks if an array of Objects is empty or <code>null</code>.
   * </p>
   *
   * @param array
   *          the array to test
   * @return <code>true</code> if the array is empty or <code>null</code>
   */
  public static boolean isEmpty(Object[] array) {
    return array == null || array.length == 0;
  }
}
