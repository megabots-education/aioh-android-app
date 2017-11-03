package br.com.escolamegabots.aioh.tasks;

/**
 * Created by Gustavo Freitas on 02/11/2017.
 */

public interface AsyncTaskListener<T> {
    void onTaskFinish(AsyncTaskResult<T> responseCode);
}
