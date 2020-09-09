package com.example.questionpaper.Network;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.questionpaper.Model.AnswerSubmitModel;
import com.example.questionpaper.Model.BeanOrderIdInput;
import com.example.questionpaper.Model.Course;
import com.example.questionpaper.Model.Dashboardmodel;
import com.example.questionpaper.Model.Get_user_test;
import com.example.questionpaper.Model.Leaderboardmodel;
import com.example.questionpaper.Model.Loginmodel;
import com.example.questionpaper.Model.OrderIdModel;
import com.example.questionpaper.Model.PrizeResponseModel;
import com.example.questionpaper.Model.Questionesmodel;
import com.example.questionpaper.Model.ScoreModel;
import com.example.questionpaper.Model.TestDetailRequestmodel;
import com.example.questionpaper.Model.UserCourses;
import com.example.questionpaper.Model.signinmodel;
import com.example.questionpaper.Model.user_response;
import com.example.questionpaper.Response.mytests.UpcomingTestsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static android.content.Context.MODE_PRIVATE;

public interface Api {
    //List<UserCourses> user_courses_final = null;
               
    //String KEY_SAVED_G = "SAVE_G";
    //SharedPreferences sharedPreferences = getSharedPreferences("MY_PREF",MODE_PRIVATE);
    //int savedradioindex = sharedPreferences.getInt(KEY_SAVED_INDEX,0);
    //getquestionno = sharedPreferences.getLong(KEY_SAVED_Question_no,0);
    //quennow = sharedPreferences.getLong(KEY_SAVED_USER_ID,0);
       // Log.i("loadpre",""+quennow);

    @GET("user-tests-info/1")
    Call<Dashboardmodel> getDashboardDetails();

    @GET("test-leader-board/1")
    Call<Leaderboardmodel> getLeaderBoardDetails();

    @POST("test-content")
    Call<PrizeResponseModel> getPrizeDetails(@Body TestDetailRequestmodel testDetailRequestmodel);

    @GET("getAllQuesDetails/3")
    Call<Object> getAllQuesDetails();
    @GET("getUserResult/1/1")
    Call<ScoreModel> getUserResult();
    @POST("saveUserExamResp")
    Call<user_response> userLogins(
            @Body user_response Response);
    @POST("login")
    Call<Loginmodel> signinnewuser(
            @Body Loginmodel Signin);
    @POST("addUser")
    Call<signinmodel> newregistration(
            @Body signinmodel Register);
    //@GET("getAllCourses/G")
    //Call<List<Course>> getAllCourses();
    @GET("getAllCourses/"+"G")
    Call<List<Course>> getAllCourses();
    @POST("saveUserEnrolledCourses")
    Call<List<UserCourses>> newUserCourses(
            @Body List<UserCourses> UserCourses);
    @GET("getUserTests/1")
    Call<List<Get_user_test>> getAllTests();
    @POST("saveUserExamResp")
    Call<AnswerSubmitModel> submitAnswers(
            @Body AnswerSubmitModel Signin);

    @POST("addTranOrderId")
    Call<OrderIdModel> getOrderId(
            @Body BeanOrderIdInput input);

    @POST("ems_device_login.php")
    Call<UpcomingTestsResponse> upcomingTestsData(
            @Field("typeOfTests") String typeOfTests,
            @Field("userId") String userId);

/*

    @POST("ems_device_settings.php")
    Call<WaitingScreenDeviceSettingsResponse> qrScanEntryAPI(
            @Field("token") String accessToken,
            @Field("scan_type") String scan_type,
            @Field("address") String address,
            @Field("latitude") String latitude,
            @Field("longitude") String longitude,
            @Field("emp_image") String emp_image,
            @Field("image_type") String image_type );*/



}
