package com.example.myapp.New_interface;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapp.MainActivity;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.carepaynonactive_interface.CheckPaperDone;
import com.example.myapp.data.ItemNewDataComment;
import com.example.myapp.data.ItemNewDataCommentAdapter;
import com.example.myapp.data.ItemNewDataPost;
import com.example.myapp.data.ItemNewDataPostAdapter;
import com.example.myapp.data.ItemProfileDataRelativeAdapter;
import com.example.myapp.profile_interface.ProfileRelativeData;
import com.example.myapp.profile_interface.ProfileRelativeDataAdd;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Comment_New extends AppCompatActivity {
    ImageView back, face, image, like, send, selectimg;
    TextView name, date, time, content, likeText;
    RecyclerView commet;
    Uri muir = null;
    String img;
    ItemNewDataCommentAdapter itemNewDataCommentAdapter;
    ProgressDialog progressDialog;


    LinearLayout likeAll;
    EditText commetText;
    Integer numberLikeNow  = Integer.parseInt(MyApplication.getMyApplication().getDataUser().getCommentNumberLike());;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_new);
        back = findViewById(R.id.imv_item_new_comment_back);
        face = findViewById(R.id.rcv_item_new_comment_imageFace);
        image = findViewById(R.id.imv_item_new_comment_image);
        like = findViewById(R.id.imv_item_new_comment_like);
        name = findViewById(R.id.txt_item_new_comment_fullName);
        date = findViewById(R.id.txt_item_new_comment_date);
        time = findViewById(R.id.txt_item_new_comment_time);
        content = findViewById(R.id.txt_item_new_comment_content);
        likeText = findViewById(R.id.txt_item_new_comment_liketext);
        likeAll = findViewById(R.id.ln_item_new_comment_likeAll);
        send = findViewById(R.id.imv_item_new_comment_send);
        commet = findViewById(R.id.rcv_comment);
        commetText = findViewById(R.id.txt_item_new_comment_comment);
        selectimg = findViewById(R.id.imv_item_new_comment_selectimg);




        commet.setLayoutManager(new LinearLayoutManager(Comment_New.this));
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(Comment_New.this, DividerItemDecoration.VERTICAL);
//        commet.addItemDecoration(itemDecoration);






        selectimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(Comment_New.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        String checkFace = MyApplication.getMyApplication().getDataUser().getCommentImageFace();
        String checkImage = MyApplication.getMyApplication().getDataUser().getCommentImage();

        if(checkFace.equals("")){
            face.setImageResource(R.drawable.profile);
        }
        else {
//            face.setImageURI(Uri.parse(checkFace));
            Glide.with(Comment_New.this).load(MyApplication.getMyApplication().getDataUser().getAPI()+checkFace).into(face);
        }

        String likec = MyApplication.getMyApplication().getDataUser().getLike();
        Toast.makeText(this, likec, Toast.LENGTH_SHORT).show();
        if(likec.equals("1")){
            like.setColorFilter(Color.parseColor("#2BC4BF"));
            likeText.setTextColor(Color.parseColor("#2BC4BF"));
            likeText.setText(String.valueOf(numberLikeNow) + " Like");
        }else {
            MyApplication.myApplication.getDataUser().setLIKEORDIS(0);
            like.setColorFilter(Color.parseColor("#656362"));
            likeText.setTextColor(Color.parseColor("#656362"));
            likeText.setText(String.valueOf(numberLikeNow) + " Like");
        }

        String dateCheck = MyApplication.getMyApplication().getDataUser().getCommentDate();
        if(dateCheck.equals(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()))){
            Calendar cal = Calendar.getInstance();
            String timepost = MyApplication.getMyApplication().getDataUser().getCommentTime();
            String[] parts = timepost.split(":");
            int hourp = Integer.parseInt(parts[0]);
            int mip = Integer.parseInt(parts[1]);

            int minute = cal.get(Calendar.MINUTE);
            int hourofday = cal.get(Calendar.HOUR_OF_DAY);
            if(hourp!=hourofday){
                int changeH = hourofday - hourp;
                time.setText(String.valueOf(changeH) + " hour ago");

            }else {
                int changeP = minute - mip;
                if(changeP<=1){
                    time.setText("now");
                }else {

                    time.setText(String.valueOf(changeP) + " minute ago");
                }
            }

        }else {

            date.setText(MyApplication.getMyApplication().getDataUser().getCommentDate());
            time.setText(MyApplication.getMyApplication().getDataUser().getCommentTime());

        }
        MyApplication.myApplication.getDataUser().setK(1);
        name.setText(MyApplication.getMyApplication().getDataUser().getCommentFullName());
        likeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String likec = MyApplication.getMyApplication().getDataUser().getLike();
                if(likec.equals("0")){
                    MyApplication.myApplication.getDataUser().setLike("1");
                }else {
                    MyApplication.myApplication.getDataUser().setLike("0");
                }
                if(likec.equals("0")){
                    like.setColorFilter(Color.parseColor("#2BC4BF"));
                    likeText.setTextColor(Color.parseColor("#2BC4BF"));
                    ///////////////

                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    try {
                        String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                        jsonObject = new JSONObject();
                        jsonObject.put("email", email);
                        jsonObject.put("likec", likec);
                        jsonObject.put("idpost", MyApplication.myApplication.getDataUser().getCommentIDPost());
                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.like(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String notice = null;

                            try {
                                notice = response.body().string();

                                Toast.makeText(Comment_New.this, "thanks for the contribution", Toast.LENGTH_LONG).show();
                                likeText.setText(String.valueOf(notice) + " Like");
//                                ItemNewDataPost.setNumberlike(notice);



                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(Comment_New.this, "Fail to call API", Toast.LENGTH_LONG).show();

                        }
                    });



                }else {
                    MyApplication.myApplication.getDataUser().setLIKEORDIS(0);
                    like.setColorFilter(Color.parseColor("#656362"));
                    likeText.setTextColor(Color.parseColor("#656362"));
                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    try {
                        String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                        jsonObject = new JSONObject();
                        jsonObject.put("email", email);
                        jsonObject.put("likec", likec);
                        jsonObject.put("idpost", MyApplication.myApplication.getDataUser().getCommentIDPost());
                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.like(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String notice = null;

                            try {
                                notice = response.body().string();

                                Toast.makeText(Comment_New.this, "thanks for the contribution", Toast.LENGTH_LONG).show();

                                likeText.setText(String.valueOf(notice) + " Like");
//                                ItemNewDataPost.setNumberlike(notice);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(Comment_New.this, "Fail to call API", Toast.LENGTH_LONG).show();

                        }
                    });

                }

//                numberLikeNow = Integer.parseInt(ItemNewDataPost.getNumberlike());
//
//                MyApplication.myApplication.getDataUser().setK( MyApplication.myApplication.getDataUser().getK() + 1);
//
//
//                if(MyApplication.myApplication.getDataUser().getK()%2==0) {
//                    MyApplication.myApplication.getDataUser().setLIKEORDIS(1);
//                    numberLikeNow  = numberLikeNow + 1;
//                    holder.like.setColorFilter(Color.parseColor("#2BC4BF"));
//                    holder.likeText.setTextColor(Color.parseColor("#2BC4BF"));
//                    holder.likeText.setText(String.valueOf(numberLikeNow) + " Like");
//                }else {
//                    MyApplication.myApplication.getDataUser().setLIKEORDIS(0);
//                    holder.like.setColorFilter(Color.parseColor("#656362"));
//                    holder.likeText.setTextColor(Color.parseColor("#656362"));
//                    holder.likeText.setText(String.valueOf(numberLikeNow) + " Like");
//
//                }
            }
//
//                MyApplication.myApplication.getDataUser().setK( MyApplication.myApplication.getDataUser().getK() + 1);
//
//                if(MyApplication.myApplication.getDataUser().getK()%2==0) {
//
//                    numberLikeNow  = numberLikeNow + 1;
//                    like.setColorFilter(Color.parseColor("#2BC4BF"));
//                    likeText.setTextColor(Color.parseColor("#2BC4BF"));
//                    likeText.setText(String.valueOf(numberLikeNow) + " Like");
//                }else {
//                    numberLikeNow = numberLikeNow - 1;
//                    like.setColorFilter(Color.parseColor("#656362"));
//                    likeText.setTextColor(Color.parseColor("#656362"));
//                    likeText.setText(String.valueOf(numberLikeNow) + " Like");
//
//                }
//            }
        });

        if(checkImage.equals("0")){
            image.getLayoutParams().height = 0;
            image.getLayoutParams().width = 0;
        }
        else {
//            image.setImageURI(Uri.parse(MyApplication.myApplication.getDataUser().getCommentImage()));
            Glide.with(Comment_New.this).load(MyApplication.getMyApplication().getDataUser().getAPI()+checkImage).into(image);
        }
        content.setText(MyApplication.myApplication.getDataUser().getCommentContent());

        if(MyApplication.myApplication.getDataUser().getLIKEORDIS()==1){
            like.setColorFilter(Color.parseColor("#2BC4BF"));
            likeText.setTextColor(Color.parseColor("#2BC4BF"));
            likeText.setText(String.valueOf(numberLikeNow + 1) + " Like");
        }else {
            like.setColorFilter(Color.parseColor("#656362"));
            likeText.setTextColor(Color.parseColor("#656362"));
            likeText.setText(String.valueOf(numberLikeNow) + " Like");
        }
        commetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send.setColorFilter(Color.parseColor("#2BC4BF"));
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content =  commetText.getText().toString();
                if(content.length()==0){
                    Toast.makeText(Comment_New.this, "Please input a comment", Toast.LENGTH_LONG).show();
                }else {
                    if(muir!=null) {
//                        img = muir.toString();
                        img = muir.toString();
                        String selectedImagePath = getPath(getApplicationContext(), muir);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                        // Read BitMap by file path
                        Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath, options);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        img = Base64.encodeToString(byteArray, Base64.DEFAULT);




                    }else {
                        img = "0";
                    }
                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    try {
                        jsonObject = new JSONObject();
                        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                        jsonObject.put("content", content);
                        jsonObject.put("image", img);
                        jsonObject.put("date", currentDate);
                        jsonObject.put("time", currentTime);

                        jsonObject.put("idpeople", MyApplication.myApplication.getDataUser().getEmailStatic());
                        jsonObject.put("idpost", MyApplication.myApplication.getDataUser().getCommentIDPost());
                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.postacomment(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String notice = null;

                            try {
                                notice = response.body().string();
                                if(notice.equals("success")){
                                    commetText.setText("");
                                    muir = null;
                                    selectimg.setImageResource(R.drawable.commentwrite2);
                                    Toast.makeText(Comment_New.this, "Success", Toast.LENGTH_LONG).show();
                                    UpdateComment();

                                }else {
                                    Toast.makeText(Comment_New.this, "Fail to comment", Toast.LENGTH_LONG).show();
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(Comment_New.this, "Fail to Call API", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });
        UpdateComment();




    }
    private void UpdateComment(){

        progressDialog = new ProgressDialog(Comment_New.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);
        String likec = MyApplication.getMyApplication().getDataUser().getLike();
        if(likec.equals("1")){
            like.setColorFilter(Color.parseColor("#2BC4BF"));
            likeText.setTextColor(Color.parseColor("#2BC4BF"));
            likeText.setText(String.valueOf(numberLikeNow) + " Like");
        }else {
            MyApplication.myApplication.getDataUser().setLIKEORDIS(0);
            like.setColorFilter(Color.parseColor("#656362"));
            likeText.setTextColor(Color.parseColor("#656362"));
            likeText.setText(String.valueOf(numberLikeNow) + " Like");
        }

        JSONObject jsonObject = new JSONObject();
        String jsonStr = null;
        try {

            jsonObject = new JSONObject();
            jsonObject.put("idpeople", MyApplication.myApplication.getDataUser().getCommentIDPeople());
            jsonObject.put("idpost", MyApplication.myApplication.getDataUser().getCommentIDPost());
            jsonStr = jsonObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body =
                RequestBody.create(MediaType.parse("text/plain"), jsonStr);

        Apiservice.apiservice.allcomment(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = "";
                try {
                    notice = response.body().string();
                    List<ItemNewDataComment> list = new ArrayList<ItemNewDataComment>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);


                        String content = obj.get("content").toString();

                        String date = obj.get("date").toString();
                        String time = obj.get("time").toString();
                        String image = obj.get("image").toString();
                        String imageface = obj.get("imageface").toString();
                        String fullname = obj.get("fullname").toString();



                        list.add(new ItemNewDataComment(imageface,
                                time, date, fullname, image, content));



                    }


                    itemNewDataCommentAdapter = new ItemNewDataCommentAdapter(Comment_New.this ,list);
                    commet.setAdapter(itemNewDataCommentAdapter);
                    progressDialog.dismiss();



                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Log.d("111", "rr");
            muir = data.getData();
            selectimg.setImageURI(muir);
        }else {
            return;
        }

    }



    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }


}