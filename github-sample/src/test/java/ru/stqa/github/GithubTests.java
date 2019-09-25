package ru.stqa.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {
    @Test

    public void testCommits() throws IOException {
        Github github = new RtGithub("c26158d28932edcf950af34dc58e40a5edc3796b");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("svo216", "java_studying")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
