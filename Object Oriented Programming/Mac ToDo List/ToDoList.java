import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ListView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.Calendar;
import java.util.Date;
import javafx.util.Duration;
/**
 * @author Rhea Jaxon
 * @version 1
 */
public class ToDoList extends Application {
    /**
     * Instance variable that keeps track of completed tasks
     */
    private int completed = 0;
    /**
     * Instance variable of number of uncompleted tasks
     */
    private int count = 0;
    /**
     * Text ArrayList of all the tasks the user has submitted
     */
    private ArrayList<Text> tasks = new ArrayList<Text>();
    /**
     * String ArrayList of the the task type the user inputted
     */
    private ArrayList<String> typeChoice = new ArrayList<String>();
    /**
     * Integer ArrayList of the amount of time the user will take to
     * finish the task
     */
    private ArrayList<Integer> times = new ArrayList<Integer>();
    /**
     * Text value to keep to keep track of the tasks completed and
     * tasks yet to be completed
     */
    private Text counter;
    /**
     * BorderPane for the notebook background
     */
    private BorderPane root = new BorderPane();
    /**
     * HBox pane for the top part of the main pane
     */
    private HBox top = new HBox();
    /**
     * Calender object for user task time
     */
    private Calendar cal = Calendar.getInstance();
    /**
     * Instance variable to obtain music file
     */
    private Media buzzer = new Media(getClass().getResource("click.wav").toExternalForm());
    /**
     * MediaPlayer object to get click noises
     */
    private MediaPlayer mediaPlayer = new MediaPlayer(buzzer); //sound
    /**
     * Main method to launch the GUI.
     * @param args args are the Arguments to run the GUI
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
     * Start method that sets up all the panes,
     * scene, and stage for the GUI along with additional visual
     * and structural elements.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("To Do List");   //Page panel and stage setup
        setTop();
        paintBackground();
        VBox setList = new VBox();
        TextField txt = new TextField("");   //Textboxes and choice bars
        VBox inputter = new VBox();
        final ComboBox<String> type = new ComboBox<String>();
        type.getItems().addAll("Study", "Shop", "Cook", "Sleep");
        type.setPromptText("Task Type");
        type.setEditable(true);
        final ComboBox<String> hours = new ComboBox<String>();
        hours.getItems().addAll("1", "2", "3", "4", "5");
        hours.setPromptText("Number of hours");
        hours.setEditable(true);
        ListView<Text> listView = new ListView<Text>();  //ListView Setup to showcase the list of tasks
        listView.setMaxSize(700, 450);
        Alert a = new Alert(AlertType.WARNING);   //Notification pop up
        Button queue = new Button();   //Creating Add Task Button
        queue.setText("Queue");
        queue.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
        queue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
                boolean clear = true;
                if (txt.getText() == null) {
                    a.setContentText("No task inputted!");
                    a.show();
                    txt.setText("");
                    clear = false;
                } else if (hours.getValue() == null) {
                    a.setContentText("No time inputted!");
                    a.show();
                    hours.setValue(null);
                    clear = false;
                } else if (type.getValue() == null) {
                    a.setContentText("No type inputted!");
                    a.show();
                    type.setValue(null);
                    clear = false;
                }
                Text tempTask = new Text(txt.getText());
                String tempType = type.getValue();
                int tempTime = Integer.parseInt(hours.getValue());
                type.setValue(null);
                hours.setValue(null);
                txt.setText("");
                for (int i = 0; i < tasks.size(); i++) {
                    if (tempTask.getText().toLowerCase().equals(tasks.get(i).getText().toLowerCase())) {
                        if (tempType.toLowerCase().equals(typeChoice.get(i).toLowerCase())) {
                            if (tempTime == times.get(i)) {
                                a.setContentText("Task already inputted!");
                                a.show();
                                clear = false;
                            } else {
                                times.set(i, tempTime);
                                cal.setTime(new Date());     // sets calendar time/date
                                cal.add(Calendar.HOUR_OF_DAY, tempTime);      // adds one hour
                                Text line = new Text(tasks.get(i).getText() + "\tType: " + typeChoice.get(i)
                                    + "\tComplete by: " + cal.getTime());
                                line.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
                                listView.getItems().set(i, line);
                                clear = false;
                            }
                        } else {
                            a.setContentText("Task already inputted!");
                            a.show();
                            clear = false;
                        }
                    }
                }
                if (clear) {
                    tasks.add(tempTask);
                    times.add(tempTime);
                    typeChoice.add(tempType);
                    count++;
                    cal.setTime(new Date());   // sets calendar time/date
                    cal.add(Calendar.HOUR_OF_DAY, tempTime);
                    counter.setText("\t\t\t\tTasks completed: " + completed + "\tTasks remaining: " + count);
                    Text line = new Text(tasks.get(count - 1).getText() + "\tType: " + typeChoice.get(count - 1)
                        + "\tComplete by: " + cal.getTime());
                    line.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
                    listView.getItems().add(line);
                }
            }
        });
        setList.getChildren().add(listView);   //Adds listView onto centeral pane
        setList.setPadding(new Insets(50, 50, 80, 80));
        inputter.getChildren().add(txt);
        HBox bottom = new HBox();   //Bottom pane to add on to the main pane
        bottom.setPadding(new Insets(0, 0, 25, 10));
        bottom.setSpacing(15);
        Button dequeue = new Button();   //Creating Dequeue button
        dequeue.setText("Dequeue");
        dequeue.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
        dequeue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.seek(Duration.ZERO);
                mediaPlayer.play();
                if (listView != null) {
                    listView.getItems().remove(0);
                    tasks.remove(0);
                    typeChoice.remove(0);
                    times.remove(0);
                    count--;
                    completed++;
                    counter.setText("\t\t\t\tTasks completed: " + completed + "\tTasks remaining: " + count);
                } else {
                    a.setContentText("There are no tasks left to dequeue!");
                    a.show();
                }
            }
        });
        Button clearList = new Button(); //Creates clear button to clear all tasks the user has inputted
        clearList.setText("Clear All");
        clearList.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
        clearList.setOnAction(event -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
            if (listView != null) {
                listView.getItems().clear();
                tasks.clear();
                typeChoice.clear();
                times.clear();
                completed += count;
                count = 0;
                counter.setText("\t\t\t\tTasks completed: " + completed + "\tTasks remaining: " + count);
            } else {
                a.setContentText("There are no tasks!");
                a.show();
            }
        });
        bottom.getChildren().add(inputter);   //Adding all the buttons and choice bars to bottom panel
        bottom.getChildren().add(type);
        bottom.getChildren().add(hours);
        bottom.getChildren().add(queue);
        bottom.getChildren().add(dequeue);
        bottom.getChildren().add(clearList);
        BorderPane main = new BorderPane();   //Creating main pane and adding panes on
        main.getChildren().add(root);
        main.setCenter(setList);
        main.setTop(top);
        main.setBottom(bottom);
        primaryStage.setScene(new Scene(main, 850, 550));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    /**
     * The paintBackground() method paints in the rectangle and lines
     * to make it seem like a notepad.
     */
    public void paintBackground() {
        Rectangle r = new Rectangle();
        r.setX(0);
        r.setY(0);
        r.setWidth(850);
        r.setHeight(550);
        r.setFill(Color.FLORALWHITE);
        root.getChildren().add(r);
        //Adding the lines for the notebook
        for (double i = 50.0; i < r.getHeight(); i += 40.0) {
            Line line = new Line();
            line.setStartX(0.0f);
            line.setStartY(i);
            line.setEndX(850.0f);
            line.setEndY(i);
            root.getChildren().add(line);
        }
    }
    /**
     * The setTop method creates and sets up the visual elements for the top pane
     * of the main pane.
     */
    public void setTop() {
        //Title of the GUI
        Text title = new Text("To Do List");
        title.setY(100);
        title.setX(20);
        title.setFill(Color.BLACK);
        title.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 25));
        //Tasks completed and task count
        //Setting top layout
        counter = new Text("\t\t\tTasks completed: " + completed + "\t  Tasks remaining: " + count);
        counter.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 12));
        top.setMargin(title, new Insets(18, 0, 0, 370));
        top.setMargin(counter, new Insets(30, 0, 0, 0));
        top.getChildren().add(title);
        top.getChildren().add(counter);
    }
}