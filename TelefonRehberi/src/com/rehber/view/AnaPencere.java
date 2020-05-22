package com.rehber.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.ListUI;

import com.rehber.dao.TelefonDomain;
import com.rehber.db.MyGetConnection;
import com.rehber.interfaces.EkranOlusturma;

public class AnaPencere extends JFrame implements EkranOlusturma{
	
	private MyGetConnection connection = new MyGetConnection();
	static JList kisilerJList;
	
	public AnaPencere() {
		initPencere();
	}
	
	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		JMenuBar menuBar = initBar();		
		
		add(panel);		
		setJMenuBar(menuBar);		
		
		setSize(350,500);
		setTitle("Telefon Rehberi (V1.0)");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setVisible(true);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel bulJPanel = new JPanel(new GridLayout(4,2,3,3));
		JPanel butonJPanel = new JPanel(new GridLayout(1,3));		
		
		kisilerJList = new JList();
		kisilerJList.setListData(connection.rehberiListele().toArray());		
		JScrollPane pane = new JScrollPane(kisilerJList);		
			
		JButton bulButton = new JButton("Bul");
		JButton silButton = new JButton("Sil");
		JButton düzenleButton = new JButton("Düzenle");
		
		JLabel idJLabel = new JLabel("Id : ",JLabel.RIGHT);
		JTextField idField = new JTextField(25);
		bulJPanel.add(idJLabel);
		bulJPanel.add(idField);
		
		JLabel adiJLabel = new JLabel("Adı : ",JLabel.RIGHT);
		JTextField adiField = new JTextField(15);
		bulJPanel.add(adiJLabel);
		bulJPanel.add(adiField);
		
		JLabel soyadJLabel = new JLabel("Soyadı : ",JLabel.RIGHT);
		JTextField soyadField = new JTextField(15);		
		bulJPanel.add(soyadJLabel);
		bulJPanel.add(soyadField);
		
		JLabel telefonJLabel = new JLabel("Telefon : ",JLabel.RIGHT);
		JTextField telefonField = new JTextField(15);
		bulJPanel.add(telefonJLabel);
		bulJPanel.add(telefonField);
		
		butonJPanel.add(bulButton);
		butonJPanel.add(silButton);
		butonJPanel.add(düzenleButton);
		
		bulButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TelefonDomain bulunacakkisi = connection.bul(Integer.parseInt(idField.getText()));
				
				adiField.setText(bulunacakkisi.getAdi());
				soyadField.setText(bulunacakkisi.getSoyadi());
				telefonField.setText(bulunacakkisi.getTelefonNo());
			}
		});
		
		silButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TelefonDomain telefonDomain = null;
				telefonDomain = (TelefonDomain) kisilerJList.getSelectedValue();
				connection.sil(telefonDomain);				
				connection.getAlter();
				kisilerJList.setListData(connection.rehberiListele().toArray());					
				JOptionPane.showMessageDialog(null, telefonDomain.getAdi()+ " İsimli Kullanıcı Silindi.");
			}
		});
		
		düzenleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				TelefonDomain telefonDomain = (TelefonDomain) AnaPencere.kisilerJList.getSelectedValue();
				new Duzenle(telefonDomain);
			}
		});
		
		panel.add(bulJPanel,BorderLayout.NORTH);
		panel.add(butonJPanel,BorderLayout.SOUTH);
		panel.add(pane,BorderLayout.CENTER);
		
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = new JMenuBar();
		JMenu dosyaJMenu = new JMenu("Dosya");
		bar.add(dosyaJMenu);
		JMenuItem kayıtYapItem = new JMenuItem("Kayıt Yap");
		
		kayıtYapItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new KayitEkrani();
			}
		});
		
		dosyaJMenu.add(kayıtYapItem);
		
		return bar;
	}

	
}
