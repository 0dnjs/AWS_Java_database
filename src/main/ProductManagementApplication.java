package main;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import frame.ProductCategoryRegisterFrame;
import frame.ProductColorRegisterFrame;
import frame.ProductRegisterFrame;
import frame.ProductSearchFrame;

public class ProductManagementApplication extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductManagementApplication frame = new ProductManagementApplication();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ProductManagementApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton productColorRegisterFrameOpenButton = new JButton("상품색상등록");
		productColorRegisterFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductColorRegisterFrame productColorRegisterFrame = 
						new ProductColorRegisterFrame();
				productColorRegisterFrame.setVisible(true);
			}
		});
		productColorRegisterFrameOpenButton.setBounds(46, 129, 376, 45);
		contentPane.add(productColorRegisterFrameOpenButton);
		
		JButton productSearchFrameOpenButton = new JButton("상품조회");
		productSearchFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductSearchFrame productSearchFrame = 
						ProductSearchFrame.getInstance();
				productSearchFrame.setVisible(true);
			}
		});
		productSearchFrameOpenButton.setBounds(46, 22, 376, 45);
		contentPane.add(productSearchFrameOpenButton);
		
		JButton productRegisterFrameOpenButton = new JButton("상품등록");
		productRegisterFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductRegisterFrame productRegisterFrame = 
						new ProductRegisterFrame();
				productRegisterFrame.setVisible(true);
			}
		});
		productRegisterFrameOpenButton.setBounds(46, 74, 376, 45);
		contentPane.add(productRegisterFrameOpenButton);
		
		JButton productCategoryRegisterFrameOpenButton = new JButton("상품카테고리등록");
		productCategoryRegisterFrameOpenButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ProductCategoryRegisterFrame productCategoryRegisterFrame = 
						new ProductCategoryRegisterFrame();
				productCategoryRegisterFrame.setVisible(true);
			}
		});
		productCategoryRegisterFrameOpenButton.setBounds(46, 184, 376, 45);
		contentPane.add(productCategoryRegisterFrameOpenButton);
	}
}
