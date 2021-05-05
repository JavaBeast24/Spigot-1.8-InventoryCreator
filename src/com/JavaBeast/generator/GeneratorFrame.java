package com.JavaBeast.generator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.JavaBeast.generator.inventory.MainFrame;

import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GeneratorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField title;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeneratorFrame frame = new GeneratorFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GeneratorFrame() {
		setTitle("JavaBeasts Inventory generator - v 0.1");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 379, 246);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JButton btnNewButton = new JButton("new Inventory");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MainFrame frame = new MainFrame(name.getText(), title.getText());
				frame.setVisible(true);
				
			}
		});
		btnNewButton.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnNewButton.setForeground(Color.LIGHT_GRAY);
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setBounds(10, 70, 128, 23);
		layeredPane.add(btnNewButton);
		
		name = new JTextField();
		name.setFont(new Font("Courier New", Font.PLAIN, 11));
		name.setForeground(Color.LIGHT_GRAY);
		name.setBackground(Color.DARK_GRAY);
		name.setBounds(148, 11, 205, 20);
		layeredPane.add(name);
		name.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("generate");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JavaGenerator.generateFile();
				
			}
		});
		btnNewButton_1.setForeground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setBounds(10, 173, 343, 23);
		layeredPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("name:");
		lblNewLabel.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setBounds(10, 14, 128, 14);
		layeredPane.add(lblNewLabel);
		
		JLabel lblTitle = new JLabel("title:");
		lblTitle.setForeground(Color.LIGHT_GRAY);
		lblTitle.setFont(new Font("Courier New", Font.PLAIN, 11));
		lblTitle.setBackground(Color.DARK_GRAY);
		lblTitle.setBounds(10, 45, 128, 14);
		layeredPane.add(lblTitle);
		
		title = new JTextField();
		title.setForeground(Color.LIGHT_GRAY);
		title.setFont(new Font("Courier New", Font.PLAIN, 11));
		title.setColumns(10);
		title.setBackground(Color.DARK_GRAY);
		title.setBounds(148, 42, 205, 20);
		layeredPane.add(title);
		
		textField = new JTextField();
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setFont(new Font("Courier New", Font.PLAIN, 11));
		textField.setColumns(10);
		textField.setBackground(Color.DARK_GRAY);
		textField.setBounds(10, 104, 205, 20);
		layeredPane.add(textField);
		
		JButton btnNewEvent = new JButton("new Event");
		btnNewEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Content.addEvent(textField.getText());
				
			}
		});
		btnNewEvent.setForeground(Color.LIGHT_GRAY);
		btnNewEvent.setFont(new Font("Courier New", Font.PLAIN, 11));
		btnNewEvent.setBackground(Color.DARK_GRAY);
		btnNewEvent.setBounds(225, 102, 128, 23);
		layeredPane.add(btnNewEvent);
	}
}
