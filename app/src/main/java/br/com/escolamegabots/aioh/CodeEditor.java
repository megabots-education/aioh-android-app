package br.com.escolamegabots.aioh;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.blockly.android.AbstractBlocklyActivity;
import com.google.blockly.android.codegen.CodeGenerationRequest;
import com.google.blockly.android.codegen.LoggingCodeGeneratorCallback;
import com.google.blockly.model.DefaultBlocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeEditor extends AbstractBlocklyActivity {

    private static final List<String> JAVASCRIPT_GENERATORS = Arrays.asList(
        // Custom block generators go here. Default blocks are already included.
    );

    CodeGenerationRequest.CodeGeneratorCallback mCodeGeneratorCallback =
            new LoggingCodeGeneratorCallback(this, "LoggingTag");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setContentView(R.layout.activity_code_editor);
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected String getToolboxContentsXmlPath() {
        return "default/toolbox.xml";
    }

    @NonNull
    @Override
    protected List<String> getBlockDefinitionsJsonPaths() {
        List<String> assetPaths = new ArrayList<>(DefaultBlocks.getAllBlockDefinitions());
        // Append your own block definitions here.
        return assetPaths;
    }

    @NonNull
    @Override
    protected List<String> getGeneratorsJsPaths() {
        return JAVASCRIPT_GENERATORS;
    }

    @NonNull
    @Override
    protected CodeGenerationRequest.CodeGeneratorCallback getCodeGenerationCallback() {
        return mCodeGeneratorCallback;
    }

    @Override
    protected void onInitBlankWorkspace() {
        // Initialize available variable names.
        getController().addVariable("item");
    }
}
