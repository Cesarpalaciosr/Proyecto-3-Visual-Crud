package Conexiones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;



public class Window extends JFrame{
	
	private JFrame frame;
	private JPanel contentPane;
	private JTextField campoCodigo;
	private JTextField campostock;
	private JTextField campoNombre;
	private JTextField campoPrecio;
	private JTable table;
	private int idProducto = 0;
	Window ventup;
	private String encabezado[]={"Id","codigo","Nombre","Precio","Stock"};

	
	public void wClose(Window wClose) {
		this.ventup = ventup;
	}
	
	public Window() {
		setTitle("Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 283);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblCodigo = new JLabel("Codigo");
		
		JLabel lblNombre = new JLabel("Nombre");
		
		JLabel etStock = new JLabel("Stock");
		
		JLabel lblPrecio = new JLabel("Precio");
		
		campoCodigo = new JTextField();
		campoCodigo.setColumns(10);
		
		campostock = new JTextField();
		campostock.setColumns(10);
		
		campoNombre = new JTextField();
		campoNombre.setColumns(10);
		
		campoPrecio = new JTextField();
		campoPrecio.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btn1 = new JButton("Guardar");
		btn1.setForeground(Color.BLACK);
		btn1.setBackground(new Color(153, 204, 204));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (validarDato()) {
						BBDD cnn = new BBDD();
						
						int codigo = Integer.parseInt(campoCodigo.getText());
						String nombre = campoNombre.getText();
						int precio = Integer.parseInt(campoPrecio.getText());
						int stock = Integer.parseInt(campostock.getText());
						
	
						if (idProducto == 0)
							
							cnn.ejecutarCambio(cnn,"INSERT INTO productos(codigo_product,nombre,precio ,stock) VALUES ("+"'"+codigo+"','"+nombre+"','"+stock+"','"+precio+"')");
						else 
							cnn.ejecutarCambio(cnn,"UPDATE productos SET codigo = " + codigo + ", nombre = '" + nombre+"'"+",precio = "+precio+",Stock = "+stock+" WHERE id_producto = " + idProducto);
						
						loadDataTable();	
					}
					else
						JOptionPane.showMessageDialog(null, "Faltan datos, verfique!", "Sistema", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		JButton btndelete = new JButton("Eliminar");
		btndelete.setBackground(new Color(153, 204, 204));
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					BBDD cnn = new BBDD();
					cnn.ejecutarCambio(cnn,"DELETE FROM productos WHERE id_producto = " + Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString()));
					loadDataTable();
				}
			});
		
		JButton btexit = new JButton("Salir");
		btexit.setBackground(new Color(153, 204, 204));
		btexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventup.setVisible(false);
			}
		});
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(14, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCodigo)
							.addGap(17)
							.addComponent(campoCodigo, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(campoNombre, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(etStock, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(campostock, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
							.addComponent(lblPrecio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(campoPrecio, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(11))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(61)
					.addComponent(btn1)
					.addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
					.addComponent(btndelete)
					.addGap(123)
					.addComponent(btexit)
					.addGap(58))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(3)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCodigo)
								.addComponent(campoCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombre)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(etStock)
							.addComponent(lblPrecio)
							.addComponent(campoPrecio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(campoNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(campostock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btexit)
						.addComponent(btndelete)
						.addComponent(btn1))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},encabezado));
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
					idProducto = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					campoCodigo.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
					campoNombre.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
					campostock.setText(table.getValueAt(table.getSelectedRow(),3).toString());
					campoPrecio.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				}
			});
		loadDataTable();
	}
	
	private void loadDataTable() {
		table.setModel(new DefaultTableModel(findDataTable(), encabezado));
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		idProducto = 0;
		campoCodigo.setText("");
		campoNombre.setText("");
		campostock.setText("");
		campoPrecio.setText("");
	}
	
	private String[][] findDataTable(){
		String matrizInfo[][] = new String [0][0];
		int i = 0;
		
		try {
			BBDD cnn = new BBDD();
			ResultSet rs =  cnn.abrirConsulta(cnn,"SELECT id_producto,codigo_product,nombre,precio,stock FROM productos ORDER BY nombre");
			matrizInfo = new String[cnn.totalFilas()][table.getColumnCount()];
			if (cnn.totalFilas() > 0) {
		        do {
		        	matrizInfo[i][0]=Integer.parseInt(rs.getString("id_producto"))+"";
					matrizInfo[i][1]=Integer.parseInt(rs.getString("codigo_product"))+"";
					matrizInfo[i][2]=rs.getString("nombre")+"";
					matrizInfo[i][3]=Integer.parseInt(rs.getString("precio"))+"";
					matrizInfo[i][4]=rs.getFloat("stock")+"";
					i++;
				} while (rs.next());
			}
			cnn.cerrarConsulta();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Error al consultar", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return matrizInfo;
	}

	private boolean validarDato() {
		boolean salida = false;
		
		if (!campoCodigo.getText().equals(""))
			if (!campoNombre.getText().equals(""))
				if (!campostock.getText().equals(""))
					if (!campoPrecio.getText().equals(""))
						salida = true;
		return salida;
	}

}
