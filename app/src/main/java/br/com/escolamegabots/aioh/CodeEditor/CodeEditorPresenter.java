package br.com.escolamegabots.aioh.CodeEditor;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import br.com.escolamegabots.aioh.tasks.AsyncTaskResult;
import br.com.escolamegabots.aioh.tasks.Communication.CodeUploaderTask;
import br.com.escolamegabots.aioh.R;
import okhttp3.Response;

/**
 * Created by Gustavo Freitas on 01/11/2017.
 */

public class CodeEditorPresenter implements CodeEditorContract.Presenter {

    private Context mViewContext;
    private CodeEditorContract.View mCodeEditorView;

    public CodeEditorPresenter(@NonNull CodeEditorContract.View codeEditorView) {
        this.mCodeEditorView = codeEditorView;
        this.mViewContext = codeEditorView.getContext();
    }

    @Override
    public void start() {

    }

    @Override
    public void uploadCode(String sourceCode) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mViewContext);
        String address = preferences.getString(mViewContext.getString(R.string.pref_aioh_address), null);

        CodeUploaderTask codeUploaderTask = new CodeUploaderTask((AsyncTaskResult<Response> result) -> {

            int stringId;

            Log.w("Upload Code Response", "\t" + result);

            if(result.isSuccessful()){
                stringId = R.string.code_editor_upload_success;
            }
            else{
                Log.w("Upload Exception", "\t" + result.getError());
                if(result.getError() instanceof UnknownHostException){
                    stringId = R.string.code_editor_unknown_host_error;
                }
                else if(result.getError() instanceof SocketTimeoutException){
                    stringId = R.string.code_editor_connection_timeour_error;
                }
                else if(result.getError() instanceof IllegalArgumentException){
                    stringId = R.string.code_editor_invalid_hostname;
                }
                else{
                    stringId = R.string.code_editor_cant_upload;
                }
            }

            mCodeEditorView.showRunResult(mViewContext.getString(stringId));
        });
        Log.w("Upload Code", "\t Trying to upload code at address " + address);
        codeUploaderTask.execute(sourceCode, address);
    }

    @Override
    public void uploadAndRunCode(String sourceCode) {
        this.uploadCode(sourceCode);
        Log.w("Código Gerado", "\t" + sourceCode);
    }

}
