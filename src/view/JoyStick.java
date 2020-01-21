package view;

import javafx.beans.NamedArg;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class JoyStick extends AnchorPane {
	private Circle bigCircle;
    private Circle smallCircle;
    
    public JoyStick(@NamedArg("bigCircleColor") String bigCircleColor, @NamedArg("smallCircleColor") String smallCircleColor, @NamedArg("circleDim") String circleDim){
        bigCircle = new Circle(Double.parseDouble(circleDim), Double.parseDouble(circleDim), Double.parseDouble(circleDim), Paint.valueOf(bigCircleColor));
        smallCircle = new Circle(Double.parseDouble(circleDim), Double.parseDouble(circleDim), Double.parseDouble(circleDim) /2, Paint.valueOf(smallCircleColor));
        
    }
    public Circle getSmallCircle() {
    	return this.smallCircle;
    }
    public Circle getBigCircle() {
    	return this.bigCircle;
    }
    
    public void display(){
		super.getChildren().add(bigCircle);
		super.getChildren().add(smallCircle);
    }
}
