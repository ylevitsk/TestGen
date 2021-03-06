/**
 * @author Grant Picket
 * @version 6/1/14
 */

package testtool.views.testdb;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import testtool.models.questiondb.Question;
import testtool.models.testdb.AutomaticGeneration;
import testtool.models.testdb.TestDatabase;

public class GeneratingGUI {
	static JButton generateButton;

	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		String petName = (String) cb.getSelectedItem();
	}

	AutomaticGeneration ag = null;
	TestDatabase tdb;
	JLabel classlabel = new JLabel("Class:");
	JLabel authorlabel = new JLabel("Author:");
	JLabel lastUsedlabel = new JLabel("Last Used Before:");
	JLabel difficultylabel = new JLabel("Difficulty:");
	JLabel typelabel = new JLabel("Question Types:");
	JLabel amntlabel = new JLabel("Question Amount:");
	JLabel timeSlabel = new JLabel("Start Time:");
	JLabel timeTlabel = new JLabel("Total Time:");
	JLabel titlelabel = new JLabel("Test Title:");
	JLabel keylabel = new JLabel("Keywords:");
	JLabel passlabel = new JLabel("Password:");
	JLabel additionallabel = new JLabel("Extra Information:");
	JLabel pointslabel = new JLabel("Points:");
	JLabel catLabel = new JLabel("Category:");
	JLabel catNumLabel = new JLabel("Category Number:");
	JPanel fields = new JPanel(new BorderLayout());
	JTextField pointsField = new JTextField(20);
	JTextField passField = new JTextField(20);
	JTextField diffField = new JTextField(20);
	JTextField keyField = new JTextField(20);
	JTextField typeField = new JTextField(20);
	JTextField amntField = new JTextField(20);
	JTextField startField = new JTextField(20);
	JTextField totalField = new JTextField(20);
	JTextField lastUsedField = new JTextField(20);
	JTextField additField = new JTextField(20);
	JTextField titleField = new JTextField(20);
	JTextField testCategory = new JTextField(20);
	JTextField testCategoryNum = new JTextField(20);
	JComboBox classList = null;
	JFrame guiFrame;
	public GeneratingGUI(TestDatabase td, ArrayList<Question> qs) {
		questions = qs;
		ag = new AutomaticGeneration(td);
		tdb = td;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}

				 guiFrame = new JFrame();

				JPanel guiPanel = new JPanel(new GridBagLayout());
				JPanel guiPanel2 = new JPanel(new GridBagLayout());
				String[] classStrings = { "CSC101", "CSC102", "CSC103",
						"CPE308", "CPE309" };
				String[] authorStrings = { "G. Fisher" };

				classList = new JComboBox(classStrings);
				classList.setSelectedIndex(3);
				JComboBox authorList = new JComboBox(authorStrings);
				authorList.setSelectedIndex(0);

				guiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				guiFrame.setTitle("Generating A Test");
				guiFrame.setSize(400, 400);
				guiFrame.setLocationRelativeTo(null);
				GridBagConstraints gbc = new GridBagConstraints();

				gbc.gridwidth = GridBagConstraints.REMAINDER;
				guiPanel.add(classlabel);
				guiPanel.add(classList, gbc);
				guiPanel.add(authorlabel);
				guiPanel.add(authorList, gbc);
				guiPanel.add(titlelabel);
				guiPanel.add(titleField, gbc);
				guiPanel.add(timeTlabel);
				guiPanel.add(totalField, gbc);
				guiPanel.add(typelabel);
				guiPanel.add(typeField, gbc);
				guiPanel.add(amntlabel);
				guiPanel.add(amntField, gbc);
				guiPanel.add(difficultylabel);
				guiPanel.add(diffField, gbc);
				guiPanel.add(passlabel);
				guiPanel.add(passField, gbc);
				guiPanel.add(pointslabel);
				guiPanel.add(pointsField, gbc);
				guiPanel.add(lastUsedlabel);
				guiPanel.add(lastUsedField, gbc);
				guiPanel.add(keylabel);
				guiPanel.add(keyField, gbc);
				guiPanel.add(additionallabel);
				guiPanel.add(additField, gbc);
				guiPanel.add(catLabel);
				guiPanel.add(testCategory, gbc);
				guiPanel.add(catNumLabel);
				guiPanel.add(testCategoryNum, gbc);
				generateButton = new JButton("Generate");
				generateButton.addActionListener(new genListener());

				guiPanel2.add(generateButton);
				guiPanel.add(fields);

				guiFrame.add(guiPanel, BorderLayout.NORTH);
				guiFrame.add(guiPanel2);
				guiFrame.setJMenuBar(Menu());
				guiFrame.setVisible(true);
			}
		});
	}

	String[] fileItems = new String[] { "New", "Open", "Save", "Exit" };
	String[] editItems = new String[] { "Undo", "Cut", "Copy", "Paste" };
	char[] fileShortcuts = { 'N', 'O', 'S', 'X' };
	char[] editShortcuts = { 'Z', 'X', 'C', 'V' };
	public ArrayList<Question> questions = new ArrayList<Question>();

	public JMenuBar Menu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu viewMenu = new JMenu("View");

		// Assemble the File menus with mnemonics.
		ActionListener printListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.out.println("Menu item [" + event.getActionCommand()
						+ "] was pressed.");
			}
		};
		for (int i = 0; i < fileItems.length; i++) {
			JMenuItem item = new JMenuItem(fileItems[i], fileShortcuts[i]);
			item.addActionListener(printListener);
			fileMenu.add(item);
		}

		// Assemble the File menus with keyboard accelerators.
		for (int i = 0; i < editItems.length; i++) {
			JMenuItem item = new JMenuItem(editItems[i]);
			item.setAccelerator(KeyStroke
					.getKeyStroke(editShortcuts[i], Toolkit.getDefaultToolkit()
							.getMenuShortcutKeyMask(), false));
			item.addActionListener(printListener);
			editMenu.add(item);
		}

		// Insert a separator in the Edit menu in Position 1 after "Undo".
		editMenu.insertSeparator(1);

		// Assemble the submenus of the Other menu.
		JMenuItem item;

		// Finally, add all the menus to the menu bar.
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		return menuBar;
	}

	class genListener implements ActionListener {

		public genListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			HashMap<String, String> params = new HashMap<String, String>();
			if (pointsField.getText() != null
					&& !pointsField.getText().equals(""))
				params.put("totalPoints", pointsField.getText());
			if (titleField.getText() != null
					&& !titleField.getText().equals(""))
				params.put("testTitle", titleField.getText());
			params.put("author", "gfisher");
			if (lastUsedField.getText() != null
					&& !lastUsedField.getText().equals(""))
				params.put("lastUsed", lastUsedField.getText());
			if (totalField.getText() != null
					&& !totalField.getText().equals(""))
				params.put("totalTime", totalField.getText());
			if (diffField.getText() != null && !diffField.getText().equals(""))
				params.put("avgDifficulty", diffField.getText());
			params.put("course", classList.getSelectedItem().toString());
			// params.put("gradeType", );
			if (typeField.getText() != null && !typeField.getText().equals(""))
				params.put("gradeType", typeField.getText());
			if (passField.getText() != null && !passField.getText().equals(""))
				params.put("password", passField.getText());
			if (additField.getText() != null && !additField.getText().equals(""))
				params.put("notes", additField.getText());
			if (totalField.getText() != null
					&& !totalField.getText().equals(""))
				params.put("endTime", totalField.getText());
			if (typeField.getText() != null && !typeField.getText().equals(""))
				params.put("testType", typeField.getText());
			if (testCategory.getText() != null
					&& !testCategory.getText().equals(""))
				params.put("testCategory", testCategoryNum.getText());
			if (testCategoryNum.getText() != null
					&& !testCategoryNum.getText().equals(""))
				params.put("testCategoryNumber", testCategoryNum.getText());
			ag.generate(params, questions);
			new TestCreationResultGUI(tdb);
			guiFrame.dispose();
		}
	}
}
