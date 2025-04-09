package org.mps.ronqi2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mps.dispositivo.Dispositivo;

public class RonQI2SilverTest {

    
    /*
     * Analiza con los caminos base qué pruebas se han de realizar para comprobar que al inicializar funciona como debe ser. 
     * El funcionamiento correcto es que si es posible conectar ambos sensores y configurarlos, 
     * el método inicializar de ronQI2 o sus subclases, 
     * debería devolver true. En cualquier otro caso false. Se deja programado un ejemplo.
     */

    

     @Test
    @DisplayName("Test de que inicializar no funciona correctamente nada ")
    public void testInicializarMal(){
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        when(dispositivo.conectarSensorPresion()).thenReturn(false);
        when(dispositivo.conectarSensorSonido()).thenReturn(false);
        when (dispositivo.configurarSensorPresion()).thenReturn(false);
        when(dispositivo.configurarSensorSonido()).thenReturn(false);

        ronQ.anyadirDispositivo(dispositivo);
        boolean resultado = ronQ.inicializar();

        assertEquals(false, resultado);
    }
    @Test
    @DisplayName("Test de que inicializar no funciona correctamente uno de los dos")
    public void testInicializarUnoMal(){
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        when(dispositivo.conectarSensorPresion()).thenReturn(true);
        when(dispositivo.conectarSensorSonido()).thenReturn(false);
        when (dispositivo.configurarSensorPresion()).thenReturn(true);
        when(dispositivo.configurarSensorSonido()).thenReturn(true);

        ronQ.anyadirDispositivo(dispositivo);
        boolean resultado = ronQ.inicializar();

        assertEquals(false, resultado);
    }
    @Test
    @DisplayName("Que configurar sonido sea false")
    public void testConfigurarSonidoFalse(){
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        when(dispositivo.conectarSensorPresion()).thenReturn(true);
        when(dispositivo.conectarSensorSonido()).thenReturn(true);
        when (dispositivo.configurarSensorPresion()).thenReturn(true);
        when(dispositivo.configurarSensorSonido()).thenReturn(false);

        ronQ.anyadirDispositivo(dispositivo);
        boolean resultado = ronQ.inicializar();

        assertEquals(false, resultado);
    }
    @Test
    @DisplayName("Que configurar presion sea false")
    public void testConfigurarPresionFalse(){
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        when(dispositivo.conectarSensorPresion()).thenReturn(true);
        when(dispositivo.conectarSensorSonido()).thenReturn(true);
        when (dispositivo.configurarSensorPresion()).thenReturn(false);
        when(dispositivo.configurarSensorSonido()).thenReturn(true);

        ronQ.anyadirDispositivo(dispositivo);
        boolean resultado = ronQ.inicializar();

        assertEquals(false, resultado);
    }
    

    /*
     * Un inicializar debe configurar ambos sensores, comprueba que cuando se inicializa de forma correcta (el conectar es true), 
     * se llama una sola vez al configurar de cada sensor.
     */

     @Test
     @DisplayName("Test de que inicializar funciona correctamente")
     public void testInicializar(){
            RonQI2Silver ronQ = new RonQI2Silver();
            Dispositivo dispositivo = mock(Dispositivo.class);

            when(dispositivo.conectarSensorPresion()).thenReturn(true);
            when(dispositivo.conectarSensorSonido()).thenReturn(true);
            when (dispositivo.configurarSensorPresion()).thenReturn(true);
            when(dispositivo.configurarSensorSonido()).thenReturn(true);

            ronQ.anyadirDispositivo(dispositivo);
            boolean resultado = ronQ.inicializar();

            assertEquals(true, resultado);
            verify(dispositivo).configurarSensorPresion();
            verify(dispositivo).configurarSensorSonido();
     }




    /*
     * Un reconectar, comprueba si el dispositivo desconectado, en ese caso, conecta ambos y devuelve true si ambos han sido conectados. 
     * Genera las pruebas que estimes oportunas para comprobar su correcto funcionamiento. 
     * Centrate en probar si todo va bien, o si no, y si se llama a los métodos que deben ser llamados.
     */

     @Test 
     @DisplayName("Test de que reconectar funciona correctamente")
     public void testReconectar(){
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        when(dispositivo.estaConectado()).thenReturn(false);
        when(dispositivo.conectarSensorPresion()).thenReturn(true);
        when(dispositivo.conectarSensorSonido()).thenReturn(true);

        ronQ.anyadirDispositivo(dispositivo);
        boolean resultado = ronQ.reconectar();

        assertEquals(true, resultado);
        verify(dispositivo).conectarSensorPresion();
        verify(dispositivo).conectarSensorSonido();
     }  

     @Test 
     @DisplayName("Test de que reconectar no funciona correctamente")
     public void testReconectarMal(){
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        when(dispositivo.estaConectado()).thenReturn(true);
        when(dispositivo.conectarSensorPresion()).thenReturn(false); //te da igual 
        when(dispositivo.conectarSensorSonido()).thenReturn(false); // te da igual 

        ronQ.anyadirDispositivo(dispositivo);
        boolean resultado = ronQ.reconectar();

        assertEquals(false, resultado);
        verify(dispositivo, times(0)).conectarSensorPresion();
        verify(dispositivo, times(0)).conectarSensorSonido();
     }
     @Test 
     @DisplayName("Test de reconectar que no esten conectado pero que conectarSensorPresion sea true y conectarSensorSonido false")
        public void testReconectarMalUno(){
            RonQI2Silver ronQ = new RonQI2Silver();
            Dispositivo dispositivo = mock(Dispositivo.class);
    
            when(dispositivo.estaConectado()).thenReturn(false);
            when(dispositivo.conectarSensorPresion()).thenReturn(true);
            when(dispositivo.conectarSensorSonido()).thenReturn(false);
    
            ronQ.anyadirDispositivo(dispositivo);
            boolean resultado = ronQ.reconectar();
    
            assertEquals(false, resultado);
            verify(dispositivo).conectarSensorPresion();
            verify(dispositivo).conectarSensorSonido();
        }
    @Test
    @DisplayName("Test de reconectar que no esten conectado pero que conectarSensorPresion sea false y conectarSensorSonido true")
    public void testReconectarMalDos(){
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        when(dispositivo.estaConectado()).thenReturn(false);
        when(dispositivo.conectarSensorPresion()).thenReturn(false);
        when(dispositivo.conectarSensorSonido()).thenReturn(true);

        ronQ.anyadirDispositivo(dispositivo);
        boolean resultado = ronQ.reconectar();

        assertEquals(false, resultado);
        verify(dispositivo).conectarSensorPresion();
        verify(dispositivo,times(0)).conectarSensorSonido();
    }

    
    /* 
     * El método evaluarApneaSuenyo, evalua las últimas 5 lecturas realizadas con obtenerNuevaLectura(), 
     * y si ambos sensores superan o son iguales a sus umbrales, que son thresholdP = 20.0f y thresholdS = 30.0f;, 
     * se considera que hay una apnea en proceso. Si hay menos de 5 lecturas también debería realizar la media.
    */
        
    @Test
    @DisplayName("Test de que evaluarApneaSuenyo funciona correctamente")
    public void testApneaCorrecto() {
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        // Simulamos 5 lecturas de presión y sonido por encima del umbral
        when(dispositivo.leerSensorPresion()).thenReturn(25.0f, 30.0f, 35.0f, 40.0f, 45.0f);
        when(dispositivo.leerSensorSonido()).thenReturn(30.0f, 35.0f, 40.0f, 45.0f, 50.0f);

        ronQ.anyadirDispositivo(dispositivo);
        ronQ.inicializar();

        for (int i = 0; i < 5; i++) {
            ronQ.obtenerNuevaLectura();
        }

        boolean resultado = ronQ.evaluarApneaSuenyo();

        assertTrue(resultado);
        verify(dispositivo, times(5)).leerSensorPresion();
        verify(dispositivo, times(5)).leerSensorSonido();
    }

    @Test
    @DisplayName("Test de que evaluarApneaSuenyo en el que el valor es menor que el umbral")
    public void testApneaIncorrecto() {
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        // Simulamos 5 lecturas de presión y sonido por debajo del umbral
        when(dispositivo.leerSensorPresion()).thenReturn(15.0f, 10.0f, 5.0f, 0.0f, -5.0f);
        when(dispositivo.leerSensorSonido()).thenReturn(20.0f, 15.0f, 10.0f, 5.0f, 0.0f);

        ronQ.anyadirDispositivo(dispositivo);
        ronQ.inicializar();

        for (int i = 0; i < 5; i++) {
            ronQ.obtenerNuevaLectura();
        }

        boolean resultado = ronQ.evaluarApneaSuenyo();

        assertEquals(false, resultado);
        verify(dispositivo, times(5)).leerSensorPresion();
        verify(dispositivo, times(5)).leerSensorSonido();
    }

    @Test
    @DisplayName("Test de que evaluarApneaSuenyo el valor de sonido es menor que el umbral y el valor de presion es mayor")
    public void testApneaIncorrectoSonido() {
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        // Simulamos 5 lecturas de presión por encima del umbral y sonido por debajo
        when(dispositivo.leerSensorPresion()).thenReturn(25.0f, 30.0f, 35.0f, 40.0f, 45.0f);
        when(dispositivo.leerSensorSonido()).thenReturn(20.0f, 15.0f, 10.0f, 5.0f, 0.0f);

        ronQ.anyadirDispositivo(dispositivo);
        ronQ.inicializar();

        for (int i = 0; i < 5; i++) {
            ronQ.obtenerNuevaLectura();
        }

        boolean resultado = ronQ.evaluarApneaSuenyo();

        assertEquals(false, resultado);
        verify(dispositivo, times(5)).leerSensorPresion();
        verify(dispositivo, times(5)).leerSensorSonido();
    }
    @Test
    @DisplayName("Test de que evaluarApneaSuenyo el valor de sonido es mayor que el umbral y el valor de presion es menor")
    public void testApneaIncorrectoPresion() {
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        // Simulamos 5 lecturas de presión por debajo del umbral y sonido por encima
        when(dispositivo.leerSensorPresion()).thenReturn(15.0f, 10.0f, 5.0f, 0.0f, -5.0f);
        when(dispositivo.leerSensorSonido()).thenReturn(30.0f, 35.0f, 40.0f, 45.0f, 50.0f);

        ronQ.anyadirDispositivo(dispositivo);
        ronQ.inicializar();

        for (int i = 0; i < 5; i++) {
            ronQ.obtenerNuevaLectura();
        }

        boolean resultado = ronQ.evaluarApneaSuenyo();

        assertEquals(false, resultado);
        verify(dispositivo, times(5)).leerSensorPresion();
        verify(dispositivo, times(5)).leerSensorSonido();
    }

    @Test
    @DisplayName("Test de que evaluarApneaSuenyo reciba mas valores de que indica numLecturas")
    public void testApneaIncorrectoMasLecturas() {
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        // Simulamos 10 lecturas de presión y sonido por encima del umbral
        when(dispositivo.leerSensorPresion()).thenReturn(25.0f, 30.0f, 35.0f, 40.0f, 45.0f, 50.0f, 55.0f, 60.0f, 65.0f, 70.0f);
        when(dispositivo.leerSensorSonido()).thenReturn(30.0f, 35.0f, 40.0f, 45.0f, 50.0f, 55.0f, 60.0f, 65.0f, 70.0f, 75.0f);

        ronQ.anyadirDispositivo(dispositivo);
        ronQ.inicializar();

        for (int i = 0; i < 10; i++) {
            ronQ.obtenerNuevaLectura();
        }

        boolean resultado = ronQ.evaluarApneaSuenyo();

        assertTrue(resultado);
        verify(dispositivo, times(10)).leerSensorPresion();
        verify(dispositivo, times(10)).leerSensorSonido();
    }

    @Test
    @DisplayName("Debe devolver true si el dispositivo está conectado")
    void testEstaConectadoTrue() {
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        when(dispositivo.estaConectado()).thenReturn(true);

        ronQ.anyadirDispositivo(dispositivo);

        assertTrue(ronQ.estaConectado());
        verify(dispositivo).estaConectado();
    
    }

    @Test
    @DisplayName("Debe devolver false si el dispositivo no está conectado")
    void testEstaConectadoFalse() {
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        when(dispositivo.estaConectado()).thenReturn(false);

        ronQ.anyadirDispositivo(dispositivo);

        assertEquals(false, ronQ.estaConectado());
        verify(dispositivo).estaConectado();
    }

     /* Realiza un primer test para ver que funciona bien independientemente del número de lecturas.
     * Usa el ParameterizedTest para realizar un número de lecturas previas a calcular si hay apnea o no (por ejemplo 4, 5 y 10 lecturas).
     * https://junit.org/junit5/docs/current/user-guide/index.html#writing-tests-parameterized-tests
     */

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 10})
    @DisplayName("Apnea detectada correctamente con distintas cantidades de lecturas válidas")
    void testApneaConMediaValida(int numLecturas) {
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);

        // Siempre devuelve el mismo valor
        when(dispositivo.leerSensorPresion()).thenReturn(15.0f);
        when(dispositivo.leerSensorSonido()).thenReturn(15.0f);

        ronQ.anyadirDispositivo(dispositivo);
        ronQ.inicializar();

        for (int i = 0; i < numLecturas; i++) {
            ronQ.obtenerNuevaLectura();
        }

        boolean resultado = ronQ.evaluarApneaSuenyo();

        assertFalse(resultado);
    }


}
