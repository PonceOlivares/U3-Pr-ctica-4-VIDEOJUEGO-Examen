package mx.edu.ittepic.dadm_videojuego;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Lienzo(this));
    }

    public void musicaInicio(){

        mp = MediaPlayer.create(this, R.raw.introjuego);
        mp.start();
    }

    public void disparo(){
        mp = MediaPlayer.create(this, R.raw.disparo);
        mp.start();
    }

    public void explosion(){
        mp = MediaPlayer.create(this, R.raw.golpeadoyo);
        mp.start();
    }

    public  void gameOver(){
        mp = MediaPlayer.create(this, R.raw.gameoversonido);
        mp.start();
    }
    public void sonidoGanaste(){
        mp= MediaPlayer.create(this, R.raw.missioncomplete);
        mp.start();
    }
    public void detenerMusica(){
        mp.stop();
        mp.release();
    }

}
