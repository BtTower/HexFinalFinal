package UI;

/**
 * Created by Gleb on 10/04/2019.
 */



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by Gleb on 11/01/2019.
 */
public class StartPanel extends JPanel implements ActionListener {
    private JButton button;
    private JSpinner spinner;
    private StartFrame frame;
    private Component spinnerEditor;
    private JComboBox<String> player1Choice, player2Choice;


    public StartPanel(StartFrame frame){
        String [] players = {"Random Player", "Human player", "ShortestPath Simple"};
        this.frame =frame;
        this.button = new JButton("Start");
        this.button.setActionCommand("Start");
        this.button.addActionListener(this);
        this.spinner = new JSpinner();
        spinnerEditor = spinner.getEditor();
        JFormattedTextField tf= ((JSpinner.DefaultEditor) spinnerEditor).getTextField();
        tf.setColumns(2);
        player1Choice = new JComboBox<>(players);
        player2Choice = new JComboBox<>(players);
        JTextArea textArea = new JTextArea("Board Size       RedPlayer               BluePlayer");
        JPanel panel2 = new JPanel(new GridLayout(2,1));
        panel2.add(textArea,BorderLayout.NORTH);
        JPanel panel3 = new JPanel();
        panel3.add(spinner, BorderLayout.WEST);
        panel3.add(player1Choice,BorderLayout.SOUTH);
        panel3.add(player2Choice,BorderLayout.SOUTH);
        panel3.add(button,BorderLayout.EAST);
        panel2.add(panel3);
        this.add(panel2,BorderLayout.SOUTH);

    }


    public int getSpinnerValue(){
        return (Integer) this.spinner.getValue();
    }

    public int test(){
        return 2;
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        if("Start".equals(ae.getActionCommand())) {
            int theValue = (Integer)this.spinner.getValue();
            frame.setHasStartedSize(true, theValue,this.player1Choice.getSelectedIndex(),
                    this.player2Choice.getSelectedIndex());
        }

    }




}
