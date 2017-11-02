package br.com.escolamegabots.aioh.CodeEditor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.net.UnknownHostException;

import br.com.escolamegabots.aioh.tasks.AssyncTaskResult;
import br.com.escolamegabots.aioh.tasks.CodeUploaderTask;
import br.com.escolamegabots.aioh.R;
import okhttp3.Response;

/**
 * Created by Gustavo Freitas on 01/11/2017.
 */

public class CodeEditorPresenter implements CodeEditorContract.Presenter {

    private CodeUploaderTask mCodeUploaderTask;
    private CodeEditorContract.View mCodeEditorView;
    private Context mViewContext;

    public CodeEditorPresenter(@NonNull CodeEditorContract.View codeEditorView) {
        this.mCodeEditorView = codeEditorView;
        this.mViewContext = codeEditorView.getContext();
    }

    @Override
    public void start() {

    }

    @Override
    public void uploadCode(String sourceCode) {
        this.mCodeUploaderTask = new CodeUploaderTask(sourceCode, (AssyncTaskResult<Response> result) -> {
            Log.w("Upload Code Response", "\t" + result);
            if(result.isSuccessful()){
                mCodeEditorView.showRunResult(mViewContext.getString(R.string.code_editor_upload_success));
            }
            else{
                Log.w("Upload Exception", "\t" + result.getError());
                if(result.getError() instanceof UnknownHostException){
                    mCodeEditorView.showRunResult(mViewContext.getString(R.string.code_editor_unknown_host_error));
                }
                else{
                    mCodeEditorView.showRunResult(mViewContext.getString(R.string.code_editor_cant_upload));
                }
            }
        });
        mCodeUploaderTask.execute();
    }

    @Override
    public void uploadAndRunCode(String sourceCode) {
        this.uploadCode(sourceCode);
        Log.w("Código Gerado", "\t" + sourceCode);
    }

}
