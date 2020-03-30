package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ButtonHandler implements EventHandler<ActionEvent> {
    private RootLayout rootLayout;
    private MainLayout mainLayout;

    public ButtonHandler() {

    }



    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == "testButton") {
            rootLayout.setCenter(mainLayout);
        }

    }
}
