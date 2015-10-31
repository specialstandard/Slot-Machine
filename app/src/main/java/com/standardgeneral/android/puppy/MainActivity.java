package com.standardgeneral.android.puppy;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button button1;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    TextView textView_balance;

    Integer balance;
    Integer first = new Integer(0);
    Integer second = new Integer(0);
    Integer third = new Integer(0);
    final ArrayList<Integer> mImages = new ArrayList<>();
    private float y1,y2;
    static final int MIN_DISTANCE = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        balance = new Integer(100);

        button1 = (Button)findViewById(R.id.button1);
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        textView_balance = (TextView)findViewById(R.id.textView_balance);

        String tmp = "$" + balance.toString();
        textView_balance.setText(tmp);

        mImages.add(R.drawable.puppy_1);
        mImages.add(R.drawable.puppy_2);
        mImages.add(R.drawable.puppy_3);
        mImages.add(R.drawable.puppy_4);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinSlotMachine();
            }
        });
    }

public void spinSlotMachine(){

    final MediaPlayer mp = MediaPlayer.create(this, R.raw.slot_machine_handle_pull);
    mp.start();

    AnimationDrawable animation = new AnimationDrawable();
    for (int i=0; i<20; i++) {
        Random rand = new Random();
        first = mImages.get(rand.nextInt(mImages.size()));
        animation.addFrame(getResources().getDrawable(first), 100);
    }
    animation.setOneShot(true);

    AnimationDrawable animation2 = new AnimationDrawable();
    for (int i=0; i<20; i++) {
        Random rand = new Random();
        second = mImages.get(rand.nextInt(mImages.size()));
        animation2.addFrame(getResources().getDrawable(second), 100);

    }
    animation.setOneShot(true);

    AnimationDrawable animation3 = new AnimationDrawable();
    for (int i=0; i<20; i++) {
        Random rand = new Random();
        third = mImages.get(rand.nextInt(mImages.size()));
        animation3.addFrame(getResources().getDrawable(third), 100);

    }
    animation.setOneShot(true);

    imageView1.setImageDrawable(animation);
    animation.start();
    imageView2.setImageDrawable(animation2);
    animation2.start();;
    imageView3.setImageDrawable(animation3);
    animation3.start();

    String tmp2 = new String();
    if(R.drawable.puppy_1 == first && first == second && first == third){
        Toast.makeText(MainActivity.this, "You Win $10!", Toast.LENGTH_LONG).show();
        balance+=15;
        tmp2 = "$" + balance.toString();
        textView_balance.setText(tmp2);
        final MediaPlayer mpWin = MediaPlayer.create(this, R.raw.coins_drop_pirate_gold);
        mpWin.start();
        View layout = (View)findViewById(R.id.layout_prize_1);
        layout.setBackgroundColor(0x2196f3);

    } else if(R.drawable.puppy_2 == first && first == second && first == third) {
        Toast.makeText(MainActivity.this, "You Win $25!", Toast.LENGTH_LONG).show();
        balance += 25;
        tmp2 = "$" + balance.toString();
        textView_balance.setText(tmp2);
        final MediaPlayer mpWin = MediaPlayer.create(this, R.raw.coins_drop_pirate_gold);
        mpWin.start();
        View layout = (View)findViewById(R.id.layout_prize_2);
        layout.setBackgroundColor(0x2196f3);
    } else if(R.drawable.puppy_3 == first && first == second && first == third){
        Toast.makeText(MainActivity.this, "You Win $35!", Toast.LENGTH_LONG).show();
        balance+=35;
        tmp2 = "$" + balance.toString();
        textView_balance.setText(tmp2);
        final MediaPlayer mpWin = MediaPlayer.create(this, R.raw.coins_drop_pirate_gold);
        mpWin.start();
        View layout = (View)findViewById(R.id.layout_prize_3);
        layout.setBackgroundColor(0x2196f3);
    }else if(R.drawable.puppy_4 == first && first == second && first == third){
        Toast.makeText(MainActivity.this, "You Win $45!", Toast.LENGTH_LONG).show();
        balance+=45;
        tmp2 = "$" + balance.toString();
        textView_balance.setText(tmp2);
        final MediaPlayer mpWin = MediaPlayer.create(this, R.raw.coins_drop_pirate_gold);
        mpWin.start();
        View layout = (View)findViewById(R.id.layout_prize_4);
        layout.setBackgroundColor(0x2196f3);
    } else {
        balance -= 1;
        tmp2 = "$" + balance.toString();
        textView_balance.setText(tmp2);

    }
}

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                y2 = event.getY();
                float deltaX = y2 - y1;
                if (Math.abs(deltaX) > MIN_DISTANCE)
                {
                    //Toast.makeText(this, "left2right swipe", Toast.LENGTH_SHORT).show ();
                    spinSlotMachine();
                }
                else
                {
                    // consider as something else - a screen tap for example
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*        if (id == R.id.action_settings) {
            return true;
        }*/
        if (id == R.id.action_sound_off) {
            AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
            return true;
        }
        if (id == R.id.action_sound_on) {
            AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
            amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
