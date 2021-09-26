package com.example.questionpaper.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.questionpaper.Model.AnswerSubmitModel;
import com.example.questionpaper.Model.BeanOrderIdInput;
import com.example.questionpaper.Model.OrderIdModel;
import com.example.questionpaper.Network.RetrofitClient;
import com.example.questionpaper.R;
import com.razorpay.Checkout;
import com.razorpay.PaymentData;
import com.razorpay.PaymentResultListener;
import com.razorpay.PaymentResultWithDataListener;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentGatewayActivity extends AppCompatActivity implements PaymentResultWithDataListener {

    private static String testKey = "rzp_test_Cl86cizLbaHCgE";
    private Checkout checkout;
    private static String TAG = "PaymentGatewayActivity";
    private Button payButton;
    private ProgressBar progress_bar;
    private String orderId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);
        Checkout.preload(getApplicationContext());
        payButton = findViewById(R.id.pay_button);
        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderId();
            }
        });
        progress_bar = findViewById(R.id.progress_bar);
    }

    public void startPayment() {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();
        checkout.setKeyID(testKey);
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
//            options.put("amount", "500");

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }



    @Override
    public void onPaymentSuccess(String s, PaymentData paymentData) {
        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        payButton.setVisibility(View.VISIBLE);
        progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void onPaymentError(int i, String s, PaymentData paymentData) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        payButton.setVisibility(View.VISIBLE);
        progress_bar.setVisibility(View.GONE);
    }

    public  void getOrderId(){
        payButton.setVisibility(View.GONE);
        progress_bar.setVisibility(View.VISIBLE);
        final BeanOrderIdInput beanOrderIdInput = new BeanOrderIdInput(50000, "INR", 1);
        Call<OrderIdModel> call = RetrofitClient.getInstance().getApi().getOrderId(beanOrderIdInput);
        call.enqueue(new Callback<OrderIdModel>() {
            @Override
            public void onResponse(Call<OrderIdModel> call, Response<OrderIdModel> response) {
              if(response.isSuccessful()){
//                  orderId = response.body();
                  startPayment();
              }else{
                  payButton.setVisibility(View.VISIBLE);
                  progress_bar.setVisibility(View.GONE);
                  Toast.makeText(PaymentGatewayActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
              }
            }

            @Override
            public void onFailure(Call<OrderIdModel> call, Throwable t) {
                payButton.setVisibility(View.VISIBLE);
                progress_bar.setVisibility(View.GONE);
                Toast.makeText(PaymentGatewayActivity.this, getString(R.string.unknown_error), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
