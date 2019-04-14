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
    private JSpinner player1Lookahead, player2Lookahead;
    private StartFrame frame;
    private Component spinnerEditor, spinnerEditor1arg, spinnerEditor2arg;
    private JComboBox<String> player1Choice, player2Choice;
    private JCheckBox player1CheckBox, player2CheckBox;
    private JCheckBox displayBoardBox;
    private JSpinner runsNumber;
    private JSpinner moveDelay;


    public StartPanel(StartFrame frame){
        String [] players = {"Random Player", "Human player", "ShortestPath Simple"
                ,"ShortestPathBlocking","Simple Random Fill","Random Fill w/Shortest path"
        ,"ShortestPath Eval Function", "Random Fill Eval Function"};
        this.frame =frame;
        this.button = new JButton("Start");
        this.button.setActionCommand("Start");
        this.button.addActionListener(this);
        this.spinner = new JSpinner();
        this.player1Fillargs = new JSpinner();
        this.player2Fillargs = new JSpinner();
        this.moveDelay = new JSpinner();


        this.player1Lookahead = new JSpinner();
        this.player2Lookahead = new JSpinner();
        this.player1CheckBox = new JCheckBox();
        this.player2CheckBox = new JCheckBox();

        this.runsNumber = new JSpinner();

        player1CheckBox.setSelected(true);
        player2CheckBox.setSelected(true);

        this.displayBoardBox = new JCheckBox();
        displayBoardBox.setSelected(true);
        JPanel gridPanel = new JPanel(new GridLayout(1,3));
        JPanel metaPanel = new JPanel(new GridLayout(2,2));
        JPanel player1Panel = new JPanel(new GridLayout(4,1));
        JPanel player2Panel = new JPanel(new GridLayout(4,1));



        JLabel sizeText = new JLabel("Size Of Board");
        JLabel player1Spinner = new JLabel("Player 1 (red)");
        JLabel player1Args = new JLabel("Player 1 Fill count");
        JLabel player2Spinner = new JLabel("player 2 (blue)");
        JLabel player2Args = new JLabel("player 2 fill count");
        JLabel startGame = new JLabel("start game");

        JLabel player1Depth = new JLabel("Depth Of Lookahead");
        JLabel player2Depth = new JLabel("Depth Of Lookahead");

        JLabel player1TreeBox = new JLabel("Reduce tree");
        JLabel player2TreeBox = new JLabel("Reduce tree");

        JLabel textShowBoard = new JLabel("Show Board");
        JLabel runsText = new JLabel("How many runs");

        JLabel delayText = new JLabel("Move time delay(ms)");

        player1Fillargs.setValue(500);
        player2Fillargs.setValue(500);
        runsNumber.setValue(1);
        player1Lookahead.setValue(1);
        player2Lookahead.setValue(1);




        JPanel sizePanel = new JPanel(new GridLayout(2,1));
        sizePanel.add(sizeText);
        sizePanel.add(spinner);
        metaPanel.add(sizePanel);

        JPanel metaDelay = new JPanel(new GridLayout(2,1));
        metaDelay.add(delayText);
        metaDelay.add(moveDelay);
        JPanel metaTopright = new JPanel(new GridLayout(2,1));
        metaTopright.add(metaDelay);
        JPanel displayBoardPanel = new JPanel(new GridLayout(1,2));
        displayBoardPanel.add(textShowBoard);
        displayBoardPanel.add(displayBoardBox);
        metaTopright.add(startGame);
        metaPanel.add(metaTopright);

        JPanel runsPanel = new JPanel(new GridLayout(2,1));
        JPanel runsTopPanel = new JPanel(new GridLayout(2,1));
        runsTopPanel.add(displayBoardPanel);
        runsTopPanel.add(runsText);
        runsPanel.add(runsTopPanel);
        runsPanel.add(runsNumber);
        metaPanel.add(runsPanel);


        metaPanel.add(button);

        spinnerEditor = spinner.getEditor();
        JFormattedTextField tf= ((JSpinner.DefaultEditor) spinnerEditor).getTextField();
        tf.setColumns(2);

        spinner.setValue(7);






        player1Choice = new JComboBox<>(players);
        player2Choice = new JComboBox<>(players);


        player1Panel.add(player1Spinner);
        player1Panel.add(player1Choice);

        JPanel fillCount1 = new JPanel(new GridLayout(2,1));
        fillCount1.add(player1Args);
        fillCount1.add(this.player1Fillargs);

        player1Panel.add(fillCount1);

        JPanel player1Look = new JPanel(new GridLayout(2,2));
        player1Look.add(player1Depth);
        player1Look.add(player1Lookahead);
        player1Look.add(player1TreeBox);
        player1Look.add(player1CheckBox);

        player1Panel.add(player1Look);


        player2Panel.add(player2Spinner);
        player2Panel.add(player2Choice);

        JPanel player2fillCount = new JPanel(new GridLayout(2,1));
        player2fillCount.add(player2Args);
        player2fillCount.add(player2Fillargs);
        player2Panel.add(player2fillCount);

        JPanel player2Look = new JPanel(new GridLayout(2,2));
        player2Look.add(player2Depth);
        player2Look.add(player2Lookahead);
        player2Look.add(player2TreeBox);
        player2Look.add(player2CheckBox);

        player2Panel.add(player2Look);





        gridPanel.add(metaPanel);
        gridPanel.add(player1Panel);
        gridPanel.add(player2Panel);
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
            int reduceTree1 = 0;
            int reduceTree2=0;
            int showBoard =0;
            if(player1CheckBox.isSelected()){
                reduceTree1 = 1;
            }
            if(player2CheckBox.isSelected()){
                reduceTree2 = 1;
            }
            if(displayBoardBox.isSelected()){
                showBoard = 1;
            }

            int []argsArray = {(Integer)this.player1Fillargs.getValue(),
                    (Integer)this.player2Fillargs.getValue(), (Integer)this.player1Lookahead.getValue(),
                    (Integer)this.player2Lookahead.getValue(),reduceTree1,reduceTree2,showBoard,
                    (Integer)this.runsNumber.getValue(),(Integer)this.moveDelay.getValue()};

            int theValue = (Integer)this.spinner.getValue();
            frame.setHasStartedSize(true, theValue,this.player1Choice.getSelectedIndex(),
                    this.player2Choice.getSelectedIndex(),argsArray);
        }

    }




}
