/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busquedaincersionhash;

import javax.swing.JOptionPane;

/**
 *
 * @author koerhunt
 */

public class Hash {
    
    //Solucion de colisiones
    static final int PRUEBA_LINEAL = 0;
    static final int PRUEBA_CUADRATICA = 1;
    static final int DOBLE_DIRECCION_HASH = 2;
    
    //Metodos de resolucion
    static final int METODO_MOD = 0;
    static final int METODO_CUADRADO = 1;
    static final int METODO_PLEGAMIENTO = 2;
    static final int METODO_TRUNCAMIENTO = 3;
    
    //que metodo usar
    static int metodo;
    
    //intentos
    static int intentos = 0;
    
    
    //##------- METODOS PRINCIPALES -------##//
    private static int calcularIndice(int clave, Casilla[] array, int metodo_hash){
        switch(metodo_hash){
            case METODO_MOD:
                return GenerarIndicePorMod(clave, array);
            case METODO_CUADRADO:
                return GenerarIndicePorCuadrado10n(clave, array);
            case METODO_PLEGAMIENTO:
                return GenerarIndicePorPlegamiento10n(clave, array);
            case METODO_TRUNCAMIENTO:
                return GenerarIndicePorTruncamiento10n(clave, array);
            default:
                return GenerarIndicePorMod(clave, array);
        }
    }
    
    //Insertar elemento en la posicion k
    public static void insertarElemento(int data, int k, Casilla[] array, int metodo_hash, int solucion_de_colision){
        
        
        //se calcula la posicion normalmente con el metodo indicado
        if(k==-1){
          k = calcularIndice(data, array, metodo_hash);
        }
        JOptionPane.showMessageDialog(null, "Indice calculado: "+k,"Clave Hash", JOptionPane.INFORMATION_MESSAGE);
        
        
        if(array[k].getData()==0){
            array[k].setData(data);
            JOptionPane.showMessageDialog(null, "Indice: "+k+" asignado para "+data,"Clave Hash", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null, "el indice "+k+" ya ha sido asignado, recalculando...", "Indice ocupado",JOptionPane.ERROR_MESSAGE);
            switch(solucion_de_colision){
                case Hash.PRUEBA_LINEAL:
                    pruebaLineal(data,array,k);
                    break;
                case Hash.PRUEBA_CUADRATICA:
                    pruebaCuadratica(data,array,k);
                    break;
                case Hash.DOBLE_DIRECCION_HASH:
                    dobleDireccionHash(data,array,k);
                    break;
                default:
                    pruebaLineal(data,array,k);
                    break;
            }
        }
    }
    
    
    //##------- METODOS PARA CALCULAR LOS INDICE EN BASE A LA CLAVE -------##//
    //Para un arreglo de n elementos
    private static int GenerarIndicePorMod(int clave, Casilla[] array){
        metodo = Hash.METODO_MOD;
        int k = clave%(array.length);
        return k;
    }
    
    //Para un arreglo de 10 elementos
    private static int GenerarIndicePorCuadrado10n(int clave, Casilla[] array){
        metodo = Hash.METODO_CUADRADO;
        
        String cad = String.valueOf(Math.pow( Double.parseDouble(String.valueOf(clave)) , 2.0));
        int k;
        
        if(cad.length()%2==0){
            k = Integer.parseInt( cad.substring(cad.length()/2-1, cad.length()/2) );
        }else{
            k = Integer.parseInt( cad.substring(cad.length()/2-1, cad.length()/2) );
        }
        
        return k;
    }
    
    //Para un arreglo de 10 elementos
    private static int GenerarIndicePorPlegamiento10n(int clave, Casilla[] array){
        metodo = Hash.METODO_PLEGAMIENTO;
        
        String cad = String.valueOf(clave);
        int k = sumarRecursivo(cad);
        return k;
    }
    
    //Para un arreglo de 10 elementos
    private static int GenerarIndicePorTruncamiento10n(int data, Casilla[] array){
        metodo = Hash.METODO_TRUNCAMIENTO;
        
        String cad = String.valueOf(data);
        int k = Integer.parseInt(String.valueOf(cad.charAt(cad.length()-1)));
        
        return k;
    }
        
    //##------- METODOS PARA SOLUCIONAR LAS COLISIONES -------##//
    
    //Solucion de colision por prueba lineal
    private static int pruebaLineal(int data, Casilla[] array, int fpos) {
        int flag = fpos;
        boolean found = false;
        do{
            //incremento
            flag++;
            
            //tratamiento de cola circular
            if(flag>=array.length){
                flag = 0;
            }
            
            JOptionPane.showMessageDialog(null, "nuevo indice calculado: "+flag,", revisando...", JOptionPane.INFORMATION_MESSAGE);
            
            if(array[flag].getData()==0){
                array[flag].setData(data);
                found = true;
                JOptionPane.showMessageDialog(null, "Indice: "+flag+" asignado para "+data,"Clave Hash", JOptionPane.INFORMATION_MESSAGE);
                return 0;
            }else if(array[flag].getData()==data){
                return flag;
            }else{
                JOptionPane.showMessageDialog(null, "el indice "+flag+" ya ha sido asignado, recalculando...", "Indice ocupado",JOptionPane.ERROR_MESSAGE);
            }
        
        }while(flag!=fpos);
        if(!found){
            JOptionPane.showMessageDialog(null, "El arreglo se encuentra lleno","Arreglo lleno",JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        return 0;
    }
    
    //Solucion de colision por prueba lineal
    private static int pruebaCuadratica(int data, Casilla[] array, int fpos) {
        
        //pos
        int flag = fpos;
        
        //i
        int i = 1;
        
        boolean found = false;
        
        do{
           
            flag = flag + (int)Math.pow( Double.parseDouble( String.valueOf(i)  ), 2.0 );
            i++;
            
            if(flag>=array.length){
                flag = 0;
                i = 1;
            }
            
            JOptionPane.showMessageDialog(null, "nuevo indice calculado: "+flag,", revisando...", JOptionPane.INFORMATION_MESSAGE);
            
            if(array[flag].getData()==0){
                array[flag].setData(data);
                found = true;
                JOptionPane.showMessageDialog(null, "Indice: "+flag+" asignado para "+data,"Clave Hash", JOptionPane.INFORMATION_MESSAGE);
                return 0;
            }else if(array[flag].getData()==data){
                return flag;
            }else{
                JOptionPane.showMessageDialog(null, "el indice "+flag+" ya ha sido asignado, recalculando...", "Indice ocupado",JOptionPane.ERROR_MESSAGE);
            }
        
        }while(flag!=fpos);
        if(!found){
            JOptionPane.showMessageDialog(null, "El arreglo se encuentra lleno","Arreglo lleno",JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        return 0;
    }

    //Doble direccion hash
    private static void dobleDireccionHash(int data, Casilla[] array, int k) {
        //JOptionPane.showMessageDialog(null, "La resolucion de colision para este metodo hash podrai causar recursividad infinita, por lo tanto se han hecho algunas modificaciones");
        switch(metodo){
            case METODO_MOD:
                insertarElemento(data,  GenerarIndicePorMod(k+1, array), array,  METODO_MOD, DOBLE_DIRECCION_HASH);
                break;
            case METODO_CUADRADO:
                insertarElemento(data,  GenerarIndicePorCuadrado10n(k, array), array,  METODO_MOD, DOBLE_DIRECCION_HASH);
                break;
            case METODO_PLEGAMIENTO:
                insertarElemento(data,  GenerarIndicePorPlegamiento10n(k+1, array), array,  METODO_MOD, DOBLE_DIRECCION_HASH);
                break;
            case METODO_TRUNCAMIENTO:
                insertarElemento(data,  GenerarIndicePorTruncamiento10n(k+1, array), array,  METODO_MOD, DOBLE_DIRECCION_HASH);
                break;
            default:
                insertarElemento(data,  GenerarIndicePorMod(k+1, array), array,  METODO_MOD, DOBLE_DIRECCION_HASH);
                break;
        }
    }
    
    
    //##------- METODOS AUXILIARES-------##//
    //Suma recursiva para 10 digitos
    // 4 + 15 =  19 = 1 + 9 = 10 = 1 + 0 = 1
    private static int sumarRecursivo(String cad) {
        int suma = 0;
        for(int i = 0; i < cad.length(); i++){
            suma += Integer.parseInt(String.valueOf(cad.charAt(i)));
        }
        if(suma>9){
            return sumarRecursivo(String.valueOf(suma));
        }else{
            return suma;
        }
    }
    
    //##------- METODOS DE BUSQUEDA-------##//
    //Algoritmos de busqueda
    public static byte busquedaSecuencial(int data, Casilla[] array){    
        boolean flag = false;
        
        int i;
        
        for(i = 0; i<array.length; i++){
            if(array[i].getData()==data){
                flag = true;
                break;
            }
        }
        
        if(flag){
            JOptionPane.showMessageDialog(null, "El elemento "+data+" se encontro en el indice "+i);
            return (byte)i;
        }else{
            JOptionPane.showMessageDialog(null, "El elemento "+data+" no se encontro en el arreglo");
            return -1;
        }
    }

    static byte buscarPorHash(int data, Casilla[] casillas, int medoto_hash) {
        int k = calcularIndice(data, casillas, medoto_hash);
        
        if(k==-1 || k==0){
            JOptionPane.showMessageDialog(null, "Elemento no encontrado ");
            return -1;
        }else{
            if(casillas[k].getData()==data){
                JOptionPane.showMessageDialog(null, "Elemento "+data+" encontrado en el indice "+k);
                return (byte) k;
            }
        }
        return -1;
    }

}

//Para evitar recursividad
//        if(intentos<=2){
//            intentos++;
//            insertarElemento(data, array,k2,solucion_de_colision);
//        }else{
//            intentos = 0;
//            JOptionPane.showMessageDialog(null, "Numero de intentos maximos de asignacion alcanzados, realizando prueba lineal");
//            insertarElemento(data, array,k2,PRUEBA_LINEAL);
//        }