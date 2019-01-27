/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculadora_Cientifica;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.lang.Math;

public class Calculadora extends JFrame {

	
	private static final long serialVersionUID = 1583724102189855698L;

	/** numero tecleado */
	JTextField pantalla;

	/** Resultado de la operación */
	double resultado;
        double resultado2;
        
	/** Guarda la operacion a realizar */
	String operacion;

	/** Paneles con Botones */
	JPanel panelNumeros, panelOperaciones;

	/** Indica si estamos iniciando o no una operación */
	boolean nuevaOperacion = true;

	public Calculadora() {
		super();
		setSize(500, 500);
		setTitle("Calculadora Cientifica Frida");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());
		pantalla = new JTextField("0", 20);
		pantalla.setBorder(new EmptyBorder(2, 2, 2, 2));
		pantalla.setFont(new Font("Century Gothic", Font.BOLD, 35));
		pantalla.setHorizontalAlignment(JTextField.CENTER);
		pantalla.setEditable(false);
		pantalla.setBackground(Color.white);
		panel.add("North", pantalla);
		panelNumeros = new JPanel();
		panelNumeros.setLayout(new GridLayout(4, 3));
		panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));
                panelNumeros.setBackground(Color.BLACK);

		for (int n = 9; n >= 0; n--) {
			nuevoBotonNumerico("" + n);
                        
		}

		

		panel.add("Center", panelNumeros);

		panelOperaciones = new JPanel();
		panelOperaciones.setLayout(new GridLayout(6, 1));
		panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
                panelOperaciones.setBackground(Color.BLACK);

		nuevoBotonOperacion("+");
		nuevoBotonOperacion("-");
		nuevoBotonOperacion("*");
		nuevoBotonOperacion("/");
                nuevoBotonOperacion("^");
                nuevoBotonOperacion("√");
                nuevoBotonOperacion("SEN");
                nuevoBotonOperacion("COS");
                nuevoBotonOperacion("TAN");
		nuevoBotonOperacion("DELETE");
                nuevoBotonOperacion("=");

		panel.add("East", panelOperaciones);

		validate();
	}

	
	private void nuevoBotonNumerico(String digito) {
		JButton btn = new JButton();
		btn.setText(digito);
                btn.setBackground(Color.PINK);
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				numeroPulsado(btn.getText());
			}
		});

		panelNumeros.add(btn);
	}

	private void nuevoBotonOperacion(String operacion) {
		JButton btn = new JButton(operacion);
		btn.setForeground(Color.BLACK);
                btn.setBackground(Color.lightGray);
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				operacionPulsado(btn.getText());
			}
		});

		panelOperaciones.add(btn);
	}

	private void numeroPulsado(String digito) {
		if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(digito);
		} else {
			pantalla.setText(pantalla.getText() + digito);
		}
		nuevaOperacion = false;
	}

	
	private void operacionPulsado(String tecla) {
		if (tecla.equals("=")) {
			calcularResultado();
		} else if (tecla.equals("DELETE")) {
			resultado = 0;
			pantalla.setText("");
			nuevaOperacion = true;
		} else {
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pantalla.getText());
			}
		}

		nuevaOperacion = true;
	}

	/**
	 * Calcula el resultado y lo muestra por pantalla
	 */
	private void calcularResultado() {
		if (operacion.equals("+")) {
			resultado += new Double(pantalla.getText());
		} else if (operacion.equals("-")) {
			resultado -= new Double(pantalla.getText());
		} else if (operacion.equals("/")) {
			resultado /= new Double(pantalla.getText());
		} else if (operacion.equals("*")) {
			resultado *= new Double(pantalla.getText());
		} else if (operacion.equals("^")) {
			resultado = Math.exp(new Double(pantalla.getText()));
		} else if (operacion.equals("√")) {
			resultado = Math.sqrt(new Double(pantalla.getText()));
		} else if (operacion.equals("seno")) {
			resultado = Math.sin(new Double(pantalla.getText()));
		} else if (operacion.equals("coseno")) {
			resultado = Math.cos(new Double(pantalla.getText()));
		} else if (operacion.equals("tangente")) {
			resultado = Math.tan(new Double(pantalla.getText()));
		}

		pantalla.setText("" + resultado);
		operacion = "";
	}
}