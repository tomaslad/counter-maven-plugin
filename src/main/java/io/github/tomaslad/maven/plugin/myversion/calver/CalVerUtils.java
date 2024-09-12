package io.github.tomaslad.maven.plugin.myversion.calver;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public final class CalVerUtils {

    public static CalVer parse(String version) {
        if (version == null || version.isEmpty()) {
            throw new IllegalArgumentException("Version string is null or empty.");
        }

        CalVer.CalVerBuilder builder = CalVer.builder();
        builder.modifier("");

        // v1.0.0
        // v1.0.0-rc.1
        String[] parts = version.split("-");

        for (String part : parts) {
            if (part.startsWith("v")) {
                String[] parts2 = part.split("\\.");
                builder.major(Integer.parseInt(parts2[0].substring(1)));
                builder.minor(Integer.parseInt(parts2[1]));
                builder.micro(Integer.parseInt(parts2[2]));
            } else {
                builder.modifier(part);
            }
        }

        return builder.build();
    }
}