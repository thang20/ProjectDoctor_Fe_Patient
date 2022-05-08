package com.example.myapp.profile_interface;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.AsyncTaskLoader;
import androidx.transition.FragmentTransitionSupport;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapp.Login;
import com.example.myapp.MyApplication;
import com.example.myapp.ProfileFragment;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.api.RealPathUtil;
import com.example.myapp.carepaynonactive_interface.IDCard;
import com.example.myapp.data.DataUser;
import com.github.dhaval2404.imagepicker.ImagePicker;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends AppCompatActivity {
    EditText fullName, dateOfBirth, province, district, wards, address;
    TextView txtSave, fullNameTop;
    ImageView imageViewBack, imageViewFace, btnUpdate;
    Spinner spinnerGender;
    Uri muir = null;
    Integer K = 0;
    ProgressDialog progressDialog;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        fullName = (EditText) findViewById(R.id.edt_profile_update_fullName);
        dateOfBirth = (EditText) findViewById(R.id.edt_profile_update_dataOfBirth);
        province = (EditText) findViewById(R.id.edt_profile_update_province);
        district = (EditText) findViewById(R.id.edt_profile_update_district);
        wards = (EditText) findViewById(R.id.edt_profile_update_wards);
        address = (EditText) findViewById(R.id.edt_profile_update_address);
        spinnerGender = (Spinner) findViewById(R.id.spl_profile_update_gender);
        txtSave = (TextView) findViewById(R.id.txt_profile_update_save);
        imageViewBack = (ImageView) findViewById(R.id.imv_profile_update_back);
        imageViewFace = (ImageView) findViewById(R.id.rcv_profile_imageUpdate);
        fullNameTop = (TextView) findViewById(R.id.txt_profile_fullName_update);
        btnUpdate = (ImageView) findViewById(R.id.imv_profile_update_btnFace);


        if( MyApplication.getMyApplication().getDataUser().getByteArrayImageStatic().equals("")) {

//            Glide.with(this).load("http://192.168.1.149:5000/user/thang1").into(imageViewFace);
        }else {
            new LoadImage1().execute(MyApplication.getMyApplication().getDataUser().getAPI() + MyApplication.getMyApplication().getDataUser().getByteArrayImageStatic());


//
        }

        dateOfBirth.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    dateOfBirth.setText(current);
                    dateOfBirth.setSelection(sel < current.length() ? sel : current.length());



                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });




        fullNameTop.setText(MyApplication.getMyApplication().getDataUser().getFullNameStatic());
        fullName.setText(MyApplication.getMyApplication().getDataUser().getFullNameStatic());


        dateOfBirth.setText( MyApplication.getMyApplication().getDataUser().getDateOfBirthStatic());

        province.setText( MyApplication.getMyApplication().getDataUser().getCityStatic());

        district.setText( MyApplication.getMyApplication().getDataUser().getTownshipStatic());

        wards.setText( MyApplication.getMyApplication().getDataUser().getWardStatic());

        address.setText( MyApplication.getMyApplication().getDataUser().getApartmentNumberStatic());

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("male");
        arrayList.add("female");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(arrayAdapter);

        if( MyApplication.getMyApplication().getDataUser().getApartmentNumberStatic().equals("")|| MyApplication.getMyApplication().getDataUser().getApartmentNumberStatic().equals("male"))
        {

        }else {
            spinnerGender.setSelection(1);
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              ImagePicker.Companion.with(UpdateProfile.this)
                      .crop()	    			//Crop image(Optional), Check Customization for more option
                      .compress(1024)			//Final image size will be less than 1 MB(Optional)
                      .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                      .start();
            }
        });

        txtSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(UpdateProfile.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.process_dialog);

                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                K += 1;
                if(K ==1) {
                    //File file = new File(UpdateProfile.this, muir);
                    String sfullname = fullName.getText().toString();
                    String semail = MyApplication.getMyApplication().getDataUser().getEmailStatic();

                    String sbytearrayimage = MyApplication.getMyApplication().getDataUser().getByteArrayImageStatic();
                    String imgnew = "";

                    if (muir != null) {
                        imgnew = muir.toString();
                        String selectedImagePath = getPath(getApplicationContext(), muir);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inPreferredConfig = Bitmap.Config.RGB_565;
                        // Read BitMap by file path
                        Bitmap bitmap = BitmapFactory.decodeFile(selectedImagePath, options);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        imgnew = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    }else {
                        imgnew = "0";
                    }

                    String sdateofbirth = dateOfBirth.getText().toString();
                    String ssex = spinnerGender.getSelectedItem().toString();
                    String scity = province.getText().toString();
                    String stownship = district.getText().toString();
                    String sward = wards.getText().toString();
                    String sapartmentnumber = address.getText().toString();


                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    boolean check = false;
                    try {

                        jsonObject = new JSONObject();
                        if (MyApplication.getMyApplication().getDataUser().getFullNameStatic().equals(sfullname)  && imgnew.equals("0")&&
                                MyApplication.getMyApplication().getDataUser().getDateOfBirthStatic().equals(sdateofbirth) && MyApplication.getMyApplication().getDataUser().getSexStatic().equals(ssex) &&
                                MyApplication.getMyApplication().getDataUser().getCityStatic().equals(scity) && MyApplication.getMyApplication().getDataUser().getTownshipStatic().equals(stownship)
                                && MyApplication.getMyApplication().getDataUser().getWardStatic().equals(sward) && MyApplication.getMyApplication().getDataUser().getApartmentNumberStatic().equals(sapartmentnumber)) {
                        } else {
                            check = true;
                        }

                        jsonObject.put("fullName", sfullname);
                        jsonObject.put("bytearrayimage", sbytearrayimage);
                        jsonObject.put("email", semail);
                        jsonObject.put("dateofbirth", sdateofbirth);
                        jsonObject.put("sex", ssex);
                        jsonObject.put("city", scity);
                        jsonObject.put("township", stownship);
                        jsonObject.put("ward", sward);
                        jsonObject.put("apartmentnumber", sapartmentnumber);
                        jsonObject.put("imgnew", imgnew);


                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);
                    if (check == true) {
                        String finalSbytearrayimage = sbytearrayimage;
                        Apiservice.apiservice.updateprofile(body).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                String notice = null;
                                try {
                                    notice = response.body().string();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (notice.equals("0")) {

                                    Toast.makeText(UpdateProfile.this, "Save error", Toast.LENGTH_LONG).show();


                                } else {

                                    MyApplication.getMyApplication().getDataUser().setFullNameStatic(sfullname);
                                    MyApplication.getMyApplication().getDataUser().setDateOfBirthStatic(sdateofbirth);
                                    MyApplication.getMyApplication().getDataUser().setSexStatic(ssex);
                                    MyApplication.getMyApplication().getDataUser().setCityStatic(scity);
                                    MyApplication.getMyApplication().getDataUser().setTownshipStatic(stownship);
                                    MyApplication.getMyApplication().getDataUser().setWardStatic(sward);
                                    MyApplication.getMyApplication().getDataUser().setApartmentNumberStatic(sapartmentnumber);
                                    MyApplication.getMyApplication().getDataUser().setByteArrayImageStatic(notice);
                                    progressDialog.dismiss();
                                    if (MyApplication.getMyApplication().getDataUser().isCarepay() == false) {
                                        onBackPressed();
                                    } else {
                                        startActivity(new Intent(getApplicationContext(), IDCard.class));
                                        finish();
                                    }

                                }

                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(UpdateProfile.this, "Fail call API", Toast.LENGTH_LONG).show();

                            }
                        });
                    } else {
                        if (MyApplication.getMyApplication().getDataUser().isCarepay() == false) {
                            onBackPressed();
                        } else {
                            startActivity(new Intent(getApplicationContext(), IDCard.class));
                            finish();
                        }
                    }
                }else {
                    Toast.makeText(UpdateProfile.this, "please don't press too much", Toast.LENGTH_LONG).show();
                }

            }
        });

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( MyApplication.getMyApplication().getDataUser().isCarepay() == false){
                    onBackPressed();
                }else {
                    startActivity(new Intent(getApplicationContext(), IDCard.class));
                    finish();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Log.d("111", "rr");
            muir = data.getData();
            imageViewFace.setImageURI(muir);
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

    private class LoadImage1 extends AsyncTask<String, Void, Bitmap> {
        Bitmap bitmapHinh = null;
        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();

                bitmapHinh = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapHinh;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageViewFace.setImageBitmap(bitmap);
        }
    }
}