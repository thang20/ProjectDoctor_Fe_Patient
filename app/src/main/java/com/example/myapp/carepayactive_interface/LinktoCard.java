package com.example.myapp.carepayactive_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LinktoCard extends AppCompatActivity {
    ImageView back;
    Button next;
    EditText cardNumber, cardOpenDate, cardName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linkto_card);
        back = findViewById(R.id.imv_carepay_active_addcard_back);
        next = findViewById(R.id.btn_carepay_active_addcard_next);
        cardNumber = findViewById(R.id.edt_carepay_active_addcard_number);
        cardOpenDate = findViewById(R.id.edt_carepay_active_addcard_date);
        cardName = findViewById(R.id.edt_carepay_active_addcard_name);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                onBackPressed();
//                Toast.makeText(getApplicationContext(), "Please wait for a response", Toast.LENGTH_LONG).show();
                JSONObject jsonObject = new JSONObject();
                String jsonStr = null;
                try {
                    String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                    jsonObject = new JSONObject();
                    jsonObject.put("email", email);
                    jsonObject.put("number", cardNumber.getText().toString());
                    jsonStr = jsonObject.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                Apiservice.apiservice.addcard(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;

                        try {
                            notice = response.body().string();
                            if(notice.equals("success")){
                                MyApplication.myApplication.getDataUser().setAddcard("1");
                                Toast.makeText(LinktoCard.this, "Success to add card", Toast.LENGTH_LONG).show();
                                onBackPressed();
                            }else {
                                Toast.makeText(LinktoCard.this, "Fail to add card", Toast.LENGTH_LONG).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(LinktoCard.this, "Fail to call API", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}