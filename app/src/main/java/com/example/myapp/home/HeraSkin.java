package com.example.myapp.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeraSkin extends AppCompatActivity {
    ImageView back, imgSkin;
    Button takeSkin;
    TextView next;
    Uri muir = null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hera_skin);

        back = findViewById(R.id.imv_home_home4_backSkin);
        next = findViewById(R.id.txt_home_home4_imgSkin);
        takeSkin = findViewById(R.id.btn_home_home4_nextSkin);
        imgSkin = findViewById(R.id.imv_home_home4_imgSkin);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.Companion.with(HeraSkin.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
        takeSkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(muir ==null){
                    Toast.makeText(HeraSkin.this, "Please input ImageSkin", Toast.LENGTH_SHORT).show();
                }else {

                    progressDialog = new ProgressDialog(HeraSkin.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.process_dialog);
                    //Toast.makeText(HeraXray.this, "Please input ImageXray11", Toast.LENGTH_SHORT).show();
                    String semail =  MyApplication.getMyApplication().getDataUser().getEmailStatic();
                    String selectedImagePath = getPath(getApplicationContext(), muir);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inPreferredConfig = Bitmap.Config.RGB_565;
                    // Read BitMap by file path
                    Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath, options);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    String image = Base64.encodeToString(byteArray, Base64.DEFAULT);



                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    try {

                        jsonObject = new JSONObject();
                        jsonObject.put("email", semail);
                        jsonObject.put("image", image);
                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.skin(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String notice = null;

                            try {

                                notice = response.body().string();
                                MyApplication.myApplication.getDataUser().setResultXray(MyApplication.getMyApplication().getDataUser().getAPI() +"runs/detect/exp"+notice);
                                progressDialog.dismiss();
                                startActivity(new Intent(HeraSkin.this, RSSkin.class));
                                finish();
//                                Glide.with(HeraXray.this).load(MyApplication.getMyApplication().getDataUser().getAPI() +"runs/detect/exp"+notice).into(imgXray);
//                                Log.d("xray", MyApplication.getMyApplication().getDataUser().getAPI() +"runs/detect/exp"+notice);

                            } catch (IOException e) {
                                e.printStackTrace();
                                progressDialog.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(HeraSkin.this, "Fail to Call API", Toast.LENGTH_SHORT).show();
                        }
                    });

                }










            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            muir = data.getData();
            imgSkin.setImageURI(muir);
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