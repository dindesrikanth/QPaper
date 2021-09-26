package com.example.questionpaper.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.questionpaper.Common.Utility;
import com.example.questionpaper.Model.BeanOrderIdInput;
import com.example.questionpaper.Model.BeanPaymentVerification;
import com.example.questionpaper.Model.OrderIdModel;
import com.example.questionpaper.Model.PaymentVerificationModel;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.example.questionpaper.Response.Payments.ShowBalanceResponse;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBalanceActivity extends AppCompatActivity implements  View.OnClickListener, PaymentResultWithDataListener {

    private LinearLayout add_cash_main_layout;
    private ProgressBar add_cash_loader;
    private ImageView iv_back;
    private TextView current_balance_value, tv_100, tv_200, tv_300;
    private EditText et_add_amount;
    private Button add_button;
//    private static String testKey = "rzp_test_Cl86cizLbaHCgE";
    private Checkout checkout;
    private static String TAG = "AddBalanceActivity";
    private String orderId;
    private String keyId;
    private int rechargeAmount;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_balance);
        pDialog= Utility.getProgressDialog(this);
        initViews();
        getBalanceData();
    }

    private void getBalanceData(){
        pDialog.show();
        String userId = Utility.getUserIdFromSharedPref(getApplicationContext());

        Call<ShowBalanceResponse> call = RetrofitClient.getInstance().getApi().getWalletData(userId);
        call.enqueue(new Callback<ShowBalanceResponse>() {
            @Override
            public void onResponse(Call<ShowBalanceResponse> call, Response<ShowBalanceResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        showData(response.body());
                    }else{
                        Utility.showCommonMessage(getApplicationContext(),"Failed to load API...");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                pDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ShowBalanceResponse> call, Throwable t) {
                Utility.showCommonMessage(getApplicationContext(),"Response failed ...");
                pDialog.dismiss();
                return;
            }
        });
    }
    private void showData(ShowBalanceResponse response) {
        if(response !=null) {
            int totalBalance = 0;
            if(!TextUtils.isEmpty(response.getWalletBalance())){
                totalBalance = totalBalance + Integer.parseInt(response.getWalletBalance());
            }
            if(!TextUtils.isEmpty(response.getWinsBalance())){
                totalBalance = totalBalance + Integer.parseInt(response.getWinsBalance());
            }
            if(!TextUtils.isEmpty(response.getCashBonus())){
                totalBalance = totalBalance + Integer.parseInt(response.getCashBonus());
            }
            current_balance_value.setText("₹"+totalBalance);
        }
        else{
            Toast.makeText(getApplicationContext(), R.string.no_data_found,Toast.LENGTH_LONG).show();
            current_balance_value.setText("₹0");
        }
    }

    private void initViews(){
        add_cash_main_layout = findViewById(R.id.add_cash_main_layout);
        add_cash_loader = findViewById(R.id.add_cash_loader);
        iv_back = findViewById(R.id.iv_back);
        current_balance_value = findViewById(R.id.current_balance_value);
        tv_100 = findViewById(R.id.tv_100);
        tv_200 = findViewById(R.id.tv_200);
        tv_300 = findViewById(R.id.tv_300);
        et_add_amount = findViewById(R.id.et_add_amount);
        add_button = findViewById(R.id.add_button);
        iv_back.setOnClickListener(this);
        tv_100.setOnClickListener(this);
        tv_200.setOnClickListener(this);
        tv_300.setOnClickListener(this);
        add_button.setOnClickListener(this);
        getTestDetailData();
    }

    private void getTestDetailData(){
//        Call<Object> call = RetrofitClient.getInstance().getApi().getAllQuesDetails();
//        call.enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                try {
//                    if (response.isSuccessful()) {
//                        List<Questionesmodel> list = (List<Questionesmodel>)response.body();
//                        Gson gson = new Gson();
//
//                    }else{
//                        showMessageAndCloseScreen();
//                    }
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                    showMessageAndCloseScreen();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//                showMessageAndCloseScreen();
//                return;
//            }
//        });
        add_cash_loader.setVisibility(View.GONE);
        add_cash_main_layout.setVisibility(View.VISIBLE);
    }

    private void showMessageAndCloseScreen(){
        Toast.makeText(AddBalanceActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.iv_back:
                    finish();
                    break;

                case R.id.tv_100:
                    et_add_amount.setText("100");
                    break;

                case R.id.tv_200:
                    et_add_amount.setText("200");
                    break;

                case R.id.tv_300:
                    et_add_amount.setText("300");
                    break;

                case R.id.add_button:
                    if(TextUtils.isEmpty(et_add_amount.getText().toString())){
                        Toast.makeText(AddBalanceActivity.this, getString(R.string.add_cash_alert), Toast.LENGTH_SHORT).show();
                    }else{
                      getOrderId();
                    }
                    break;

                default:
                    break;

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("test_detail_response.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public  void getOrderId(){
        add_cash_main_layout.setVisibility(View.GONE);
        add_cash_loader.setVisibility(View.VISIBLE);
        rechargeAmount = Integer.parseInt(et_add_amount.getText().toString());
        final BeanOrderIdInput beanOrderIdInput = new BeanOrderIdInput(rechargeAmount, "INR", 1);
        Call<OrderIdModel> call = RetrofitClient.getInstance().getApi().getOrderId(beanOrderIdInput);
        call.enqueue(new Callback<OrderIdModel>() {
            @Override
            public void onResponse(Call<OrderIdModel> call, Response<OrderIdModel> response) {
                if(response.isSuccessful()){
                    OrderIdModel orderIdModel = (OrderIdModel) response.body();
                    if(orderIdModel != null){
                        orderId = orderIdModel.getOrderId();
                        keyId = orderIdModel.getKeyId();
                    }
                    startPayment();
                }else{
                    add_cash_main_layout.setVisibility(View.VISIBLE);
                    add_cash_loader.setVisibility(View.GONE);
                    Toast.makeText(AddBalanceActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderIdModel> call, Throwable t) {
                add_cash_main_layout.setVisibility(View.VISIBLE);
                add_cash_loader.setVisibility(View.GONE);
                Toast.makeText(AddBalanceActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void startPayment() {
        /**
         * Instantiate Checkout
         */
        
        if(TextUtils.isEmpty(orderId) || TextUtils.isEmpty(keyId)){
            Toast.makeText(AddBalanceActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
            return;
        }

        Checkout checkout = new Checkout();
        checkout.setKeyID(keyId);

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.icons_checked);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "Question Paper TestApp");

            /**
             * Description can be anything
             * eg: Reference No. #123123 - This order number is passed by you for your internal reference. This is not the `razorpay_order_id`.
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", orderId);
            options.put("currency", "INR");

            /**
             * Amount is always passed in currency subunits
             * Eg: "500" = INR 5.00
             */
            options.put("amount", rechargeAmount * 100);

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }



    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
//        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
//        add_cash_main_layout.setVisibility(View.VISIBLE);
//        add_cash_loader.setVisibility(View.GONE);
        verifyPayment(paymentData);
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
        add_cash_main_layout.setVisibility(View.VISIBLE);
        add_cash_loader.setVisibility(View.GONE);
    }

    public  void verifyPayment(PaymentData paymentData){
        String userId = Utility.getUserIdFromSharedPref(getApplicationContext());

        final BeanPaymentVerification beanPaymentVerification = new BeanPaymentVerification(orderId, paymentData.getPaymentId(),paymentData.getSignature(), Long.parseLong(userId));
        Call<PaymentVerificationModel> call = RetrofitClient.getInstance().getApi().verifyPayment(beanPaymentVerification);
        call.enqueue(new Callback<PaymentVerificationModel>() {
            @Override
            public void onResponse(Call<PaymentVerificationModel> call, Response<PaymentVerificationModel> response) {
                        add_cash_main_layout.setVisibility(View.VISIBLE);
                        add_cash_loader.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    PaymentVerificationModel paymentVerificationModel = (PaymentVerificationModel) response.body();
                    if(paymentVerificationModel != null){
                        if(!TextUtils.isEmpty(paymentVerificationModel.getStatus())){
                            if(paymentVerificationModel.getStatus().equalsIgnoreCase("success")){
                                String text = String.format(getResources().getString(R.string.add_cash_success_message), paymentVerificationModel.getAmountPaid(), paymentVerificationModel.getCurrentBalance());
                                new AlertDialog.Builder(AddBalanceActivity.this)
                                        .setTitle(getResources().getString(R.string.add_cash))
                                        .setMessage(text)

                                        // A null listener allows the button to dismiss the dialog and take no further action.
                                        .setPositiveButton(android.R.string.ok, null)
                                        .setIcon(R.drawable.ic_check_circle)
                                        .show();
                            }else{
                                Toast.makeText(AddBalanceActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(AddBalanceActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(AddBalanceActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(AddBalanceActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PaymentVerificationModel> call, Throwable t) {
                add_cash_main_layout.setVisibility(View.VISIBLE);
                add_cash_loader.setVisibility(View.GONE);
                Toast.makeText(AddBalanceActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

