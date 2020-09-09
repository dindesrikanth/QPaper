package com.example.questionpaper.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.questionpaper.Adapter.Loadcources_Recyclerviewadapter;
import com.example.questionpaper.Model.Course;
import com.example.questionpaper.Model.Loginmodel;
import com.example.questionpaper.Model.UserCourses;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Load_cources extends AppCompatActivity implements Loadcources_Recyclerviewadapter.OnItemClickListenerload {
    Loadcources_Recyclerviewadapter recyclerViewAdapter;
    RecyclerView recyclerView;
    private List<Course> courseList;
    String KEY_SAVED_USER_ID = "SAVE_USER_ID";
    private ArrayList<Long> user_courses_final2 ;
    private List<UserCourses> user_courses_final ;
    TextView testing;
    Button submit;
    private Long quennow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_cources);
         recyclerView = findViewById(R.id.load_recycle);
        testing = findViewById(R.id.testing);
        submit = findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course_submit();
            }
        });
        getcources();
        SharedPreferences sharedPreferences = getSharedPreferences("MY_PREF",MODE_PRIVATE);
        //int savedradioindex = sharedPreferences.getInt(KEY_SAVED_INDEX,0);
        //getquestionno = sharedPreferences.getLong(KEY_SAVED_Question_no,0);
         quennow = sharedPreferences.getLong(KEY_SAVED_USER_ID,0);
        Log.i("loadpre",""+quennow);
    }
    public void getcources(){
          Call<List<Course>> call = RetrofitClient.getInstance().getApi().getAllCourses();
        call.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                try {
                    courseList = response.body();
                    recyclerViewAdapter = new Loadcources_Recyclerviewadapter(courseList, getApplicationContext());
                    recyclerViewAdapter.setOnItemClickListener(Load_cources.this);
                    GridLayoutManager GridLayoutManager = new GridLayoutManager(getApplicationContext(), 2, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(GridLayoutManager);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }
                catch (Exception ex){
                    //testing.setText(ex.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
               // testing.setText(t.getMessage());
            }

        }  );
    }

   // @Override
   // public void quesclick(List<Integer> checkid) {
      //  user_courses_final2 = checkid;
        /*user_courses_final = new ArrayList<UserCourses>();
        for(int i=0; i< user_courses_final2.size(); i++){
            final UserCourses thisusercourse = new UserCourses();
            thisusercourse.setUser_id((long) 1);
            thisusercourse.setCourse_id(user_courses_final2.get(i));
            thisusercourse.setUser_main_course_id("Y");
            thisusercourse.setCreatedBy("Hasnu");
            thisusercourse.setUpdatedBy("H");
            thisusercourse.setCreatedAt(null);
            thisusercourse.setUpdatedAt(null);
           // Log.i("values in array","" + selectedCourses.get(i).getId() );
            Log.i("values in array","" + thisusercourse );
            user_courses_final.add(thisusercourse);
        }*/
  //  }
    public  void course_submit(){

        final List<UserCourses> user_courses_load = user_courses_final;

       // final UserCourses user_courses_load = new UserCourses((long)1, (long) 34,"hasnu",null,null,null,null);
        Call<List<UserCourses>> call = RetrofitClient.getInstance().getApi().newUserCourses( user_courses_load);
        call.enqueue(new Callback<List<UserCourses>>() {
            @Override
            public void onResponse(Call<List<UserCourses>> call, Response<List<UserCourses>> response) {
                try {
                    Log.d("code",""+response.code());
                        int i = response.code();
                        if(i==200) {
                            Toast toast = Toast.makeText(Load_cources.this, "Your Courses are submited", Toast.LENGTH_LONG);
                            toast.show();
                            Intent mainscreen = new Intent(Load_cources.this, GetUserTest.class);
                            startActivity(mainscreen);
                        }
                        return;

                }catch (Exception ex){
                    Log.d("error",""+ex.getMessage());
                    return;
                }
            }

            @Override
            public void onFailure(Call<List<UserCourses>> call, Throwable t) {
                testing.setText(t.getMessage());
            }
        });
    }

    @Override
    public void quesclick(ArrayList<Long> checkid) {
        user_courses_final2 = checkid;
        Log.i("msg",""+checkid);
        user_courses_final = new ArrayList<UserCourses>();
        for(int i=0; i< user_courses_final2.size(); i++) {
            // UserCourses thisusercourse = new UserCourses();
           final UserCourses thisusercourse = new UserCourses();

            thisusercourse.setUser_id(quennow);
            thisusercourse.setCourse_id(user_courses_final2.get(i));
            Log.i("msg",""+user_courses_final2.get(i));
            thisusercourse.setUser_main_course_id("Y");
            thisusercourse.setCreatedBy("Hasnu");
            thisusercourse.setUpdatedBy("H");
            thisusercourse.setCreatedAt(null);
            thisusercourse.setUpdatedAt(null);
            // Log.i("values in array","" + selectedCourses.get(i).getId() );
            Log.i("values in array", "" + thisusercourse);
            user_courses_final.add(thisusercourse);
        }
    }

}
