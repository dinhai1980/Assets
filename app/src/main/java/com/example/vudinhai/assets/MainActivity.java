package com.example.vudinhai.assets;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.text.HtmlCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button, button2, button3, button4;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    AssetManager assetManager = getAssets();
                    InputStream inputStream = assetManager.open("textFile.txt");

                    byte[] textFile = new byte[inputStream.available()];

                    inputStream.read(textFile);

                    inputStream.close();

                    String tmp = new String(textFile);

                    //textView.setText(tmp);

                    textView.setText(HtmlCompat.fromHtml(tmp,0));


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        imageView = findViewById(R.id.imageView);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AssetManager assetManager = getAssets();
                    InputStream inputStream = assetManager.open("image_1.jpg");


                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    imageView.setImageBitmap(bitmap);



                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("text", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", "vudinhai");
                    editor.putInt("dtb", 5);
                    editor.apply();
                    editor.commit();
                    Toast.makeText(MainActivity.this, "OKE", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, "NOKE", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SharedPreferences sharedPreferences = getSharedPreferences("text", MODE_PRIVATE);

                    String user =
                            sharedPreferences.getString("username","");
                    int dtb =
                            sharedPreferences.getInt("dtb",0);

                    Toast.makeText(MainActivity.this, user +" " + dtb, Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, "NOKE", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
