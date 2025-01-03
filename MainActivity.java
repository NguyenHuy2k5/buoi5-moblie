package com.example.dialogd04k15;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvTile;
    private ListView lvList;
    private Button btnPlus;
    private TodoDialog todoDialog = null;
    private List<String> data;
    private ArrayAdapter<String> arrayAdapter;
    private AlertDialog alertDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvTile = findViewById(R.id.tv_tile);
        lvList = findViewById(R.id.lv_list);
        btnPlus = findViewById(R.id.btnPlus);

        data = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lvList.setAdapter(arrayAdapter);


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todoDialog == null){
                    todoDialog = new TodoDialog(MainActivity.this) {
                        @Override
                        public void add(String todo) {
                            data.add(todo);
                            arrayAdapter.notifyDataSetChanged();
                        }
                    };
                }
                todoDialog.show();
            }
        });
        
        lvList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (alertDialog == null) {
                    alertDialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Cảnh báo")
                            .setMessage("Bạn có chắc chắn muốn xóa")
                            .setCancelable(false)
                            .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    data.remove(position);
                                    arrayAdapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();
                }
                alertDialog.show();
                return false;
            }
        });



    }
}