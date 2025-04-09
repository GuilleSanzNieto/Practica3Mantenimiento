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

    // Grupo 1: Tests de Inicialización

    @Test
    @DisplayName("Test de que inicializar no funciona correctamente nada")
    public void testInicializarMal(){
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.conectarSensorPresion()).thenReturn(false);
        when(dispositivo.conectarSensorSonido()).thenReturn(false);
        when(dispositivo.configurarSensorPresion()).thenReturn(false);
        when(dispositivo.configurarSensorSonido()).thenReturn(false);
        ronQ.anyadirDispositivo(dispositivo);
        
        // Act
        boolean resultado = ronQ.inicializar();
        
        // Assert
        assertEquals(false, resultado);
    }

    @Test
    @DisplayName("Test de que inicializar no funciona correctamente uno de los dos")
    public void testInicializarUnoMal(){
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.conectarSensorPresion()).thenReturn(true);
        when(dispositivo.conectarSensorSonido()).thenReturn(false);
        when(dispositivo.configurarSensorPresion()).thenReturn(true);
        when(dispositivo.configurarSensorSonido()).thenReturn(true);
        ronQ.anyadirDispositivo(dispositivo);
        
        // Act
        boolean resultado = ronQ.inicializar();
        
        // Assert
        assertEquals(false, resultado);
    }

    @Test
    @DisplayName("Que configurar sonido sea false")
    public void testConfigurarSonidoFalse(){
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.conectarSensorPresion()).thenReturn(true);
        when(dispositivo.conectarSensorSonido()).thenReturn(true);
        when(dispositivo.configurarSensorPresion()).thenReturn(true);
        when(dispositivo.configurarSensorSonido()).thenReturn(false);
        ronQ.anyadirDispositivo(dispositivo);
        
        // Act
        boolean resultado = ronQ.inicializar();
        
        // Assert
        assertEquals(false, resultado);
    }

    @Test
    @DisplayName("Que configurar presion sea false")
    public void testConfigurarPresionFalse(){
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.conectarSensorPresion()).thenReturn(true);
        when(dispositivo.conectarSensorSonido()).thenReturn(true);
        when(dispositivo.configurarSensorPresion()).thenReturn(false);
        when(dispositivo.configurarSensorSonido()).thenReturn(true);
        ronQ.anyadirDispositivo(dispositivo);
        
        // Act
        boolean resultado = ronQ.inicializar();
        
        // Assert
        assertEquals(false, resultado);
    }

    @Test
    @DisplayName("Test de que inicializar funciona correctamente")
    public void testInicializar(){
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.conectarSensorPresion()).thenReturn(true);
        when(dispositivo.conectarSensorSonido()).thenReturn(true);
        when(dispositivo.configurarSensorPresion()).thenReturn(true);
        when(dispositivo.configurarSensorSonido()).thenReturn(true);
        ronQ.anyadirDispositivo(dispositivo);
        
        // Act
        boolean resultado = ronQ.inicializar();
        
        // Assert
        assertEquals(true, resultado);
        verify(dispositivo).configurarSensorPresion();
        verify(dispositivo).configurarSensorSonido();
    }


    // Grupo 2: Tests de Reconexión

    @Test 
    @DisplayName("Test de que reconectar funciona correctamente")
    public void testReconectar(){
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.estaConectado()).thenReturn(false);
        when(dispositivo.conectarSensorPresion()).thenReturn(true);
        when(dispositivo.conectarSensorSonido()).thenReturn(true);
        ronQ.anyadirDispositivo(dispositivo);
        
        // Act
        boolean resultado = ronQ.reconectar();
        
        // Assert
        assertEquals(true, resultado);
        verify(dispositivo).conectarSensorPresion();
        verify(dispositivo).conectarSensorSonido();
    }

    @Test 
    @DisplayName("Test de que reconectar no funciona correctamente")
    public void testReconectarMal(){
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.estaConectado()).thenReturn(true);
        when(dispositivo.conectarSensorPresion()).thenReturn(false);
        when(dispositivo.conectarSensorSonido()).thenReturn(false);
        ronQ.anyadirDispositivo(dispositivo);
        
        // Act
        boolean resultado = ronQ.reconectar();
        
        // Assert
        assertEquals(false, resultado);
        verify(dispositivo, times(0)).conectarSensorPresion();
        verify(dispositivo, times(0)).conectarSensorSonido();
    }

    @Test 
    @DisplayName("Test de reconectar que no esten conectado pero que conectarSensorPresion sea true y conectarSensorSonido false")
    public void testReconectarMalUno(){
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.estaConectado()).thenReturn(false);
        when(dispositivo.conectarSensorPresion()).thenReturn(true);
        when(dispositivo.conectarSensorSonido()).thenReturn(false);
        ronQ.anyadirDispositivo(dispositivo);
        
        // Act
        boolean resultado = ronQ.reconectar();
        
        // Assert
        assertEquals(false, resultado);
        verify(dispositivo).conectarSensorPresion();
        verify(dispositivo).conectarSensorSonido();
    }

    @Test
    @DisplayName("Test de reconectar que no esten conectado pero que conectarSensorPresion sea false y conectarSensorSonido true")
    public void testReconectarMalDos(){
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.estaConectado()).thenReturn(false);
        when(dispositivo.conectarSensorPresion()).thenReturn(false);
        when(dispositivo.conectarSensorSonido()).thenReturn(true);
        ronQ.anyadirDispositivo(dispositivo);
        
        // Act
        boolean resultado = ronQ.reconectar();
        
        // Assert
        assertEquals(false, resultado);
        verify(dispositivo).conectarSensorPresion();
        verify(dispositivo, times(0)).conectarSensorSonido();
    }


    // Grupo 3: Tests de Evaluación de Apnea

    @Test
    @DisplayName("Test de que evaluarApneaSuenyo funciona correctamente")
    public void testApneaCorrecto() {
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.leerSensorPresion()).thenReturn(25.0f, 30.0f, 35.0f, 40.0f, 45.0f);
        when(dispositivo.leerSensorSonido()).thenReturn(30.0f, 35.0f, 40.0f, 45.0f, 50.0f);
        ronQ.anyadirDispositivo(dispositivo);
        ronQ.inicializar();
        for (int i = 0; i < 5; i++) {
            ronQ.obtenerNuevaLectura();
        }
        
        // Act
        boolean resultado = ronQ.evaluarApneaSuenyo();
        
        // Assert
        assertTrue(resultado);
        verify(dispositivo, times(5)).leerSensorPresion();
        verify(dispositivo, times(5)).leerSensorSonido();
    }

    @Test
    @DisplayName("Test de que evaluarApneaSuenyo en el que el valor es menor que el umbral")
    public void testApneaIncorrecto() {
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.leerSensorPresion()).thenReturn(15.0f, 10.0f, 5.0f, 0.0f, -5.0f);
        when(dispositivo.leerSensorSonido()).thenReturn(20.0f, 15.0f, 10.0f, 5.0f, 0.0f);
        ronQ.anyadirDispositivo(dispositivo);
        ronQ.inicializar();
        for (int i = 0; i < 5; i++) {
            ronQ.obtenerNuevaLectura();
        }
        
        // Act
        boolean resultado = ronQ.evaluarApneaSuenyo();
        
        // Assert
        assertEquals(false, resultado);
        verify(dispositivo, times(5)).leerSensorPresion();
        verify(dispositivo, times(5)).leerSensorSonido();
    }

    @Test
    @DisplayName("Test de que evaluarApneaSuenyo el valor de sonido es menor que el umbral y el valor de presion es mayor")
    public void testApneaIncorrectoSonido() {
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.leerSensorPresion()).thenReturn(25.0f, 30.0f, 35.0f, 40.0f, 45.0f);
        when(dispositivo.leerSensorSonido()).thenReturn(20.0f, 15.0f, 10.0f, 5.0f, 0.0f);
        ronQ.anyadirDispositivo(dispositivo);
        ronQ.inicializar();
        for (int i = 0; i < 5; i++) {
            ronQ.obtenerNuevaLectura();
        }
        
        // Act
        boolean resultado = ronQ.evaluarApneaSuenyo();
        
        // Assert
        assertEquals(false, resultado);
        verify(dispositivo, times(5)).leerSensorPresion();
        verify(dispositivo, times(5)).leerSensorSonido();
    }

    @Test
    @DisplayName("Test de que evaluarApneaSuenyo el valor de sonido es mayor que el umbral y el valor de presion es menor")
    public void testApneaIncorrectoPresion() {
        // Arrange
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
        
        // Act
        boolean resultado = ronQ.evaluarApneaSuenyo();
        
        // Assert
        assertEquals(false, resultado);
        verify(dispositivo, times(5)).leerSensorPresion();
        verify(dispositivo, times(5)).leerSensorSonido();
    }

    @Test
    @DisplayName("Test de que evaluarApneaSuenyo reciba mas valores de que indica numLecturas")
    public void testApneaIncorrectoMasLecturas() {
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        // Simulamos 10 lecturas de presión y sonido por encima del umbral
        when(dispositivo.leerSensorPresion()).thenReturn(25.0f, 30.0f, 35.0f, 40.0f, 45.0f,
                                                        50.0f, 55.0f, 60.0f, 65.0f, 70.0f);
        when(dispositivo.leerSensorSonido()).thenReturn(30.0f, 35.0f, 40.0f, 45.0f, 50.0f,
                                                       55.0f, 60.0f, 65.0f, 70.0f, 75.0f);
        ronQ.anyadirDispositivo(dispositivo);
        ronQ.inicializar();
        for (int i = 0; i < 10; i++) {
            ronQ.obtenerNuevaLectura();
        }
        
        // Act
        boolean resultado = ronQ.evaluarApneaSuenyo();
        
        // Assert
        assertTrue(resultado);
        verify(dispositivo, times(10)).leerSensorPresion();
        verify(dispositivo, times(10)).leerSensorSonido();
    }


    // Grupo 4: Tests de Conectividad

    @Test
    @DisplayName("Debe devolver true si el dispositivo está conectado")
    void testEstaConectadoTrue() {
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.estaConectado()).thenReturn(true);
        ronQ.anyadirDispositivo(dispositivo);
        
        // Act
        boolean conectado = ronQ.estaConectado();
        
        // Assert
        assertTrue(conectado);
        verify(dispositivo).estaConectado();
    }

    @Test
    @DisplayName("Debe devolver false si el dispositivo no está conectado")
    void testEstaConectadoFalse() {
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.estaConectado()).thenReturn(false);
        ronQ.anyadirDispositivo(dispositivo);
        
        // Act
        boolean conectado = ronQ.estaConectado();
        
        // Assert
        assertEquals(false, conectado);
        verify(dispositivo).estaConectado();
    }


    // Grupo 5: Test Parameterizado

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 10})
    @DisplayName("Apnea detectada correctamente con distintas cantidades de lecturas válidas")
    void testApneaConMediaValida(int numLecturas) {
        // Arrange
        RonQI2Silver ronQ = new RonQI2Silver();
        Dispositivo dispositivo = mock(Dispositivo.class);
        when(dispositivo.leerSensorPresion()).thenReturn(15.0f);
        when(dispositivo.leerSensorSonido()).thenReturn(15.0f);
        ronQ.anyadirDispositivo(dispositivo);
        ronQ.inicializar();
        for (int i = 0; i < numLecturas; i++) {
            ronQ.obtenerNuevaLectura();
        }
        
        // Act
        boolean resultado = ronQ.evaluarApneaSuenyo();
        
        // Assert
        assertFalse(resultado);
    }
}
