package com.rehber.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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

public class Duzenle extends JDialog implements EkranOlusturma{

	private MyGetConnection connection = new MyGetConnection();
	private TelefonDomain yenitelefonDomain = null;
	private TelefonDomain eskitelefonDomain = null;
	
	public Duzenle(TelefonDomain eskitelefonDomain) {
		this.eskitelefonDomain = eskitelefonDomain;
		initPencere();
	}
	
	@Override
	public void initPencere() {		
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Yeni Bilgileri Giriniz"));
		add(panel);
		setTitle("Düzenle");
		setSize(300,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5,2));
		
		JLabel idJLabel = new JLabel("Id : ",JLabel.RIGHT);
		JTextField idField = new JTextField(String.valueOf(eskitelefonDomain.getId()));
		panel.add(idJLabel);
		panel.add(idField);

		JLabel adiJLabel = new JLabel("Adı : ",JLabel.RIGHT);
		JTextField adiField = new JTextField(eskitelefonDomain.getAdi());
		panel.add(adiJLabel);
		panel.add(adiField);
		
		JLabel soyadiJLabel = new JLabel("Soyadı : ",JLabel.RIGHT);
		JTextField soyadiField = new JTextField(eskitelefonDomain.getSoyadi());
		panel.add(soyadiJLabel);
		panel.add(soyadiField);
		
		JLabel telefonJLabel = new JLabel("Telefon : ",JLabel.RIGHT);
		JTextField telefonField = new JTextField(eskitelefonDomain.getTelefonNo());
		panel.add(telefonJLabel);
		panel.add(telefonField);		
		
		JButton duzenleButton = new JButton("Düzenle");
		JButton iptaButton = new JButton("İptal");
		panel.add(duzenleButton);
		panel.add(iptaButton);
		
		duzenleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				yenitelefonDomain = new TelefonDomain();
				yenitelefonDomain.setId(Integer.parseInt(idField.getText()));
				yenitelefonDomain.setAdi(adiField.getText());
				yenitelefonDomain.setSoyadi(soyadiField.getText());
				yenitelefonDomain.setTelefonNo(telefonField.getText());				
				connection.duzenle(yenitelefonDomain,eskitelefonDomain);
				AnaPencere.kisilerJList.setListData(connection.rehberiListele().toArray());	
				JOptionPane.showMessageDialog(null, eskitelefonDomain.getAdi() + " " + eskitelefonDomain.getSoyadi() + " Adlı Kullanıcı Başarıyla Güncellenmiştir");
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
