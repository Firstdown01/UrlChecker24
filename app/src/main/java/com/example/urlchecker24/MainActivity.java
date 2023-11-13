package com.example.urlchecker24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton = findViewById(R.id.myButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Checker.class);

                startActivity(intent);
            }
        });

    }
}
/*ArrayList<ArrayList<String[]>> comb=new ArrayList<>();
                    ArrayList<String[]> list=new ArrayList<>();
                    //comb 0
                    String ar[]=new String[]{"MALWARE", "SOCIAL_ENGINEERING"};
                    list.add(ar);
                    ar= new String[]{"WINDOWS", "ANY_PLATFORM", "LINUX", "OSX", "ALL_PLATFORMS", "CHROME"};
                    list.add(ar);
                    //comb 1
                    String ar[]=new String[]{"POTENTIALLY_HARMFUL_APPLICATION"};
                    list.add(ar);
                    ar= new String[]{"ANDROID", "IOS"};
                    list.add(ar);
                    //comb 2
                    String ar[]=new String[]{"MALWARE", "SOCIAL_ENGINEERING"};
                    list.add(ar);
                    ar= new String[]{"WINDOWS", "ANY_PLATFORM", "LINUX", "OSX", "ALL_PLATFORMS", "CHROME","IOS","ANDROID"};
                    list.add(ar);
                    ar= new String[]{"CSD_DOWNLOAD_WHITELIST"};
                    list.add(ar);
                    ar= new String[]{"WINDOWS", "ANY_PLATFORM", "LINUX", "OSX", "ALL_PLATFORMS", "CHROME","IOS","ANDROID"};
                    list.add(ar);

                    for(int i=0;i<5;i++){

                        String jsonRequest="{\n" +
                                "   \"threatInfo\": {\n" +
                                "     \"threatTypes\": "+comb[i][0] + ",\n"+
                                "     \"platformTypes\": "+comb[i][1] + ",\n"+
                                "     \"threatEntryTypes\": [\"URL\"],\n" +
                                "     \"threatEntries\": [\n" +
                                "       {\"url\": \"" + inputText + "\"}\n" +
                                "     ]\n" +
                                "   }\n" +
                                "}";
                        makeApiRequest(apiUrl, jsonRequest);
                    }
                    String jsonRequest = "{\n" +
                            "   \"threatInfo\": {\n" +
                            "     \"threatTypes\": [\"MALWARE\", \"SOCIAL_ENGINEERING\"],\n" +
                            "     \"platformTypes\": [\"WINDOWS\"],\n" +
                            "     \"threatEntryTypes\": [\"URL\"],\n" +
                            "     \"threatEntries\": [\n" +
                            "       {\"url\": \"" + inputText + "\"}\n" +
                            "     ]\n" +
                            "   }\n" +
                            "}";


                } else {
                    Toast.makeText(Checker.this, "Please enter a URL", Toast.LENGTH_SHORT).show();
                }
*/