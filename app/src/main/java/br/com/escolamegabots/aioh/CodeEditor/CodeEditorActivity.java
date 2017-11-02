package br.com.escolamegabots.aioh.CodeEditor;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.blockly.android.AbstractBlocklyActivity;
import com.google.blockly.android.codegen.CodeGenerationRequest;
import com.google.blockly.android.codegen.LanguageDefinition;
import com.google.blockly.model.DefaultBlocks;

import java.util.Arrays;
import java.util.List;

import br.com.escolamegabots.aioh.R;

import static com.google.common.base.Preconditions.checkNotNull;

public class CodeEditorActivity extends AbstractBlocklyActivity implements CodeEditorContract.View {

    private static final String TAG = "CodeGeneratorActivity";

    private static final String SAVE_FILENAME = "lua_workspace.xml";
    private static final String AUTOSAVE_FILENAME = "lua_workspace_temp.xml";

    private static final List<String> BLOCK_DEFINITIONS = DefaultBlocks.getAllBlockDefinitions();
    private static final List<String> LUA_GENERATORS = Arrays.asList();

    private static final LanguageDefinition LUA_LANGUAGE_DEF
            = new LanguageDefinition("lua_generator_compressed.js", "Blockly.Lua");

    private Handler mHandler;

    private ProgressDialog mUploadDialog = null;

    private CodeEditorContract.Presenter mPresenter = null;

    CodeGenerationRequest.CodeGeneratorCallback mCodeGeneratorCallback =
        (String generatedCode) -> {
            mHandler.post(() -> {
                mPresenter.uploadAndRunCode(generatedCode);
            });
        };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CodeEditorPresenter(this);
        mHandler = new Handler();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    protected void onRunCode(){
        this.mUploadDialog = ProgressDialog.show(this, "",
                getString(R.string.code_editor_uploading_code), true);
        super.onRunCode();
    }

    @Override
    public void setPresenter(@NonNull CodeEditorContract.Presenter presenter) {
        this.mPresenter = checkNotNull(presenter);
    }

    @Override
    protected View onCreateContentView(int parentId) {
        View root = getLayoutInflater().inflate(R.layout.activity_code_editor, null);
        return root;
    }

    @NonNull
    @Override
    protected String getToolboxContentsXmlPath() {
        return DefaultBlocks.TOOLBOX_PATH;
    }

    @NonNull
    @Override
    protected List<String> getBlockDefinitionsJsonPaths() {
        return BLOCK_DEFINITIONS;
    }

    @NonNull
    @Override
    protected LanguageDefinition getBlockGeneratorLanguage() {
        return LUA_LANGUAGE_DEF;
    }

    @NonNull
    @Override
    protected List<String> getGeneratorsJsPaths() {
        return LUA_GENERATORS;
    }

    @NonNull
    @Override
    protected CodeGenerationRequest.CodeGeneratorCallback getCodeGenerationCallback() {
        return mCodeGeneratorCallback;
    }

    @Override
    protected void onInitBlankWorkspace() {
        // Initialize available variable names.
        //getController().addVariable("item");
    }

    /**
     * Optional override of the save path, since this demo Activity has multiple Blockly
     * configurations.
     * @return Workspace save path used by this Activity.
     */
    @Override
    @NonNull
    protected String getWorkspaceSavePath() {
        return SAVE_FILENAME;
    }

    /**
     * Optional override of the auto-save path, since this demo Activity has multiple Blockly
     * configurations.
     * @return Workspace auto-save path used by this Activity.
     */
    @Override
    @NonNull
    protected String getWorkspaceAutosavePath() {
        return AUTOSAVE_FILENAME;
    }

    @Override
    public void closeUploadDialog() {
        if(this.mUploadDialog != null) {
            mUploadDialog.dismiss();
            mUploadDialog = null;
        }
    }

    @Override
    public void showRunResult(String message) {
        this.closeUploadDialog();
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }
}
