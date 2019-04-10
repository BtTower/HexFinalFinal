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
        String [] players = {"Random Player", "Human player"};
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
        this.add(spinner, BorderLayout.WEST);
        this.add(button,BorderLayout.EAST);
        this.add(player1Choice,BorderLayout.SOUTH);
        this.add(player2Choice,BorderLayout.SOUTH);
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
