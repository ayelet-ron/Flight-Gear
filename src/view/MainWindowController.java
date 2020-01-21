package view;


import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import view.MouseEvents.Myproperty;
import viewModel.ViewModel;

@SuppressWarnings("deprecation")
public class MainWindowController extends Observable implements Initializable{
	ViewModel vm; 
	MouseEvents mouseEvent;
	Boolean isConnected;
    @FXML
    private Slider slideThrottle;
    @FXML
    private Slider slideRudder;
    @FXML
    private AnchorPane joystickPart;
    @FXML
    private Button connect;
    @FXML
    private JoyStick joystick;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		isConnected=false;
		joystick.display();
		this.mouseEvent = new MouseEvents();
		mouseEvent.makeDraggable(joystick.getSmallCircle(),joystick.getBigCircle());
		joystick.getSmallCircle().setOnMousePressed(mouseEvent.onMousePressedEventHandler);
		joystick.getSmallCircle().setOnMouseDragged(mouseEvent.onMouseDraggedEventHandler);
		joystick.getSmallCircle().setOnMouseReleased(mouseEvent.onMouseReleasedEventHandler);
	}
    public void setViewModel(ViewModel vm) {
		this.vm=vm;
		vm.aileron.bind(Myproperty.aileron);
		vm.elevator.bind(Myproperty.elevator);
		vm.rudder.bind(slideRudder.valueProperty());
		vm.throttle.bind(slideThrottle.valueProperty());
		this.addObserver(vm);
		
		//...
	}
    public void connect() {
    	if(!isConnected) {
        	isConnected = true;
        	System.out.println("pressed on connect");
        	this.setChanged();
        	this.notifyObservers("connect");
    	}
    	else {
    		System.out.println("you already connected");
    	}
    }

}
