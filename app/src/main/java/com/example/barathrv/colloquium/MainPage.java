package com.example.barathrv.colloquium;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.signin.SignIn;

public class MainPage extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {

    private LinearLayout Prof_Section;
    private Button SignOut;
    private SignInButton Signin;
    private TextView Name,Email;
    private ImageView prof_pic;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE=9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Prof_Section=(LinearLayout)findViewById(R.id.profsec);
        SignOut=(Button)findViewById(R.id.logout);
        Signin=(SignInButton)findViewById(R.id.bn_login);
        Name=(TextView)findViewById(R.id.name);
        Email=(TextView)findViewById(R.id.email);
        prof_pic=(ImageView)findViewById(R.id.prof_pic);
        Signin.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        Prof_Section.setVisibility(View.GONE);
        GoogleSignInOptions signInOptions=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient=new GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();

    }

    @Override
    public void onClick(View v) {
    switch(v.getId())
    {
        case R.id.bn_login:
            signin();
            break;
        case R.id.logout:
            signout();
            break;
    }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signin()
    {
        Intent intent=Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent,REQ_CODE);

    }

    private void signout()
    {
Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
    @Override
    public void onResult(@NonNull Status status) {
        updateUI(false);
    }
});
    }

    private void handleresult(GoogleSignInResult result)
    {
        if(result.isSuccess())
        {
            GoogleSignInAccount account=result.getSignInAccount();
            String name=account.getDisplayName();
            String email=account.getEmail();
            String img_url=account.getPhotoUrl().toString();
            Name.setText(name);
            Email.setText(email);
            Glide.with(this).load(img_url).into(prof_pic);
            updateUI(true);
        }
        else
            updateUI(false);
    }

    private void updateUI(boolean islogin)
    {
if(islogin)
{
    Prof_Section.setVisibility(View.VISIBLE);
    Signin.setVisibility(View.GONE);
}
else

{
    Prof_Section.setVisibility(View.GONE);
    Signin.setVisibility(View.VISIBLE);
}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    if(requestCode==REQ_CODE)
    {
        GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        handleresult(result);
    }
    }
}
