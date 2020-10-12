package com.example.questionpaper.Network;

import com.example.questionpaper.Model.AnswerSubmitModel;
import com.example.questionpaper.Model.BeanOrderIdInput;
import com.example.questionpaper.Model.Course;
import com.example.questionpaper.Model.Dashboardmodel;
import com.example.questionpaper.Model.Get_user_test;
import com.example.questionpaper.Model.Leaderboardmodel;
import com.example.questionpaper.Model.Loginmodel;
import com.example.questionpaper.Model.OrderIdModel;
import com.example.questionpaper.Model.PrizeResponseModel;
import com.example.questionpaper.Model.ScoreModel;
import com.example.questionpaper.Model.TestDetailRequestmodel;
import com.example.questionpaper.Model.UserCourses;
import com.example.questionpaper.Model.signinmodel;
import com.example.questionpaper.Model.user_response;
import com.example.questionpaper.Response.InfoAndSettings.UpdateProfileResponse;
import com.example.questionpaper.Response.InfoAndSettings.UserInfoScreenResponse;
import com.example.questionpaper.Response.mytests.Completed.CompletedTestsResponse;
import com.example.questionpaper.Response.mytests.DetailedAnalysis.DetailedAnalysisResponse;
import com.example.questionpaper.Response.mytests.LeaderBoard.LeaderBoardResponse;
import com.example.questionpaper.Response.mytests.LiveTest.LiveTestResponse;
import com.example.questionpaper.Response.mytests.Requests.InfoAndSettings.UpdateProfileRequest;
import com.example.questionpaper.Response.mytests.Requests.MyTests.CompletedTestsRequest;
import com.example.questionpaper.Response.mytests.Requests.MyTests.DetailedAnalysisRequest;
import com.example.questionpaper.Response.mytests.Requests.MyTests.UserTestRequest;
import com.example.questionpaper.Response.mytests.UpComing.UpcomingTestsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("current-feature-tests")
    Call<UpcomingTestsResponse> upcomingTestsData(
            @Body UserTestRequest userTestRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("current-feature-tests")
    Call<LiveTestResponse> liveTestData(
            @Body UserTestRequest userTestRequest);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("tests-history")
    Call<CompletedTestsResponse> completedTestData(
            @Body CompletedTestsRequest userTestRequest);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("user-test-history-result")
    Call<DetailedAnalysisResponse> detailedAnalysisAPI(
            @Body DetailedAnalysisRequest detailedAnalysisRequest);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("leader-board-ranks")
    Call<LeaderBoardResponse> leaderBoardAPI(
            @Body DetailedAnalysisRequest detailedAnalysisRequest);


/*    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("uta/user/user-info/")
    Call<UserInfoScreenResponse> userInfoAndSettingsAPI(
            @Body InputRequest userInfoRequest);*/

    @GET("uta/user/user-info/3")
    Call<UserInfoScreenResponse> userInfoAndSettingsAPI();



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("uta/user/update-user")
    Call<UpdateProfileResponse> updateUserProfileAPI(
            @Body UpdateProfileRequest updateProfileRequest);


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
