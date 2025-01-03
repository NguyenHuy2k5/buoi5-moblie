package com.example.dialogd04k15;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public abstract class TodoDialog extends Dialog {
    private EditText edtTodo;
    private Button btnAdd;
    public TodoDialog(@NonNull Context context) {
        super(context);
    }
    public abstract void add(String todo);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_todo);

        edtTodo = findViewById(R.id.edt_todo);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = edtTodo.getText().toString();
                if (todo.isEmpty()){
                    edtTodo.setError("bat buoc nhap");
                    return;
                }
                add(todo);
                edtTodo.setText("");
                dismiss();

            }
        });

    }
}
