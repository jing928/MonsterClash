package grp.oozmakappa.monsterclash.view;

import java.awt.GridLayout;

import javax.swing.*;

public class MenuDialog extends JPanel {
	private JPanel thisPanel;
	private boolean enableTrapCell;
	
	public MenuDialog()
	{
		thisPanel = new JPanel();
		createDialog();
	}

	private void createDialog()
	{
		GridLayout layout = new GridLayout(0,2);
		thisPanel.setLayout(layout);
		//Create text label and checkbox and place them inside a panel
		JLabel text = new JLabel("Enable Trap Cells?");
		JCheckBox box = new JCheckBox();
		thisPanel.add(text);
		thisPanel.add(box);
		
		JOptionPane.showConfirmDialog(null, thisPanel, "Game Options", JOptionPane.DEFAULT_OPTION);
		//set enableTrapCell option
		enableTrapCell = box.isSelected();
	}
	
	public boolean isTrapCellEnabled()
	{
		return enableTrapCell;
	}
}
