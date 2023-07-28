package frame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entity.Product;
import service.ProductService;
import utils.CustomSwingTableUtil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductSearchFrame extends JFrame {

	private JPanel contentPane;
	private JButton productRemoveButton;
	private JButton productModifyButton;

	private JComboBox comboBox;
	private JTextField searchTextField;

	private DefaultTableModel searchProductTableModel;
	private JTable productTable;
	
	private static ProductSearchFrame instance;
	

	private ProductSearchFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		productModifyButton = new JButton("수정");
		productModifyButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!productModifyButton.isEnabled()) { return; }
				
				int productId = Integer.parseInt((String) searchProductTableModel.getValueAt(productTable.getSelectedRow(), 0));
				 
				ProductModifyFrame productModifyFrame = 
						new ProductModifyFrame(productId);
				 productModifyFrame.setVisible(true);
				
			}
		});
		productModifyButton.setBounds(767, 10, 97, 23);
		contentPane.add(productModifyButton);

		productRemoveButton = new JButton("삭제");
		productRemoveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!productRemoveButton.isEnabled()) { return; }
				int productId = Integer.parseInt((String)searchProductTableModel.getValueAt(productTable.getSelectedRow(), 0));
				if(!ProductService.getInstance().removeProduct(productId)) {
					JOptionPane.showMessageDialog(contentPane, "상품 삭제중 오류가 발생하였습니다.", "삭제오류", JOptionPane.ERROR_MESSAGE);
					return;
				}
				JOptionPane.showMessageDialog(contentPane, "선택한 상품을 삭제하였습니다.", "삭제성공", JOptionPane.PLAIN_MESSAGE);
				setSearchProductTableModel();
			}
		});

		
		productRemoveButton.setBounds(875, 10, 97, 23);
		contentPane.add(productRemoveButton);

		JLabel titleLable = new JLabel("상품 조회");
		titleLable.setBackground(new Color(255, 255, 255));
		titleLable.setBounds(12, 10, 75, 23);
		contentPane.add(titleLable);

		JLabel searchLabel = new JLabel("검색");
		searchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		searchLabel.setBounds(483, 47, 75, 23);
		contentPane.add(searchLabel);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "전체", "상품명", "색상", "카테고리" }));
		comboBox.setBounds(570, 47, 97, 23);
		contentPane.add(comboBox);

		searchTextField = new JTextField();
		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					setSearchProductTableModel();
					
				}
			}
		});
		searchTextField.setBounds(673, 47, 301, 23);
		contentPane.add(searchTextField);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 83, 960, 368);
		contentPane.add(scrollPane);

		productTable = new JTable();
		setSearchProductTableModel();
		productTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				productRemoveButton.setEnabled(true);
				productModifyButton.setEnabled(true);
			}
		});

		scrollPane.setViewportView(productTable);

	}
	
	public static ProductSearchFrame getInstance() {
		if(instance == null) {
			instance = new ProductSearchFrame();
		}
		return instance;
	}
	public void setSearchProductTableModel() {
		String searchOption = (String) comboBox.getSelectedItem();
		String searchValue = searchTextField.getText(); // 두가지 조건으로 검색을 해야한다

		List<Product> searchProductList = ProductService.getInstance().searchProduct(searchOption, searchValue);
		String[][] searchProductModelArray = CustomSwingTableUtil.searchProductListToArray(searchProductList);

		searchProductTableModel = new DefaultTableModel(
				searchProductModelArray,
				new String[] { "product_id", "product_name", "product_price", "product_color_id", "product_color_name",
						"product_category_id", "product_category_name" });
	
	
		productTable.setModel(searchProductTableModel);
		productRemoveButton.setEnabled(false);
		productModifyButton.setEnabled(false);
	}
}
