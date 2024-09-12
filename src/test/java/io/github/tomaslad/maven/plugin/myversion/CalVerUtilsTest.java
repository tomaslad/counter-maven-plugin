package io.github.tomaslad.maven.plugin.myversion;

import io.github.tomaslad.maven.plugin.myversion.calver.CalVer;
import io.github.tomaslad.maven.plugin.myversion.calver.CalVerUtils;
import io.github.tomaslad.maven.plugin.myversion.semver.SemVer;
import io.github.tomaslad.maven.plugin.myversion.semver.SemVerUtils;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class CalVerUtilsTest {

    @Test
    public void testParse() {
        String v1 = "v2.11.3-rc.2";
        CalVer calVer = CalVerUtils.parse(v1);
        assertThat(calVer.getMajor()).isEqualTo(2);
        assertThat(calVer.getMinor()).isEqualTo(11);
        assertThat(calVer.getMicro()).isEqualTo(3);
        assertThat(calVer.getModifier()).isEqualTo("rc.2");

        String v2 = "v2.11.3";
        CalVer calVer2 = CalVerUtils.parse(v2);
        assertThat(calVer2.getMajor()).isEqualTo(2);
        assertThat(calVer2.getMinor()).isEqualTo(11);
        assertThat(calVer2.getMicro()).isEqualTo(3);
        assertThat(calVer2.getModifier()).isEmpty();

        String v3 = "v2.11.3-alpha";
        CalVer calVer3 = CalVerUtils.parse(v3);
        assertThat(calVer3.getMajor()).isEqualTo(2);
        assertThat(calVer3.getMinor()).isEqualTo(11);
        assertThat(calVer3.getMicro()).isEqualTo(3);
        assertThat(calVer3.getModifier()).isEqualTo("alpha");
    }
}
