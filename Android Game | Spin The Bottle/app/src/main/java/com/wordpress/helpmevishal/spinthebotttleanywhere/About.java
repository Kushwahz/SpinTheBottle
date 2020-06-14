package com.wordpress.helpmevishal.spinthebotttleanywhere;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class About extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    ImageView gplus, linkedin, twitter, email, whatsapp, phone;
    LinearLayout share, feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        context = this;
        email = (ImageView) findViewById(R.id.about_mail);
        email.setOnClickListener(this);
        whatsapp = (ImageView) findViewById(R.id.about_whatsapp);
        whatsapp.setOnClickListener(this);
        phone = (ImageView) findViewById(R.id.about_call);
        phone.setOnClickListener(this);
        gplus = (ImageView) findViewById(R.id.ic_gplus);
        gplus.setOnClickListener(this);
        linkedin = (ImageView) findViewById(R.id.ic_linkedin);
        linkedin.setOnClickListener(this);
        twitter = (ImageView) findViewById(R.id.ic_twitter);
        twitter.setOnClickListener(this);
        share =(LinearLayout) findViewById(R.id.about_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendOthers = new Intent();
                sendOthers.setAction(Intent.ACTION_SEND);
                sendOthers.putExtra(Intent.EXTRA_TEXT, "Download 'Spin The Bottle' Game for Android. https://play.google.com/store/apps/details?id=com.wordpress.helpmevishal.spinthebotttleanywhere");
                sendOthers.setType("text/plain");
                startActivity(sendOthers);
            }
        });
        feedback =(LinearLayout) findViewById(R.id.about_feedback);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: kushwahz.vishalsingh@gmail.com"));
                startActivity(Intent.createChooser(emailIntent, "Send feedback"));
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.about_mail: Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: kushwahz.vishalsingh@gmail.com"));
                startActivity(Intent.createChooser(emailIntent, "Send feedback"));
                break;
            case R.id.about_whatsapp: if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 1);
            } else {
                callWhatsApp("7696324435", "Vishal Singh", "kushwahz.vishalsingh@gmail.com", getApplicationContext());
            }
                break;
            case R.id.about_call: if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                    getApplicationContext().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CALL_PHONE},2);
            } else {
                callPhone("7696324435", "Vishal Singh", "kushwahz.vishalsingh@gmail.com", getApplicationContext());
            }
                break;
            case R.id.ic_gplus:Intent gPlusIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/+VishalSinghKz"));
                startActivity(gPlusIntent);
                break;
            case R.id.ic_twitter:Intent twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/kushwahz"));
                startActivity(twitterIntent);
                break;
            case R.id.ic_linkedin:Intent linkedInIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/kushwahz"));
                startActivity(linkedInIntent);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callWhatsApp("7696324435", "Vishal Singh", "kushwahz.vishalsingh@gmail.com", getApplicationContext());
                } else {
                    Toast.makeText(context, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            case 2: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone("7696324435", "Vishal Singh", "kushwahz.vishalsingh@gmail.com", getApplicationContext());
                } else {
                    Toast.makeText(context, "Permission not granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    public boolean contactExists(Context context, String number) {
        Uri lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] mPhoneNumberProjection = {ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER, ContactsContract.PhoneLookup.DISPLAY_NAME};
        Cursor cur = context.getContentResolver().query(lookupUri, mPhoneNumberProjection, null, null, null);
        try {
            if (cur.moveToFirst()) {
                return true;
            }
        } finally {
            if (cur != null)
                cur.close();
        }
        return false;
    }

    public void callPhone(final String Contact, String Name, String Email, final Context context) {
        Boolean contactExist = contactExists(context, "+91" + Contact);

        if (contactExist) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + "+91" + Contact));
            try{
                startActivity(callIntent);
            } catch (Exception e){
                Toast.makeText(context, "Please refresh contacts...", Toast.LENGTH_SHORT).show();
            }

        } else {
            final String DisplayName = Name;
            final String MobileNumber = "+91" + Contact;
            final String emailID = Email;

            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

            ops.add(ContentProviderOperation.newInsert(
                    ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    .build());

            //------------------------------------------------------ Names
            if (DisplayName != null) {
                ops.add(ContentProviderOperation.newInsert(
                        ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(
                                ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                                DisplayName).build());
            }

            //------------------------------------------------------ Mobile Number
            if (MobileNumber != null) {
                ops.add(ContentProviderOperation.
                        newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                        .build());
            }

            //------------------------------------------------------ Email
            if (emailID != null) {
                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
                        .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                        .build());
            }

            try {
                context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + "+91" + Contact));
            try{
                startActivity(callIntent);
            } catch (Exception e){
                Toast.makeText(context, "Please refresh contacts", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void callWhatsApp(final String Contact, String Name, String Email, final Context context) {
        Boolean contactExist = contactExists(context, "+91" + Contact);

        if (contactExist) {
            Uri uri = Uri.parse("smsto:" + "+91" + Contact);
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            i.putExtra("sms_body", "Hi");
            i.setPackage("com.whatsapp");
            try{
                startActivity(i);
            } catch (Exception e){
                Toast.makeText(context, "Please refresh WhatsApp Contact", Toast.LENGTH_SHORT).show();
            }


        } else {
            final String DisplayName = Name;
            final String MobileNumber = "+91" + Contact;
            final String emailID = Email;

            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

            ops.add(ContentProviderOperation.newInsert(
                    ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    .build());

            //------------------------------------------------------ Names
            if (DisplayName != null) {
                ops.add(ContentProviderOperation.newInsert(
                        ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(
                                ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                                DisplayName).build());
            }

            //------------------------------------------------------ Mobile Number
            if (MobileNumber != null) {
                ops.add(ContentProviderOperation.
                        newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                        .build());
            }

            //------------------------------------------------------ Email
            if (emailID != null) {
                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                        .withValue(ContactsContract.Data.MIMETYPE,
                                ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
                        .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                        .build());
            }

            try {
                context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            //Send message to whatsapp
            Uri uri = Uri.parse("smsto:" + "+91" + Contact);
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            i.putExtra("sms_body", "Hi");
            i.setPackage("com.whatsapp");
            try{
                startActivity(i);
                startActivity(i);
            } catch (Exception e){
                Toast.makeText(context, "Please refresh WhatsApp Contacts", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
