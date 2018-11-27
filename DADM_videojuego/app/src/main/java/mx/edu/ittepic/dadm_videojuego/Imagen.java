package mx.edu.ittepic.dadm_videojuego;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;

public class Imagen {

    Bitmap icono;
    float x, y;
    boolean visible;
    float desplazamientox, desplazamientoy, desplazamientoy2;
    CountDownTimer ti;

    public Imagen(int resource, float _x, float _y, final Lienzo l) {
        icono = BitmapFactory.decodeResource(l.getResources(), resource);
        x = _x;
        y = _y;
        visible = true;
        ti = new CountDownTimer(1000, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                //x += desplazamientox;
                y += desplazamientoy;
                if (y >= l.getHeight()+20){
                    y=20;
                }

                if (y <= -20){
                    y= 1400;
                }

                y+=desplazamientoy2;
                l.invalidate();
            }

            @Override
            public void onFinish() {
                start();
            }
        };
    }

    public void pintar(Canvas canvas, Paint p) {

        if (visible) {
            canvas.drawBitmap(icono, x, y, p);
        }
    }

    public void hacerVisible(boolean v) {
        visible = v;
    }

    public boolean estaEnArea(float xp, float yp) {
        if (!visible) return false;
        float x2, y2;
        x2 = x + icono.getWidth();
        y2 = y + icono.getHeight();

        if (xp >= x && xp <= x2) {//validando el rango de las x´s.
            if (yp >= y && yp <= y2) {//valida el rando de las y´s .  Se puede hacer en un solo if.
                return true;
            }
        }

        return false;

    }

    public void mover(float xp, float yp) {
        x = xp - (icono.getWidth() / 2);
        y = yp - (icono.getHeight() / 2);
    }

    public boolean colision(Imagen objetoB) {
        float x2 = x + icono.getWidth();
        float y2 = y + icono.getHeight();

        if (objetoB.estaEnArea(x2, y)) {
            //caso 1
            return true;
        }
        if (objetoB.estaEnArea(x, y)) {
            //caso 2
            return true;
        }

        if (objetoB.estaEnArea(x2, y2)) {
            //caso 3
            return true;
        }

        if (objetoB.estaEnArea(x, y2)) {
            //caso 4
            return true;
        }
        return false;
    }

    public void mover2(float y1) {
       // desplazamientox = x1;
        desplazamientoy=y1;
        ti.start();

    }
    public void mover3(float y1) {
        desplazamientoy2=y1;
        ti.start();

    }
    public void setY(float y){
        this.y = y;
    }
    public float getY(){
        return y;
    }

    public void moverNave(float xp){
        x = xp - (icono.getWidth() / 2);
    }

}
