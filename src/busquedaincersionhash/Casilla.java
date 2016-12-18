/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedaincersionhash;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author koerhunt
 */
public class Casilla {
    
    //Posiciones x,y
    private int posx;
    private int posy;
    
    //Data
    private int data;
    
    public void Casilla(){
    }

    public Casilla(int posx, int posy, int data) {
        this.posx = posx;
        this.posy = posy;
        this.data = data;
    }
    
    
    
    //Pintar
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.fillRect(posx, posy, 50, 30);
        g.setColor(Color.RED);
        g.drawRect(posx, posy, 50, 30);
        g.setColor(Color.BLACK);
        g.drawString(String.valueOf(this.data), posx+20, posy+20);
        g.setColor(c);
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
    
}