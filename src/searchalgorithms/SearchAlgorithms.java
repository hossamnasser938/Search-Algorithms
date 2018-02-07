
package searchalgorithms;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class SearchAlgorithms extends Application 
{
    
    String startCity , goalCity , algorithmUsed;
    ArrayList<javafx.scene.Node> algorithmLines = new ArrayList<>();
    //int algorithmLines = 0;
    
    @Override
    public void start(Stage primaryStage) 
    {
        
        Tree Menofia = new Tree("Menofia" , new Node("Tala" , null , 435 , 55));
        
        Menofia.addNode("Tala" , "Shebeen" /*, 16.3*/ , 480 , 200);
        Menofia.addNode("Shebeen" , "Menoof" /*, 17.9*/ , 419 , 285);
        Menofia.addNode("Shebeen" , "Kwisna" /*, 18.3*/ , 600 , 215);
        Menofia.addNode("Shebeen" , "Berka" /*, 15.2*/ , 560 , 120);
        //Just now
        Menofia.traverse(Menofia.root, "Kwisna").leftChild = Menofia.traverse(Menofia.root, "Berka");
        //Menofia.traverse(Menofia.root, "Kwisna").leftChildDistance = 17.1;
        Menofia.traverse(Menofia.root, "Berka").leftChild = Menofia.traverse(Menofia.root, "Kwisna");
        //Menofia.traverse(Menofia.root, "Berka").leftChildDistance = 17.1;
        //
        Menofia.addNode("Menoof" , "Sadat" /*, 57.6*/ , 62 , 405);
        Menofia.addNode("Menoof" , "Sers" /*, 7.2*/ , 405 , 304);
        Menofia.addNode("Menoof" , "Shohada" /*, 22.8*/ , 400 , 150);
        //Menofia.addNode("Kwisna" , "Berka" , 17.1 , 560 , 120);
        //Menofia.addNode("Berka" , "Kwisna" , 17.1 , 600 , 215);
        Menofia.addNode("Sadat" , "Ashmoon" /*, 75*/ , 460 , 457);
        Menofia.addNode("Sers" , "Bagoor" /*, 8.3*/ , 509 , 327);

        ComboBox<String> startCityComboBox = new ComboBox<>();
        startCityComboBox.setMinSize(100 , 30);
        startCityComboBox.getItems().addAll(Menofia.citiesNames);
        startCityComboBox.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                startCity = startCityComboBox.getValue();
            }
        });
                
        ComboBox<String> goalCityComboBox = new ComboBox<>();
        goalCityComboBox.setMinSize(100,30);
        goalCityComboBox.getItems().addAll(Menofia.citiesNames);
        goalCityComboBox.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                goalCity = goalCityComboBox.getValue();
            }
        });
        
        ComboBox<String> AlgorithmUsedComboBox = new ComboBox<>();
        AlgorithmUsedComboBox.setMinSize(100, 30);
        AlgorithmUsedComboBox.getItems().addAll("DFS","BFS","Greedy","A*");
        AlgorithmUsedComboBox.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                algorithmUsed = AlgorithmUsedComboBox.getValue();
            }
        });
        
        Label startCityLabel = new Label("Start City");
        startCityLabel.setFont(Font.font(Font.getFamilies().get(21), FontWeight.BOLD, FontPosture.REGULAR, 20));
        startCityLabel.setTextFill(Color.WHITE);
        
        Label goalCityLabel = new Label("Goal City");
        goalCityLabel.setFont(Font.font(Font.getFamilies().get(21), FontWeight.BOLD, FontPosture.REGULAR, 20));
        goalCityLabel.setTextFill(Color.WHITE);
        
        Label searchAlgorithmLabel = new Label("Search Algorithm");
        searchAlgorithmLabel.setFont(Font.font(Font.getFamilies().get(21), FontWeight.BOLD, FontPosture.REGULAR, 20));
        searchAlgorithmLabel.setTextFill(Color.WHITE);
        
        BorderPane mapPane=new BorderPane();
        mapPane.setStyle("-fx-background-color : WHITE;");
        
        Button startBtn = new Button(" Start ");
        startBtn.setFont(Font.font(Font.getFamilies().get(21), FontWeight.BOLD, FontPosture.REGULAR, 20));
        DropShadow shadow = new DropShadow();
        startBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {    
            @Override 
            public void handle(MouseEvent e) 
            {
            startBtn.setEffect(shadow);
            }
        });
        startBtn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
            @Override 
            public void handle(MouseEvent e)
            { 
                startBtn.setEffect(null);
            }
        });
        startBtn.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                if(!algorithmLines.isEmpty())
                {
                    mapPane.getChildren().removeAll(algorithmLines);
                    algorithmLines.clear();
                }
                drawSolution(Menofia , startCity , goalCity , algorithmUsed , mapPane);
            }
        });
        startBtn.setStyle("-fx-background-color : WHITE;");
        
        HBox topHorizontalBox = new HBox(20);
        topHorizontalBox.setStyle("-fx-background-color : SILVER;");
        topHorizontalBox.setAlignment(Pos.CENTER);
        topHorizontalBox.getChildren().addAll(startCityLabel , startCityComboBox , goalCityLabel , goalCityComboBox , searchAlgorithmLabel , AlgorithmUsedComboBox , startBtn);
        HBox.setMargin(startBtn, new Insets(5,5,5,5));
        
        Label developedBy = new Label("Developed By : Hosam Abdelnaser");
        developedBy.setFont(Font.font(15));
        Label supervisedBy = new Label("Supervised By : Eng/Ahmed Ghozia .. Prof/Ahmed Almahalawy");
        supervisedBy.setFont(Font.font(15));
        
        HBox bottomHorizontalBox = new HBox(50);
        bottomHorizontalBox.setAlignment(Pos.CENTER);
        bottomHorizontalBox.getChildren().addAll(developedBy , supervisedBy);
        
        BorderPane fullPane = new BorderPane();
        
        fullPane.setTop(topHorizontalBox);
        fullPane.setCenter(mapPane);
        fullPane.setBottom(bottomHorizontalBox);
        
        drawMapCities(Menofia.citiesNames , Menofia.citiesLocations , mapPane);
        drawMapLines(Menofia , Menofia.citiesNames , mapPane);
        
        Scene FullScene = new Scene(fullPane, 1350, 700);
        
        primaryStage.setTitle("Search Algorithms");
        primaryStage.setScene(FullScene);
        primaryStage.show();
        
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
    void drawCity(String name , double x , double y , BorderPane pane)
    {
        Circle circle = new Circle(x , y , 3);
        Text cityName = new Text(x + 5 , y + 15 , name);
        cityName.setFont(Font.font(16));
        pane.getChildren().addAll(circle , cityName);
    }
    void drawMapCities(ArrayList<String> citiesNames , ArrayList<Location> citiesLocations , BorderPane pane)
    {
        for(int i = 0 ; i < citiesNames.size() ; i++)
            drawCity(citiesNames.get(i) , citiesLocations.get(i).longitude , citiesLocations.get(i).latitude , pane);
    }
    void drawLineBetweenTwoCities(Location city1Location , Location city2Location , String color , double strokeWidth , BorderPane pane , Integer stepNumber)
    {
        Line line = new Line(city1Location.longitude , city1Location.latitude , city2Location.longitude , city2Location.latitude);
        line.setStrokeWidth(strokeWidth);
        line.setStyle("-fx-stroke:" + color);
        pane.getChildren().add(line);
        if(color.equals("BLUE"))
        {
            algorithmLines.add(line);
            Text number = new Text((city1Location.longitude + city2Location.longitude) / 2 , ( city1Location.latitude + city2Location.latitude) / 2 , stepNumber.toString());
            number.setFill(Color.RED);
            number.setFont(Font.font(16));
            pane.getChildren().add(number);
            algorithmLines.add(number);
        }
            
    }
    void drawLinesBetweenCityAndItsChildren(Tree Governorate , String name , BorderPane pane)
    {
        Node city = Governorate.traverse(Governorate.root, name);
        if(city.leftChild != null)
            drawLineBetweenTwoCities(city.location , city.leftChild.location , "BLACK" , 1 , pane , 0);
        if(city.middleChild != null)
            drawLineBetweenTwoCities(city.location , city.middleChild.location , "BLACK" , 1 , pane , 0);
        if(city.rightChild != null)
            drawLineBetweenTwoCities(city.location , city.rightChild.location , "BLACK" , 1 , pane , 0);
    }
    void drawMapLines(Tree Governorate , ArrayList<String> citiesNames , BorderPane pane)
    {
        for(int i = 0 ; i < citiesNames.size() ; i++)
            drawLinesBetweenCityAndItsChildren(Governorate , citiesNames.get(i) , pane);
    }
    void drawAlgorithmSolution(Tree Governorate , ArrayList<String> visitedAlgorithm , BorderPane pane)
    {
        for(int i = 0 ; i < visitedAlgorithm.size() - 1 ; i++)
        {
            if(Governorate.traverse(Governorate.root, visitedAlgorithm.get(i)).isConnected(visitedAlgorithm.get(i + 1)))
                drawLineBetweenTwoCities(Governorate.traverse(Governorate.root, visitedAlgorithm.get(i)).location , Governorate.traverse(Governorate.root, visitedAlgorithm.get(i + 1)).location , "BLUE" , 3 , pane , i + 1);
            else
            {
                for(int j = i - 1 ; j >= 0 ; j--)
                {
                    if(Governorate.traverse(Governorate.root, visitedAlgorithm.get(j)).isConnected(visitedAlgorithm.get(i + 1)))
                    {
                        drawLineBetweenTwoCities(Governorate.traverse(Governorate.root, visitedAlgorithm.get(j)).location , Governorate.traverse(Governorate.root, visitedAlgorithm.get(i + 1)).location , "BLUE" , 3 , pane , i + 1 );
                        break;
                    }
                }
            }
        }
    }
    void drawSolution(Tree Governorate , String source , String destination , String algorithm , BorderPane pane)
    {
        if(source == null)
        {
            JOptionPane.showMessageDialog(null , "Please choose the start city");
            return;
        }
        if(destination == null)
        {
            JOptionPane.showMessageDialog(null , "Please choose the goal city");
            return;
        }
        
        if(algorithm.equals("DFS"))
        {
            Governorate.depthFirstSearchAlgorithm(Governorate.traverse(Governorate.root, source), source, destination);
            if(Governorate.visitedDFSA.contains(destination))
            {
                drawAlgorithmSolution(Governorate , Governorate.visitedDFSA , pane);
                Governorate.visitedDFSA.clear();
            }
            else
                JOptionPane.showMessageDialog(null , "Can not reach " + destination);
        }
        else if(algorithm.equals("BFS"))
        {
            Governorate.breadthFirstSearchAlgorithm(source, destination);
            if(Governorate.visitedBFSA.contains(destination))
            {
                drawAlgorithmSolution(Governorate , Governorate.visitedBFSA , pane);
                Governorate.visitedBFSA.clear();
            }
            else
                JOptionPane.showMessageDialog(null , "Can not reach " + destination);
        }
        else if(algorithm.equals("Greedy"))
        {
            Governorate.greedySearchAlgorithm(source, destination);
            if(Governorate.visitedGA.contains(destination))
            {
                drawAlgorithmSolution(Governorate , Governorate.visitedGA , pane);
                Governorate.visitedGA.clear();
            }
            else
                JOptionPane.showMessageDialog(null , "Can not reach " + destination);
        }
        else if(algorithm.equals("A*"))
        {
            Governorate.AStarSearchAlgorithm(source, destination);
            if(Governorate.visitedASA.contains(destination))
            {
                drawAlgorithmSolution(Governorate , Governorate.visitedASA , pane);
                Governorate.visitedASA.clear();
            }
            else
                JOptionPane.showMessageDialog(null , "Can not reach " + destination);
        }
        else
        {
            JOptionPane.showMessageDialog(null , "Please choose the algorithm");
        }
    }
    
}
