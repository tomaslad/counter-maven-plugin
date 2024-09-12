package io.github.tomaslad.maven.plugin.myversion.calver;

import lombok.Builder;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

/**
 * https://calver.org
 */
@Slf4j
@Value
@Builder
public class CalVer {

    int major;
    int minor;
    int micro;
    String modifier;

    public CalVer withBumpMajor() {
        return CalVer.builder()
                .major(major + 1)
                .minor(0)
                .micro(0)
                .modifier("")
                .build();
    }

    public CalVer withBumpMinor() {
        return CalVer.builder()
                .major(major)
                .minor(minor + 1)
                .micro(0)
                .modifier("")
                .build();
    }

    public CalVer withBumpMicro() {
        return CalVer.builder()
                .major(major)
                .minor(minor)
                .micro(micro + 1)
                .modifier("")
                .build();
    }

    public CalVer withBumpReleaseCandidate() {
        if (modifier.startsWith("rc")) {
            String rcNumber = modifier.replace("rc.", "");
            return CalVer.builder()
                    .major(major)
                    .minor(minor)
                    .micro(micro)
                    .modifier("rc." + (Integer.parseInt(rcNumber) + 1))
                    .build();
        } else {
            log.info("Version didn't have release candidate -> set to rc.1");
            return CalVer.builder()
                    .major(major)
                    .minor(minor)
                    .micro(micro)
                    .modifier("rc.1")
                    .build();
        }
    }

    public CalVer withChangeModifier(String newModifier) {
        return CalVer.builder()
                .major(major)
                .minor(minor)
                .micro(micro)
                .modifier(newModifier)
                .build();
    }

    @Override
    public String toString() {
        String version = "v" + major + "." + minor + "." + micro;

        if (!modifier.isEmpty()) {
            version = version + "-" + modifier;
        }

        return version;
    }
}