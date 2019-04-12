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
    private JSpinner player1Fillargs, player2Fillargs;
    private StartFrame frame;
    private Component spinnerEditor, spinnerEditor1arg, spinnerEditor2arg;
    private JComboBox<String> player1Choice, player2Choice;


    public StartPanel(StartFrame frame){
        String [] players = {"Random Player", "Human player", "ShortestPath Simple"
                ,"ShortestPathBlocking","Simple Random Fill"};
        this.frame =frame;
        this.button = new JButton("Start");
        this.button.setActionCommand("Start");
        this.button.addActionListener(this);
        this.spinner = new JSpinner();
        this.player1Fillargs = new JSpinner();
        this.player2Fillargs = new JSpinner();
        JPanel gridPanel = new JPanel(new GridLayout(2,6));

        JTextArea sizeText = new JTextArea("Size Of Board");
        JTextArea player1Spinner = new JTextArea("Player 1 (red)");
        JTextArea player1Args = new JTextArea("Player 1 Fill count");
        JTextArea player2Spinner = new JTextArea("player 2 (blue)");
        JTextArea player2Args = new JTextArea("player 2 fill count");
        JTextArea startGame = new JTextArea("start game");
        gridPanel.add(sizeText);
        gridPanel.add(player1Spinner);
        gridPanel.add(player1Args);
        gridPanel.add(player2Spinner);
        gridPanel.add(player2Args);
        gridPanel.add(startGame);


        spinnerEditor = spinner.getEditor();
        JFormattedTextField tf= ((JSpinner.DefaultEditor) spinnerEditor).getTextField();
        tf.setColumns(2);


        player1Choice = new JComboBox<>(players);
        player2Choice = new JComboBox<>(players);

        gridPanel.add(spinner);
        gridPanel.add(player1Choice);
        gridPanel.add(this.player1Fillargs);
        gridPanel.add(player2Choice);
        gridPanel.add(player2Fillargs);
        gridPanel.add(button);
        this.add(gridPanel);

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
            int []argsArray = {(Integer)this.player1Fillargs.getValue(),
                    (Integer)this.player2Fillargs.getValue()};

            int theValue = (Integer)this.spinner.getValue();
            frame.setHasStartedSize(true, theValue,this.player1Choice.getSelectedIndex(),
                    this.player2Choice.getSelectedIndex(),argsArray);
        }

    }




}
