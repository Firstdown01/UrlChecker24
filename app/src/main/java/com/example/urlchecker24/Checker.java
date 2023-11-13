package com.example.urlchecker24;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.MasterKey;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Checker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checker);

        Button button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int flag=0;

                EditText input=findViewById(R.id.url);
                String inputText = input.getText().toString();


                if (isValidUrl(inputText)) {
                        TextView result=findViewById(R.id.result);
                        result.setText("The text is a valid URL.");
                        flag=1;
                    } else {    TextView result=findViewById(R.id.result);
                        result.setText("The text is not a valid URL.");
                    }

//                Toast.makeText(Checker.this, inputText, Toast.LENGTH_SHORT).show();
                if (flag==1) {
                    // Define the JSON request body with the user-entered URL
                    String apiKey = "AIzaSyAgwwgxcQNTQbFfd4ZqId8r9r9xnkmVcDA";

                    // Define the API URL
                    String apiUrl = "https://safebrowsing.googleapis.com/v4/threatMatches:find?key=" + apiKey;
                    ArrayList<String[]> comb=new ArrayList<>();
//
                    String ar[]=new String[]{"[\"MALWARE\", \"SOCIAL_ENGINEERING\"]","[\"WINDOWS\", \"ANY_PLATFORM\", \"LINUX\", \"OSX\", \"ALL_PLATFORMS\", \"CHROME\"]"};
                    comb.add(ar);
                    ar= new String[]{"[\"CSD_DOWNLOAD_WHITELIST\"]","[\"WINDOWS\",  \"LINUX\", \"OSX\", ]"};
                    comb.add(ar);
                    String ar2[]=new String[]{"[\"UNWANTED_SOFTWARE\"]","[\"WINDOWS\", \"ANY_PLATFORM\", \"LINUX\", \"OSX\", \"ALL_PLATFORMS\", \"CHROME\",\"IOS\",\"ANDROID\"]"};
                    comb.add(ar2);
                    String ar3[]=new String[]{"[\"POTENTIALLY_HARMFUL_APPLICATION\"]","[\"IOS\",\"ANDROID\"]"};
                    comb.add(ar3);

//
//                    ar= new String[]{"WINDOWS", "ANY_PLATFORM", "LINUX", "OSX", "ALL_PLATFORMS", "CHROME","IOS","ANDROID"};
//                    comb.add(ar);

                    String jsonRequest="";
                    for(int i=0;i<comb.size();i++){

                         jsonRequest="{\n" +
                                "   \"threatInfo\": {\n" +
                                "     \"threatTypes\": "+comb.get(i)[0] + ",\n"+
                                "     \"platformTypes\": "+comb.get(i)[1] + ",\n"+
                                "     \"threatEntryTypes\": [\"URL\"],\n" +
                                "     \"threatEntries\": [\n" +
                                "       {\"url\": \"" + inputText + "\"}\n" +
                                "     ]\n" +
                                "   }\n" +
                                "}";
//                         Log.e("tag",jsonRequest);
                        makeApiRequest(apiUrl, jsonRequest);

//                        Toast.makeText(Checker.this, "00", Toast.LENGTH_SHORT).show();
                        Log.e("loop no.","loop"+i);
                    }
                } else {
                    Toast.makeText(Checker.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }



    public static boolean isValidUrl(String text) {
        try {
            new URL(text);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
    int i=0;
    private void makeApiRequest(String apiUrl, String jsonRequest) {

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(jsonRequest, JSON);
        Request request = new Request.Builder()
                .url(apiUrl)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    TextView result=findViewById(R.id.result);
                    result.setText("Sorry there was some problem.");
//                    Toast.makeText(Checker.this, "failure", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                runOnUiThread(() -> {
//                    // Handle the API response here
//                    Toast.makeText(Checker.this, "1", Toast.LENGTH_SHORT).show();
//                });

                if (response.isSuccessful()) {
                    final String responseBody = response.body().string();
                    runOnUiThread(() -> {
                        // Handle the API response here
                        TextView result=findViewById(R.id.result);
                        if(responseBody != null || responseBody.equals("{}"))
                            result.setText("this URL is safe");
                        else{
//                            String ans=responseBody;
                            result.setText("URL is unsafe");
                        }


                        Log.e("Response Code",  responseBody);
//                        Toast.makeText(Checker.this,Integer.toString(i++) , Toast.LENGTH_LONG).show();
                    });
                } else {
                    runOnUiThread(() -> {

                        int responseCode = response.code();
                        TextView result=findViewById(R.id.result);
                        result.setText("sorry failed to get API response. Error :"+responseCode);
                        Log.e("Response Code", "Response Code: " + responseCode);

//                        Toast.makeText(Checker.this, "Failed to get API response", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        });
    }
}
