package tresenraya;

import java.awt.Color;

public class LogicaJuego {
    int turno, pX, pO; // Turno del jugador
    boolean habilitado; // Habilita y deshabilita el tablero

    /**
     * Inicializaremos el juego con las siguientes variables_
     * @param turno (Nos indicará el turno del jugador: 0 - X, 1 - O)
     * @param pX (Contiene el valor total de las victorias de este jugador)
     * @param pO (Contiene el valor total de las victorias de este jugador)
     */
    public LogicaJuego(int turno, int pX, int pO) {
        this.turno = turno;
        this.pX = pX;
        this.pO = pO;
    }

    /**
     * Obtener turno
     * @return 
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Insertar turno
     * @param turno 
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpO() {
        return pO;
    }

    public void setpO(int pO) {
        this.pO = pO;
    }
    
    /**
     * Llamaremos a este método para cambiar de turno
     */
    public void cambioTurno(){
        // Inserta código aquí...
        if(getTurno()==0){
            setTurno(1);
            
        }else{
            setTurno(0);
            
        }
        
    }
    
    /**
     * Comprobar si se ha conseguido un tres en raya, 
     * en caso que se haya conseguido devolverá 1, en caso contrario retorna 0 y continúa el juego
     * @param matriz (Tablero de juego)
     * @return 
     */
    public int comprobarJuego(int matriz[][]){
        // Inserta código aquí...
        // Comprobar si existe tres en raya
        // Tenemos 8 posibilidades para ganar el tres en raya
        // Comprobar horizontal
        // Comprobamos la primera línea horizontal - POSIBILIDAD 1
        if(matriz[0][0]==matriz[0][1]&&matriz[0][0]==matriz[0][2]){
            return 1;
            
        }
        
        //Comprobamos la segunda línea horizontal - POSIBILIDAD 2
        if(matriz[1][0]==matriz[1][1]&&matriz[1][0]==matriz[1][2]){
            return 1;
            
        }
        
        //Comprobamos la tercera línea horizontal - POSIBILIDAD 3
        if(matriz[2][0]==matriz[2][1]&&matriz[2][0]==matriz[2][2]){
            return 1;
            
        }
        //Comprobar vertical
        //Comprobamos la primera línea vertical - POSIBILIDAD 4
        if(matriz[0][0]==matriz[1][0]&&matriz[0][0]==matriz[2][0]){
            return 1;
            
        }
        
        //Comprobamos la segunda línea vertical - POSIBILIDAD 5
        if(matriz[0][1]==matriz[1][1]&&matriz[0][1]==matriz[2][1]){
            return 1;
            
        }
        
        //Comprobamos la tercera línea vertical - POSIBILIDAD 6
        if(matriz[0][2]==matriz[1][2]&&matriz[0][2]==matriz[2][2]){
            return 1;
            
        }
        //Comprobar en diagonal
        //Comprobamos la primera línea diagonal - POSIBILIDAD 7
        if(matriz[0][0]==matriz[1][1]&&matriz[0][0]==matriz[2][2]){
            return 1;
            
        }
        
        //Comprobamos la segunda línea diagonal - POSIBILIDAD 8
        if(matriz[2][0]==matriz[1][1]&&matriz[2][0]==matriz[0][2]){
            return 1;
            
        }
        // Si no hay tres en raya
        return 0;
    }
    
    /**
     * Deshabilitará el botón para evitar que se vuelva a posicionar una ficha en ese hueco
     * @param bt (Botón seleccionado)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     * @return 
     */
    public int tiradaJugador(javax.swing.JButton bt, int x, int y, int matriz[][], javax.swing.JPanel jp, javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
        // Deshabilita el botón, lo ponemos en "false" para desactivarlo
        bt.setEnabled(false);
        
        // Insertar la ficha en el botón
        // Llamamos al método "poner ficha"
        ponerFicha(matriz, x, y, bt);
        
        // Comprobar si se ha ganado la partida
        if(comprobarJuego(matriz)==0) {
            //No ha ganado nadie, se sigue la partida
            cambioTurno();
            
        }else{
            //Se encuentra un ganador
            ganador(lX, lO);
            
            //Hacemos que el table se deshabilite
            habilitado = false;
            habilitarTablero(jp);
            
        }
        
        // Deshabilitar tablero
        return 0;
    }
    
    /**
     * Actualizar la puntuación de cada jugador al ganar la partida
     * Al finalizar el incremento de puntuación es necesario cambiar de turno
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     */
    public void ganador(javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
        
        // Incrementa jugador ganador e inserta resultado en jLabel    
        if(getTurno()==0){
            //Incrementamos el valor de X si gana
            pX++;
            
            //Insertamos el resultado
            lX.setText("" + pX);
            
        }else{
            //Incrementamos el valor de 0 si gana
            pO++;
            
            //Insertamos el resultado
            lO.setText("" + pO);
            
        }
        
        //Hacemos que el que pierda la partida empiece en la siguiente ronda como en el juego original
        cambioTurno();
 
    }
    
    /**
     * Habilitará o deshabilitará el tablero dependiendo del estado de la variable habilitado
     * @param jp  (Panel dónde se sitúa el tablero de juego)
     */
    public void habilitarTablero( javax.swing.JPanel jp){
        // Inserta código aquí...
        // Bloquea todos los elementos del JPanel
        // Deshabilitamos todos los botones del panel
        for(int i=0;i<jp.getComponents().length;i++){
            jp.getComponent(i).setEnabled(habilitado);
            
        }
        
    }
    
    /**
     * Insertaremos la ficha en la posición correspondiente de la matriz
     * Llamaremos al método pintarFicha
     * @param matriz (Tablero de juego)
     * @param t (Turno)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param bt (Botón pulsado)
     */
    public void ponerFicha(int matriz[][], int x, int y, javax.swing.JButton bt){
        // Inserta código aquí...     
        // Insertamos la ficha en la matriz
        matriz[x][y] = getTurno();
        
        // Insertar ficha en la posición de la matriz
        pintarFicha(bt);
    }
    
    /**
     * Pintará la ficha en el tablero de juego visual, es decir, en el botón
     * @param bt (Botón pulsado)
     */
    private void pintarFicha(javax.swing.JButton bt){
        // Inserta código aquí...
        // Si el turno es de 0 pintará una X en rojo
        if(getTurno()==0) {
            //Hacemos que el valor escrito sea en color rojo
            bt.setForeground(Color.red);
            
            //Escribimos la X
            bt.setText("X");
            
        }   
        
        // Si el turno es de 1, pintará una O en azul 
        else { //Al cambiar turno escribimos la O
            //Hacemos que el valor escrito sea en color azul
            bt.setForeground(Color.blue);
            
            //Escribimos la O
            bt.setText("O");
            
        }
    }
    
    /**
     * Inicializa una nueva partida, reinicia la matriz (Tablero de juego) y habilita el tablero
     * 
     * ¡¡¡¡No es necesario modificar este método!!!!.
     * 
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     */
    public void iniciarPartida(int matriz[][], javax.swing.JPanel jp){
        // Rellenamos la matriz por primera vez, evitando que se repitan los números
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                matriz[x][y]=(x+10)*(y+10);
            }
        }

        // Habilitar tablero
         habilitado = true;
         habilitarTablero(jp);
    }
}
