package com.JavaBeast.generator.inventory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.JavaBeast.generator.Content;
import com.JavaBeast.generator.JBInventory;
import com.JavaBeast.generator.JBItemStack;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private  HashMap<Integer, JBItemStack> inventory = new HashMap<>();
	
	public  HashMap<Integer, JBItemStack> getInventory(){
		return inventory;
	}
	
	public  JBItemStack getSlot(int slot) {
		return inventory.get(slot);
	}
	
	public  void setSlot(int slot, JBItemStack stack) {
		inventory.put(slot, stack);
	}

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<Slot> slot = new ArrayList<>();


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame("yeeet", "YEEET!");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static MainFrame self;
	
	public MainFrame(String invName, String title) {
		self = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 987, 606);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, BorderLayout.CENTER);
		
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		inventoryPanel.setBackground(Color.DARK_GRAY);
		inventoryPanel.setBounds(0, 0, 961, 513);
		layeredPane.add(inventoryPanel);
		inventoryPanel.setLayout(new GridLayout(6, 9));
		
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JBInventory inv = new JBInventory(invName, title, inventory);
				Content.addInventory(inv);
			}
		});
		saveBtn.setForeground(Color.LIGHT_GRAY);
		saveBtn.setFont(new Font("Courier New", Font.PLAIN, 11));
		saveBtn.setBackground(Color.DARK_GRAY);
		saveBtn.setBounds(10, 524, 86, 23);
		layeredPane.add(saveBtn);
		
		for(int i = 0; i < 54; i++) {
			if(getInventory().containsKey(i)) {
				Slot slot = new Slot(i, getSlot(i).getMaterial().toString());
				inventoryPanel.add(slot);
				this.slot.add(slot);
			}else {
				Slot slot = new Slot(i, null);
				inventoryPanel.add(slot);
				this.slot.add(slot);
			}
		}
	}

	public void draw() {
		
		for(int i = 0; i < 54; i++) {
			if(getInventory().containsKey(i)) {
				if(getInventory().get(i) != null) {
					this.slot.get(i).setText(getSlot(i).getMaterial()+" (slot "+i+")");
				}else {
					this.slot.get(i).setText("(slot "+i+")");
				}
			}
		}
		
	}
	
}

class Slot extends JLabel {
	private static final long serialVersionUID = 1L;
	
	private final int number;
	private String type;
	
	public Slot(int number, String type) {
		super(" (slot "+number+")", SwingConstants.CENTER);
		this.number = number;
		this.type = type;
		init();
	}
	
	private void init() {
		
		setBorder(new LineBorder(Color.black));
		setForeground(Color.LIGHT_GRAY);
		
		if(type != null) {
			setText(type+ "(slot "+number+")");
		}else {
			setText("(slot "+number+")");
		}
		
		setClickable();
	}
	
	private void setClickable() {
		addMouseListener(new MouseAdapter() {
		
            @Override
            public void mouseClicked(MouseEvent e) {
            	ItemSelector selector = new ItemSelector(number);
            	selector.setVisible(true);
            }

			
		});
	}
	
}
