package id.putraprima.skorbola;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String HOMETEAM_KEY = "homeTeam";
    public static final String AWAYTEAM_KEY = "awayTeam";
    public static final String HOMETEAMIMG_KEY = "homeTeamImage";
    public static final String AWAYTEAMIMG_KEY = "awayTeamImage";

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1 & 2;
    private static final int GALLERY_REQUEST=1;

    private EditText homeTeamInput;
    private EditText awayTeamInput;
    private ImageView homeTeamImg;
    private ImageView awayTeamImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
        homeTeamInput = findViewById(R.id.home_team);
        awayTeamInput = findViewById(R.id.away_team);
        homeTeamImg = findViewById(R.id.home_logo);
        awayTeamImg = findViewById(R.id.away_logo);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }
        if (requestCode == 1){
                    if (data != null) {
                        try {
                            Uri imageUri1 = data.getData();
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri1);
                            homeTeamImg.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            Toast.makeText(this, "can't load the image", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, e.getMessage());
                        }
                    }
            }
        if (requestCode == 2){
            if (data != null){
                try {
                    Uri imageUri2 = data.getData();
                    Bitmap bit = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri2);
                    awayTeamImg.setImageBitmap(bit);
                } catch (IOException e){
                    Toast.makeText(this, "can't load the image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
    public void nextAction(View view) {
        String homeTeam = homeTeamInput.getText().toString();
        String awayTeam = awayTeamInput.getText().toString();
        if ((homeTeam).equals("") || (awayTeam).equals("")){
            Toast.makeText(this, "Isi nama team", Toast.LENGTH_SHORT).show();

        }else {
            Intent intent = new Intent(this, MatchActivity.class);
            homeTeamImg.buildDrawingCache();
            awayTeamImg.buildDrawingCache();
            Bitmap imageHome=homeTeamImg.getDrawingCache();
            Bitmap imageAway = awayTeamImg.getDrawingCache();
            Bundle extra = new Bundle();
            extra.putParcelable(HOMETEAMIMG_KEY, imageHome);
            extra.putParcelable(AWAYTEAMIMG_KEY, imageAway);
            intent.putExtras(extra);
            intent.putExtra(HOMETEAM_KEY, homeTeam);
            intent.putExtra(AWAYTEAM_KEY, awayTeam);
            startActivity(intent);
        }
    }
    public void changeImage1(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    public void changeImage2(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
}

