package com.octo.java.sql.util;

import java.util.Collection;
import java.util.Iterator;

public class StringUtils {

  public static final String EMPTY = "";

  // prevent instantiation
  private StringUtils() {
  }

  /**
   * <p>
   * Checks if a String is empty ("") or null.
   * </p>
   *
   * <pre>
   * StringUtils.isEmpty(null)      = true
   * StringUtils.isEmpty("")        = true
   * StringUtils.isEmpty(" ")       = false
   * StringUtils.isEmpty("bob")     = false
   * StringUtils.isEmpty("  bob  ") = false
   * </pre>
   *
   * <p>
   * NOTE: This method changed in Lang version 2.0. It no longer trims the String. That functionality is
   * available in isBlank().
   * </p>
   *
   * @param str
   *          the String to check, may be null
   * @return <code>true</code> if the String is empty or null
   */
  public static boolean isEmpty(String str) {
    return str == null || str.isEmpty();
  }

  /**
   * <p>
   * Joins the elements of the provided array into a single String containing the provided list of elements.
   * </p>
   *
   * <p>
   * No delimiter is added before or after the list. Null objects or empty strings within the array are
   * represented by empty strings.
   * </p>
   *
   * <pre>
   * StringUtils.join(null, *)               = null
   * StringUtils.join([], *)                 = ""
   * StringUtils.join([null], *)             = ""
   * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
   * StringUtils.join(["a", "b", "c"], null) = "abc"
   * StringUtils.join([null, "", "a"], ';')  = ";;a"
   * </pre>
   *
   * @param array
   *          the array of values to join together, may be null
   * @param separator
   *          the separator character to use
   * @return the joined String, <code>null</code> if null array input
   */
  public static String join(Object[] array, char separator) {
    if (array == null) {
      return null;
    }
    return join(array, separator, 0, array.length);
  }

  /**
   * <p>
   * Joins the elements of the provided array into a single String containing the provided list of elements.
   * </p>
   *
   * <p>
   * No delimiter is added before or after the list. Null objects or empty strings within the array are
   * represented by empty strings.
   * </p>
   *
   * <pre>
   * StringUtils.join(null, *)               = null
   * StringUtils.join([], *)                 = ""
   * StringUtils.join([null], *)             = ""
   * StringUtils.join(["a", "b", "c"], ';')  = "a;b;c"
   * StringUtils.join(["a", "b", "c"], null) = "abc"
   * StringUtils.join([null, "", "a"], ';')  = ";;a"
   * </pre>
   *
   * @param array
   *          the array of values to join together, may be null
   * @param separator
   *          the separator character to use
   * @param startIndex
   *          the first index to start joining from. It is an error to pass in an end index past the end of
   *          the array
   * @param endIndex
   *          the index to stop joining from (exclusive). It is an error to pass in an end index past the end
   *          of the array
   * @return the joined String, <code>null</code> if null array input
   */
  public static String join(Object[] array, char separator, int startIndex, int endIndex) {
    if (array == null) {
      return null;
    }
    int bufSize = (endIndex - startIndex);
    if (bufSize <= 0) {
      return EMPTY;
    }

    bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + 1);
    StringBuilder b = new StringBuilder(bufSize);

    for (int i = startIndex; i < endIndex; i++) {
      if (i > startIndex) {
        b.append(separator);
      }
      if (array[i] != null) {
        b.append(array[i]);
      }
    }
    return b.toString();
  }

  /**
   * <p>
   * Gets the <code>toString</code> of an <code>Object</code> returning an empty string ("") if
   * <code>null</code> input.
   * </p>
   * 
   * <pre>
   * ObjectUtils.toString(null)         = ""
   * ObjectUtils.toString("")           = ""
   * ObjectUtils.toString("bat")        = "bat"
   * ObjectUtils.toString(Boolean.TRUE) = "true"
   * </pre>
   * 
   * @see StringUtils#defaultString(String)
   * @see String#valueOf(Object)
   * @param obj
   *          the Object to <code>toString</code>, may be null
   * @return the passed in Object's toString, or nullStr if <code>null</code> input
   */
  public static String toString(Object obj) {
    return obj == null ? "" : obj.toString();
  }

  /**
   * <p>
   * Joins the elements of the provided <code>Iterator</code> into a single String containing the provided
   * elements.
   * </p>
   *
   * <p>
   * No delimiter is added before or after the list. A <code>null</code> separator is the same as an empty
   * String ("").
   * </p>
   *
   * <p>
   * See the examples here: {@link #join(Object[],String)}.
   * </p>
   *
   * @param iterator
   *          the <code>Iterator</code> of values to join together, may be null
   * @param separator
   *          the separator character to use, null treated as ""
   * @return the joined String, <code>null</code> if null iterator input
   */
  public static String join(Iterator<?> iterator, String separator) {

    // handle null, zero and one elements before building a buffer
    if (iterator == null) {
      return null;
    }
    if (!iterator.hasNext()) {
      return EMPTY;
    }
    Object first = iterator.next();
    if (!iterator.hasNext()) {
      return toString(first);
    }

    // two or more elements
    StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
    if (first != null) {
      buf.append(first);
    }

    while (iterator.hasNext()) {
      if (separator != null) {
        buf.append(separator);
      }
      Object obj = iterator.next();
      if (obj != null) {
        buf.append(obj);
      }
    }
    return buf.toString();
  }

  /**
   * <p>
   * Joins the elements of the provided <code>Collection</code> into a single String containing the provided
   * elements.
   * </p>
   *
   * <p>
   * No delimiter is added before or after the list. A <code>null</code> separator is the same as an empty
   * String ("").
   * </p>
   *
   * <p>
   * See the examples here: {@link #join(Object[],String)}.
   * </p>
   *
   * @param collection
   *          the <code>Collection</code> of values to join together, may be null
   * @param separator
   *          the separator character to use, null treated as ""
   * @return the joined String, <code>null</code> if null iterator input
   * @since 2.3
   */
  public static String join(Collection<?> collection, String separator) {
    if (collection == null) {
      return null;
    }
    return join(collection.iterator(), separator);
  }

}
