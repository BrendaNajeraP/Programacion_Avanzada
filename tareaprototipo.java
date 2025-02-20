package a2203245140_tareas_unidad01;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	public class tareaprototipo {
	    private JFrame frame;
	    private DefaultListModel<String> productList;
	    private DefaultListModel<String> cartList;

	    public tareaprototipo() {
	        frame = new JFrame("Supermercado");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(400, 300);
	        frame.setLayout(new BorderLayout());

	    
	        productList = new DefaultListModel<>();
	        JList<String> productListView = new JList<>(productList);
	        productList.addElement("Manzana");
	        productList.addElement("Plátano");
	        productList.addElement("Leche");
	        JScrollPane productScrollPane = new JScrollPane(productListView);
	        frame.add(productScrollPane, BorderLayout.WEST);

	    
	        cartList = new DefaultListModel<>();
	        JList<String> cartListView = new JList<>(cartList);
	        JScrollPane cartScrollPane = new JScrollPane(cartListView);
	        frame.add(cartScrollPane, BorderLayout.EAST);

	       
	        
	        JButton addButton = new JButton("Agregar al carrito");
	        addButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String selectedProduct = productListView.getSelectedValue();
	                if (selectedProduct != null) {
	                    cartList.addElement(selectedProduct);
	                }
	            }
	        });
	        frame.add(addButton, BorderLayout.SOUTH);

	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        new tareaprototipo();
	    }
}
