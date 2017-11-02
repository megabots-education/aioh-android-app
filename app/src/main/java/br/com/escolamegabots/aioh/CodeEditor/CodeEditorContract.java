package br.com.escolamegabots.aioh.CodeEditor;

import android.content.Context;

import br.com.escolamegabots.aioh.BasePresenter;
import br.com.escolamegabots.aioh.BaseView;

/**
 * Created by Gustavo Freitas on 01/11/2017.
 */

public class CodeEditorContract {

    interface View extends BaseView<Presenter>{
        public void closeUploadDialog();
        public void showRunResult(String message);
        public Context getContext();
    }

    interface Presenter extends BasePresenter {
        public void uploadCode(String sourceCode);
        public void uploadAndRunCode(String sourceCode);
    }
}
