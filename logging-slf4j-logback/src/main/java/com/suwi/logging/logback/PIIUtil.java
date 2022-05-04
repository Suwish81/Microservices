package com.suwi.logging.logback;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PIIUtil {
    private static final String[] maskPatterns = {"SSN","TIN"};
    private static final String patternSchema ="\"[a-zA-Z]*{0}\"\\s*:\\s*\"(.*?)\"|\\\\\"[a-zA-Z]*{0}\\\\\"\\s*:\\s*\\\\\"(.*?)\\\\\"";

    public static  String maskJsonPIISensitiveInfo(String s) {

        String patternFormat= Arrays.stream(maskPatterns)
                .map(c-> MessageFormat.format(patternSchema,c))
                .collect(Collectors.joining("|"));

        Pattern aplpliedPattern = Pattern.compile(patternFormat,
                Pattern.MULTILINE|Pattern.CASE_INSENSITIVE );

        StringBuilder sb = new StringBuilder(s);
        Matcher matcher = aplpliedPattern.matcher(sb);

        while (matcher.find()) {
            IntStream.rangeClosed(1, matcher.groupCount()).forEach(group -> {
                if (matcher.group(group) != null) {
                    IntStream.range(matcher.start(group),
                            matcher.end(group)).forEach(i -> sb.setCharAt(i, '*'));
                }
            });
        }
        return sb.toString();

    }
}
