package com.rehber.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.rehber.dao.TelefonDomain;
import com.rehber.db.MyGetConnection;
import com.rehber.interfaces.EkranOlusturma;

public class KayitEkrani extends JDialog implements EkranOlusturma {

	private MyGetConnection connection = new MyGetConnection();
	private TelefonDomain yenikayıt = new TelefonDomain();
	
	public KayitEkrani() {
		initPencere();
	}
	
	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		add(panel);
		setTitle("Kayıt Yap");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setResizable(true);
		setVisible(true);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(4,2));
		
		JLabel adiJLabel = new JLabel("Adı :",JLabel.RIGHT);
		JTextField adiField  =new JTextField(15);
		JLabel soyadı = new JLabel("Soyadı : ",JLabel.RIGHT);
		JTextField soyadiField = new JTextField(15);
		JLabel telefonJLabel = new JLabel("Telefon : ",JLabel.RIGHT);
		JTextField telefonField = new JTextField(15);
		
		JButton kaydeButton = new JButton("Kaydet");
		JButton iptalButton = new JButton("İptal");
		
		panel.add(adiJLabel);
		panel.add(adiField);
		panel.add(soyadı);
		panel.add(soyadiField);
		panel.add(telefonJLabel);
		panel.add(telefonField);
		
		panel.add(kaydeButton);
		panel.add(iptalButton);		
		
		kaydeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				yenikayıt.setAdi(adiField.getText());
				yenikayıt.setSoyadi(soyadiField.getText());
				yenikayıt.setTelefonNo(telefonField.getText());				
				connection.insertTablo(yenikayıt);	
				AnaPencere.kisilerJList.setListData(connection.rehberiListele().toArray());	
				JOptionPane.showMessageDialog(null, yenikayıt.getAdi()+ " " + yenikayıt.getSoyadi() + " "
													+ "Adlı Kullanıcının " + yenikayıt.getTelefonNo() + " Numarası Kaydedildi.");
			}
		});
		
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

}
