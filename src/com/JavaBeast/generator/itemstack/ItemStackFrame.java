package com.JavaBeast.generator.itemstack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.bukkit.Material;

import com.JavaBeast.generator.Content;
import com.JavaBeast.generator.JBItemStack;

public class ItemStackFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField displayName;
	private JTextField permission;

	public static void main(String[] args) {
		ItemStackFrame frame = new ItemStackFrame("test");
		frame.setVisible(true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItemStackFrame(String ItemName) {
		setResizable(false);
		setTitle("Create ItemStack "+ItemName+"");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 360, 333);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("name:");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 130, 14);
		layeredPane.add(lblNewLabel);
		
		name = new JTextField(ItemName);
		name.setForeground(Color.LIGHT_GRAY);
		name.setFont(new Font("Courier New", Font.PLAIN, 11));
		name.setBackground(Color.DARK_GRAY);
		name.setBounds(151, 8, 175, 20);
		name.setBorder(new LineBorder(Color.black));
		layeredPane.add(name);
		name.setColumns(10);
		
		JLabel lblDisplayName = new JLabel("display name:");
		lblDisplayName.setForeground(Color.LIGHT_GRAY);
		lblDisplayName.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblDisplayName.setBounds(10, 36, 130, 14);
		layeredPane.add(lblDisplayName);
		
		JLabel lblUsepermission = new JLabel("use permission:");
		lblUsepermission.setForeground(Color.LIGHT_GRAY);
		lblUsepermission.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblUsepermission.setBounds(10, 61, 130, 14);
		layeredPane.add(lblUsepermission);
		
		JLabel lblCanBeMoved = new JLabel("can be moved:");
		lblCanBeMoved.setForeground(Color.LIGHT_GRAY);
		lblCanBeMoved.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblCanBeMoved.setBounds(10, 86, 130, 14);
		layeredPane.add(lblCanBeMoved);
		
		displayName = new JTextField(ItemName);
		displayName.setForeground(Color.LIGHT_GRAY);
		displayName.setFont(new Font("Courier New", Font.PLAIN, 11));
		displayName.setColumns(10);
		displayName.setBackground(Color.DARK_GRAY);
		displayName.setBounds(151, 33, 175, 20);
		displayName.setBorder(new LineBorder(Color.black));
		layeredPane.add(displayName);
		
		permission = new JTextField("javabeast.use."+ItemName);
		permission.setForeground(Color.LIGHT_GRAY);
		permission.setFont(new Font("Courier New", Font.PLAIN, 11));
		permission.setColumns(10);
		permission.setBackground(Color.DARK_GRAY);
		permission.setBounds(151, 58, 175, 20);
		permission.setBorder(new LineBorder(Color.black));
		layeredPane.add(permission);
		
		JCheckBox moveable = new JCheckBox("false");
		moveable.setBackground(Color.DARK_GRAY);
		moveable.setFont(new Font("Courier New", Font.PLAIN, 11));
		moveable.setForeground(Color.LIGHT_GRAY);
		moveable.setBounds(151, 81, 97, 23);
		moveable.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				JCheckBox checkBox = (JCheckBox) e.getSource();
				if(checkBox.isSelected()) {
					checkBox.setText("true");
				}else {
					checkBox.setText("false");
				}
				
			}
		});
		layeredPane.add(moveable);
		
		JLabel lblAction = new JLabel("action Type:");
		lblAction.setForeground(Color.LIGHT_GRAY);
		lblAction.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblAction.setBounds(10, 210, 130, 14);
		layeredPane.add(lblAction);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setForeground(Color.LIGHT_GRAY);
		comboBox_1.setFont(new Font("Courier New", Font.PLAIN, 11));
		comboBox_1.setBackground(Color.DARK_GRAY);
		comboBox_1.setBounds(151, 231, 175, 22);
		layeredPane.add(comboBox_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"OPEN_INVENTORY", "CLOSE_INVENTORY", "TRIGGER_EVENT"}));
		comboBox.setForeground(Color.LIGHT_GRAY);
		comboBox.setFont(new Font("Courier New", Font.PLAIN, 11));
		comboBox.setBackground(Color.DARK_GRAY);
		comboBox.setBounds(151, 205, 175, 22);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			
				JComboBox box = (JComboBox) e.getSource();
				String selected = (String) box.getSelectedItem();
				
				if(selected.equals("OPEN_INVENTORY")) {
					String[] invs = new String[Content.getInventorys().size()];
					
					for(int i = 0; i < Content.getInventorys().size(); i++) {
						invs[i] = Content.getInventorys().get(i).getName();
					}
					
					comboBox_1.setModel(new DefaultComboBoxModel(invs));
					
				}else if(selected.equals("CLOSE_INVENTORY")) {
					comboBox_1.setModel(new DefaultComboBoxModel(new String[0]));
				}else {
					String[] invs = new String[Content.getEvents().size()];
					
					for(int i = 0; i < Content.getEvents().size(); i++) {
						invs[i] = Content.getEvents().get(i);
					}
					
					comboBox_1.setModel(new DefaultComboBoxModel(invs));
				}
			
			}
		});
		layeredPane.add(comboBox);
		
		JLabel lblActionObject = new JLabel("action Object:");
		lblActionObject.setForeground(Color.LIGHT_GRAY);
		lblActionObject.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblActionObject.setBounds(10, 235, 130, 14);
		layeredPane.add(lblActionObject);
		

		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setForeground(Color.LIGHT_GRAY);
		lblAmount.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblAmount.setBounds(10, 111, 130, 14);
		layeredPane.add(lblAmount);
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("Courier New", Font.PLAIN, 11));
		spinner.setForeground(Color.LIGHT_GRAY);
		spinner.setBackground(Color.DARK_GRAY);
		spinner.setBounds(151, 107, 58, 20);
		layeredPane.add(spinner);
		
		

		
		JLabel lblType = new JLabel("Type:");
		lblType.setForeground(Color.LIGHT_GRAY);
		lblType.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblType.setBounds(10, 136, 130, 14);
		layeredPane.add(lblType);
		
		JComboBox material = new JComboBox();
		material.setForeground(Color.LIGHT_GRAY);
		material.setFont(new Font("Courier New", Font.PLAIN, 11));
		material.setBackground(Color.DARK_GRAY);
		material.setBounds(151, 131, 175, 22);
		material.setModel(new DefaultComboBoxModel(Material.values()));
		layeredPane.add(material);
		
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new JBItemStack(name.getText(), displayName.getText(), permission.getText(), moveable.isSelected(), (int) spinner.getValue(), (String) comboBox.getSelectedItem(), (String) comboBox_1.getSelectedItem(), (Material) material.getSelectedItem());
				
				dispose();
			}
		});
		btnNewButton.setForeground(Color.LIGHT_GRAY);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnNewButton.setBounds(10, 260, 316, 23);
		btnNewButton.setBorder(new LineBorder(Color.black));
		layeredPane.add(btnNewButton);
	}
}
