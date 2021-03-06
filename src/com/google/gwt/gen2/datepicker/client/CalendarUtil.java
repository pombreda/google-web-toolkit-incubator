/*
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.gwt.gen2.datepicker.client;

import com.google.gwt.i18n.client.LocaleInfo;

import java.util.Date;

/**
 * Useful utilities for creating views of a calendar.
 * 
 * @deprecated use the com.google.gwt.user.datepicker.client classes instead
 */
@Deprecated
public class CalendarUtil {

  /**
   * Adds the given number of days to a date.
   * 
   * @param date the date
   * @param days number of days
   */
  public static void addDaysToDate(Date date, int days) {
    date.setDate(date.getDate() + days);
  }

  /**
   * Adds the given number of months to a date.
   * 
   * @param date the date
   * @param months number of months
   */
  public static void addMonthsToDate(Date date, int months) {
    if (months != 0) {
      int month = date.getMonth();
      int year = date.getYear();

      int resultMonthCount = year * 12 + month + months;
      int resultYear = resultMonthCount / 12;
      int resultMonth = resultMonthCount - resultYear * 12;

      date.setMonth(resultMonth);
      date.setYear(resultYear);
    }
  }

  /**
   * Copies a date.
   * 
   * @param date the date
   * @return the copy
   */
  public static Date copyDate(Date date) {
    if (date == null) {
      return null;
    }
    Date newDate = new Date();
    newDate.setTime(date.getTime());
    return newDate;
  }

  /**
   * Returns the number of days between the two dates. Time is ignored.
   * 
   * @param start starting date
   * @param finish ending date
   * @return the different
   */
  public static int getDaysBetween(Date start, Date finish) {
    if (hasTime(start)) {
      start = copyDate(start);
      resetTime(start);
    }

    if (hasTime(finish)) {
      finish = copyDate(finish);
      resetTime(finish);
    }

    long aTime = start.getTime();
    long bTime = finish.getTime();

    long adjust = 60 * 60 * 1000;
    adjust = (bTime > aTime) ? adjust : -adjust;

    return (int) ((bTime - aTime + adjust) / (24 * 60 * 60 * 1000));
  }

  /**
   * Returns the day of the week on which week starts in the current locale. The
   * range between 0 for Sunday and 6 for Saturday.
   * 
   * @return the day of the week
   */
  public static int getStartingDayOfWeek() {
    return Integer.parseInt(LocaleInfo.getCurrentLocale().getDateTimeConstants().firstDayOfTheWeek()) - 1;
  }

  /**
   * Is a day in the week a weekend?
   * 
   * @param dayOfWeek day of week
   * @return is the day of week a weekend?
   */
  public static boolean isWeekend(int dayOfWeek) {
    return dayOfWeek == (Integer.parseInt(LocaleInfo.getCurrentLocale().getDateTimeConstants().weekendRange()[0]) - 1) || dayOfWeek == (Integer.parseInt(LocaleInfo.getCurrentLocale().getDateTimeConstants().weekendRange()[1]) - 1);
  }

  /**
   * Resets the date to have no time modifiers.
   * 
   * @param date the date
   */
  public static void resetTime(Date date) {
    long msec = date.getTime();
    msec = (msec / 1000) * 1000;
    date.setTime(msec);

    date.setHours(0);
    date.setMinutes(0);
    date.setSeconds(0);
  }

  /**
   * Sets a date object to be at the beginning of the month and no time
   * specified.
   * 
   * @param date the date
   */
  public static void setToFirstDayOfMonth(Date date) {
    resetTime(date);
    date.setDate(1);
  }

  private static boolean hasTime(Date start) {
    return start.getHours() != 0 || start.getMinutes() != 0
        || start.getSeconds() != 0;
  }
}
