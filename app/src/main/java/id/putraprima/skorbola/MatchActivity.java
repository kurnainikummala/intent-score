package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    TextView homeoutput;
    TextView awayOutput ;
    ImageView img1;
    ImageView img2;
    TextView homeSore;
    TextView awayScore;
    private static final String Home="home";
    private static final String Away = "away";
    private static final String hasilHome = "hasilhomeScore";
    private static  final String hasilaway = "hasilawayScore";
    private static final String hasilDraw = "hasildraw";
    Button aadScoreHome;
    Button addScoreAway;
    int scorehome=0;
    int scoreaway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO

        homeoutput = findViewById(R.id.txt_home);
        awayOutput = findViewById(R.id.txt_away);
        img1 = findViewById(R.id.home_logo);
        img2 = findViewById(R.id.away_logo);
        homeSore = findViewById(R.id.score_home);
        awayScore = findViewById(R.id.score_away);

        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            Bundle extra = getIntent().getExtras();
            Bitmap bmp = extra.getParcelable("homeTeamImage");
            img1.setImageBitmap(bmp);
            Bitmap bimp = extra.getParcelable("awayTeamImage");
            img2.setImageBitmap(bimp);
            homeoutput.setText(extras.getString("homeTeam"));
            awayOutput.setText(extras.getString("awayTeam"));
        }
    }
//tombol untuk add score menambah satu angka dari angka 0, setiap kali ditekan
    public void home_score(int score1){
        homeSore.setText(String.valueOf(score1));
    }
    public void away_score(int score2){
        awayScore.setText(String.valueOf(score2));
    }
    public void homeScore(View view) {
        scorehome=scorehome+1;
        home_score(scorehome);
    }

    public void awayScore(View view) {
        scoreaway=scoreaway+1;
        away_score(scoreaway);
    }
    //tombol cek hasil result

    public void Hasil(View view) {
        Intent intent = new Intent(this,ResultActivity.class);
        String home = homeoutput.getText().toString();
        String away = awayOutput.getText().toString();
        if(scorehome>scoreaway){
            intent.putExtra(hasilHome, home);
            startActivity(intent);
        } else if (scoreaway>scorehome){
            intent.putExtra(hasilHome, away);
            startActivity(intent);
        }else {
            intent.putExtra(hasilHome, "Hasil Draw");
            startActivity(intent);
        }
    }
}
