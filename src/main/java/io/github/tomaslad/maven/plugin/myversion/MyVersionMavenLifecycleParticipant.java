package io.github.tomaslad.maven.plugin.myversion;

import org.apache.maven.AbstractMavenLifecycleParticipant;
import org.apache.maven.MavenExecutionException;
import org.apache.maven.execution.MavenSession;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.logging.Logger;

@Component(role = AbstractMavenLifecycleParticipant.class, hint = "myversion")
public class MyVersionMavenLifecycleParticipant extends AbstractMavenLifecycleParticipant {

    @Requirement
    private Logger logger;

    @Override
    public void afterSessionStart(MavenSession mavenSession) throws MavenExecutionException {
        logger.info("Using myversion-maven-plugin");

        GitDescribe describe = GitUtils.describe();
        SemVer semVer = SemVer.parse(describe.getVersion());
        String version = semVer.toString();

        if (!describe.isReleaseCommit()) {
            version += "-SNAPSHOT";
        }

        mavenSession.getUserProperties().setProperty("MY_VERSION", version);
    }

    @Override
    public void afterProjectsRead(MavenSession session) throws MavenExecutionException {
        // ask a beer to the machine
    }

}