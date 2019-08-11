import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

import static javafx.scene.paint.Color.rgb;

public class Pong extends Application {
    @Override
    public void start( Stage stage ) {

        Pane canvas = new Pane( );
        Scene scene = new Scene( canvas, 300, 300, Color.BLACK );

        Sphere ball = new Sphere( 20,24 );
        ball.relocate( 100, 250 );

        Rectangle paddle = new Rectangle(100, 25, Color.RED);
        paddle.relocate( 0, 250 );

        canvas.getChildren( ).addAll( ball, paddle );

        stage.setTitle( "Animated Ball" );
        stage.setScene( scene );
        stage.show( );

        // Move paddle with mouse
        canvas.setOnMouseMoved( e -> {
            paddle.setLayoutX( e.getX() ) ;
        } );

        paddle.setOnKeyPressed( e -> {
            switch ( e.getCode( ) ) {
                case LEFT:
                    paddle.setLayoutX( paddle.getLayoutX( ) - paddle.getWidth()/4 ); ;
                    break;
                case RIGHT:
                    paddle.setLayoutX( paddle.getLayoutX( ) + paddle.getWidth()/4 ) ;
                    break;
            }
            switch ( e.getText( ).charAt( 0 ) ) {
                case 'A':
                case 'a':
                    paddle.setLayoutX( paddle.getLayoutX( ) - paddle.getWidth()/4 ) ;
                    break;
                case 'D':
                case 'd':
                    paddle.setLayoutX( paddle.getLayoutX( ) + paddle.getWidth()/4 ) ;
                    break;
            }
        });

        paddle.requestFocus( );


        Timeline timeline = new Timeline( new KeyFrame( Duration.millis( 20 ),
                new EventHandler< ActionEvent >( ) {
                    double dx = 10; //Step on x or velocity
                    double dy = 10; //Step on y

                    @Override
                    public void handle( ActionEvent t ) {
                        System.out.printf("<Ball: %f,%f> <Paddle: %f,%f>\n", ball.getLayoutX(), ball.getLayoutY(), paddle.getLayoutX(), paddle.getLayoutY());
                        //move the ball
                        ball.setLayoutX( ball.getLayoutX( ) + dx );
                        ball.setLayoutY( ball.getLayoutY( ) + dy );

                        // Check if ball hits paddle
                        if (ball.getLayoutX() > paddle.getLayoutX() &&
                                ball.getLayoutX() < paddle.getLayoutX() + paddle.getWidth() &&
                                Math.abs(ball.getLayoutY() - (paddle.getLayoutY() + paddle.getHeight()/2.0)) < ball.getRadius() ){
                            dy = -dy/dy * 4;
                        }

                        Bounds bounds = canvas.getBoundsInLocal( );
                        //If the ball reaches the left or right border make the step negative
                        if ( ball.getLayoutX( ) <= ( bounds.getMinX( ) + ball.getRadius( ) ) ||
                                ball.getLayoutX( ) >= ( bounds.getMaxX( ) - ball.getRadius( ) ) ) {
                            dx = -dx;
                        }
                        //If the ball reaches the bottom or top border make the step negative
                        if ( ( ball.getLayoutY( ) >= ( bounds.getMaxY( ) - ball.getRadius( ) ) ) ||
                                ( ball.getLayoutY( ) <= ( bounds.getMinY( ) + ball.getRadius( ) ) ) ) {
                            dy = -dy/dy * 4;
                        }
                        dy=dy+5;
                    }
                } ) );
        timeline.setCycleCount( Timeline.INDEFINITE );
        timeline.play( );
    }

    public static void main( String[] args ) {
        launch( );
    }
}