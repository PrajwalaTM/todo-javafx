import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.TodoTableData;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/MainLayout.fxml"));
        root.getChildrenUnmodifiable().forEach(System.out::println);
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(0);
        stage.setY(0);
        stage.setWidth(bounds.getWidth()/2);
        stage.setHeight(bounds.getHeight()/2);
        Scene scene = new Scene(root);
        stage.setTitle("Todo");
        stage.setScene(scene);
        TableView<TodoTableData> todoTable = (TableView<TodoTableData>) root.getChildrenUnmodifiable().get(1);
        todoTable.prefHeightProperty().bind(stage.heightProperty().divide(100/50));
        todoTable.prefWidthProperty().bind(stage.widthProperty());

        /*double yPosition = stage.getHeight()*0.75;
        buttonsPane.setLayoutY(yPosition);

        buttonsPane.scaleYProperty().bind(stage.heightProperty().divide(20));*/

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
