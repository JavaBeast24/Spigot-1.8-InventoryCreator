package com.JavaBeast.generator.inventory;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.JavaBeast.generator.Content;
import com.JavaBeast.generator.itemstack.ItemStackFrame;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemSelector extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ItemSelector dialog = new ItemSelector(0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ItemSelector(int slot) {
		setTitle("Set Slot "+slot);
		setResizable(false);
		setBounds(100, 100, 258, 162);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		JComboBox itemBox;
		
		
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLayeredPane layeredPane = new JLayeredPane();
			contentPanel.add(layeredPane, BorderLayout.CENTER);
			{
				JComboBox comboBox = new JComboBox();
				itemBox = comboBox;
				comboBox.setForeground(Color.LIGHT_GRAY);
				comboBox.setFont(new Font("Courier New", Font.PLAIN, 11));
				comboBox.setBackground(Color.DARK_GRAY);
				comboBox.setModel(new DefaultComboBoxModel(new String[] {"none"}));
				
				String[] content = new String[Content.getStacks().size()+1];
				content[0] = "none";
				
				for(int i = 1; i < content.length; i++) {
					content[i] = Content.getStacks().get(i-1).getName();
				}
				
				comboBox.setModel(new DefaultComboBoxModel(content));
				
				comboBox.setBounds(10, 11, 219, 22);
				layeredPane.add(comboBox);
			}
			{
				JButton btnNewButton = new JButton("create new Item");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ItemStackFrame frame = new ItemStackFrame("");
						frame.setVisible(true);
						setVisible(false);
					}
				});
				btnNewButton.setFont(new Font("Courier New", Font.PLAIN, 11));
				btnNewButton.setBackground(Color.DARK_GRAY);
				btnNewButton.setForeground(Color.LIGHT_GRAY);
				btnNewButton.setBounds(10, 44, 219, 23);
				layeredPane.add(btnNewButton);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = (String) itemBox.getSelectedItem();
						if(name.equals("none")) {
							MainFrame.self.setSlot(slot, null);
							System.out.println("slot "+slot+" : \n NONE");
						}else {
							MainFrame.self.setSlot(slot, Content.getStack(name));
						}
						
						MainFrame.self.draw();
						
						dispose();
					}
				});
				okButton.setForeground(Color.LIGHT_GRAY);
				okButton.setFont(new Font("Courier New", Font.PLAIN, 11));
				okButton.setBackground(Color.DARK_GRAY);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			}
			{
				JButton okButton = new JButton("cancel");
				okButton.setForeground(Color.LIGHT_GRAY);
				okButton.setFont(new Font("Courier New", Font.PLAIN, 11));
				okButton.setBackground(Color.DARK_GRAY);
				okButton.setActionCommand("CANCEL");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
