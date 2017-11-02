package br.com.escolamegabots.aioh.tasks;

/**
 * Created by Gustavo Freitas on 02/11/2017.
 */

public class AssyncTaskResult<T> {

    private T result = null;
    private Exception error = null;

    public AssyncTaskResult(T result) {
        super();
        this.result = result;
    }

    public AssyncTaskResult(Exception error) {
        super();
        this.error = error;
    }

    public T getResult() {
        return result;
    }

    public Exception getError() {
        return error;
    }

    public boolean isSuccessful(){
        return (error == null);
    }
}
