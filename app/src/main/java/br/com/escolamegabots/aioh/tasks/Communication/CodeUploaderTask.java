package br.com.escolamegabots.aioh.tasks.Communication;

import br.com.escolamegabots.aioh.tasks.AsyncTask;
import br.com.escolamegabots.aioh.tasks.AsyncTaskListener;
import br.com.escolamegabots.aioh.tasks.AsyncTaskResult;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Gustavo Freitas on 01/11/2017.
 */
public class CodeUploaderTask extends AsyncTask<String, Void, Response> {

    private static final MediaType BODY_TYPE
            = MediaType.parse("text/plain; charset=utf-8");

    private static final Headers CODE_UPLOAD_HEADERS = new Headers.Builder()
            .add("action", "create-file")
            .add("file-name", "project.lua")
            .build();

    public CodeUploaderTask(AsyncTaskListener<Response> listener) {
        super(listener);
    }

    @Override
    protected AsyncTaskResult<Response> doInBackground(String... strings) {

        AsyncTaskResult<Response> taskResult = null;
        OkHttpClient client = new OkHttpClient();

        this.param = strings[0];

        RequestBody body = RequestBody.create(BODY_TYPE, this.param);

        try {
            Request request = new Request.Builder()
                    .url(strings[1])
                    .post(body)
                    .headers(CODE_UPLOAD_HEADERS)
                    .build();
            taskResult =  new AsyncTaskResult<>(client.newCall(request).execute());
        }
        catch (Exception e) {
            taskResult = new AsyncTaskResult<>(e);
        }

        return taskResult;
    }
}
