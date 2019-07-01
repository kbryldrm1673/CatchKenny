package com.kubra.catchthekenny;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView TextTime;
    TextView TextScore;
    int score=0;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextTime=(TextView) findViewById(R.id.TextTime);
        TextScore=(TextView) findViewById(R.id.textScore);
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9 };
        Sakla();
        new CountDownTimer(30000,10000){

            @Override
            public void onTick(long millisUntilFinished) {
               TextTime.setText("Time"+ millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                TextTime.setText("Time off");
                handler.removeCallbacks(runnable);
                for(ImageView Image:imageArray){
                    Image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert= new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("YENİDEN BAŞLA ");
                alert.setMessage("Yeniden başlamaya emin misin?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Intent intent =getIntent();
                       finish();
                       startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Oyun Bitti",Toast.LENGTH_SHORT).show();

                    }});
            alert.show();
            }

        }.start();

    }
    public void increaseScore(View view){
        score++;
        TextScore.setText("Score"+score);


    }
    public void Sakla(){
        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,50);
            }
        };
        handler.post(runnable);

    }
}