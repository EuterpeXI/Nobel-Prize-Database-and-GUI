/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobel;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.*;

/**
 * FXML Controller class
 *
 * @author Renato
 */
public class MainSceneController implements Initializable {

    private int CurrentScreen; // flag for current screen used by home and back
    private double HS_Transparent = 0.6;
    UndoManager undomanager = new UndoManager();
    Database DB = new Database(undomanager);
    int quotenum = 0;

    //edit me for database build here, comment out for testing ui 
    //so you dont have to always wait for it to load in the begining
    //unless you need the database for tests that is...
    //Database DB = new Database(); 
    //if you are messing with ALLITEMS, you're doing something horribly wrong
    //ask me before you mess with it as it contains literally everything. if
    //you need a specific screen access it directly using [screen]_Pane 
    //ex. HS_Pane (Home screen anchorpane), if you need a specific part of
    //the screen like a label or a grid, the same format applies. ex. HS_Grid
    @FXML
    private AnchorPane ALLITEMS;
    @FXML
    private AnchorPane IV_Pane;
    @FXML
    private AnchorPane RS_Pane;
    @FXML
    private AnchorPane HS_Pane;
    @FXML
    private ImageView BackIcon;
    @FXML
    private ImageView HomeIcon;
    @FXML
    private ImageView HS_PrizeIcon;
    @FXML
    private ImageView HS_YearsIcon;
    @FXML
    private ImageView HS_GenderIcon;
    @FXML
    private ImageView HS_BornIcon;
    @FXML
    private ImageView HS_NameIcon;
    @FXML
    private Label HS_NameLabel;
    @FXML
    private Label HS_PrizeLabel;
    @FXML
    private Label HS_YearsLabel;
    @FXML
    private Label HS_BornLabel;
    @FXML
    private Label HS_GenderLabel;
    @FXML
    private Label HS_Title;
    @FXML
    private GridPane HS_Grid;
    @FXML
    private ImageView SearchEntryExampleImage;

//    @FXML
//    private GridPane RS_SearchPane; // the second pane for more searches
    @FXML
    private GridPane RS_FilterPane; // the pane for the filters

    // Choiceboxes for filters
    @FXML
    private ChoiceBox RS_GenderFilter;
    @FXML
    private ChoiceBox RS_NameFilter;
    @FXML
    private ChoiceBox RS_CountryFilter;
    @FXML
    private ChoiceBox RS_PrizeFilter;

    // Sliders for Year
    @FXML
    private Slider RS_YearFilter1;
    @FXML
    private Slider RS_YearFilter2;

    @FXML
    private GridPane RS_IconPane; //icon pane also should have labels for name of person
    @FXML
    private Label IV_FirstName;
    @FXML
    private Label IV_Surname;
    @FXML
    private Label IV_Gender;
    @FXML
    private Label IV_BornCountry;
    @FXML
    private Label IV_DiedCountry;
    @FXML
    private Label IV_BornCity;
    @FXML
    private Label IV_DiedCity;
    @FXML
    private Label IV_Born;
    @FXML
    private Label IV_Died;
    @FXML
    private ImageView IV_PersonImage;
    @FXML
    private AnchorPane IV_MultimediaPane;
    @FXML
    private Label IV_Motivation;

    @FXML
    private ImageView RS_ClearButton;
    @FXML
    private ImageView RS_SearchButton;

    @FXML
    private ImageView RS_GenderIcon;
    @FXML
    private ImageView RS_NameIcon;
    @FXML
    private ImageView RS_PrizeIcon;
    @FXML
    private ImageView RS_YearIcon;
    @FXML
    private Label RS_GenderHistory; // "history" labels change the text beside the icons
    @FXML
    private Label RS_NameHistory;
    @FXML
    private Label RS_CountryHistory;
    @FXML
    private Label RS_PrizeHistory;
    @FXML
    private Label RS_YearHistory;
    @FXML
    private Label HS_Quote;
    @FXML
    private AnchorPane HomeandBack_Pane;
    @FXML
    private ImageView RS_CountryIcon;
    @FXML
    private ImageView HS_TitleImage;
    @FXML
    private ImageView RS_TitleImage;
    @FXML
    private ImageView IV_TitleImage;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // image import
        Image yearsimage = new Image("file:///C:../nobel/image/years.png");
        Image bornimage = new Image("file:///C:../nobel/image/born.png");
        Image prizeimage = new Image("file:///C:../nobel/image/prize.png");
        Image genderimage = new Image("file:///C:../nobel/image/gender.png");
        Image nameimage = new Image("file:///C:../nobel/image/name.png");
        Image homeiconimage = new Image("file:///C:../nobel/image/homeicon.png");
        Image backiconimage = new Image("file:///C:../nobel/image/backicon.png");
        Image yearsimageicon = new Image("file:///C:../nobel/image/yearsicon.png");
        Image bornimageicon = new Image("file:///C:../nobel/image/bornicon.png");
        Image prizeimageicon = new Image("file:///C:../nobel/image/prizeicon.png");
        Image genderimageicon = new Image("file:///C:../nobel/image/gendericon.png");
        Image nameimageicon = new Image("file:///C:../nobel/image/nameicon.png");
        Image clearbutton = new Image("file:///C:../nobel/image/ClearButton.png");
        Image searchbutton = new Image("file:///C:../nobel/image/SearchButton.png");
        Image titleimage = new Image("file:///C:../nobel/image/TitleImage.png");
        
        HS_TitleImage.setImage(titleimage);
        RS_TitleImage.setImage(titleimage);
        IV_TitleImage.setImage(titleimage);
        
        HS_Quote.setText("Every great and deep difficulty bears in itself it's own solution.It forces us to change our thinking in order to find it.-Niels Bohr"); 
        
        RS_SearchButton.setCursor(Cursor.HAND);
        RS_ClearButton.setCursor(Cursor.HAND);
        
        //examples
//        Image Exampleimage = new Image("https://www.nobelprize.org/nobel_prizes/physics/laureates/1901/rontgen.jpg");
//        SearchEntryExampleImage.setImage(Exampleimage);
//        SearchEntryExampleImage.setCursor(Cursor.HAND);
//        SearchEntryExampleImage.setOpacity(HS_Transparent);
//        IV_PersonImage.setImage(Exampleimage);

        // home screen (first panel) icon setup
//        HS_PrizeIcon.setImage(prizeimage);
//        HS_YearsIcon.setImage(yearsimage);
//        HS_GenderIcon.setImage(genderimage);
//        HS_BornIcon.setImage(bornimage);
//        HS_NameIcon.setImage(nameimage);
//
//        HS_PrizeIcon.setOpacity(HS_Transparent);
//        HS_YearsIcon.setOpacity(HS_Transparent);
//        HS_GenderIcon.setOpacity(HS_Transparent);
//        HS_BornIcon.setOpacity(HS_Transparent);
//        HS_NameIcon.setOpacity(HS_Transparent);
//
//        HS_PrizeIcon.setCursor(Cursor.HAND);
//        HS_YearsIcon.setCursor(Cursor.HAND);
//        HS_GenderIcon.setCursor(Cursor.HAND);
//        HS_BornIcon.setCursor(Cursor.HAND);
//        HS_NameIcon.setCursor(Cursor.HAND);

        // home and back buttons setup
        BackIcon.setImage(backiconimage);
        BackIcon.setOpacity(0.5);
        BackIcon.setCursor(Cursor.HAND);
        HomeIcon.setImage(homeiconimage);
        HomeIcon.setOpacity(0.5);
        HomeIcon.setCursor(Cursor.HAND);

        // Result Screen (2nd panel) icon setup
        RS_GenderIcon.setImage(genderimageicon);
        RS_YearIcon.setImage(yearsimageicon);
        RS_NameIcon.setImage(nameimageicon);
        RS_CountryIcon.setImage(bornimageicon);
        RS_PrizeIcon.setImage(prizeimageicon);
        RS_ClearButton.setImage(clearbutton);
        RS_SearchButton.setImage(searchbutton);

        // Information bits
        RS_GenderFilter.setItems(FXCollections.observableArrayList("male", "female", "org"));
        RS_NameFilter.setItems(FXCollections.observableArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "Y", "X"));
        RS_CountryFilter.setItems(FXCollections.observableArrayList(DB.getCountrySet()));
        RS_PrizeFilter.setItems(FXCollections.observableArrayList(DB.getPrizeSet()));
        RS_YearFilter1.setValue(1901);
        RS_YearFilter2.setValue(2017);

        int height = 257;
        RS_IconPane.setPrefHeight(height);
        for (int y = 0; y < 197; y++) {
            for (int x = 0; x < 5; x++) {
                height = height + 257;
                RS_IconPane.addRow(y);
                RS_IconPane.setPrefHeight(height);
            }
        }
        
        // All screens setup
        CurrentScreen = 0;
        HS_Pane.setVisible(true);
        RS_Pane.setVisible(false);
        IV_Pane.setVisible(false);
        HomeandBack_Pane.setVisible(false);

    }

    @FXML
    private void BackButton(MouseEvent event) {
        switch (CurrentScreen) {
            case 0:
                break; //do nothing (code will be added later)
            case 1:
                HS_Pane.setVisible(true);
                RS_Pane.setVisible(false);
                IV_Pane.setVisible(false);
                HomeandBack_Pane.setVisible(false);
                BackIcon.setOpacity(0.5);
                HomeIcon.setOpacity(0.5);
                CurrentScreen = 0;
                resetFilters();
                break;
            case 2:
                HS_Pane.setVisible(false);
                RS_Pane.setVisible(true);
                IV_Pane.setVisible(false);
                CurrentScreen = 1;
                break;
            default:
                break;
        }
    }

    /**
     * This is a helper function to reset the current history tab at the top of
     * the result screen (2nd panel) back to N/A.
     */
    private void resetFilters() {
        RS_GenderHistory.setText("N/A");
        RS_NameHistory.setText("N/A");
        RS_CountryHistory.setText("N/A");
        RS_PrizeHistory.setText("N/A");
        RS_YearHistory.setText("1901 - 2017");
    }

    @FXML
    private void HomeButton(MouseEvent event) {
        HS_Pane.setVisible(true);
        RS_Pane.setVisible(false);
        IV_Pane.setVisible(false);
        HomeandBack_Pane.setVisible(false);
        BackIcon.setOpacity(0.5);
        HomeIcon.setOpacity(0.5);
        CurrentScreen = 0;
        resetFilters();
    }

    @FXML
    private void HS_Prize_Exited(MouseEvent event) {
        HS_PrizeIcon.setOpacity(HS_Transparent);
    }

    @FXML
    private void HS_Prize_Entered(MouseEvent event) {
        HS_PrizeIcon.setOpacity(1.0);
    }

    @FXML
    private void HS_Years_Exited(MouseEvent event) {
        HS_YearsIcon.setOpacity(HS_Transparent);
    }

    @FXML
    private void HS_Years_Entered(MouseEvent event) {
        HS_YearsIcon.setOpacity(1.0);
    }

    @FXML
    private void HS_Gender_Exited(MouseEvent event) {
        HS_GenderIcon.setOpacity(HS_Transparent);
    }

    @FXML
    private void HS_Gender_Entered(MouseEvent event) {
        HS_GenderIcon.setOpacity(1.0);
    }

    @FXML
    private void HS_Born_Exited(MouseEvent event) {
        HS_BornIcon.setOpacity(HS_Transparent);
    }

    @FXML
    private void HS_Born_Entered(MouseEvent event) {
        HS_BornIcon.setOpacity(1.0);
    }

    @FXML
    private void HS_Name_Exited(MouseEvent event) {
        HS_NameIcon.setOpacity(HS_Transparent);
    }

    @FXML
    private void HS_Name_Entered(MouseEvent event) {
        HS_NameIcon.setOpacity(1.0);
    }

    @FXML
    private void RS_ExampleExited(MouseEvent event) {
        SearchEntryExampleImage.setOpacity(HS_Transparent);
    }

    @FXML
    private void RS_ExampleEntered(MouseEvent event) {
        SearchEntryExampleImage.setOpacity(1.0);
    }

    @FXML
    private void RS_ExampleClicked(MouseEvent event) {
        HS_Pane.setVisible(false);
        RS_Pane.setVisible(false);
        IV_Pane.setVisible(true);
        HomeandBack_Pane.setVisible(true);
        CurrentScreen = 2;
    }
    
    @FXML
    private void RS_ClearButtonMouseOver(MouseEvent event) {
        Tooltip.install(RS_ClearButton, new Tooltip("Press to clear previous results!"));
    }

    @FXML
    private void RS_ClearButtonClicked(MouseEvent event) {
        RS_IconPane.getChildren().remove(0, DB.getCount());
        DB.database = undomanager.flush();
    }

    /**
     * Once search button is clicked, the user is ready to search for people
     * with the given filter choiceboxes and sliders. It will first grab the
     * selections given by both the choiceboxes and the sliders and then utilize
     * the getter functions within our Database. Lastly, Database will return
     * all found Persons and it will update the panel on the right with all
     * found persons.
     *
     * @param event Given mouse click event
     */
    @FXML
    private void RS_SearchButtonClicked(MouseEvent event) {
        // Obtaining values from filter choice boxes and sliders
        String gender_input = (String) RS_GenderFilter.getValue();
        String name_input = (String) RS_NameFilter.getValue();
        String country_input = (String) RS_CountryFilter.getValue();
        String prize_input = (String) RS_PrizeFilter.getValue();
        

        // Slider values not working!! 
        // Null exception errors returned and it crashes on minYear_input 
        int minYear_input = (int) RS_YearFilter1.getValue();
        int maxYear_input = (int) RS_YearFilter2.getValue();

        // Setting the new current history tabs
        if (gender_input != null) {
            RS_GenderHistory.setText(gender_input);
            DB.searchGender(gender_input, undomanager);
        } else {
            RS_GenderHistory.setText("N/A");
        }

        if (name_input != null) {
            RS_NameHistory.setText(name_input);
            DB.searchSurname(name_input, undomanager);
        } else {
            RS_NameHistory.setText("N/A");
        }

        if (prize_input != null) {
            RS_PrizeHistory.setText(prize_input);
            DB.searchPrize(prize_input, undomanager);
        } else {
            RS_PrizeHistory.setText("N/A");
        }

        RS_YearHistory.setText(minYear_input + " - " + maxYear_input);
        DB.searchYears(minYear_input, maxYear_input, undomanager);

        if (country_input != null) {
            RS_CountryHistory.setText(country_input);
            DB.searchCountry(country_input, undomanager);
        } else {
            RS_CountryHistory.setText("N/A");
        }
        
        // Clearing the selction box for future selections. This allows for future empty selection fields.
        RS_GenderFilter.getSelectionModel().clearSelection();
        RS_NameFilter.getSelectionModel().clearSelection();
        RS_CountryFilter.getSelectionModel().clearSelection();
        RS_PrizeFilter.getSelectionModel().clearSelection();
        RS_YearFilter1.setValue(1901);
        RS_YearFilter2.setValue(2017);

        int x = 0;
        int y = 0;
        for (int i = 0; i < DB.getCount(); i++) {
            DB.getPerson(i).getFullname();
            RS_IconPane.setVgap(50);

            Label new_label = new Label();
            new_label.setCursor(Cursor.HAND);
            new_label.setOnMouseClicked((MouseEvent event1) -> {
                Label current_label = (Label) event1.getSource();

                Person current_person = DB.getSinglePerson(current_label.getText());
                Image current_img = new Image(current_person.getJpegURL());

                IV_PersonImage.setImage(current_img);
                IV_FirstName.setText(current_person.getFirstName());
                IV_Surname.setText(current_person.getSurname());
                IV_Gender.setText(current_person.getGender());
                IV_BornCountry.setText(current_person.getBornCountry());
                IV_DiedCountry.setText(current_person.getDiedCountry());
                IV_BornCity.setText(current_person.getBornCity());
                IV_DiedCity.setText(current_person.getDiedCity());
                IV_Born.setText(current_person.getBornDate());
                IV_Died.setText(current_person.getDiedDate());

                IV_Motivation.setText(current_person.getAllMotivations());

                HS_Pane.setVisible(false);
                RS_Pane.setVisible(false);
                IV_Pane.setVisible(true);
                HomeandBack_Pane.setVisible(true);
                CurrentScreen = 2;
            });

            // Adding image and label for the searched individuals in each of the gridpane nodes
            Image new_image;
            try {
                new_image = new Image(DB.getPerson(i).getJpegURL());
            } catch (Exception E) {
                new_image = new Image("file:///C:../nobel/image/NA.png");
            }
            ImageView new_imageView = new ImageView();
            
            new_label.setFont(Font.font(14));
            new_label.setText(DB.getPerson(i).getFullname());
            new_label.setTextFill(Color.BLACK);
            new_label.setTextOverrun(OverrunStyle.CENTER_ELLIPSIS);
            new_label.setAlignment(Pos.BOTTOM_CENTER);
            
            new_imageView.setImage(new_image);
            new_imageView.setFitHeight(227);
            new_imageView.setFitWidth(162);

            SplitPane splitpane = new SplitPane();
            splitpane.setOrientation(Orientation.VERTICAL);
            splitpane.setDividerPosition(0, 0.2);
            splitpane.getItems().addAll(new_imageView, new_label);
            
            RS_IconPane.add(splitpane, x, y);

            x++;
            if (x == 5) {
                x = 0;
                y++;
            }
        }

        // Print Test
        //System.out.println("Gender: " + gender_input + "\nName Letter: " + name_input + "\nCountry: " + country_input + "\nPrize: " + prize_input + "\nMin Year: " + minYear_input + "\nMax Year: " + maxYear_input);
        // + "\nMin Year: " + minYear_input + "\nMax Year: " + maxYear_input
    }

    @FXML // USE THESE TO HIGHLIGHT THEIR SELECTION ON THE NEXT SCREEN AND AUTO POPULATE THEIR FIRST CATEGORY
    private void HS_PrizeIconClicked(MouseEvent event) {
        HS_Pane.setVisible(false);
        RS_Pane.setVisible(true);
        IV_Pane.setVisible(false);
        HomeandBack_Pane.setVisible(true);
        BackIcon.setOpacity(1.0);
        HomeIcon.setOpacity(1.0);
        CurrentScreen = 1;
        quotenum++;
        switch (quotenum){
            case 0: 
                HS_Quote.setText("Every great and deep difficulty bears in itself it's own solution.It forces us to change our thinking in order to find it.-Niels Bohr"); 
                break;
            case 1:
                HS_Quote.setText("Darkness cannot drive out darkness; only light can do that. Hate cannot drive out hate; only love can do that. -Martin Luther King Jr"); 
                break;
            case 2:
                HS_Quote.setText("Above all, don't fear difficult moments. The best comes from them. -Rita Levi-Montalcini"); 
                break;
            case 3:
                HS_Quote.setText("One child, one teacher, one pen and one book can change the world. -Malala Yousafzai"); 
                break;
            case 4:
                HS_Quote.setText("Before you act, listen. Before you react, think. Before you spend, earn. Before you criticize, wait. Before you pray, forgive. Before you quit, try. - Ernest Hemingway"); 
                break;
            case 5:
                HS_Quote.setText("The end is in the beginning and yet you go on. -Samuel Beckett"); 
                break;
            default:
                quotenum = -1;
                break;
        }
    }

    @FXML
    private void HS_YearsIconClicked(MouseEvent event) {
        HS_Pane.setVisible(false);
        RS_Pane.setVisible(true);
        IV_Pane.setVisible(false);
        HomeandBack_Pane.setVisible(true);
        BackIcon.setOpacity(1.0);
        HomeIcon.setOpacity(1.0);
        CurrentScreen = 1;
    }

    @FXML
    private void HS_GenderIconClicked(MouseEvent event) {
        HS_Pane.setVisible(false);
        RS_Pane.setVisible(true);
        IV_Pane.setVisible(false);
        HomeandBack_Pane.setVisible(true);
        BackIcon.setOpacity(1.0);
        HomeIcon.setOpacity(1.0);
        CurrentScreen = 1;
    }

    @FXML
    private void HS_BornIconClicked(MouseEvent event) {
        HS_Pane.setVisible(false);
        RS_Pane.setVisible(true);
        IV_Pane.setVisible(false);
        HomeandBack_Pane.setVisible(true);
        BackIcon.setOpacity(1.0);
        HomeIcon.setOpacity(1.0);
        CurrentScreen = 1;
    }

    @FXML
    private void HS_NameIconClicked(MouseEvent event) {
        HS_Pane.setVisible(false);
        RS_Pane.setVisible(true);
        IV_Pane.setVisible(false);
        HomeandBack_Pane.setVisible(true);
        BackIcon.setOpacity(1.0);
        HomeIcon.setOpacity(1.0);
        CurrentScreen = 1;
    }
}
