package org.Tarea3.Logica;

/**
 * Enum que define los estados cíclicos del comprador para la interfaz gráfica.
 * <p>
 * Representa las etapas del proceso de compra: selección de monedas, selección de producto,
 * recolección del producto y recolección del vuelto.
 * </p>
 *
 * @author Francisco Fuentealba
 * @author Leonardo Guerrero
 */
public enum EstadoComprador {
    SELECCIONAR_MONEDA, SELECCIONAR_PRODUCTO, RECOGER_PRODUCTO, RECOGER_VUELTO
}