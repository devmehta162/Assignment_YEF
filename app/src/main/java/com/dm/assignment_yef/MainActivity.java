package com.dm.assignment_yef;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static android.R.layout.simple_list_item_1;

public class MainActivity extends AppCompatActivity {

    private TextView number_view;
    private Button generate_number;
    private ImageButton deleteNumbers;
    private MediaPlayer mediaPlayer, mediaPlayer2;
    private ArrayList<Integer> arrayList;
    private ArrayAdapter<Integer> adapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number_view = findViewById(R.id.number_view);
        generate_number = findViewById(R.id.generate_number);
        deleteNumbers = findViewById(R.id.delete_numbers);
        mediaPlayer = new MediaPlayer();
        mediaPlayer2 = new MediaPlayer();
        mediaPlayer2 = MediaPlayer.create(MainActivity.this, R.raw.sound);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.sound2);
        listView = findViewById(R.id.list_item);


        arrayList = new ArrayList<Integer>();
        adapter = new ArrayAdapter<Integer>(MainActivity.this, simple_list_item_1, arrayList);

        onclick();
    }

    private void onclick() {
        // to generate the number
        generate_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // to get 10 number only
                if (arrayList.size() > 9) {
                    Toast.makeText(MainActivity.this, "You have generated 10 numbers", Toast.LENGTH_SHORT).show();
                    number_view.setText("");
                } else {
                    Random random = new Random();

                    int number = random.nextInt(500) + 1;   // 1 to 500 (generate the number)
                    YoYo.with(Techniques.Hinge)
                            .duration(1000)
                            .repeat(0)
                            .playOn(number_view);
                    mediaPlayer.start();    // to add a sound
                    number_view.setText(String.valueOf(number));
                    arrayList.add(number);     // add the number to the list
                    Collections.sort(arrayList);   // sorting the list in ascending order
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                }

            }

        });
        // to delete the list
        deleteNumbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayList.size() > 0) {
                    arrayList.clear();
                    mediaPlayer2.start();
                    adapter.notifyDataSetChanged();
                    number_view.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Please Generate a number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}