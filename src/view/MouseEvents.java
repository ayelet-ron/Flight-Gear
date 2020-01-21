package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class MouseEvents {
	class DragContext {
        double x;
        double y;
    }
	static class Mycircle{
		static double smallCenterX;
		static double smallCenterY;
		static double smallRadios;
		static double bigCenterX;
		static double bigCenterY;
		static double bigRadios;
	}
	static class Myproperty{
		public static DoubleProperty aileron = new SimpleDoubleProperty();
		public static DoubleProperty elevator= new SimpleDoubleProperty();
	}
	
	
    DragContext dragContext = new DragContext();
    
    public void makeDraggable(Circle small, Circle big) {
    	Mycircle.smallCenterX = small.getCenterX();
    	Mycircle.smallCenterY = small.getCenterY();
    	Mycircle.smallRadios = small.getRadius();
    	Mycircle.bigCenterX = big.getCenterX();
    	Mycircle.bigCenterY = big.getCenterY();
    	Mycircle.bigRadios = big.getRadius();
    }

    EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if( event.getSource() instanceof Circle) {
                Circle circle = ((Circle) (event.getSource()));
                dragContext.x = circle.getCenterX() - event.getSceneX();
                dragContext.y = circle.getCenterY() - event.getSceneY();
            } 
        }
    };

    EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            if( event.getSource() instanceof Circle) {
                Circle circle = ((Circle) (event.getSource()));
                if(Math.sqrt(Math.pow((Mycircle.smallCenterX-(dragContext.x + event.getSceneX())), 2)+Math.pow((Mycircle.smallCenterY-(dragContext.y + event.getSceneY())), 2))<=Mycircle.smallRadios) {
                	circle.setCenterX(dragContext.x + event.getSceneX());
                	circle.setCenterY(dragContext.y + event.getSceneY());
                	Myproperty.aileron.set((dragContext.x + event.getSceneX()-Mycircle.bigCenterX)/Mycircle.bigRadios); 
                	Myproperty.elevator.set((Mycircle.bigCenterY - dragContext.y - event.getSceneY())/Mycircle.bigRadios); 
                }
            }
        }
    };

    EventHandler<MouseEvent> onMouseReleasedEventHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
        	if( event.getSource() instanceof Circle) {
            	Circle circle = ((Circle) (event.getSource()));
                circle.setCenterX(Mycircle.smallCenterX);
                circle.setCenterY(Mycircle.smallCenterY);
                Myproperty.aileron.set(0.0); 
            	Myproperty.elevator.set(0.0); 
        	}
        }
    };

}
