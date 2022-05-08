package com.example.myapp.profile_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapp.BuildConfig;
import com.example.myapp.Login;
import com.example.myapp.R;
import com.example.myapp.data.ItemProfileData01;

public class ProfileData extends AppCompatActivity {
    TextView textViewDetail;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data);
        Bundle bundle = getIntent().getExtras();
        if(bundle==null){
            return;
        }
        ItemProfileData01 itemProfileData01 = (ItemProfileData01) bundle.get("object_profileData");
        textViewDetail = findViewById(R.id.txt_profile_detail);
        name = itemProfileData01.getName();
        textViewDetail.setText(itemProfileData01.getName());
        if(name.equals("Profile of a relative")){
            startActivity(new Intent(getApplicationContext(), ProfileRelativeData.class));
            finish();
        }else if(name.equals("Endow")){
            startActivity(new Intent(getApplicationContext(), ProfileDataEndow.class));
            finish();

        }
        else if(name.equals("Donations")){
            startActivity(new Intent(getApplicationContext(), ProfileDataDonation.class));
            finish();

        }
        else if(name.equals("Health declaration")){
            startActivity(new Intent(getApplicationContext(), ProfileDataDeclaration.class));
            finish();
        }
        else if(name.equals("Remind")){
            startActivity(new Intent(getApplicationContext(), ProfileDataSchedule.class));
            finish();
        }
        else if(name.equals("Invite friends")){
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My Doctor");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
                onBackPressed();
            } catch(Exception e) {
                //e.toString();
                onBackPressed();
            }
        }
        else if(name.equals("Set screen lock")){
            startActivity(new Intent(getApplicationContext(), ProfileDatascreenlock.class));
            finish();
        }

        else if(name.equals("Notification settings")){
            startActivity(new Intent(getApplicationContext(), ProfileDataNotification.class));
            finish();
        }
        else if(name.equals("Comments - Questions")){
            startActivity(new Intent(getApplicationContext(), ProfileDataCommet.class));
            finish();
        }
        else if(name.equals("Change Password")){
            startActivity(new Intent(getApplicationContext(), ProfileDataChangepass.class));
            finish();
        }
        else if(name.equals("Call Customer Care(1900 1851)")){
            String phone = "1900 1851";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
            onBackPressed();
        }
        else if(name.equals("Facebook Y.DT")){
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100025510756626"));
                startActivity(intent);
                onBackPressed();
            } catch(Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/appetizerandroid")));
                onBackPressed();
            }
        }
        else if(name.equals("Terms of use")){
            startActivity(new Intent(getApplicationContext(), ProfileDataterm.class));
            finish();
        }
        else if(name.equals("Introduction")){
            startActivity(new Intent(getApplicationContext(), ProfileDataIntroduce.class));
            finish();
        }else if(name.equals("Appointment schedule")){
            startActivity(new Intent(getApplicationContext(), ProfileDataRemind.class));
            finish();
        }
        else if(name.equals("Transaction history")){
            startActivity(new Intent(getApplicationContext(), ProfileHistory.class));
            finish();
        }else if(name.equals("My medical record")){
            startActivity(new Intent(getApplicationContext(), ProfileRecodMedical.class));
            finish();
        }






    }
}