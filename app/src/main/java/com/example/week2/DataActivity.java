package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class DataActivity extends AppCompatActivity {


    private Button create;
    private Button add;
    private Button search;
    private TextView data1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        create = findViewById(R.id.create_bt);
        add = findViewById(R.id.add_bt);
        search = findViewById(R.id.search_bt);
        data1 = findViewById(R.id.data_store_tv1);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                点击按钮 数据库就创建成功
                Connector.getWritableDatabase();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Album album = new Album();
                album.setAuthor("小春");
                album.setName("学习javaEE开发");
                album.setPages(400);
                album.setPrice(40.6);
                album.save();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                找到表中满足价格小于40的集合
                List<Album> bookList = DataSupport.where("price>?","40").find(Album.class);
                for (Album book:bookList){
//                    Log.d("datatest",book.getName());
                    data1.setText("查询大于40的数据：书名：" + book.getName() + " 作者：" + book.getAuthor());

                }
            }
        });
    }
}