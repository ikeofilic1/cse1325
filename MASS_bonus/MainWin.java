import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
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
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import shelter.Shelter;
import shelter.DogBreed;
import shelter.CatBreed;
import shelter.BunnyBreed;
import shelter.Animal;
import shelter.Dog;
import shelter.Cat;
import shelter.Bunny;
import shelter.Client;
import shelter.Gender;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import javax.imageio.ImageIO;


public class MainWin extends JFrame {
    private Shelter shelter;
    private JLabel data;
    private boolean isSaved;
    private enum DataView {ANIMALS, CLIENTS, ADOPTIONS}

    public MainWin(String title) {
        super(title);

        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout
        JMenuBar menubar     = new JMenuBar();        
        JMenu     file       = new JMenu("File"); 
        JMenuItem open       = new JMenuItem("Open Shelter");
        JMenuItem newF       = new JMenuItem("New Shelter");
        JMenuItem save       = new JMenuItem("Save Shelter");
        JMenuItem saveAs     = new JMenuItem("Save Shelter As");       
        JMenuItem quit       = new JMenuItem("Quit");
        JMenu     animal     = new JMenu("Animal");
        JMenuItem newDog     = new JMenuItem("New Dog");
        JMenuItem newCat     = new JMenuItem("New Cat");
        JMenuItem newBunny   = new JMenuItem("New Bunny");
        JMenuItem animList   = new JMenuItem("List Available");
        JMenuItem adoptL     = new JMenuItem("List Adopted");
        JMenu     client     = new JMenu("Client");
        JMenuItem newCli     = new JMenuItem("New");
        JMenuItem cliList    = new JMenuItem("List");
        JMenuItem adopt      = new JMenuItem("Adopt Animal");       
        JMenu     help       = new JMenu("Help");
        JMenuItem about      = new JMenuItem("About");

        newDog  .addActionListener(event -> onNewDogClick());
        newCat  .addActionListener(event -> onNewCatClick());
        newBunny.addActionListener(event -> onNewBunnyClick());
        newCli  .addActionListener(event -> onNewClientClick());
        adopt   .addActionListener(event -> onAdoptAnimalClick());
        quit    .addActionListener(event -> onQuitClick());
        about   .addActionListener(event -> onAboutClick());
        open    .addActionListener(event -> onOpenShelterClick());
        newF    .addActionListener(event -> onNewShelterClick());
        save    .addActionListener(event -> onSaveShelterClick());
        saveAs  .addActionListener(event -> onSaveShelterAsClick());
        animList.addActionListener(event -> updateDisplay(DataView.ANIMALS));
        cliList .addActionListener(event -> updateDisplay(DataView.CLIENTS));
        adoptL  .addActionListener(event -> updateDisplay(DataView.ADOPTIONS));
        
        file.add(open);
        file.add(newF);
        file.add(save);
        file.add(saveAs);
        file.add(quit);
        animal.add(newDog);
        animal.add(newCat);
        animal.add(newBunny);
        animal.add(animList);
        animal.add(adoptL);
        client.add(newCli);
        client.add(cliList);
        client.add(adopt);
        help.add(about);        
        menubar.add(file);
        menubar.add(animal);
        menubar.add(client);
        menubar.add(help);
        setJMenuBar(menubar);

        
        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R
        // Add a toolbar to the PAGE_START region below the menu
        JToolBar toolbar = new JToolBar("Toolbar");

        JButton openB = new JButton(new ImageIcon("images/get-file.png"));
          openB.setActionCommand("Load an existing shelter from file");
          openB.setToolTipText("Load an existing shelter");
          toolbar.add(openB);
          openB.addActionListener(event -> onOpenShelterClick());

        toolbar.add(Box.createHorizontalStrut(5));
        JButton newB = new JButton(new ImageIcon("images/new-file.png"));
          newB.setActionCommand("New shelter");
          newB.setToolTipText("Create a new, empty shelter");
          toolbar.add(newB);
          newB.addActionListener(event -> onNewShelterClick());

        toolbar.add(Box.createHorizontalStrut(5));
        JButton saveB  = new JButton(new ImageIcon("images/save-file.png"));
          saveB.setActionCommand("Save current shelter");
          saveB.setToolTipText("Save current shelter");
          toolbar.add(saveB);
          saveB.addActionListener(event -> onSaveShelterClick());

        toolbar.add(Box.createHorizontalStrut(5));
        JButton saveAsB  = new JButton(new ImageIcon("images/save-file-as.png"));
          saveAsB.setActionCommand("Save current shelter as");
          saveAsB.setToolTipText("Save current shelter to new location");
          toolbar.add(saveAsB);
          saveAsB.addActionListener(event -> onSaveShelterAsClick());

        toolbar.add(Box.createHorizontalStrut(15));
        JButton newDogB  = new JButton(new ImageIcon("images/noun-jack-russell-terrier.png"));
          newDogB.setActionCommand("Add new dog");
          newDogB.setToolTipText("Add a new dog to the shelter");
          toolbar.add(newDogB);
          newDogB.addActionListener(event -> onNewDogClick());          

        toolbar.add(Box.createHorizontalStrut(5));
        JButton newCatB = new JButton(new ImageIcon("images/noun-cat.png"));
          newCatB.setActionCommand("Add new cat");
          newCatB.setToolTipText("Add a new cat to the shelter");
          toolbar.add(newCatB);
          newCatB.addActionListener(event -> onNewCatClick());

        toolbar.add(Box.createHorizontalStrut(5));
        JButton newBunnyB = new JButton(new ImageIcon("images/noun-bunny.png"));
          newBunnyB.setActionCommand("Add new bunny");
          newBunnyB.setToolTipText("Add a new bunny to the shelter");
          toolbar.add(newBunnyB);
          newBunnyB.addActionListener(event -> onNewBunnyClick());

        toolbar.add(Box.createHorizontalStrut(20));
        JButton newCliB  = new JButton(new ImageIcon("images/client-1.png"));
          newCliB.setActionCommand("Add new client");
          newCliB.setToolTipText("Add a new client");
          toolbar.add(newCliB);
          newCliB.addActionListener(event -> onNewClientClick());

        toolbar.add(Box.createHorizontalStrut(10));
        JButton adoptB  = new JButton(new ImageIcon("images/noun-adopt-pet.png"));
          adoptB.setActionCommand("Adopt an animal");
          adoptB.setToolTipText("Adopt an animal from the shelter");
          toolbar.add(adoptB);
          adoptB.addActionListener(event -> onAdoptAnimalClick());

        toolbar.addSeparator();
        add(toolbar, BorderLayout.PAGE_START);
        
        shelter = new Shelter("Mav's Animal Shelter");
        shelter.setFilename("");

        data = new JLabel();
        data.setFont(new Font(null, Font.BOLD, 14));        
        data.setVerticalAlignment(JLabel.TOP);
        add(data, BorderLayout.CENTER);

        isSaved = true;
        setBounds(75,25,920,550);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onQuitClick();
            } 
        });
    }

    private void updateDisplay(DataView view) {
        String text = "Invalid display selected: " + view;
        if(view == DataView.ANIMALS)   text = shelter.toString();
        if(view == DataView.CLIENTS)   text = shelter.clientsToString();
        if(view == DataView.ADOPTIONS) text = shelter.adoptionsToString();

        data.setText("<HTML>" + text
                        .replaceAll("<","&lt;")
                        .replaceAll(">", "&gt;")
                        .replaceAll("\n", "<br/>")
                    +"</HTML>");
    }

    public void onNewCatClick() {
        newAnimal(new Cat(), new JComboBox<>(CatBreed.values()), 
            new ImageIcon("images/noun-cat.png"));
    }

    public void onNewDogClick() {
        newAnimal(new Dog(), new JComboBox<>(DogBreed.values()), 
            new ImageIcon("images/noun-jack-russell-terrier.png"));
    }

    public void onNewBunnyClick() {
        newAnimal(new Bunny(), new JComboBox<>(BunnyBreed.values()), 
            new ImageIcon("images/noun-bunny.png"));
    }

    public void onNewClientClick() {
        JLabel nameTag = new JLabel("Name");
        JTextField name = new JTextField(20);

        JLabel phoneTag = new JLabel("Phone");
        JTextField number = new JTextField(20);

        Object[] objects = {
            nameTag,   name,
            phoneTag,  number 
        };

        int button = JOptionPane.showConfirmDialog( 
            this, objects, "New Client",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            new ImageIcon("images/client-1.png"));
        if(button == JOptionPane.OK_OPTION) {
            try {
                shelter.addClient(new Client(
                    name.getText(), number.getText()));
                updateDisplay(DataView.CLIENTS);
                isSaved = false;
            } catch (IllegalArgumentException e) {
                errorDialog(e.getMessage());
            }
        }   
    }

    public <T extends Animal> void newAnimal(T animal, JComboBox breeds, ImageIcon petIcon) {
        JLabel nameTag = new JLabel("Name");
        JTextField names = new JTextField(20); 
               
        JLabel genderTag = new JLabel("<HTML><br/>Gender</HTML>");        
        JComboBox<Gender> genders = new JComboBox<>(Gender.values());

        JLabel ageTag = new JLabel("<HTML><br/>Age</HTML>");
        SpinnerModel range = new SpinnerNumberModel(0, 0, 50, 1);
        JSpinner ages = new JSpinner(range);

        JLabel breedTag = new JLabel("<HTML><br/>Breed</HTML>");

        Object[] objects = { nameTag, names, 
            breedTag, breeds, genderTag, genders, ageTag, ages};

        int button = JOptionPane.showConfirmDialog( 
            this,
            objects,
            "New " + animal.family(),
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            petIcon);
        if(button == JOptionPane.OK_OPTION) {
            try {
                animal.create(breeds.getSelectedItem(), names.getText(), 
                    (Gender)genders.getSelectedItem(), (int)ages.getValue());
                shelter.addAnimal(animal);
                isSaved = false;
                updateDisplay(DataView.ANIMALS);
            } catch (Exception e) {
                errorDialog(e.getMessage());
            }
        }   
    }

    public void onAdoptAnimalClick() {
        JLabel animalTag = new JLabel("Animal");
        JLabel clientTag = new JLabel("<HTML><br/>Client</HTML>");
        JComboBox<Animal> animals = new JComboBox<>();
        JComboBox<Client> clients = new JComboBox<>();
        var it1 = shelter.animalListIterator();
        var it2 = shelter.clientListIterator();

        if (!it1.hasNext()) {
            errorDialog("No animals available for adoption");
            return;
        }
        if (!it2.hasNext()) {
            errorDialog("Add a new client to adopt animals");
            return;
        }

        while(it1.hasNext()) animals.addItem(it1.next());
        while(it2.hasNext()) clients.addItem(it2.next());
        
        Object[] objects = { 
            animalTag, animals,
            clientTag, clients
        };
        int button = JOptionPane.showConfirmDialog( 
            this, objects,
            "❤ Adoption ❤",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            new ImageIcon("images/noun-adopt-pet.png"));
        if(button == JOptionPane.OK_OPTION) {
            try {
                shelter.adopt((Animal) animals.getSelectedItem(),
                    (Client) clients.getSelectedItem());
                updateDisplay(DataView.ADOPTIONS);
                isSaved = false;
            } catch (IllegalArgumentException e) {
                errorDialog(e.getMessage());
            }            
        }
    }

    public void onAboutClick() {
        JDialog about = new JDialog();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        about.setLayout(new GridLayout(1,2));
        about.setTitle("About MASS");
        
        try {
            BufferedImage myPicture = ImageIO.read(new File("images/MASS_logo.jpg"));
            JLabel logo = new JLabel(new ImageIcon(myPicture));
            about.add(logo);
        } catch(IOException e) {
        }
        
        JLabel title = new JLabel("<HTML><p><font size=+5>MASS</font></p></HTML>", SwingConstants.CENTER);
        JLabel subtitle = new JLabel("<HTML><p><font size=+2>"
            + "Mav's Animal Shelter Software</font></p><br/></HTML>", SwingConstants.CENTER);

        JPanel titlePane = new JPanel();
        titlePane.add(title);
        JPanel subPane = new JPanel();
        subPane.add(subtitle);
        panel.add(titlePane);
        panel.add(subPane);

        JLabel artists = new JLabel("<html>"
          + "<p>Version 2.0</p>"
          + "<p>Copyright 2022 by Ikechukwu C. Ofili</p>"
          + "<p>Licensed under GNU GPL 3.0</p><p><br/></p>"
          + "<p><em>Logo (free for commercial use)</em></p>"
          + "<p><font size=-1>https://pixabay.com/photos/dog-cat-friendship-cute-pets-6188658/</font></p><p><br/></p>"
          + "<p><em>Jack Russell Terrier by Alena from NounProject.com</em></p>"
          + "<p>Licensed for personal and commercial purposes (with attribution)</p>"
          + "<p><font size=-1>https://thenounproject.com/icon/jack-russell-terrier-4075864/</font></p><p><br/></p>"
          + "<p><em>Cat by Llisole from NounProject.com</em></p>"
          + "<p>Licensed for personal and commercial purposes (with attribution)</p>"
          + "<p><font size=-1>https://thenounproject.com/icon/cat-3652151/</font></p><p><br/></p>"
          + "<p><em>Bunny by Nithinan Tatah from NounProject.com</em></p>"
          + "<p><font size=-1>https://thenounproject.com/icon/bunny-1565760/</font></p><p><br/></p>"
          + "<p><em>Client icon by Icons8</em></p>"
          + "<p><font size=-1>https://icons8.com/icon/D3hRNbakq0Fp/client</font></p><p><br/></p>"
          + "<p><em>Save and save as icons by Icons8</em></p>"
          + "<p><font size=-1>https://icons8.com/icon/</font></font></p><p><br/></p>"                 
          + "<p><em>Folder icons created by Freepik - Flaticon</em></p>"
          + "<p><font size=-1>https://www.flaticon.com/free-icons/folder</font></p><br/><p><br/></p>"
          + "</html>");

        JPanel artistePane = new JPanel();
        artistePane.add(artists);
        panel.add(artistePane);

        JPanel okpanel = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        okpanel.add(ok);
        panel.add(okpanel);
        
        about.add(panel);
        //about.setBounds(100,0,1020,650);
        about.pack();
        about.setVisible(true);
    }

    public void onSaveShelterClick() {
        if (shelter.getFilename().equals("")) onSaveShelterAsClick();
        if (shelter.getFilename().equals("")) return;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(shelter.getFilename()))) {
            shelter.save(bw);
            isSaved = true;
        }
        catch (IOException e) {
            errorDialog("Cannot save to " + 
                shelter.getFilename() + '\n' + e.getMessage());
        }
    }

    public void onSaveShelterAsClick() {
        File filename;
        if (!shelter.getFilename().equals(""))
             filename = new File(shelter.getFilename());
        else filename = new File(System.getProperty("user.dir") + "/untitled.mass");

        final JFileChooser fc = new JFileChooser(filename) {
            @Override
            public void approveSelection() {
                int approved = JOptionPane.YES_OPTION;
                File file = this.getSelectedFile();        
                if(!file.getName().endsWith(".mass"))  
                    file = new File(file.getAbsolutePath() + ".mass");

                if (file.exists() && !file.getAbsolutePath().equals(shelter.getFilename())){
                    approved = JOptionPane.showConfirmDialog(this,
                        "This file already exists, do you want to overwrite it?",
                        "Overwrite file", 
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);
                }                    
                if (approved == JOptionPane.YES_OPTION) {
                    this.setSelectedFile(file);
                    super.approveSelection();
                }
            }
        };

        FileFilter massFiles = new FileNameExtensionFilter("Mass files", "mass");
        fc.addChoosableFileFilter(massFiles);         
        fc.setFileFilter(massFiles);
        fc.setSelectedFile(filename); 
        
        int result = fc.showSaveDialog(this);        
        if (result == JFileChooser.APPROVE_OPTION) {
            shelter.setFilename(fc.getSelectedFile().getAbsolutePath());
            onSaveShelterClick();
            if(!filename.getAbsolutePath().equals(shelter.getFilename()))
                filename.delete();
        }
    }

    public boolean safeToExit() {
        if (!isSaved) {
            int result = JOptionPane.showConfirmDialog(this,
                    "Save changes to current shelter?",
                    "File not saved", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.YES_OPTION) 
                onSaveShelterClick();
            if (result != JOptionPane.NO_OPTION) return isSaved;
        }
        return true;
    }

    public void errorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, 
            "Error", JOptionPane.ERROR_MESSAGE); 
    }

    public void onNewShelterClick() {
        if (!safeToExit()) return;

        String name = JOptionPane.showInputDialog(this, "Enter the shelter's name");
        if (name == null) return;
        if (name.length() == 0) {
            errorDialog("Shelter name cannot be empty");
            return;
        }

        shelter = new Shelter(name);
        shelter.setFilename("");
        updateDisplay(DataView.ANIMALS);
        isSaved = true;
    }

    public void onOpenShelterClick() {
        if (!safeToExit()) return;  

        final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        FileFilter massFiles = new FileNameExtensionFilter("Mass files", "mass");
        fc.addChoosableFileFilter(massFiles);         
        fc.setFileFilter(massFiles);

        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File filename = fc.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {                 
                shelter = new Shelter(br);
                updateDisplay(DataView.ANIMALS);
                isSaved = true;
            }
            catch(Exception e) {
                errorDialog("Unable to open " + filename + '\n' + e.getMessage());
            }
        }
    }

    public void onQuitClick() {
        if (!safeToExit()) return;
        System.exit(0);
    }

    private void setBorder(JComponent component) {
        // component.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        component.setBorder(BorderFactory.createEmptyBorder());
    }
}

//about-all panels, window listener
//add data valid : shelter, client, *(default name for animal dialog)
//for open see if label has changed (or check if numofclients > 0)