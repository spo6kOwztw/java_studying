package ru.stqa.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import ru.stqa.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

public class TestBase {

    public Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "aaa");
    }


    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json?limit=5000")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        String json = getExecutor().execute(Request
                .Get(String.format("http://bugify.stqa.ru/api/issues/1861.json")))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonArray issues = parsed.getAsJsonObject().getAsJsonArray("issues");
        String state_name = issues.get(0).getAsJsonObject().get("state_name").getAsString();
        if (state_name.equals("Open")) {
            return true;
        }
        return false;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}