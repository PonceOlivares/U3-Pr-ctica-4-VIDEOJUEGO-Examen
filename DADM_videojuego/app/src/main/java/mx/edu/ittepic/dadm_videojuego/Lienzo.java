package mx.edu.ittepic.dadm_videojuego;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class Lienzo extends View {
    Imagen puntero, punteroDisparo, botonReiniciar, loser, ganaste, botonReiniciarGanaste;
    Imagen fondo, alien1, alien2, alien3, disparoAlien1, disparoAlien2, disparoAlien3;
    Imagen nave, disparo, barrera;
    Imagen muro1, muro2;
    MainActivity musica, reinicio;
    int puntaje = 000;
    String cadena;

    public Lienzo(Context context) {
        super(context);
        cadena = "Puntuación: " + puntaje;

        fondo = new Imagen(R.drawable.fondo21, -50, -100, this);

        alien1 = new Imagen(R.drawable.alien, 75, 1, this);
        disparoAlien1 = new Imagen(R.drawable.disparoalien, 165, 10, this);

        alien2 = new Imagen(R.drawable.alien, 450, 1, this);
        disparoAlien2 = new Imagen(R.drawable.disparoalien, 540, 10, this);

        alien3 = new Imagen(R.drawable.alien, 850, -10, this);
        disparoAlien3 = new Imagen(R.drawable.disparoalien, 940, 10, this);

        muro1 = new Imagen(R.drawable.barrera2, 200, 700, this);
        muro2 = new Imagen(R.drawable.barrera2, 700, 700, this);


        nave = new Imagen(R.drawable.nave, 450, 1370, this);
        disparo = new Imagen(R.drawable.disparoyo, 470, 1370, this);


        loser = new Imagen(R.drawable.gameover, 225, 500, this);
        botonReiniciar = new Imagen(R.drawable.reset, 300, 950, this);

        ganaste = new Imagen(R.drawable.missioncomplete, 250, 600, this);
        botonReiniciarGanaste = new Imagen(R.drawable.reset, 350, 900, this);

        ganaste.hacerVisible(false);
        botonReiniciarGanaste.hacerVisible(false);

        musica = (MainActivity) context;
        musica.musicaInicio();

        alien1.mover2(6);
        disparoAlien1.mover3(8);

        alien2.mover2(5);
        disparoAlien2.mover3(7);

        alien3.mover2(4);
        disparoAlien3.mover3(6);

        loser.hacerVisible(false);
        botonReiniciar.hacerVisible(false);

        reinicio = (MainActivity) context;
        puntero = null;
        punteroDisparo = null;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        fondo.pintar(canvas, p);
        disparoAlien1.pintar(canvas, p);
        disparoAlien2.pintar(canvas, p);
        disparoAlien3.pintar(canvas, p);

        alien1.pintar(canvas, p);
        alien2.pintar(canvas, p);
        alien3.pintar(canvas, p);


        disparo.pintar(canvas, p);
        nave.pintar(canvas, p);
        muro1.pintar(canvas,p);
        muro2.pintar(canvas,p);
        ganaste.pintar(canvas, p);
        botonReiniciarGanaste.pintar(canvas, p);
        loser.pintar(canvas, p);
        botonReiniciar.pintar(canvas, p);



        p.setColor(Color.DKGRAY);
        p.setStrokeWidth(60);
        p.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0, 30, 1200, 30, p);

        p.setStrokeWidth(0);
        p.setTextSize(70);
        p.setColor(Color.RED);
        canvas.drawText(cadena, 50, 52, p);
        if (disparoAlien1.getY() >= getHeight()) {
            disparoAlien1.setY(alien1.getY() + 100);
        }
        if (disparoAlien2.getY() >= getHeight()) {
            disparoAlien2.setY(alien2.getY() + 100);
        }
        if (disparoAlien3.getY() >= getHeight()) {
            disparoAlien3.setY(alien3.getY() + 100);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xp = event.getX();
        float yp = event.getY();
        //mensaje= "No se ha tocado objeto";
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (nave.estaEnArea(xp, yp)) {
                    puntero = nave;
                    disparo = new Imagen(R.drawable.disparoyo, xp, yp, this);
                    punteroDisparo = disparo;

                    musica.disparo();
                }
                if (botonReiniciarGanaste.estaEnArea(xp, yp)) {
                    if (puntaje == 000) {
                        musica.detenerMusica();
                        Intent i = new Intent(reinicio, MainActivity.class);
                        reinicio.startActivity(i);
                    }
                }
                if (botonReiniciar.estaEnArea(xp, yp)) {
                    if (puntaje == -1) {
                        musica.detenerMusica();
                        Intent i = new Intent(reinicio, MainActivity.class);
                        reinicio.startActivity(i);
                    }
                }
                disparo.mover2(-18);
                break;
            case MotionEvent.ACTION_MOVE:
                if (puntero != null) {
                    nave.moverNave(xp);
                    if (puntero.colision(alien1) && puntero == nave) {
                        puntaje = -1;
                        nave.hacerVisible(false);
                        disparo.hacerVisible(false);
                        loser.hacerVisible(true);
                        botonReiniciar.hacerVisible(true);
                        alien1.hacerVisible(false);
                        alien2.hacerVisible(false);
                        alien3.hacerVisible(false);
                        disparoAlien1.hacerVisible(false);
                        disparoAlien2.hacerVisible(false);
                        disparoAlien3.hacerVisible(false);
                        muro1.hacerVisible(false);
                        muro2.hacerVisible(false);
                        musica.gameOver();
                    }
                    if (puntero.colision(disparoAlien1) && puntero == nave) {
                        puntaje = -1;
                        nave.hacerVisible(false);
                        disparo.hacerVisible(false);
                        loser.hacerVisible(true);
                        botonReiniciar.hacerVisible(true);
                        alien1.hacerVisible(false);
                        alien2.hacerVisible(false);
                        alien3.hacerVisible(false);
                        disparoAlien1.hacerVisible(false);
                        disparoAlien2.hacerVisible(false);
                        disparoAlien3.hacerVisible(false);
                        muro1.hacerVisible(false);
                        muro2.hacerVisible(false);
                        musica.gameOver();
                    }
                    if (puntero.colision(alien2) && puntero == nave) {
                        puntaje = -1;
                        nave.hacerVisible(false);
                        disparo.hacerVisible(false);
                        loser.hacerVisible(true);
                        botonReiniciar.hacerVisible(true);
                        alien1.hacerVisible(false);
                        alien2.hacerVisible(false);
                        alien3.hacerVisible(false);
                        disparoAlien1.hacerVisible(false);
                        disparoAlien2.hacerVisible(false);
                        disparoAlien3.hacerVisible(false);
                        muro1.hacerVisible(false);
                        muro2.hacerVisible(false);
                        musica.gameOver();
                    }
                    if (puntero.colision(disparoAlien2) && puntero == nave) {
                        puntaje = -1;
                        nave.hacerVisible(false);
                        disparo.hacerVisible(false);
                        loser.hacerVisible(true);
                        botonReiniciar.hacerVisible(true);
                        alien1.hacerVisible(false);
                        alien2.hacerVisible(false);
                        alien3.hacerVisible(false);
                        disparoAlien1.hacerVisible(false);
                        disparoAlien2.hacerVisible(false);
                        disparoAlien3.hacerVisible(false);
                        muro1.hacerVisible(false);
                        muro2.hacerVisible(false);
                        musica.gameOver();
                    }
                    if (puntero.colision(alien3) && puntero == nave) {
                        puntaje = -1;
                        nave.hacerVisible(false);
                        disparo.hacerVisible(false);
                        loser.hacerVisible(true);
                        botonReiniciar.hacerVisible(true);
                        alien1.hacerVisible(false);
                        alien2.hacerVisible(false);
                        alien3.hacerVisible(false);
                        disparoAlien1.hacerVisible(false);
                        disparoAlien2.hacerVisible(false);
                        disparoAlien3.hacerVisible(false);
                        muro1.hacerVisible(false);
                        muro2.hacerVisible(false);
                        musica.gameOver();
                    }
                    if (puntero.colision(disparoAlien3) && puntero == nave) {
                        puntaje = -1;
                        nave.hacerVisible(false);
                        disparo.hacerVisible(false);
                        loser.hacerVisible(true);
                        botonReiniciar.hacerVisible(true);
                        alien1.hacerVisible(false);
                        alien2.hacerVisible(false);
                        alien3.hacerVisible(false);
                        disparoAlien1.hacerVisible(false);
                        disparoAlien2.hacerVisible(false);
                        disparoAlien3.hacerVisible(false);
                        muro1.hacerVisible(false);
                        muro2.hacerVisible(false);
                        musica.gameOver();
                    }

                    if (punteroDisparo.colision(alien1) && punteroDisparo == disparo) {
                        puntaje = puntaje + 100;
                        cadena = "Puntuación: " + puntaje;
                        alien1 = new Imagen(R.drawable.alien, 75, 1, this);
                        alien1.mover2(6);
                        alien1.hacerVisible(true);
                        disparoAlien1 = new Imagen(R.drawable.disparoalien, 165, 10, this);
                        disparoAlien1.mover3(8);
                        disparoAlien1.hacerVisible(true);
                        disparo = new Imagen(R.drawable.disparoyo, xp, yp, this);
                        disparo.hacerVisible(false);
                        disparo.hacerVisible(false);
                        musica.explosion();

                        alien1 = new Imagen(R.drawable.alien, 75, 1, this);
                        alien1.hacerVisible(true);
                        alien1.mover2(6);
                    }
                    if (punteroDisparo.colision(alien2) && punteroDisparo == disparo) {
                        puntaje = puntaje + 100;
                        cadena = "Puntuación: " + puntaje;
                        alien2 = new Imagen(R.drawable.alien, 450, 1, this);
                        alien2.mover2(5);
                        alien2.hacerVisible(true);
                        disparoAlien2 = new Imagen(R.drawable.disparoalien, 540, 10, this);
                        disparoAlien2.mover3(7);
                        disparoAlien2.hacerVisible(true);
                        disparo = new Imagen(R.drawable.disparoyo, xp, yp, this);
                        disparo.hacerVisible(false);
                        musica.explosion();


                        alien2 = new Imagen(R.drawable.alien, 450, 1, this);
                        alien2.hacerVisible(true);
                        alien2.mover2(5);

                    }
                    if (punteroDisparo.colision(alien3) && punteroDisparo == disparo) {
                        puntaje = puntaje + 100;
                        cadena = "Puntuación: " + puntaje;
                        alien3 = new Imagen(R.drawable.alien, 850, -10, this);
                        alien3.mover2(4);
                        alien3.hacerVisible(true);
                        disparoAlien3 = new Imagen(R.drawable.disparoalien, 940, 10, this);
                        disparoAlien3.mover3(6);
                        disparoAlien3.hacerVisible(true);
                        disparo = new Imagen(R.drawable.disparoyo, xp, yp, this);
                        disparo.hacerVisible(false);
                        musica.explosion();

                        alien3 = new Imagen(R.drawable.alien, 850, -10, this);
                        alien3.hacerVisible(true);
                        alien3.mover2(4);


                    }
                    if (punteroDisparo.colision(muro1)&&punteroDisparo==disparo){
                        muro1.hacerVisible(false);
                        disparo.hacerVisible(false);
                    }
                    if (punteroDisparo.colision(muro2)&&punteroDisparo==disparo){
                        muro2.hacerVisible(false);
                        disparo.hacerVisible(false);
                    }
                    if (puntaje == 400) {
                        puntaje = 000;
                        musica.detenerMusica();
                        disparo.hacerVisible(false);
                        nave.hacerVisible(false);
                        alien1.hacerVisible(false);
                        alien2.hacerVisible(false);
                        alien3.hacerVisible(false);
                        disparoAlien1.hacerVisible(false);
                        disparoAlien2.hacerVisible(false);
                        disparoAlien3.hacerVisible(false);
                        musica.sonidoGanaste();
                        ganaste.hacerVisible(true);
                        botonReiniciarGanaste.hacerVisible(true);
                        muro1.hacerVisible(false);
                        muro2.hacerVisible(false);
                    }
                }

                break;
            case MotionEvent.ACTION_UP:

                puntero = null;
                break;
        }
        invalidate();
        return true;
    }
}
