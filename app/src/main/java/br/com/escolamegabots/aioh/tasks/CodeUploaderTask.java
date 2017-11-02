package br.com.escolamegabots.aioh.tasks;

import android.os.AsyncTask;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Gustavo Freitas on 01/11/2017.
 */
public class CodeUploaderTask extends AsyncTask<String, Void, AssyncTaskResult<Response>> {

    public static final MediaType BODY_TYPE
            = MediaType.parse("text/plain; charset=utf-8");

    public static final String AIOH_DEFAULT_URL = "http://aiohdevice.local";

    public static final Headers CODE_UPLOAD_HEADERS = new Headers.Builder()
            .add("action", "create-file")
            .add("file-name", "project.lua")
            .build();

    public interface CodeUploaderTaskListener{
        void onCodeUploaded(AssyncTaskResult<Response> responseCode);
    }

    private String sourceCode;
    private CodeUploaderTaskListener listener;

    public CodeUploaderTask(String sourceCode, CodeUploaderTaskListener listener){
        this.sourceCode = sourceCode;
        this.listener = listener;
    }

    @Override
    protected AssyncTaskResult<Response> doInBackground(String... strings) {

        AssyncTaskResult<Response> taskResult = null;
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(BODY_TYPE, this.sourceCode);

        Request request = new Request.Builder()
                .url(AIOH_DEFAULT_URL)
                .post(body)
                .headers(CODE_UPLOAD_HEADERS)
                .build();

        try {
            taskResult =  new AssyncTaskResult<>(client.newCall(request).execute());
        }
        catch (Exception e) {
            taskResult = new AssyncTaskResult<>(e);
        }

        return taskResult;
    }

    protected void onPostExecute(AssyncTaskResult<Response> response) {
        this.listener.onCodeUploaded(response);
    }
}
