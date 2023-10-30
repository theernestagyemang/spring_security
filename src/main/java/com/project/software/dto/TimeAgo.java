
package com.project.software.dto;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class TimeAgo {

    public static final List<Long> times = Arrays.asList(
            TimeUnit.DAYS.toMillis(365),
            TimeUnit.DAYS.toMillis(30),
            TimeUnit.DAYS.toMillis(1),
            TimeUnit.HOURS.toMillis(1),
            TimeUnit.MINUTES.toMillis(1),
            TimeUnit.SECONDS.toMillis(1));
    public static final List<String> timesString = Arrays.asList("year", "month", "day", "hour", "minute", "second");


    public static String toDuration2(long duration) {


        StringBuilder res = new StringBuilder();

        for (int i = 0; i < TimeAgo.times.size(); i++) {
            Long current = TimeAgo.times.get(i);
            long temp = duration / current;
            if (temp > 0) {
                res.append(temp).append(" ").append(TimeAgo.timesString.get(i)).append(temp != 1 ? "s" : "").append(" ago");
                break;
            }
        }
        if (res.toString().isEmpty())
            return "Not Provided";
        else
            return res.toString();
    }

    public String toDuration(Date date) {

        if (date == null) {
            return "Not Provided";
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < TimeAgo.times.size(); i++) {
            Long current = TimeAgo.times.get(i);
            long duration = getDuration(date);
            long temp = duration / current;
            if (temp > 0) {
                res.append(temp).append(" ").append(TimeAgo.timesString.get(i)).append(temp != 1 ? "s" : "").append(" ago");
                break;
            }
        }
        if (res.toString().isEmpty())
            return "Not Provided";
        else
            return res.toString();
    }


    private long getDuration(Date firstDate) {

        Date secondDate = new Date();
        long diffInMills = Math.abs(secondDate.getTime() - firstDate.getTime());

        return TimeUnit.MILLISECONDS.convert(diffInMills, TimeUnit.MILLISECONDS);
    }
}

