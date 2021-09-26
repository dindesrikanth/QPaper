package com.example.questionpaper.Network;

import com.example.questionpaper.Model.AnswerSubmitModel;
import com.example.questionpaper.Model.BeanOrderIdInput;
import com.example.questionpaper.Model.BeanPaymentVerification;
import com.example.questionpaper.Model.Course;
import com.example.questionpaper.Model.DashboardModelNew;
import com.example.questionpaper.Model.Dashboardmodel;
import com.example.questionpaper.Model.Get_user_test;
import com.example.questionpaper.Model.Leaderboardmodel;
import com.example.questionpaper.Model.Loginmodel;
import com.example.questionpaper.Model.OrderIdModel;
import com.example.questionpaper.Model.PaymentVerificationModel;
import com.example.questionpaper.Model.PrizeResponseModel;
import com.example.questionpaper.Model.ScoreModel;
import com.example.questionpaper.Model.TestDetailRequestmodel;
import com.example.questionpaper.Model.UserCourses;
import com.example.questionpaper.Model.signinmodel;
import com.example.questionpaper.Model.user_response;
import com.example.questionpaper.Requests.Courses.CoursesListSubmitRequest;
import com.example.questionpaper.Requests.InfoAndSettings.UpdateProfileRequest;
import com.example.questionpaper.Requests.Login.LoginApiRequest;
import com.example.questionpaper.Requests.Login.RegisterApiRequest;
import com.example.questionpaper.Requests.MyTests.CompletedTestsRequest;
import com.example.questionpaper.Requests.MyTests.DetailedAnalysisRequest;
import com.example.questionpaper.Requests.MyTests.ExamTestQuestionRequest;
import com.example.questionpaper.Requests.MyTests.UserTestRequest;
import com.example.questionpaper.Requests.MyTests.review.ExamReviewRequest;
import com.example.questionpaper.Response.CommonResponse;
import com.example.questionpaper.Response.Courses.CoursesListScreenResponse;
import com.example.questionpaper.Response.InfoAndSettings.UpdateProfileResponse;
import com.example.questionpaper.Response.InfoAndSettings.UserInfoScreenResponse;
import com.example.questionpaper.Response.Login.LoginApiResponse;
import com.example.questionpaper.Response.Payments.ShowBalanceResponse;
import com.example.questionpaper.Response.mytests.Completed.CompletedTestsResponse;
import com.example.questionpaper.Response.mytests.DetailedAnalysis.DetailedAnalysisResponse;
import com.example.questionpaper.Response.mytests.LeaderBoard.LeaderBoardResponse;
import com.example.questionpaper.Response.mytests.LiveTest.LiveTestResponse;
import com.example.questionpaper.Response.mytests.Review.ExamReviewResponse;
import com.example.questionpaper.Response.mytests.UpComing.UpcomingTestsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @GET("user-tests-info/{userId}")
    Call<Dashboardmodel> getDashboardDetails(@Path("userId") String userId);



    @GET("test-leader-board/5")
    Call<Leaderboardmodel> getLeaderBoardDetails();

    @POST("test-content")
    Call<PrizeResponseModel> getPrizeDetails(@Body TestDetailRequestmodel testDetailRequestmodel);


    @GET("getAllQuesDetails/{userId}")
    Call<Object> getAllQuesDetails(@Path("userId") String userId);



    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("exam/v1/get-exam-test-questions")
    Call<Object> getExamTestQuestionResponse(
            @Body ExamTestQuestionRequest examTestQuestionRequest);




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


    @GET("getUserTests/{userId}")
    Call<List<Get_user_test>> getAllTests(@Path("userId") String userId);

    @POST("saveUserExamResp")
    Call<AnswerSubmitModel> submitAnswers(
            @Body AnswerSubmitModel Signin);

    @POST("trans/order/get-order-id")
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


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("uta/user/login")
    Call<LoginApiResponse> loginApiRequest(
            @Body LoginApiRequest loginApiRequest);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("uta/user/register-user")
    Call<LoginApiResponse> registerApiRequest(
            @Body RegisterApiRequest registerApiRequest);

    @GET("uta/user/user-info/{userId}")
    Call<UserInfoScreenResponse> userInfoAndSettingsAPI(@Path("userId") String userId);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PUT("uta/user/update-user")
    Call<UpdateProfileResponse> updateUserProfileAPI(
            @Body UpdateProfileRequest updateProfileRequest);

    //(@Body body: String)
    @GET("course/course-details/{userId}")
    Call<CoursesListScreenResponse> coursesListAPI(@Path("userId") String userId);

    @Headers({
            "Accept: application/json",
            "content-type: application/json"
    })
    @POST("course/user-course-pref")
    Call<CommonResponse> coursesSubmitAPI(
            @Body CoursesListSubmitRequest submitRequest);



    @POST("trans/order/validate-transaction") //verifyPayment
    Call<PaymentVerificationModel> verifyPayment(
            @Body BeanPaymentVerification input);

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

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("user-exam-review")
    Call<ExamReviewResponse> getUserExamReview(
            @Body ExamReviewRequest examReviewRequest);

    @GET("user/wallet/get-wallet-data/{userId}")
    Call<ShowBalanceResponse> getWalletData(@Path("userId") String userId);

    @GET("dash-board-screen/{userId}")
    Call<DashboardModelNew> getDashboardDetailsNew(@Path("userId") String userId);

}
