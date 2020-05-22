package com.rehber.test;

import javax.swing.SwingUtilities;

import com.rehber.view.AnaPencere;

public class Driver {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				new AnaPencere();
				
			}
		});
	}
}
