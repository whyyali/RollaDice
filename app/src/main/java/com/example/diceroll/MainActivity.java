package com.example.diceroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
        private ImageView diceimg;
        private AppCompatButton button;
        private int[] diceImages ={
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6
        };
        Random random = new Random();
        private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.dicevoice);

        diceimg = findViewById(R.id.diceimg);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                int randomIndex = random.nextInt(diceImages.length);
                int randomImageResource = diceImages[randomIndex];
                diceimg.setImageResource(randomImageResource);
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_image);

                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        int randomIndex = random.nextInt(diceImages.length);
                        int randomImageResource = diceImages[randomIndex];
                        diceimg.setImageResource(randomImageResource);
                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                diceimg.startAnimation(animation);
            }
        });
    }

    protected  void onDestory(){
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}