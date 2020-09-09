package com.example.questionpaper.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.questionpaper.Adapter.Get_Test_indicator_adapter;
import com.example.questionpaper.Adapter.Get_test_header_adapter;
import com.example.questionpaper.Model.Get_user_test;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserTest extends AppCompatActivity {
RecyclerView recyclerView_header,recyclerView_body;
    Get_Test_indicator_adapter recyclerViewAdapter1;
    Get_test_header_adapter recyclerViewAdapter2;
    //private List<Get_user_test> testlist;
    public  List<Get_user_test> testList = new ArrayList<Get_user_test>();
    TextView testing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_test);
        recyclerView_header = findViewById(R.id.Header);
        recyclerView_body = findViewById(R.id.indicater);
        testing = findViewById(R.id.testing);
        gettest();
    }
    public void gettest(){
        Call<List<Get_user_test>> call = RetrofitClient.getInstance().getApi().getAllTests();
        call.enqueue(new Callback<List<Get_user_test>>() {
            @Override
            public void onResponse(Call<List<Get_user_test>> call, Response<List<Get_user_test>> response) {
                try {
                    testing.setText(""+response.code());
                    testList = response.body();



                    HashSet<String> courses = new HashSet <String>();

                    for(int i =0; i < testList.size(); i++){
                        courses.add(testList.get(i).getCourse_name());
                    }
                    final List<String> courses_list = new ArrayList<>() ;
                    for (String s : courses ) {
                        courses_list.add(s);
                        System.out.println("Value: " +s);
                    }

                    Get_test_header_adapter.callback indicatorCallback = new Get_test_header_adapter.callback() {
                        @Override
                        public void onTitleClicked(int position) {
                            String course = courses_list.get(position);
                            List<Get_user_test> restauranList1 = new ArrayList<Get_user_test>();
                            for (int i=0;i< testList.size();i++) {
                                Get_user_test rest = new Get_user_test();
                                if(course.equals(testList.get(i).getCourse_name())){
                                    rest = testList.get(i);
                                    restauranList1.add(rest);
                                }
                            }

                            recyclerViewAdapter1 = new Get_Test_indicator_adapter(restauranList1, getApplicationContext(), course);
                            // recyclerViewAdapter.setOnItemClickListener(Load_cources.this);
                            LinearLayoutManager Linearmang = new LinearLayoutManager(GetUserTest.this, LinearLayoutManager.VERTICAL, false);
                            recyclerView_body.setLayoutManager(Linearmang);
                            recyclerView_body.setAdapter(recyclerViewAdapter1);
                        }
                    };
                    String course_first = courses_list.get(0);
                    // String course = courses_list.get(position);
                    List<Get_user_test> restauranList1 = new ArrayList<Get_user_test>();
                    for (int i=0;i< testList.size();i++) {
                        Get_user_test rest = new Get_user_test();
                        if(course_first.equals(testList.get(i).getCourse_name())){
                            rest = testList.get(i);
                            restauranList1.add(rest);
                        }
                    }
                    recyclerViewAdapter1 = new Get_Test_indicator_adapter(restauranList1, getApplicationContext(),course_first);
                    // recyclerViewAdapter.setOnItemClickListener(Load_cources.this);
                    LinearLayoutManager Linearmang = new LinearLayoutManager(GetUserTest.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView_body.setLayoutManager(Linearmang);
                    recyclerView_body.setAdapter(recyclerViewAdapter1);

                     recyclerViewAdapter2 = new Get_test_header_adapter(courses_list, getApplicationContext(),indicatorCallback);
                    // recyclerViewAdapter.setOnItemClickListener(Load_cources.this);
                    LinearLayoutManager Linearmanger = new LinearLayoutManager(GetUserTest.this, LinearLayoutManager.HORIZONTAL, false);
                    recyclerView_header.setLayoutManager(Linearmanger);
                    recyclerView_header.setAdapter(recyclerViewAdapter2);
                }

                catch (Exception ex){
                    testing.setText(""+ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Get_user_test>> call, Throwable t) {
                testing.setText(""+t.getMessage());
            }
        });
        }
    }

