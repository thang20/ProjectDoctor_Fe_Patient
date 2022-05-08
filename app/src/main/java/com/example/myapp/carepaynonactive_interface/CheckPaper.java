package com.example.myapp.carepaynonactive_interface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.data.DataUser;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class CheckPaper extends AppCompatActivity {
    ImageView back;
    Button comtinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_paper);
        back = findViewById(R.id.imv_carepay_non_paper_back);
        comtinue = findViewById(R.id.btn_carepay_non_paper_take_phone);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckPaper.this, CheckFaceDone.class);
                startActivity(intent);
                finish();
            }
        });
        comtinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImagePicker.Companion.with(CheckPaper.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();

            }



        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            MyApplication.getMyApplication().getDataUser().setPaper1(data.getData());
            //ima.setImageURI(muir);
            Intent intent = new Intent(CheckPaper.this, CheckPaperDone.class);
            startActivity(intent);
            finish();
        }else {
            return;
        }

    }
    }
