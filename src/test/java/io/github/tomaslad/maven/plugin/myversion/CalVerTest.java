package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.calver.CalVer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class CalVerTest {

    private CalVer calVer;

    @BeforeEach
    public void beforeEach() {
        calVer = calVer.builder().major(1).minor(0).micro(0).modifier("rc.2").build();
    }

    @Test
    public void calVerTest() {
        assertEquals("v1.0.0-rc.2", calVer.toString());
    }

    @Test
    public void calVerBumpMajorTest() {
        assertEquals("v2.0.0", calVer.withBumpMajor().toString());
    }

    @Test
    public void calVerBumpMinorTest() {
        assertEquals("v1.1.0", calVer.withBumpMinor().toString());
    }

    @Test
    public void calVerBumpMicroTest() {
        assertEquals("v1.0.1", calVer.withBumpMicro().toString());
    }

    @Test
    public void calVerBumpReleaseCandidateTest() {
        assertEquals("v1.0.0-rc.3", calVer.withBumpReleaseCandidate().toString());
    }

    @Test
    public void calVerChangeModifierTest() {
        assertEquals("v1.0.0-alpha", calVer.withChangeModifier("alpha").toString());
    }

}