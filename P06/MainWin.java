import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import shelter.Shelter;
import shelter.DogBreed;
import shelter.CatBreed;
import shelter.Animal;
import shelter.Dog;
import shelter.Cat;
import shelter.Gender;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class MainWin extends JFrame {
    private Shelter shelter;
    private JLabel data;

    public MainWin(String title) {
        super(title);
        shelter = new Shelter(title);
        data = new JLabel();
        data.setVerticalAlignment(JLabel.TOP);

        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout
        JMenuBar menubar     = new JMenuBar();        
        JMenu     file       = new JMenu("File");        
        JMenuItem quit       = new JMenuItem("Quit");
        JMenu     animal     = new JMenu("Animal");
        JMenuItem newDog     = new JMenuItem("New Dog");
        JMenuItem newCat     = new JMenuItem("New Cat");
        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");

        JLabel msg = new JLabel("<HTML>"+
                "<em>"+
                    "<font color='blue'>"+
                        "Tip: you can click the icons on the "+ 
                        "toolbar to add the corresponding animal"+
                    "</font>"+
                "</em>"+
            "</HTML>");
        
        newDog.addActionListener(event -> {
            onNewDogClick(); 
            if(msg.isEnabled()) msg.setVisible(true);
        });
        newCat.addActionListener(event -> {
            onNewCatClick();
            if(msg.isEnabled()) msg.setVisible(true);
        });
        quit  .addActionListener(event -> onQuitClick());
        about.addActionListener(event -> onAboutClick());
        
        file.add(quit);
        animal.add(newDog);
        animal.add(newCat);
        help.add(about);        
        menubar.add(file);
        menubar.add(animal);
        menubar.add(help);
        setJMenuBar(menubar);

        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("New Animal Shortcut");  

        JButton newDogB  = new JButton(new ImageIcon("images/noun-jack-russell-terrier.png"));
          newDogB.setActionCommand("Add new dog");
          newDogB.setToolTipText("Add a new dog to the shelter");
          toolbar.add(newDogB);
          newDogB.addActionListener(event -> {
            onNewDogClick();
            if (msg.isVisible()) msg.setVisible(false);             
            if (msg.isEnabled()) msg.setEnabled(false);
          });          

        toolbar.add(Box.createHorizontalStrut(5));

        JButton newCatB = new JButton(new ImageIcon("images/noun-cat.png"));
          newCatB.setActionCommand("Add new cat");
          newCatB.setToolTipText("Add a new cat to the shelter");
          toolbar.add(newCatB);
          newCatB.addActionListener(event -> {
            onNewCatClick();
            if (msg.isVisible()) msg.setVisible(false);             
            if (msg.isEnabled()) msg.setEnabled(false);
          });

        toolbar.addSeparator();

        add(toolbar, BorderLayout.PAGE_START);
        add(data, BorderLayout.CENTER);
        add(msg, BorderLayout.PAGE_END);
        add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
        msg.setVisible(false);

        setSize(1080, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void updateDisplay() {
        data.setText("<HTML>" + shelter.toString()
                        .replaceAll("<","&lt;")
                        .replaceAll(">", "&gt;")
                        .replaceAll("\n", "<br/>")
                    +"</HTML>");
    }

    public void onNewCatClick() {
        JLabel nameTag = new JLabel("Name");
        JTextField names = new JTextField(20); 
               
        JLabel genderTag = new JLabel("<HTML><br/>Gender</HTML>");        
        JComboBox<Gender> genders = new JComboBox<>(Gender.values());

        JLabel ageTag = new JLabel("<HTML><br/>Age</HTML>");
        SpinnerModel range = new SpinnerNumberModel(0, 0, 40, 1); //
        JSpinner ages = new JSpinner(range);

        JLabel breedTag = new JLabel("<HTML><br/>Breed</HTML>");
        JComboBox breeds = new JComboBox<>(CatBreed.values()); ///

        Object[] objects = { 
            nameTag,   names,
            breedTag,  breeds, 
            genderTag, genders, 
            ageTag,    ages
        };

        ImageIcon catIcon = new ImageIcon("images/noun-cat.png");
        int button = JOptionPane.showConfirmDialog( 
            this,
            objects,
            "New Cat",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            catIcon);
        if(button == JOptionPane.OK_OPTION) {
            shelter.addAnimal(new Cat(
                names.getText(), 
                (Gender) genders.getSelectedItem(),
                (Integer) ages.getValue(),
                (CatBreed) breeds.getSelectedItem()));
            updateDisplay();
        }   
    }

    public void onNewDogClick() {
        JLabel nameTag = new JLabel("Name");
        JTextField names = new JTextField(20); 
               
        JLabel genderTag = new JLabel("<HTML><br/>Gender</HTML>");        
        JComboBox genders = new JComboBox<>(Gender.values());

        JLabel ageTag = new JLabel("<HTML><br/>Age</HTML>");
        SpinnerModel range = new SpinnerNumberModel(0, 0, 35, 1); //
        JSpinner ages = new JSpinner(range);

        JLabel breedTag = new JLabel("<HTML><br/>Breed</HTML>");
        JComboBox breeds = new JComboBox<>(DogBreed.values()); ///

        Object[] objects = { 
            nameTag,   names,
            breedTag,  breeds, 
            genderTag, genders, 
            ageTag,    ages
        };

        ImageIcon dogIcon = new ImageIcon("images/noun-jack-russell-terrier.png");
        int button = JOptionPane.showConfirmDialog( 
            this,
            objects,
            "New Dog",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            dogIcon);
        if(button == JOptionPane.OK_OPTION) {
            shelter.addAnimal(new Dog(
                names.getText(), 
                (Gender) genders.getSelectedItem(),
                (Integer) ages.getValue(),
                (DogBreed) breeds.getSelectedItem()));
            updateDisplay();
        }   
    }

    public void onAboutClick() {
        JDialog about = new JDialog();
        JPanel panel = new JPanel(new FlowLayout());
        about.setLayout(new GridLayout(1,2));
        about.setTitle("About MASS");
        
        try {
            BufferedImage myPicture = ImageIO.read(new File("images/MASS_logo.jpg"));
            JLabel logo = new JLabel(new ImageIcon(myPicture));
            about.add(logo);
        } catch(IOException e) {
        }
        
        String title = new String("<p><font size=+5>MASS</font></p>"
            + "<p><font size=+2>Mav's Animal Shelter Software</font></p><br/>");

        JLabel artists = new JLabel("<html>"
          + title
          + "<p>Version 1.1</p>"
          + "<p>Copyright 2022 by Ikechukwu C. Ofili</p>"
          + "<p>Licensed under GNU GPL 3.0</p><p><br/></p>"
          + "<p><em>Logo (free for commercial use)</em></p>"
          + "<p><font size=-1>https://pixabay.com/photos/dog-cat-friendship-cute-pets-6188658/</font></p><p><br/></p>"
          + "<p><em>Jack Russell Terrier by Alena from NounProject.com</em></p>"
          + "<p>Licensed for personal and commercial purposes (with attribution)</p>"
          + "<p><font size=-1>https://thenounproject.com/icon/jack-russell-terrier-4075864/</font></p><p><br/></p>"
          + "<p><em>Cat by Llisole from NounProject.com.</em></p>"
          + "<p>Licensed for personal and commercial purposes (with attribution)</p>"
          + "<p><font size=-1>https://thenounproject.com/icon/cat-3652151/</font></p><br/><p><br/></p>"
          + "</html>");
        panel.add(artists);

        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        panel.add(ok);
        
        about.setSize(1000,605);
        about.add(panel);
        about.setVisible(true);
    }

    public void onQuitClick() {System.exit(0);}
}

//Way 1: pass animal id and icon and use that to figure out data to use then add the created animal to shelter.
//Way 2 (preferred): pass animal id and other useful information then use those to add animal to the shelter.