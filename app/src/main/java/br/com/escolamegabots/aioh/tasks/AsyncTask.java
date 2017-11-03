package br.com.escolamegabots.aioh.tasks;

/**
 * Created by Gustavo Freitas on 02/11/2017.
 */

public abstract class AsyncTask<Param, Progress, Result> extends android.os.AsyncTask<Param, Progress, AsyncTaskResult<Result>> {

    protected Param param;
    protected AsyncTaskListener<Result> listener;

    public AsyncTask(AsyncTaskListener<Result> listener){
        this.listener = listener;
    }

    public AsyncTask(Param param, AsyncTaskListener<Result> listener){
        this.param = param;
        this.listener = listener;
    }

    protected void onPostExecute(AsyncTaskResult<Result> response) {
        this.listener.onTaskFinish(response);
    }
}
